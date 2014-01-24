package net.ald.projet.DAO;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.LoggerFactory;

import net.ald.projet.entities.Collection;
import net.ald.projet.entities.Oeuvre;


public class CollectionDAO extends GenericDAO {


        private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CollectionDAO.class);
        
        public void createCollection(Collection collection) {
                EntityManager em = createEntityManager();
                EntityTransaction tx = null;
                try {
                        tx = em.getTransaction();
                        tx.begin();
                        em.persist(collection);
                        tx.commit();
                } catch (Exception re) {
                        if (tx != null)
                                LOG.error("create collection failed", re);
                        tx.rollback();
                }
        }


        /*public void updateCollection(Collection collection){
                EntityManager em = createEntityManager();
                EntityTransaction tx = null;
                try {
                        tx = em.getTransaction();
                        tx.begin();
                        em.merge(collection);
                        tx.commit();
                } catch (Exception re) {
                        if (tx != null)
                                LOG.error("update collection failed", re);
                        tx.rollback();
                }
        }*/

        @SuppressWarnings("unchecked")
		public List<Collection> findAll() {
                List<Collection> collections = new ArrayList<Collection>();
                List<Collection> res = new ArrayList<Collection>();
                EntityManager em = createEntityManager();

                res = em.createQuery("SELECT p FROM Collection p").getResultList();
                for(Collection c : res){
                        collections.add(new Collection(c.getId(), c.getEtat()));
                }
                
                return collections;
        }
      
        public List<Oeuvre> getOeuvresOfCollection(int collectionId) {
                List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
                EntityManager em = createEntityManager();
                Collection c = em.getReference(Collection.class,collectionId);
                oeuvres = c.getOeuvres();
                
                return oeuvres;
        }
        
        public Collection findById(int id){
                EntityManager em = createEntityManager();
                Collection artiste = em.find(Collection.class,id);
                return artiste;
        }

        public void deleteCollection(Collection persistentInstance){
                EntityManager em = createEntityManager();
                EntityTransaction tx = null;
                try {
                        tx = em.getTransaction();
                        tx.begin();
                        em.remove(persistentInstance);
                        tx.commit();

                } catch (Exception re) {
                        if (tx != null)
                                LOG.error("delete collection failed", re);
                        tx.rollback();
                }
        }
}

