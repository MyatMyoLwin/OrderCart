package com.myatmyo.order.service;

import java.util.List;

import com.myatmyo.order.model.Product;

public interface OrderService {
	
	public boolean saveCart(List<Product> cart);

}
