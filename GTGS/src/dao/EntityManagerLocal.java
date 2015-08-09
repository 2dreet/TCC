package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerLocal{

	public static EntityManagerFactory emf;
	public static EntityManager em;
	
	public static void closeEntityManager(){
		em.close();
		emf.close();
	}
	
	public static void openEntityManager(){
		emf = Persistence.createEntityManagerFactory("GTGS");
		em = emf.createEntityManager();
	}
	
	public static EntityManager getEntityManager(){
		return em;
	}
	
	
}
