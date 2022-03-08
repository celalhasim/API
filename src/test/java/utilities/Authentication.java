package utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {
    public static void main(String[] args) {
        String gunceltoken=generateToken();
        System.out.println(gunceltoken);
    }

    public static String generateToken(){
        String username="Batch44";
        String password="Batch44+";

        Map<String, Object> map = new HashMap<>();
        map.put("Batch44", username);
        map.put("Batch44+", password);

        String endPoint="https://www.gmibank.com/api/authenticate";

        Response response=given().contentType(ContentType.JSON).body(map).when().post(endPoint);

        JsonPath token=response.jsonPath();
        return token.getString("id_token");
    }
}
