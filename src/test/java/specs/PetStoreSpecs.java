package specs;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

public class PetStoreSpecs {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification successResponseSpec;
    public static ResponseSpecification failureResponseSpec;
    public static ResponseSpecification notFoundResponseSpec;
    public static ResponseSpecification failToCreateResponseSpec;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri("https://petstore.swagger.io/v2")
            .build();

        successResponseSpec = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .expectStatusCode(200)
            .build();

        failureResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .expectContentType(ContentType.JSON)
            .build();

        notFoundResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .expectContentType(ContentType.JSON)
            .build();

        failToCreateResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(405)
            .build();
    }
}
