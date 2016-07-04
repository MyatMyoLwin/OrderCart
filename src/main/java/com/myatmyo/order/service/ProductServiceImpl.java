package com.myatmyo.order.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.myatmyo.order.dao.ProductDAO;
import com.myatmyo.order.model.Product;

public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDAO productDao;
	
	ArrayList<Product> productList;

	@Override
	public ArrayList<Product> getAllProducts() {
		
		ArrayList<Product> productList = new ArrayList<Product>();

		try {
			productList = productDao.getAll();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return productList;
	}	
	
	@Override
	public int getNoOfPages(int noOfRecords, int recordsPerPage) {

		return (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
	}

	@Override
	public List<Product> getProductsForPage(ArrayList<Product> productList, int page, int recordsPerPage) {
		
		int startIndex = (page - 1) * recordsPerPage;
		int lastIndex = startIndex + recordsPerPage;

		if (lastIndex > productList.size()) {
			lastIndex = productList.size();
		}

		return (List<Product>) productList.subList(startIndex, lastIndex);
	}

	@Override
	public JSONArray createJsonArray(List<Product> productList) {
		
		JSONArray products = new JSONArray();

		try {
			for (Product product : productList) {
				JSONObject tempJSON = new JSONObject();
				tempJSON.put("id", product.getId());
				tempJSON.put("name", product.getName());
				tempJSON.put("price", product.getPrice());
				products.put(tempJSON);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return products;
	}

}
