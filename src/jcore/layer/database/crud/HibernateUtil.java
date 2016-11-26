package layer.database.crud;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	 private static SessionFactory sessionFactory;

     public static SessionFactory getSessionFactory() {
    	 
    	 try {
             // Create the SessionFactory from hibernate.cfg.xml
             sessionFactory = new Configuration().configure().buildSessionFactory();
         } catch (Throwable ex) {
             // Make sure you log the exception, as it might be swallowed
             System.err.println("Initial SessionFactory creation failed." + ex);
             throw new ExceptionInInitializerError(ex);
         }
    	 
         return sessionFactory;
     }

     
     
}
