package controllers;

import java.util.ArrayList;
import java.util.List;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ProductController extends Controller {

	public static Result getProducts() {
		ObjectNode result = Json.newObject();
		result.put("code", "200");
		result.put("status", "OK");
		result.putPOJO("products", Json.toJson(Products.getAll()));
		return ok(result);
	}

}

class Products {
	public static List<Product> getAll() {
		List<Product> list = new ArrayList<Product>();
		list.add(new Product(1, "IPAD 1"));
		list.add(new Product(2, "IPAD 2"));
		list.add(new Product(3, "IPAD 3"));
		return list;
	}
}

class Product {
	private int productId;
	private String productName;
	
	public Product(int productId, String productName) {
		this.productId = productId;
		this.productName = productName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
