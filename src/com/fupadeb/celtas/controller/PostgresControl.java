package com.fupadeb.celtas.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fupadeb.celtas.logger.LoggerUtility;
import com.fupadeb.celtas.model.Role;
import com.fupadeb.celtas.model.Secure;
import com.fupadeb.celtas.model.User;

public class PostgresControl {
	private EntityManagerFactory emFactory;
	private final String persistenceUnit = "celtas";
	private LoggerUtility logger;
	private Secure securityProvider;

	public PostgresControl() {
		this.logger = new LoggerUtility(0);
		logger.log(4, this.getClass().getName(), "constructor", "Initializating");
		this.emFactory = Persistence.createEntityManagerFactory(persistenceUnit);
		this.securityProvider = new Secure();
	}

	public EntityManagerFactory getEmFactory() {
		if (this.emFactory == null) {
			logger.log(4, this.getClass().getName(), "getEmFactory", "emFactory is null!!!");
			this.emFactory = Persistence.createEntityManagerFactory(persistenceUnit);
		}
		return emFactory;
	}

	public Secure getSecurityProvider() {
		if (this.securityProvider == null) {
			logger.log(4, this.getClass().getName(), "getSecurityProvider", "securityProvider is null!!!");
			this.securityProvider = new Secure();
		}
		return securityProvider;
	}

	public boolean createUser(String name, String surName, String email, String password, List<Role> userRoles) {
		EntityManager tempEntityManager = getEmFactory().createEntityManager();
		tempEntityManager.getEntityManagerFactory().getCache().evictAll();
		//tempEntityManager.getTransaction().begin();

		User tempUser = new User();
		String encodedPassword = getSecurityProvider().encode(password);
		tempUser.setName(name);
		tempUser.setSurName(surName);
		tempUser.setEmail(email);
		tempUser.setActive(true);
		tempUser.setPassword(encodedPassword);
		tempUser.setRoles(userRoles);

		//tempEntityManager.persist(tempUser);
		//tempEntityManager.getTransaction().commit();
		tempEntityManager.close();
		return true;
	}

	public Role getRole(String description) {		
		EntityManager tempEntityManager = getEmFactory().createEntityManager();
		Role role = tempEntityManager.createNamedQuery("Role.findByName", Role.class).setParameter("inputRole", description).getSingleResult();
		return role;
	}

	/*public List<User> getUserByName(String nameOrSurname) {
		EntityManager tempEntityManager = getEmFactory().createEntityManager();
		List<User> users = tempEntityManager.createNamedQuery("User.genericNameSearch", User.class).setParameter("name", nameOrSurname).getResultList();
		return users;
	}*/
	
	public List<User> getAllUsers() {
		EntityManager tempEntityManager = getEmFactory().createEntityManager();
		List<User> users = tempEntityManager.createNamedQuery("User.findAll", User.class).getResultList();
		return users;
	}
	
	public List<User> getAllUsers(String email) {
		EntityManager tempEntityManager = getEmFactory().createEntityManager();
		List<User> users = tempEntityManager.createNamedQuery("User.findEmail", User.class).setParameter("email", email).getResultList();
		return users;
	}
}
