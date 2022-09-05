package Steps_Definition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GET_UserID {
    @Given("user navigate to Base URL")

    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Then("GET random user ID and print his email to console")

    public void getRequestWithQueryParam() {
        // get a random user ID
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users?id=8")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
        String responseInString = response.asString();
        System.out.println("the response body of userID " + responseInString);

        int statusCode = response.getStatusCode();
        // Assert that correct status code is returned.
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/);
        System.out.println("the status code is " + statusCode);
        System.out.println("Pass Successfully");

        // print email address from the response to the console
        JsonPath jsonPath = new JsonPath(responseInString);
        String email_Address = jsonPath.getString("email");
        System.out.println("the E-mail address of the user is " + email_Address);
    }
    @And("get this user’s associated posts and verify they contains a valid Post IDs")
    public void userPosts() {
        // GET all posts associated with his userID
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/posts?userId=8")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
        // Print this user ID Body
        String responseInString = response.asString();
        System.out.println("user's ID body " + responseInString);

        // Print all posts associated with this userID
        JsonPath jsonPath = new JsonPath(responseInString);
        String userPosts = jsonPath.getString("title");
        System.out.println("All posts associated with this user" + userPosts);

        // Verify that the posts contains a valid Post IDs.
        String postId = response.asString();
        System.out.println("the posts contains a valid Post's IDs " + postId);
        //verify that IDs has a valid IDs num (an Integer between 1 and 100).
        response.then().assertThat().body("id", everyItem(allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(100))));

    }
}
