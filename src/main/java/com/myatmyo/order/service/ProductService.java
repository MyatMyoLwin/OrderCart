package com.myatmyo.order.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.myatmyo.order.model.Product;

public interface ProductService {
	
	public ArrayList<Product> getAllProducts();
	
	public int getNoOfPages(int noOfRecords, int recordsPerPage);

	public List<Product> getProductsForPage(ArrayList<Product> productList, int page, int recordsPerPage);
	
	public JSONArray createJsonArray(List<Product> productList);
}
