package com.myatmyo.order.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.myatmyo.order.dao.ProductDAO;
import com.myatmyo.order.model.Customer;
import com.myatmyo.order.model.Product;

@Controller
@SessionAttributes({"cart"})
public class ProductController {

	ArrayList<Product> productList;

	@Autowired
	private ProductDAO productDao;
	

	/*@Autowired
	@Qualifier("productValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}*/
	
	

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String showProducts(HttpServletRequest request, HttpServletResponse response, Model model) {

		productList = new ArrayList<Product>();

		try {
			productList = productDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int noOfRecords = productList.size();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		int startIndex = (page - 1) * recordsPerPage;
		int lastIndex = startIndex + recordsPerPage;

		if (lastIndex > productList.size()) {
			lastIndex = productList.size();
		}

		JSONArray products = new JSONArray();

		List<Product> transferList = productList.subList(startIndex, lastIndex);
		try {
			for (Product product : transferList) {
				JSONObject tempJSON = new JSONObject();
				tempJSON.put("id", product.getId());
				tempJSON.put("name", product.getName());
				tempJSON.put("price", product.getPrice());
				products.put(tempJSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!model.containsAttribute("cart")) {
		      model.addAttribute("cart", new ArrayList<Product>());
		    }

		model.addAttribute("product", new Product());
		model.addAttribute("productList", products.toString());
		model.addAttribute("noOfPages", noOfPages);
		model.addAttribute("currentPage", page);
		return "product";
	}
	
	@RequestMapping(value = "addproduct", method = RequestMethod.GET)
	  public String addProduct(@ModelAttribute Product product, HttpServletRequest request,Model model,
			  @ModelAttribute("cart") List<Product> cart) {
		
		int id=0;
		
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		
		Product p = findProductById(id);
		cart.add(p);
		System.out.println(p.getName()+"/"+p.getPrice());
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
