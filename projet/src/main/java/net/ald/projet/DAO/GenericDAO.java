package net.ald.projet.DAO;


import javax.persistence.EntityManager;

import org.slf4j.LoggerFactory;

import net.ald.projet.filters.JpaUtil;


public abstract class GenericDAO {
    private EntityManager entityManager;
    @SuppressWarnings("unused")
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(GenericDAO.class);       

    public EntityManager createEntityManager() {
	entityManager = JpaUtil.getEntityManager();
	return entityManager;
    }

    public void closeEntityManager() {
	entityManager.close();
    }
}

