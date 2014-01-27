package net.ald.projet.DAO;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.Response;

import org.slf4j.LoggerFactory;

import net.ald.projet.entities.Artiste;
import net.ald.projet.entities.Collection;
import net.ald.projet.entities.Oeuvre;
//import net.ald.projet.simplified.OeuvreSimplifiee;
import net.ald.projet.entities.Peinture;
import net.ald.projet.entities.Photo;
import net.ald.projet.entities.Photographie;
import net.ald.projet.entities.Sculpture;
import net.ald.projet.property.Dimension;


public class OeuvreDAOImpl extends GenericDAO {

        private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(OeuvreDAOImpl.class);
        
        public void createOeuvre(Oeuvre oeuvre) {
        		
        	EntityManager em = createEntityManager();
            EntityTransaction tx=em.getTransaction();
            try{
            	tx.begin();
            	em.persist(oeuvre);
            	LOG.debug("Add a new oeuvre ");
            } catch (RuntimeException re) {
            	LOG.error("add oeuvre failed", re);
            }finally{
            	tx.commit();
            }
        }
        
        public void createOeuvre(List<Oeuvre> oeuv) {
            EntityManager em = createEntityManager();
            EntityTransaction tx = null;
            try {
                    tx = em.getTransaction();
                    tx.begin();
                    for (Oeuvre u : oeuv) {
                            em.persist(u);
                    }
                    tx.commit();

            } catch (Exception e) {
                    if (tx != null)
                            System.err.println("Something went wrong");
                    tx.rollback();
            }
    }
        
        void createOeuvre(int id, Artiste artiste, String titre, Integer annee, String type) {
        	
        	System.out.println("Create an Oeuvre");
        	EntityManager em = createEntityManager();
            EntityTransaction tx = null;
            try {
                    tx = em.getTransaction();
                    tx.begin();
                    Oeuvre oeuvre = new Oeuvre(id, artiste, titre, annee, type);
                    em.persist(oeuvre);
                    tx.commit();
            } catch (Exception e) {
                    if (tx != null)
                            System.err.println("Something went wrong");
                    tx.rollback();
            }
        }

        public void updateOeuvre(Oeuvre oeuvre){
                EntityManager em = createEntityManager();
                EntityTransaction tx = null;
                try {
                        tx = em.getTransaction();
                        tx.begin();
                        em.merge(oeuvre);
                        tx.commit();

                } catch (Exception re) {
                        if (tx != null)
                                LOG.error("update oeuvre failed", re);
                        tx.rollback();
                }
        }

        public void removeOeuvre(Oeuvre oeuvre){
                EntityManager em = createEntityManager();
                EntityTransaction tx = null;
                CollectionDAO co = new CollectionDAO();
                try {
                        tx = em.getTransaction();
                        tx.begin();

                        for (Collection c : getAllCollectionsOfOeuvre(oeuvre)) {
                                Collection collection = co.findById(c.getId());
                                collection.removeOeuvre(oeuvre);
                        }
                        em.remove(em.merge(oeuvre));
                        tx.commit();
                } catch (Exception re) {
                        if (tx != null)
                                LOG.error("remove oeuvre failed", re);
                        tx.rollback();
                }
        }

        public void artistofOeuvre(Oeuvre oeuv) {
            EntityManager em = createEntityManager();
            EntityTransaction tx = null;
            try {
                    tx = em.getTransaction();
                    tx.begin();
                    Oeuvre oeuvre =  em.find(Oeuvre.class,oeuv.getId());
                    LOG.info("info : "+ oeuvre.getArtiste().getLast_name());
                    LOG.info("info : "+ oeuvre.getArtiste().getFirst_name());
                    tx.commit();

            } catch (Exception e) {
                    if (tx != null)
                            LOG.error("test artiste failed", e);
                    tx.rollback();
            }
    }
        
        
		public List<Oeuvre> findAll() {
                List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
                List<Oeuvre> res = new ArrayList<Oeuvre>();
                EntityManager em = createEntityManager();

                res = em.createQuery("SELECT p FROM Oeuvre p").getResultList();

                for(Oeuvre o : res){
                        if(o.getClass().getName().contains("Sculpture")){
                                oeuvres.add(new Sculpture(o.getId(), o.getTitre()));
                        }else if(o.getClass().getName().contains("Peinture")){
                                oeuvres.add(new Peinture(o.getId(), o.getTitre()));
                        }else if(o.getClass().getName().contains("Photographie")){
                                oeuvres.add(new Photographie(o.getId(), o.getTitre()));
                        }
                }
                return res;
        }

