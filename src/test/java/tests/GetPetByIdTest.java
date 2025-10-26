package tests;

import dto.Pet;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import specs.PetStoreSpecs;
import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.given;

public class GetPetByIdTest extends PetStoreSpecs {
    @Test
    public void getPetByIdSuccess() {
        Pet pet = Pet.builder()
            .id(123)
            .status("available")
            .name("Liza")
            .build();
        given()
            .spec(requestSpec())
            .body(pet)
            .when()
            .post("/pet")
            .then()
            .spec(successResponseSpec())
            .statusCode(200)
            .body("id", equalTo(123))
            .body("name", notNullValue())
            .extract().body().as(Pet.class);

        given()
            .spec(requestSpec())
            .when()
            .get("/pet/{petId}", pet.getId())
            .then()
            .spec(successResponseSpec())
            .body("id", equalTo(123))
            .extract().body().as(Pet.class);
    }

    @Test
    public void getPetByINotFound() {
        Pet notFoundPet = Pet.builder()
            .id(Integer.MAX_VALUE)
            .status("sold")
            .name("Liza")
            .build();
        given()
            .spec(requestSpec())
            .body(notFoundPet)
            .when()
            .post("/pet")
            .then()
            .spec(successResponseSpec())
            .statusCode(200)
            .body("name", notNullValue())
            .extract().body().as(Pet.class);

        given()
            .spec(requestSpec())
            .when()
            .get("/pet/{petId}", notFoundPet.getId())
            .then()
            .spec(notFoundResponseSpec())
            .body("message", equalTo("Pet not found"));
    }
}
