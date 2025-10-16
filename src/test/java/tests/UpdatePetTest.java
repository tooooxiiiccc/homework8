package tests;

import dto.Pet;
import org.junit.jupiter.api.Test;
import specs.PetStoreSpecs;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UpdatePetTest extends PetStoreSpecs {
    @Test
    public void updatePet() {
        Pet updatedPet = Pet.builder()
            .id(345434)
            .name("Danila")
            .status("sold")
            .build();
        given()
            .spec(requestSpec())
            .body(updatedPet)
            .when()
            .put("/pet")
            .then()
            .statusCode(200)
            .body("name", equalTo("Danila"))
            .body("status", oneOf("available", "pending", "sold"));

    }
}
