package get_http_request.day08;

import base_url.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends DummyBaseUrl {
/*http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.*/


    @Test
    public void test01(){
        spec02.pathParam("1", "employees");

        DummyTestData expectedObje=new DummyTestData();
        HashMap<String, Integer> expectedDataMap=expectedObje.setUpTestData02();
        System.out.println("expectedDataMap = " + expectedDataMap);

        Response response=given().accept("application/json").spec(spec02).when().get("/{1}");
        response.prettyPrint();
    }
}
