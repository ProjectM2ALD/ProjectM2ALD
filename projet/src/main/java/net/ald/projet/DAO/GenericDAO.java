package net.ald.projet.DAO;


import javax.persistence.EntityManager;


import net.ald.projet.filters.JpaUtil;


public abstract class GenericDAO {
	private EntityManager entityManager;
    
    public EntityManager createEntityManager() {
    	entityManager = JpaUtil.getEntityManager();
    	return entityManager;
        }

        public void closeEntityManager() {
    	entityManager.close();
        }

    }
  
