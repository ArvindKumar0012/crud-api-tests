package services.test;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.YamlUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostServiceRequestTests {
    YamlUtils yamlUtils = new YamlUtils();
    ValidatableResponse response;
    @Test
    @DisplayName("To verify all the fields are returned in the response with status code 200 when POST request is called")
    public void testPostHttpBinOperation(){
        ValidatableResponse response = given().
                log().all().header("Content-type","application/json").when().post(yamlUtils.getProperty("base_uri")
                + yamlUtils.getProperty("post_path_uri")).then().log().all();
        response.assertThat().statusCode(200)
                .and().body("url",equalTo(yamlUtils.getProperty("base_uri")+yamlUtils.getProperty("post_path_uri")))
                .and().body("origin",notNullValue())
                .and().body("headers.Accept",equalTo("*/*"))
                .and().body("data",blankOrNullString())
                .and().body("json",nullValue())
                .and().body("headers.X-Amzn-Trace-Id",containsString("Root="))
                .and().header("access-control-allow-credentials","true");
    }

}