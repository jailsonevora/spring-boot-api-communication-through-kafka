package com.BusinessEntityManagementSystem;

import com.BusinessEntityManagementSystem.models.BusinessEntityModel;
import com.BusinessEntityManagementSystem.models.PartnerModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.joshka.junit.json.params.JsonFileSource;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import javax.json.JsonObject;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BusinessEntityManagementApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BusinessEntityIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;
    HttpHeaders headers = new HttpHeaders();

    @ParameterizedTest
    @JsonFileSource(resources = "/business-entity-test-param.json")
    @DisplayName("create business entity with json parameter")
    void createBusinessEntity(JsonObject object) throws IOException, URISyntaxException {

        BusinessEntityModel businessEntityModel;
        businessEntityModel = new BusinessEntityModel();

        ObjectMapper mapper = new ObjectMapper();
        businessEntityModel = mapper.readValue(object.toString(), BusinessEntityModel.class);

        HttpEntity<BusinessEntityModel> request = new HttpEntity<>(businessEntityModel, headers);

        try {

            ResponseEntity<String> response = this.restTemplate.postForEntity(createURLWithPort("/api/businessEntityManagementSystem/v1/businessEntity"), request, String.class);
            assertAll(
                    () -> assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value()),
                    () -> assertThat(response.getHeaders().getLocation().getPath()).contains("/v1")
            );
        }
        catch(HttpClientErrorException ex) {
            assertAll(
                    () -> Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), ex.getRawStatusCode()),
                    () -> Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"))
            );
        }
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/partner-test-param.json")
    @DisplayName("create partner with json parameter")
    void creatPartnerWithJsonParameter(JsonObject object) throws IOException, URISyntaxException {

        PartnerModel partnerModel;
        partnerModel = new PartnerModel();

        ObjectMapper mapper = new ObjectMapper();
        partnerModel = mapper.readValue(object.toString(), PartnerModel.class);

        HttpEntity<PartnerModel> request = new HttpEntity<>(partnerModel, headers);

        try {

            ResponseEntity<String> response = this.restTemplate.postForEntity(createURLWithPort("/api/businessEntityManagementSystem/v1/partners"), request, String.class);
            assertAll(
                    () -> assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value()),
                    () -> assertThat(response.getHeaders().getLocation().getPath()).contains("/v1")
            );
        }
        catch(HttpClientErrorException ex) {
            assertAll(
                    () -> Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), ex.getRawStatusCode()),
                    () -> Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"))
            );
        }
    }

    @Test
    @DisplayName("create partner with object by hardcode")
    void creatPartner() throws URISyntaxException {

        PartnerModel partnerModel;
        partnerModel = new PartnerModel();
        partnerModel.setStatus(1);
        partnerModel.setName("Test");
        partnerModel.setNum(1);
        partnerModel.setApprovedBy("me");
        partnerModel.setCreatedBy("you");
        partnerModel.setLastModifiedBy("they");
        partnerModel.setRenewedBy("him");

        HttpEntity<PartnerModel> request = new HttpEntity<>(partnerModel, headers);

        try {

            ResponseEntity<String> response = this.restTemplate.postForEntity(createURLWithPort("/api/businessEntityManagementSystem/v1/partners"), request, String.class);
            assertAll(
                    () -> assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value()),
                    () -> assertThat(response.getHeaders().getLocation().getPath()).contains("/v1")
            );
            //Assert.fail();
        }
        catch(HttpClientErrorException ex) {
            assertAll(
                    () -> Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), ex.getRawStatusCode()),
                    () -> Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"))
            );
        }
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
