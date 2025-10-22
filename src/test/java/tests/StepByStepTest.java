package tests;

import dto.ApiResponse;
import dto.Pet;
import org.junit.jupiter.api.Test;
import specs.PetStoreSpecs;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StepByStepTest extends PetStoreSpecs {
    @Test
    public void testStepByStep() {
        Pet petNew = Pet.builder()
            .id(333334)
            .name("Kira")
            .status("available")
            .build();

        Pet createdPet = given()
            .spec(requestSpec())
            .body(petNew)
            .when()
            .post("/pet")
            .then()
            .spec(successResponseSpec())
            .extract().body().as(Pet.class);

        assertThat(createdPet.getId()).isEqualTo(petNew.getId());


        ApiResponse deleteResponse = given()
            .spec(requestSpec())
            .when()
            .delete("/pet/{id}", petNew.getId())
            .then()
            .spec(successResponseSpec())
            .extract().body().as(ApiResponse.class);
        assertThat(deleteResponse.getCode()).isEqualTo(200);

        given()
            .spec(requestSpec())
            .when()
            .get("/pet/{id}", petNew.getId())
            .then()
            .spec(notFoundResponseSpec());
    }
}
