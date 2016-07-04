package com.myatmyo.order.dao;


import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.myatmyo.order.model.Customer;
import com.myatmyo.order.model.Product;


public interface CustomerDAO {

	public SessionFactory getSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory);
	
	@SuppressWarnings("unchecked")
	public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException;

	public void add(Customer customer) throws SQLException, ClassNotFoundException;

	public void update(Customer customer) throws SQLException, ClassNotFoundException;

	public void delete(Customer customer) throws SQLException, ClassNotFoundException;
}
