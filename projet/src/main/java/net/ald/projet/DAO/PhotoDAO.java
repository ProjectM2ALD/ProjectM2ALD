package net.ald.projet.DAO;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.LoggerFactory;

import net.ald.projet.entities.Photo;


public class PhotoDAO extends GenericDAO {


        private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PhotoDAO.class);

        public void createPhoto(Photo photo) {
                EntityManager em = createEntityManager();
                EntityTransaction tx = null;
                try {
                        tx = em.getTransaction();
                        tx.begin();
                        em.persist(photo);
                        tx.commit();
                } catch (Exception re) {
                        if (tx != null){
                                LOG.error("create Photo failed", re);
                        }
                        tx.rollback();
                }
        }

       /* public void updatePhoto(Photo photo){
                EntityManager em = createEntityManager();
                EntityTransaction tx = null;
                try {
                        tx = em.getTransaction();
                        tx.begin();
                        em.merge(photo);
                        tx.commit();
                } catch (Exception re) {
                        if (tx != null)
                                LOG.error("update Photo failed", re);
                        tx.rollback();
                }
        }*/

        public void removePhoto(Photo photo){
                EntityManager em = createEntityManager();
                EntityTransaction tx = null;
                try {
                        tx = em.getTransaction();
                        tx.begin();
                        em.remove(em.merge(photo));
                        tx.commit();

                } catch (Exception re) {
                        if (tx != null)
                                LOG.error("remove Photo failed", re);
                        tx.rollback();
                }
        }

        @SuppressWarnings("unchecked")
		public List<Photo> findAll() {
                List<Photo> Photos = new ArrayList<Photo>();
                EntityManager em = createEntityManager();

                Photos = em.createQuery("SELECT p FROM Photo p").getResultList();
                return Photos;
        }


        public Photo findById(int id){
                EntityManager em = createEntityManager();
                Photo photo = em.find(Photo.class,id);
                return photo;
        }


        public void deletePhoto(Photo persistentInstance){
                EntityManager em = createEntityManager();
                EntityTransaction tx = null;
                try {
                        tx = em.getTransaction();
                        tx.begin();
                        em.remove(persistentInstance);
                        tx.commit();


                } catch (Exception re) {
                        if (tx != null)
                                LOG.error("delete Photo failed", re);
                        tx.rollback();
                }
        }
}

