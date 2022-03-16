package get_http_request.day07;

import base_url.DummyBaseUrl;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequestN10 extends DummyBaseUrl {
    /*
    http://dummy.restapiexample.com/api/v1/employees
url ine bir istek gönderildiğinde
Dönen response un
Status kodunun 200,
1)10’dan büyük tüm id’leri ekrana yazdırın ve
10’dan büyük 14 id olduğunu,
2)30’dan küçük tüm yaşları ekrana yazdırın ve
bu yaşların içerisinde en büyük yaşın 23 olduğunu
3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
bunların içerisinde “Charde Marshall” olduğunu test edin

     */

    @Test
    public void test10(){
        spec02.pathParam("1","employees");

        Response response= given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{1}");
        //response.prettyPrint();

        JsonPath json=response.jsonPath();

        Assert.assertEquals(200, response.getStatusCode());

        List<Integer> idList=json.getList("data.findAll{it.id>10}.id");
        System.out.println("idList = " + idList);
        Assert.assertEquals(14, idList.size());

        List<Integer> yasList=json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println("yasList = " + yasList);
        Collections.sort(yasList);
        System.out.println("yasList.get(yasList.size()-1) = " + yasList.get(yasList.size() - 1));

        List<String> name=json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println("name = " + name);

        Assert.assertTrue(name.contains("Charde Marshall"));

    }

}
