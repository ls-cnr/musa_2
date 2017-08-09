package layer.database.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import layer.database.entity.GeneralConfiguration;

public class GeneralConfigurationDAO {

	public void saveOrUpdateGeneralConfiguration(GeneralConfiguration generalConfiguration) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(generalConfiguration);
		System.out.println("Inserted Successfully");
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

	}

	public void saveGeneralConfiguration(GeneralConfiguration generalConfiguration) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(generalConfiguration);
		System.out.println("Inserted Successfully");
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();

	}

	public void updateGeneralConfiguration(GeneralConfiguration generalConfiguration) {
		System.out.println("ID BEAN TO UPDATE:" + generalConfiguration.getIdGeneralConfiguration());

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(generalConfiguration);
		System.out.println("Save/Update Successfully");
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}

	public List<GeneralConfiguration> getAllGeneralConfiguration() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from GeneralConfiguration");
		List<GeneralConfiguration> generalConfigurations = query.list();
		session.getTransaction().commit();
		sessionFactory.close();

		return generalConfigurations;
	}

	public GeneralConfiguration getGeneralConfigurationByID(Integer idGeneralConfiguration) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		GeneralConfiguration generalConfigurationDB = (GeneralConfiguration) session.get(GeneralConfiguration.class,
				idGeneralConfiguration);

		session.getTransaction().commit();
		sessionFactory.close();

		return generalConfigurationDB;
	}

	public void deleteGeneralConfiguration(int idGeneralConfiguration) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		GeneralConfiguration generalConfiguration = (GeneralConfiguration) session.load(GeneralConfiguration.class,
				idGeneralConfiguration);
		session.delete(generalConfiguration);
		System.out.println("Deleted Successfully");
		session.getTransaction().commit();
		sessionFactory.close();

	}

}
