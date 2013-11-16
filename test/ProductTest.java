import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.restassured.RestAssured;

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
				Assert.assertEquals(3, node.findPath("products"));
			}
		});
	}
}
