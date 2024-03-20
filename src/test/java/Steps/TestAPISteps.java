package Steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.RestAssured;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class TestAPISteps {
    private Response response;

    @Given("Eu configuro o endpoint do serviço de GET users")
    public void eu_configuro_o_endpoint_do_servico_de_get_users() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/users/1";
    }

    @When("Eu envio uma requisição HTTP GET")
    public void euEnvioUmaRequisicaoHTTPGET() {
        response = RestAssured.given().get();
    }

    @Then("Eu recebo o código de resposta HTTP {int}")
    public void euReceboOCodigoDeRespostaHTTP(Integer int1) {
        //assertEquals((Integer) response.getStatusCode(), int1);
        response.then().statusCode(int1);
    }

    @And("Eu recebo o usuário com id {int}")
    public void euReceboOUsuarioComId(Integer int1) {
        //assertEquals((Integer) response.jsonPath().getInt("id"), int1);
        response.then().body("id", equalTo(int1));
    }
}
