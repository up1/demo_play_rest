import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class ProductTest {

	private static final int PORT = 3333;

	@Before
	public void setUp() {
		RestAssured.port = PORT;
	}

	@After
	public void tearDown() {
		RestAssured.reset();
	}

	@Test
	public void testGetAllProducts() {
		running(testServer(PORT), new Runnable() {
			@Override
			public void run() {
				String body = RestAssured.expect().statusCode(200).when().get("/product").andReturn().body().asString();
				JsonNode node = Json.parse(body);
				Assert.assertTrue(node.findPath("products").isArray());
				Assert.assertEquals(3, node.findPath("products").size());
			}
		});
	}

	@Test
	public void testCreate() {
		running(testServer(PORT), new Runnable() {
			@Override
			public void run() {
				String body = RestAssured.given()
				.contentType(ContentType.JSON)
				.content("{\"productId\":5,\"productName\":\"IPHONE\"}")
				.expect().statusCode(200)
				.when().post("/product").andReturn().body().asString();
				
				JsonNode node = Json.parse(body);
				Assert.assertEquals(5, node.get("productId").asInt());
				Assert.assertEquals("IPHONE", node.get("productName").asText());
			}
		});
	}
	
	@Test
	public void testUpdate() {
		running(testServer(PORT), new Runnable() {
			@Override
			public void run() {
				String body = RestAssured.given()
				.contentType(ContentType.JSON)
				.content("{\"productId\":5,\"productName\":\"IPHONE\"}")
				.expect().statusCode(200)
				.when().put("/product/5").andReturn().body().asString();
				
				JsonNode node = Json.parse(body);
				Assert.assertEquals(5, node.get("productId").asInt());
				Assert.assertEquals("IPHONE", node.get("productName").asText());
			}
		});
	}
	
	@Test
	public void testDelete() {
		running(testServer(PORT), new Runnable() {
			@Override
			public void run() {
				String body = RestAssured.given()
				.contentType(ContentType.JSON)
				.expect().statusCode(200)
				.when().delete("/product/5").andReturn().body().asString();
				
				JsonNode node = Json.parse(body);
				Assert.assertEquals("200", node.get("code").asText());
				Assert.assertEquals("OK", node.get("status").asText());
			}
		});
	}
}
