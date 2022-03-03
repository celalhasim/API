package get_http_request;

import base_url.ReqresinBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest07 extends ReqresinBaseUrl {

    @Test
    public void test07(){
       spec01.pathParams("parametre1", "api", "parametre2","users");
        Response response=given().spec(spec01).when().get("/{parametre1}/{parametre2}");
        response.prettyPrint();
        response.then().assertThat().body("data[4].email", equalTo("charles.morris@reqres.in"),
                                                "data[4].first_name", equalTo("Charles"),
                                                "data[4].last_name", equalTo("Morris"));

        //JSonPath

        JsonPath json=response.jsonPath();
        System.out.println(json.getList("data.email"));
        System.out.println(json.getString("data.first_name"));
        System.out.println(json.getString("data.last_name"));
        Assert.assertEquals("charles.morris@reqres.in", json.getString("data[4].email"));
        Assert.assertEquals("Charles", json.getString("data[4].first_name"));

    }
}
