package get_http_request.day10;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest23 extends DummyBaseUrl {
    /*
http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
14. Çalışan isminin "Haley Kennedy" olduğunu,
Çalışan sayısının 24 olduğunu,
Sondan 3. çalışanın maaşının 675000 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi

{
        "id": 10,
        "employee_name": "Sonya Frost",
        "employee_salary": 103600,
        "employee_age": 23,
        "profile_image": ""
 }

  olduğunu test edin.
*/

    @Test
    public void test23() {
        //1) Url oluştur

        spec02.pathParams("1", "api", "2", "v1", "3", "employees");

        //2)Expected Data Oluşturma
        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedTestDataMap = expectedObje.setUpTestData();
        //System.out.println(expectedTestDataMap);

        //3)) Repuest ve Response oluşturma
        Response response = given().spec(spec02).contentType(ContentType.JSON).when().get("/{1}/{2}/{3}");
        response.prettyPrint();

        //4)) Doğrulama
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        Assert.assertEquals(expectedTestDataMap.get("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedTestDataMap.get("ondorduncücalisan"), ((Map) ((List) actualDataMap.get("data")).get(13)).get("employee_name"));

        Assert.assertEquals(expectedTestDataMap.get("calisansayisi"), (((List<?>) actualDataMap.get("data")).size()));
        int dataSize = ((List) actualDataMap.get("data")).size();
        Assert.assertEquals(expectedTestDataMap.get("sondanucuncucalısanmaası"),
                ((Map) ((List) actualDataMap.get("data")).get(dataSize - 3)).get("employee_salary"));

        List<Integer> actualYasListesi = new ArrayList<>();

        for (int i = 0; i < dataSize; i++) {

        actualYasListesi.add((Integer) ((Map) ((List)actualDataMap.get("data")).get(i)).get("employee_age"));

    }
        Assert.assertTrue(actualYasListesi.containsAll((Collection<?>) expectedTestDataMap.get("arananyaslar")));

    }
}
