package tests;

import org.junit.jupiter.api.Test;
import specs.PetStoreSpecs;
import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.given;

public class GetPetByIdTest extends PetStoreSpecs {
    @Test
    public void getPetByIdSuccess() {
        given()
            .spec(requestSpec())
            .when()
            .get("/pet/{petId}", 123)
            .then()
            .spec(successResponseSpec())
            .body("id", equalTo(123))
            .body("status", oneOf("available", "pending", "sold"));
    }

    @Test
    public void getPetByINotFound() {
        given()
            .spec(requestSpec())
            .when()
            .get("/pet/9999999932132131")
            .then()
            .spec(notFoundResponseSpec())
            .body("message", equalTo("Pet not found"));
    }
}
