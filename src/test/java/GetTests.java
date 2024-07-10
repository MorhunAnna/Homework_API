
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class GetTests
{
@Test
    public void findArticulatedLorry()throws IOException
{
    String  url = "https://api.github.com/emojis";

    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(url);
    CloseableHttpResponse response = httpClient.execute(httpGet);

    System.out.println("Status code: " + response.getStatusLine().getStatusCode());
    System.out.println("Status phrase: " + response.getStatusLine().getReasonPhrase());

    HttpEntity entity = response.getEntity();
    String json = EntityUtils.toString(entity);

    System.out.println("Response body: " + json);

    assertTrue(json.contains("articulated_lorry"), "Response contains key 'articulated_lorry'");
}

@Test
public void findArticulatedLorryRestAssured() {
    String url = "https://api.github.com/emojis";

    Response response = RestAssured.given()
            .baseUri(url)
            .get()
            .then()
            .statusCode(200)
            .extract().response();

    System.out.println("Status code: " + response.getStatusCode());
    System.out.println("Response body: " + response.getBody().asString());

    String json = response.getBody().asString();
    assertTrue(json.contains("articulated_lorry"), "Response contains key 'articulated_lorry'");

}
}
