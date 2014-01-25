package net.ald.projet.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.LoggerFactory;

import net.ald.projet.entities.User;
import net.ald.projet.property.Connexion;


public class ConnexionDAOImpl extends GenericDAO {


        private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ConnexionDAOImpl.class);


        public String createConnexion(String login, String password){
    		LOG.info("> createConnexion");
    		EntityManager em = createEntityManager(); 
    		EntityTransaction tx = null;
    		User u =new User();
    		try{
    			tx = em.getTransaction();
    			tx.begin();
    			Query query = em.createQuery("from User u" +
    					" where u.login=:param1 and" +
    					" u.password=:param2");
    			query.setParameter("param1", login);
    			query.setParameter("param2", password);
    			u = (User)query.getSingleResult();
    			tx.commit();
    			LOG.debug("la recherche a rÃ©ussi");
    		} catch (RuntimeException re) {
    			LOG.error("Erreur dans la fonction createConnexion", re);
    			throw re;
    		}
    		if(u.getStatut() != null)
    			return u.getStatut();
    		return "wrong";
    	}
    	
    	public void createUser(User u){
    		LOG.info("> createUser");
    		EntityManager em = createEntityManager(); 
    		EntityTransaction tx = null;
    		try{
    			tx = em.getTransaction();
    			tx.begin();
    			em.persist(u); 
    			tx.commit();
    			LOG.debug("creation User");
    		} catch (RuntimeException re) {
    			LOG.error("Erreur dans la fonction createUser", re);
    			throw re;
    		}
    	}
    }


