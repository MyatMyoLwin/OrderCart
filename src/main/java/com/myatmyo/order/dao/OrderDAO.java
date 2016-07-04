package com.myatmyo.order.dao;


import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.myatmyo.order.model.Order;
import com.myatmyo.order.model.Product;


public interface OrderDAO {

	public SessionFactory getSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory);
	
	public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException;

	public void add(Order order) throws SQLException, ClassNotFoundException;

	public void update(Order order) throws SQLException, ClassNotFoundException;

	public void delete(Order order) throws SQLException, ClassNotFoundException;
}
