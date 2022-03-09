package test_data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {
    //40,21 ve 19 yaslarında çalışanlar olup olmadığını
    public HashMap<String, Object> setUpTestData(){
        List<Integer> yaslar= new ArrayList<>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);
//{
//        "id": 10,
//        "employee_name": "Sonya Frost",
//        "employee_salary": 103600,
//        "employee_age": 23,
//        "profile_image": ""
// }
        HashMap<String , Object> onuncu=new HashMap<>();
        onuncu.put("id", 10);
        onuncu.put("employee_name", "Sonya Frost");
        onuncu.put("employee_salary", 103600);
        onuncu.put("employee_age", 23);
        onuncu.put("profile_image", "");

        HashMap<String, Object> expectedData=new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("ondorduncücalisan", "Haley Kennedy");
        expectedData.put("calisansayisi", 24);
        expectedData.put("sondanucuncucalısanmaası", 675000);
        expectedData.put("arananyaslar", yaslar);
        expectedData.put("onuncucalisan", onuncu);
        return expectedData;
    }
}