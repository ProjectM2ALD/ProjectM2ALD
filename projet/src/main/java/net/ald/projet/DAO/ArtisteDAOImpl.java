package net.ald.projet.DAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.LoggerFactory;

import net.ald.projet.entities.Artiste;
import net.ald.projet.entities.Oeuvre;
import net.ald.projet.entities.Sculpture;
import net.ald.projet.entities.Peinture;
import net.ald.projet.entities.Photographie;



public class ArtisteDAOImpl extends GenericDAO {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ArtisteDAOImpl.class);

    public void createArtiste(List<Artiste> users) {
        EntityManager em = createEntityManager();
        EntityTransaction tx = null;
        try {
                tx = em.getTransaction();
                tx.begin();
                for (Artiste u : users) {
                        em.persist(u);
                }
                tx.commit();

        } catch (Exception re) {
                if (tx != null)
                        System.err.println("Something went wrong");
                tx.rollback();
        }
}
    public void createAlbum(int id, String first_name, String last_name) {
        System.out.println("Create a Artiste");
        EntityManager em = createEntityManager();
        EntityTransaction tx = null;
        try {
                tx = em.getTransaction();
                tx.begin();
                Artiste artiste = new Artiste(id, first_name, last_name);
                em.persist(artiste);
                tx.commit();
        } catch (Exception re) {
                if (tx != null)
                        System.err.println("Something went wrong");
                tx.rollback();
        }
}
 
    public void createArtiste(Artiste artiste) {
        System.out.println("Create a Artiste");
        EntityManager em = createEntityManager();
        EntityTransaction tx = null;
        try {
                tx = em.getTransaction();
                tx.begin();
                em.persist(artiste);
                tx.commit();
        } catch (Exception re) {
                if (tx != null)
                        System.err.println("Something went wrong");
                tx.rollback();
        }
}

   /* public void updateArtiste(Artiste artiste){
	EntityManager em = createEntityManager();
	EntityTransaction tx = null;
	try {
	    tx = em.getTransaction();
	    tx.begin();
	    em.merge(artiste);
	    tx.commit();

	} catch (Exception re) {
	    if (tx != null)
		LOG.error("update artiste failed", re);
	    tx.rollback();
		}
    }*/


   @SuppressWarnings("unchecked")
	public List<Artiste> findAll() {
	List<Artiste> res = new ArrayList<Artiste>();
	EntityManager em = createEntityManager();
	res = em.createQuery("SELECT p FROM Artiste p").getResultList();
	return res;
    }


    public Artiste findById(int id){
	EntityManager em = createEntityManager();
	Artiste artiste = em.find(Artiste.class,id);
	return artiste;
    }

    public void deleteArtiste(Artiste persistentInstance){
	EntityManager em = createEntityManager();
	EntityTransaction tx = null;
	try {
	    tx = em.getTransaction();
	    tx.begin();
	    em.remove(persistentInstance);
	    em.merge(persistentInstance);
	    tx.commit();

	} catch (Exception e) {
	    if (tx != null)
		LOG.error("delete artiste failed", e);
	    tx.rollback();
		}
	}
    
    public void oeuvreArtiste(Artiste artiste) {
    	EntityManager em = createEntityManager();
    	EntityTransaction tx = null;
    	try {
    	    tx = em.getTransaction();
    	    tx.begin();
    	    Artiste a =  em.find(Artiste.class,artiste.getId());

    	    for (Oeuvre o : a.getOeuvres()) {
    		LOG.info("info : "+ o.getTitre());
    	    }
    	    tx.commit();

    	} catch (Exception e) {
    	    if (tx != null)
    		LOG.error("Error", e);
    	    tx.rollback();
    		}
        }

    public List<Oeuvre> findOeuvresOfIdArtiste(int ArtisteId) {
	Set<Oeuvre> oeuvres = new HashSet<Oeuvre>();
	Set<Oeuvre> res = new HashSet<Oeuvre>();
	EntityManager em = createEntityManager();
	Artiste a = em.getReference(Artiste.class, ArtisteId);
	res = a.getOeuvres();
	    
	    
	for(Oeuvre o : res){
				
	    if(o.getClass().getName().contains("Sculpture")){
		oeuvres.add(new Sculpture(o.getId(), o.getTitre()));
	    }else if(o.getClass().getName().contains("Peinture")){
		oeuvres.add(new Peinture(o.getId(), o.getTitre()));
	    }else if(o.getClass().getName().contains("Photographie")){
		oeuvres.add(new Photographie(o.getId(), o.getTitre()));
	    }
	}
	return em.createQuery("SELECT * FROM Oeuvre").getResultList();
    }
}

