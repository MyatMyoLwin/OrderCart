package com.myatmyo.order.dao;


import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.myatmyo.order.model.Product;


public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {
		
		org.hibernate.Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Product");
		ArrayList<Product> products = (ArrayList<Product>) query.list();
		return products;

	}

	@Override
	public void add(Product product) throws SQLException, ClassNotFoundException {

		org.hibernate.Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(product);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void update(Product product) throws SQLException, ClassNotFoundException {

		org.hibernate.Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(product);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public void delete(Product product) throws SQLException, ClassNotFoundException {

		org.hibernate.Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(product);
		session.getTransaction().commit();
		session.close();

	}

	

}
