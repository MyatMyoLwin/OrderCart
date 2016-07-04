package com.myatmyo.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.myatmyo.order.dao.OrderDAO;
import com.myatmyo.order.model.Order;
import com.myatmyo.order.model.Product;

@Controller
@SessionAttributes({"cart"})
public class OrderController {

	

	@Autowired
	private OrderDAO orderDao;

	@RequestMapping(value = "orderitems", method = RequestMethod.GET)
	  public String orderItems(HttpServletRequest request,Model model,
			  @ModelAttribute("cart") List<Product> cart) {

		for(Product product : cart){

			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			System.out.println(product.getId() + "/" + userDetails.getUsername());
			Order order = new Order(product.getId(), userDetails.getUsername());
			try {
				orderDao.add(order);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return "redirect:product";
		
	  }

	
	

}
