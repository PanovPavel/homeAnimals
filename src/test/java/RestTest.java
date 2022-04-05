import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class RestTest {
    @Test
    public void getTypePet(){
        RestAssured.given()
                .baseUri("http://localhost:8080/api")
                .basePath("/type_pet")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then().statusCode(200);
    }
}
