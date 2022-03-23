package get_http_request.day12N;

import base_url.HerOkuAppUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;

public class PostRequestWithPojo02 extends HerOkuAppUrl {
    /*
    https://restful-booker.herokuapp.com/booking
url’ine aşağıdaki request body gönderildiğinde,
  "firstname": "Selim",
"lastname": "Ak",
"totalprice": 15000,
"depositpaid": true,
"bookingdates": {
"checkin": "2020-09-09",
"checkout": "2020-09-21"
}
}

    Status kodun 200 ve dönen response ‘un

{

"bookingid": 11,
"booking": {
"firstname": "Selim",
"lastname": "Ak",
"totalprice": 15000,
"depositpaid": true,
"bookingdates": {
"checkin": "2020-09-09",
"checkout": "2020-09-21"
}
}
} olduğunu test edin
     */

    @Test
    public void test01(){
        spec05.pathParam("1", "booking");
        BookingDatesPojo bookingdates=new BookingDatesPojo("2020-09-09","2020-09-21");
        BookingPojo bookingPojo=new BookingPojo("Selim", "Ak", 15000,true, bookingdates);
        Response response=given().contentType(ContentType.JSON).spec(spec05).auth().basic("admin", "password").body(bookingPojo).when().
                post("/{1}");
        response.prettyPrint();

        BookingResponsePojo actualData=response.as(BookingResponsePojo.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(bookingPojo.getFirstname(), actualData.getBooking().getFirstname());
        Assert.assertEquals(bookingPojo.getLastname(), actualData.getBooking().getLastname());
        Assert.assertEquals(bookingPojo.getTotalprice(), actualData.getBooking().getTotalprice());
        Assert.assertEquals(bookingPojo.isDepositpaid(), actualData.getBooking().isDepositpaid());
        Assert.assertEquals(bookingPojo.getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals(bookingPojo.getBookingdates().getCheckout(), actualData.getBooking().getBookingdates().getCheckout());

    }
}
