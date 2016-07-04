package com.myatmyo.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.myatmyo.order.dao.OrderDAO;
import com.myatmyo.order.model.Order;
import com.myatmyo.order.model.Product;

public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDAO orderDao;

	@Override
	public boolean saveCart(List<Product> cart) {
		
		for(Product product : cart){

			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			Order order = new Order(product.getId(), userDetails.getUsername());
			try {
				orderDao.add(order);
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return  true;
		
	}

}
