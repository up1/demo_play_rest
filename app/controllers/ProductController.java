package controllers;

import models.Product;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ProductController extends Controller {

	public static Result getProducts() {
		ObjectNode result = Json.newObject();
		result.put("code", "200");
		result.put("status", "OK");
		result.putPOJO("products", Json.toJson(Product.getAll()));
		return ok(result);
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result create() {
		JsonNode json = request().body().asJson();
		Product product = Json.fromJson(json, Product.class);
		// TODO for insert data to storage
		return ok(Json.toJson(product));
	}
	
	public static Result update(String id) {
		JsonNode json = request().body().asJson();
		Product product = Json.fromJson(json, Product.class);
		// TODO for update data to storage
		return ok(Json.toJson(product));
	}
	
	public static Result delete(String id) {
		// TODO for delete data in storage
		ObjectNode result = Json.newObject();
		result.put("code", "200");
		result.put("status", "OK");
		return ok(result);
	}

}
