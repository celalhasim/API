package get_http_request.day14N;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ReqresToken {

    public String TokenAl(){

        String url="https://regres.in/api/login";

        HashMap<String, Object> requestBody=new HashMap<>();

        requestBody.put("email","eve.holt.@reqres.in");
        requestBody.put("password", "cityslicka");

        Response response=given().contentType(ContentType.JSON).body(requestBody).when().post(url);

        JsonPath json=response.jsonPath();
        String token= json.getString("token");

        return token;
    }
}
