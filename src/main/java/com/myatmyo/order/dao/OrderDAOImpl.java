package com.myatmyo.order.dao;


import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.myatmyo.order.model.Order;
import com.myatmyo.order.model.Product;


public class OrderDAOImpl implements OrderDAO{
	
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
	public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
		
		org.hibernate.Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Orders");
		ArrayList<Order> orders = (ArrayList<Order>) query.list();
		return orders;

	}

	@Override
	public void add(Order order) throws SQLException, ClassNotFoundException {

		org.hibernate.Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(order);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void update(Order order) throws SQLException, ClassNotFoundException {

		org.hibernate.Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(order);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public void delete(Order order) throws SQLException, ClassNotFoundException {

		org.hibernate.Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(order);
		session.getTransaction().commit();
		session.close();

	}

}
