package com.fupadeb.celtas.model;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class managerDAO {
	private static EntityManagerFactory factory;
	private static EntityManager em;
	
	/**
	 * Constructor de la clase managerDAO. Se encarga de crear los objetos
	 * factory y entity manager utilizando el patron de disenno Singleton
	 */
	
	public managerDAO(){
		//mostrarLog(this.getClass(), "constructor", "ManagerDAO Creado");
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("celtas");
			//mostrarLog(this.getClass(), "constructor", "Factory creado");
		}
		if (em==null) {
			em=factory.createEntityManager();
		}
	}
	
	//Finder 
	public List<Person> findAllPerson(){
		List<Person> listado;
		Query q;
		
		em.getTransaction().begin();
		q=em.createQuery("SELECT p FROM Person p ORDER BY p.id_person");
		listado = q.getResultList();
		em.getTransaction().commit();
		return listado;
	}
	

}
