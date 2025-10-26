package tests;

import dto.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import specs.PetStoreSpecs;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreatePetTest extends PetStoreSpecs {
    @Test
    public void createPetWithBuilderSuccessTest() {
        Pet createPet = Pet.builder()
            .id(2313)
            .name("danechka")
            .status("available")
            .build();
        given()
            .spec(requestSpec())
            .contentType(ContentType.JSON)
            .body(createPet)
            .when()
            .post("/pet")
            .then()
            .spec(successResponseSpec())
            .statusCode(200)
            .body("id", equalTo(2313))
            .body("name", notNullValue())
            .body("status", oneOf("available", "pending", "sold"));
    }

    @Test
    public void createPetWithBuilderFailureTest() {
        Pet failureCreatePet = Pet.builder()
            .name("piska")
            .build();
        given()
            .spec(requestSpec())
            .body(failureCreatePet)
            .when()
            .post("/pet")
            .then()
            .spec(badResponseSpec())
            .body("message", equalTo("Invalid input"));
    }
}
