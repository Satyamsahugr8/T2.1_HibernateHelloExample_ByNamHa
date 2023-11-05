package net.codejava.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BookManager {

	protected SessionFactory sessionFactory;

	protected void setup() throws Exception	{
		// code to load Hibernate Session factory
		// configures settings from hibernate.cfg.xml

		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
		}

	}

	protected void exit() {
		// code to close Hibernate Session factory

		sessionFactory.close();

	}

	protected void create() {
		// code to save a book

		Book book = new Book();
		book.setTitle("Effective Java");
		book.setAuthor("Joshua Bloch");
		book.setPrice(32.59f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(book);

		session.getTransaction().commit();
		session.close();

	}

	protected void read() {
		// code to get a book

	}

	public static void main(String[] args) throws Exception {
		BookManager manager = new BookManager();
		manager.setup();
		manager.create();
		manager.exit();

	}

}
