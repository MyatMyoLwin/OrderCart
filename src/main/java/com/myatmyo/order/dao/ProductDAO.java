package com.myatmyo.order.dao;


import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.myatmyo.order.model.Product;


public interface ProductDAO {

	public SessionFactory getSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory);
	
	@SuppressWarnings("unchecked")
	public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException;

	public void add(Product product) throws SQLException, ClassNotFoundException;

	public void update(Product product) throws SQLException, ClassNotFoundException;

	public void delete(Product product) throws SQLException, ClassNotFoundException;
}
