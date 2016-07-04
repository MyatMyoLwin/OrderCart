package com.myatmyo.order.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.myatmyo.order.model.Product;
import com.myatmyo.order.service.OrderService;

@Controller
@SessionAttributes({"cart"})
public class OrderController {
	
	
	@Autowired
	private OrderService orderService;


	@RequestMapping(value = "orderitems", method = RequestMethod.GET)
	  public String orderItems(Model model, @ModelAttribute("cart") List<Product> cart) {
		
		ArrayList<Product> cartToShow = new ArrayList<Product>();
		cartToShow.addAll(cart);
		
		if (orderService.saveCart(cart)) {
			
			cart.clear();
			model.addAttribute("orderSuccess", "Order has been placed");
			model.addAttribute("cartToShow", cartToShow);
			return "orderSuccess";
		
		} 
			
		model.addAttribute("orderUnsuccess", "Order Unsuccess. Try Again");
		return "redirect:product";
		
		
	  }

}
