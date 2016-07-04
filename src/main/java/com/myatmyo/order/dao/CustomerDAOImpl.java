package com.myatmyo.order.dao;


import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.myatmyo.order.model.Customer;
import com.myatmyo.order.model.Product;


public class CustomerDAOImpl implements CustomerDAO{
	
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
	public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
		
		org.hibernate.Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Customer");
		ArrayList<Customer> customers = (ArrayList<Customer>) query.list();
		return customers;

	}

	@Override
	public void add(Customer customer) throws SQLException, ClassNotFoundException {
		
		customer.setActive(1);
		customer.setAuthority("ROLE_USER");
		org.hibernate.Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(customer);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void update(Customer customer) throws SQLException, ClassNotFoundException {

		org.hibernate.Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(customer);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public void delete(Customer customer) throws SQLException, ClassNotFoundException {

		org.hibernate.Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(customer);
		session.getTransaction().commit();
		session.close();

	}

}