        public Oeuvre findById(int id){
                EntityManager em = createEntityManager();
                Oeuvre oeuvre = em.find(Oeuvre.class,id);
                return oeuvre;
        }
        
        @SuppressWarnings("unused")
		public List<Oeuvre> findByCriteria(Oeuvre o){

                List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
                EntityManager em = createEntityManager();
                EntityTransaction tx = null;
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<Oeuvre> cq = cb.createQuery(Oeuvre.class);
                Root<Oeuvre> root = cq.from(Oeuvre.class); // FROM
                cq.select(root); //SELECT
                List<Predicate> predicateList = new ArrayList<Predicate>();

                if(o.getAnnee() != null){
                        Predicate annee = cb.equal(root.get("annee"), o.getAnnee());
                        predicateList.add(annee);
                }

                /*if(o.hasBeenReproduced() == true){
                        Predicate reproduced = cb.equal(root.get("hasBeenReproduced"), o.hasBeenReproduced());
                        predicateList.add(reproduced);
                }*/

                if(o.getArtiste() != null){
                        Predicate artiste = cb.equal(root.get("artiste"), o.getArtiste());
                        predicateList.add(artiste);
                }


                if(o.getTag() != null){
                        Predicate tag = cb.equal(root.get("tag"), o.getTag());
                        predicateList.add(tag);
                }


                Predicate[] predicates = new Predicate[predicateList.size()];
                predicateList.toArray(predicates);
                cq.where(predicates);


                for(Oeuvre oeuvre: em.createQuery(cq).getResultList()){
                        oeuvres.add(oeuvre);
                }
                
                return oeuvres;
        }

        @SuppressWarnings("unchecked")
		public List<Collection> getAllCollectionsOfOeuvre(Oeuvre o){
                List<Collection> res = new ArrayList<Collection>();


                EntityManager em = createEntityManager();
                Query q = em.createQuery("select c from Collection c where :o MEMBER OF c.oeuvres");
                q.setParameter("o", o);
                res = q.getResultList();
                
                LOG.info("nb element = "+res.size());
                return res;
        }

        @SuppressWarnings("unchecked")
		public List<Oeuvre> findOeuvresNotReproduced() {
                List<Oeuvre> res = new ArrayList<Oeuvre>();
                List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
        
                
                EntityManager em = createEntityManager();
                Query q = em.createQuery("select DISTINCT o from Oeuvre o where o NOT IN (SELECT r.oeuvre FROM Reproduction r)");
                res = q.getResultList();
                
                
                for(Oeuvre o : res){
                        if(o.getClass().getName().contains("Sculpture")){
                                oeuvres.add(new Sculpture(o.getId(), o.getTitre()));
                        }else if(o.getClass().getName().contains("Peinture")){
                                oeuvres.add(new Peinture(o.getId(), o.getTitre()));
                        }else if(o.getClass().getName().contains("Photographie")){
                                oeuvres.add(new Photographie(o.getId(), o.getTitre()));
                        }
                }
                return oeuvres;
        }
        
        
        @SuppressWarnings("unchecked")
		public List<Oeuvre> findOeuvresReproduced() {
                List<Oeuvre> res = new ArrayList<Oeuvre>();
                List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
        
                
                EntityManager em = createEntityManager();
                Query q = em.createQuery("select DISTINCT o from Oeuvre o where o IN (SELECT r.oeuvre FROM Reproduction r)");
                res = q.getResultList();
                
                
                for(Oeuvre o : res){
                        if(o.getClass().getName().contains("Sculpture")){
                                oeuvres.add(new Sculpture(o.getId(), o.getTitre()));
                        }else if(o.getClass().getName().contains("Peinture")){
                                oeuvres.add(new Peinture(o.getId(), o.getTitre()));
                        }else if(o.getClass().getName().contains("Photographie")){
                                oeuvres.add(new Photographie(o.getId(), o.getTitre()));
                        }
                }
                return oeuvres;                
        }
        
}

