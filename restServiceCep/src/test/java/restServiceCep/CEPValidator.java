package restServiceCep;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


public class CEPValidator {
	
	String validCEP 	= "92310140";
	String incorrectCEP = "12345678";
	String invalidCEP	= "TEXT1230";
	
	
	@Test
	public void TC00() {
		
		given().
			contentType("application/json").
		when().
			get("https://viacep.com.br/ws/"+validCEP+"/json/").
		then().
			statusCode(200);	
	}
	
	@Test
	public void TC01() {
		
		given().
			contentType("application/json").
		when().
			get("https://viacep.com.br/ws/"+validCEP+"/json/").
		then().
			statusCode(200).
			assertThat().
			body("$", hasKey("cep")).
			body("$", hasKey("logradouro")).
			body("$", hasKey("bairro")).
			body("$", hasKey("localidade")).
			body("$", hasKey("uf")).
			body("$", hasKey("ibge"));
	}
	
	@Test
	public void TC02() {
		
		given().
			contentType("application/json").
		when().
			get("https://viacep.com.br/ws/"+incorrectCEP+"/json/").
		then().
			statusCode(200).
			assertThat().
			body("erro", equalTo(true));
	}	
	
	@Test
	public void TC03() {
		
		given().
			contentType("application/json").
		when().
			get("https://viacep.com.br/ws/"+invalidCEP+"/json/").
		then().
			statusCode(404);
	}	
	
	
	
}
