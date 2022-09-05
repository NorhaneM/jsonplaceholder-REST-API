package Steps_Definition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;



import static io.restassured.RestAssured.given;


public class POST_UserID {
    private static String requestBody = "{\n" +
            "  \"title\": \"Norhane\",\n" +
            "  \"body\": \"Mahrouss\",\n" +
            "  \"userId\": \"8\" \n}";

    @Given("user navigate to main base URL")
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @And("create a new user with same past user ID")
    public void postRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .extract().response();
        String responseInString = response.asString();
        System.out.println(responseInString);

        // Get the status code of the request.
        //If request is successful, status code will be 200
        int statusCode = response.getStatusCode();
        // Assert that correct status code is returned.
        Assert.assertEquals(statusCode /*actual value*/, 201 /*expected value*/);
        System.out.println(statusCode);
        System.out.println("Pass Successfully");

        // Assert that title has been changed to the new one.
        String title = response.jsonPath().getString("title");
        Assert.assertEquals(title, "Norhane", "passed Successfully");
        System.out.println(title);
        System.out.println("Pass Successfully");

        // Assert that body has been changed to the new one.
        String body = response.jsonPath().getString("body");
        Assert.assertEquals(body, "Mahrouss");
        System.out.println(body);
        System.out.println("Pass Successfully");

        // Assert that userId has been changed to the new one.
        String userId = response.jsonPath().getString("userId");
        Assert.assertEquals(userId, "8");
        System.out.println(userId);
        System.out.println("Pass Successfully");

        // Assert that userId has been changed to the new one.
        String id = response.jsonPath().getString("id");
        Assert.assertEquals(id, "101");
        System.out.println(id);
        System.out.println("Pass Successfully");


    }
}
