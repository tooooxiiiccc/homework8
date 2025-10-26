package tests;

import dto.Pet;
import org.junit.jupiter.api.Test;
import specs.PetStoreSpecs;

import static io.restassured.RestAssured.given;

public class DeletePetTest extends PetStoreSpecs {
    @Test
    public void deletePetTest(){
        given()
            .spec(requestSpec())
            .when()
            .delete("pet/123")
            .then()
            .spec(successResponseSpec());
    }
}
