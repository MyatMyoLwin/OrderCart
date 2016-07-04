package com.myatmyo.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.myatmyo.order.dao.ProductDAO;
import com.myatmyo.order.model.Product;
import com.myatmyo.order.service.OrderService;
import com.myatmyo.order.service.ProductService;

@Controller
@SessionAttributes({"cart"})
public class ProductController {

	ArrayList<Product> productList;
	
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String showProducts(HttpServletRequest request, HttpServletResponse response, Model model) {

		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		productList = productService.getAllProducts();
		
		int noOfPages = productService.getNoOfPages(productList.size(), recordsPerPage);
		
		List<Product> transferList = productService.getProductsForPage(productList, page, recordsPerPage);
		
		JSONArray products = productService.createJsonArray(transferList);
		
		if (!model.containsAttribute("cart")) {
			model.addAttribute("cart", new ArrayList<Product>());
		} 
		
		model.addAttribute("product", new Product());
		model.addAttribute("productList", products.toString());
		model.addAttribute("noOfPages", noOfPages);
		model.addAttribute("currentPage", page);
		return "product";
	}
	
	@RequestMapping(value = "addproduct", method = RequestMethod.GET)
	  public String addProduct(HttpServletRequest request,Model model,
			  @ModelAttribute("cart") List<Product> cart) {
		
		int id=0;
		
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		
		cart.add(findProductById(id));

		model.addAttribute("cart", cart);
	    return "redirect:product";
	  }
		
	private Product findProductById(int id) {

		Product product = new Product();

		for (Product p : productList) {
			if (p.getId() == id) {
				product.setId(p.getId());
				product.setName(p.getName());
				product.setPrice(p.getPrice());
			}
		}

		return product;
	}
	


}
