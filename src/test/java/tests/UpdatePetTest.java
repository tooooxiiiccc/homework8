package tests;

import dto.Pet;
import org.junit.jupiter.api.Test;
import specs.PetStoreSpecs;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;

public class UpdatePetTest extends PetStoreSpecs {
    @Test
    public void updatePet() {
        Pet petNew = Pet.builder()
            .id(12345633)
            .name("Kira")
            .status("available")
            .build();

        Pet updatedPet = Pet.builder()
            .id(12345633)
            .name("Kira")
            .status("sold")
            .build();

        given()
            .spec(requestSpec())
            .body(petNew)
            .when()
            .post("/pet")
            .then()
            .spec(successResponseSpec());

        Pet updatedPetResponse = given()
            .spec(requestSpec())
            .body(updatedPet)
            .when()
            .put("/pet")
            .then()
            .spec(successResponseSpec())
            .extract().body().as(Pet.class);

        assertThat(updatedPetResponse.getId()).isEqualTo(updatedPet.getId());
        assertThat(updatedPetResponse.getName()).isEqualTo(updatedPet.getName());
        assertThat(updatedPetResponse.getStatus()).isEqualTo(updatedPet.getStatus());

        Pet getPetAfterUpdate =
            given()
                .spec(requestSpec())
                .when()
                .get("/pet/{id}", updatedPet.getId())
                .then()
                .spec(successResponseSpec())
            .extract().body().as(Pet.class);

        assertThat(getPetAfterUpdate.getId()).isEqualTo(updatedPet.getId());
        assertThat(getPetAfterUpdate.getName()).isEqualTo(updatedPet.getName());
        assertThat(getPetAfterUpdate.getStatus()).isEqualTo(updatedPet.getStatus());

    }
}
