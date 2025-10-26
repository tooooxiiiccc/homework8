package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PetStoreSpecs {
    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
            .setBaseUri("https://petstore.swagger.io/v2/")
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .build();
    }

    public static ResponseSpecification successResponseSpec(){
        return new ResponseSpecBuilder()
            .expectContentType("application/json")
            .expectStatusCode(200)
            .build();

    };

    public static ResponseSpecification badResponseSpec(){
        return new ResponseSpecBuilder()
            .expectContentType("application/json")
            .expectStatusCode(400)
            .build();
    }

    public static ResponseSpecification notFoundResponseSpec(){
        return new ResponseSpecBuilder()
            .expectStatusCode(404)
            .build();
    }
}
