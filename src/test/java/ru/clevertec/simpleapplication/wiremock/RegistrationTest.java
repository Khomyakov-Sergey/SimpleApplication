package ru.clevertec.simpleapplication.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest {

    WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(9877));


    @Test
    void wiremock_rule_test() throws IOException {
        wireMockServer.start();
        configureStubs();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
                "{" +
                        "\r\n  \"id\": 3," +
                        "\r\n  \"nickname\": \"Kappa\"," +
                        "\r\n  \"firstname\": \"Igor\"," +
                        "\r\n  \"lastname\": \"Igor\"," +
                        "\r\n  \"age\": 23\r\n" +
                        "}");
        Request request = new Request.Builder()
                .url("http://localhost:9877/registration")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        assertEquals("Welcome", response.body().string());
        assertEquals(201, response.code());
        wireMockServer.stop();
    }


    private void configureStubs() {
        configureFor("localhost", 9877);
        stubFor(
                post(urlEqualTo("/registration"))
                        .willReturn(aResponse()
                                .withBody("Welcome")
                                .withStatus(201)));

    }


}
