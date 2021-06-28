package Files;

import io.restassured.path.json.JsonPath;

public class reusableMethod {
	
	public static JsonPath rawToJson(String response) {
		JsonPath jsadress = new JsonPath(response);
		return jsadress;
				
	}

}
