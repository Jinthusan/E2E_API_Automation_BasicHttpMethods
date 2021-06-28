
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Payload;
import Files.reusableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com/";

		// Add place
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.AddPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).extract().response().asString();

		System.out.println(response);
		JsonPath jp = new JsonPath(response);
		String placeid = jp.getString("place_id");
		System.out.println(placeid);

		// Update place
		String Updateaddress = "70 winter walk, USA";
		given().log().all().param("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.updatePlace(placeid, Updateaddress)).when().put("maps/api/place/update/json").then().log()
				.all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

		// Get place
		String getPlaceResponse = given().log().all().param("key", "qaclick123").param("place_id", placeid).when()
				.get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().asString();

		JsonPath jsadress = reusableMethod.rawToJson(getPlaceResponse);
		String actualAddress = jsadress.getString("address");
		System.out.println(actualAddress);

		Assert.assertEquals(actualAddress, Updateaddress);
		
		/*given().log().all().param("key", "qaclick123").header("Content-Type", "application/json")
		.body(Payload.deletePlace(placeid))
		.when().delete("maps/api/place/delete/json")
		.then().log().all().assertThat().statusCode(200).body("status", equalTo("OK"));*/

	}

}
