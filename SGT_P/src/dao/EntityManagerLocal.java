package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EntityManagerLocal{

	public static EntityManagerFactory emf;
	public static EntityManager em;
	
	public static void closeEntityManager(){
		em.close();
		emf.close();
	}
	
	private static void openEntityManager(){
		emf = Persistence.createEntityManagerFactory("GTGS");
		em = emf.createEntityManager();
	}
	
	public static EntityManager getEntityManager(){
		if(emf==null){
			openEntityManager();
		}
		return em;
	}
	
	 public static boolean persist(Object obj) {
	        getEntityManager().persist(obj);
	        return true;
	    }

	    public static boolean refresh(Object obj) {
	        getEntityManager().refresh(obj);
	        return true;
	    }

	    public static boolean flush() {
	        getEntityManager().flush();
	        return true;
	    }

	    public static Object merge(Object obj) {
	        return getEntityManager().merge(obj);
	    }

	    public static boolean clear() {
	        getEntityManager().clear();
	        return true;
	    }
	    
	    public static boolean delete(Object obj) {
	        getEntityManager().remove(obj);
	        return true;
	    }

	    public static boolean commit() {
	        // getEntityManager().flush();
	        getEntityManager().getTransaction().commit();
	        return true;
	    }
	    
	    public static boolean begin() {
	        getEntityManager().getTransaction().begin();
	        return true;
	    }

	    public static Query createNativeQuery(String sql, Class classe) {
	        return getEntityManager().createNativeQuery(sql, classe);
	    }

	    public static Query createNativeQuery(String sql) {
	        return getEntityManager().createNativeQuery(sql);
	    }

	    public static Query createQuery(String sql) {
	        return getEntityManager().createQuery(sql);
	    }
	
}
