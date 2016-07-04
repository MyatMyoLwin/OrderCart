package com.myatmyo.order.controller;

import java.util.ArrayList;
/*import java.text.DateFormat;
import java.util.Date;*/
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myatmyo.order.dao.CustomerDAO;
import com.myatmyo.order.model.Customer;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	@Qualifier("customerValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String initialPath() {
		logger.info("/ get");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error, Model model) {

		logger.info("login get");

		if (error != null) {
			model.addAttribute("message", "Invalid username and password!");
		}
		return "login";

	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGet(Locale locale, Model model) {
		model.addAttribute("customer", new Customer());
		logger.info("register Get");
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(@ModelAttribute("customer") @Validated Customer customer, BindingResult result,
			Model model) {

		logger.info("register post");

		if (result.hasErrors()) {

			return "register";

		} else {

			try {
				ArrayList<Customer> customers = customerDao.getAll();

				for (Customer c : customers) {
					if (customer.getUsername().equals(c.getUsername())) {
						model.addAttribute("message", "username is already exit");
						return "register";
					}
				}

				customerDao.add(customer);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:login";
		}

	}

}
