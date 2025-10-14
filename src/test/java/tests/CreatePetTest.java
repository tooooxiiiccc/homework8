package tests;

import dto.Pet;
import org.junit.jupiter.api.Test;
import specs.PetStoreSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreatePetTest extends PetStoreSpecs {
    @Test
    public void createPetWithBuilderSuccessTest() {
        Pet createPet = Pet.builder()
            .id(345434)
            .name("piska")
            .status("available")
            .build();
        given()
            .spec(requestSpec)
            .body(createPet)
            .when()
            .post("/pet")
            .then()
            .spec(successResponseSpec)
            .statusCode(200)
            .body("id", equalTo(345434))
            .body("name", notNullValue())
            .body("status", oneOf("available", "pending", "sold"));
    }

    @Test
    public void createPetWithBuilderFailureTest() {
        Pet failureCreatePet = Pet.builder()
            .name("piska")
            .build();
        given()
            .spec(requestSpec)
            .body(failureCreatePet)
            .when()
            .post("/pet")
            .then()
            .spec(failToCreateResponseSpec)
            .body("message", equalTo("Invalid input"));
    }
}
