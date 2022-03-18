package get_http_request.day08;

import base_url.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends DummyBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
5. Çalışan isminin "Airi Satou" olduğunu , çalışan sayısının 24 olduğunu,
Sondan 2. çalışanın maaşının 106450 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
11. Çalışan bilgilerinin
{
“id”:”11”
"employee_name": "Jena Gaines",
"employee_salary": "90560",
"employee_age": "30",
"profile_image": "" }
} gibi olduğunu test edin.
     */

    @Test
    public void test01(){
        spec02.pathParams("1","employees");
        DummyTestData expectedData=new DummyTestData();
        HashMap<String, Object> expectedDataMap=expectedData.setUpTestData();
        //System.out.println("expectedDataMap = " + expectedDataMap);

        Response response=given().accept("application/json").spec(spec02).when().get("/{1}");
        response.prettyPrint();
    }

}
