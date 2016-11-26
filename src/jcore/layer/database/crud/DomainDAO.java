package layer.database.crud;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import layer.database.entity.Domain;
import layer.database.entity.GeneralConfiguration;


public class DomainDAO {

	public  void newDomain(Domain domain)
	{
			
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(domain);
			System.out.println("Inserted Successfully");
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
		
	}
	

	public  void saveOrUpdateDomain(Domain domain)
	{
			
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(domain);
			System.out.println("Inserted Successfully");
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
		
	}
	public  List<Domain>  getAllDomain()
	{
		  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  Session session = sessionFactory.openSession();
		  session.beginTransaction();
		  Query query = session.createQuery("from Domain");
		  List<Domain> domains = query.list();
		  session.getTransaction().commit();
		  sessionFactory.close();
	 
		  return domains;
	}
	
	public  Domain  getDomainByID(Integer idDomain)
	{
		  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  Session session = sessionFactory.openSession();
		  session.beginTransaction();
		  Domain domainDB = (Domain)session.get(Domain.class,idDomain);
		
		  session.getTransaction().commit();
		  sessionFactory.close();
	 
		  return domainDB;
	}
	
	
	public  void updateDomainDescription(int idDomain, String newDescription)
	{
		  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  Session session = sessionFactory.openSession();
		  session.beginTransaction();
		  
		  Domain domainDB = (Domain)session.get(Domain.class,idDomain);
		  domainDB.setDescription(newDescription);
		  System.out.println("Updated Successfully");
		  session.getTransaction().commit();
		  sessionFactory.close();
	
	}
	public void deleteDomain(int idDomain){
		 SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  Session session = sessionFactory.openSession();
		  session.beginTransaction();
		  Domain domain = (Domain)session.load(Domain.class, idDomain);
		  session.delete(domain);
		  System.out.println("Deleted Successfully");
		  session.getTransaction().commit();
		     sessionFactory.close();
		
	}


}
