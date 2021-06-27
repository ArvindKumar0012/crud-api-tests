package services.test;

import utils.YamlUtils;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetServiceRequestTests {
	YamlUtils yamlUtils = new YamlUtils();
	ValidatableResponse response;
	@Test
	@DisplayName("To verify all the fields are returned in the response with status code 200 when GET request is called")
	public void testGetHttpBinOperation(){
		ValidatableResponse response = given().
				log().all().header("Content-type","application/json").when().get(yamlUtils.getProperty("base_uri")
				+ yamlUtils.getProperty("get_path_uri")).then().log().all();
		response.assertThat().statusCode(200)
				.and().body("url",equalTo(yamlUtils.getProperty("base_uri")+yamlUtils.getProperty("get_path_uri")))
				.and().body("origin",notNullValue())
				.and().body("headers.Accept",equalTo("*/*"));
	}
}