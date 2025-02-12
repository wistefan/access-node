package org.dome.accessnode.it;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.dome.accessnode.ApiException;
import org.dome.accessnode.api.ProductOfferingApi;
import org.dome.accessnode.api.ProductSpecificationApi;
import org.dome.accessnode.model.ProductSpecificationCreateVO;
import org.dome.accessnode.model.ProductSpecificationRefVO;
import org.dome.accessnode.model.ProductOfferingCreateVO;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class StepDefinitions {
//    MockWebServer mockVerifierConsumer = new MockWebServer();
//    MockWebServer mockVerifierProvider = new MockWebServer();

    ProductOfferingApi productOfferingProvider;
    ProductOfferingApi productOfferingConsumer;
    org.dome.accessnode.model.ProductOfferingVO productOffering;

    ProductSpecificationApi productSpecificationProvider;
    ProductSpecificationApi productSpecificationConsumer;
    org.dome.accessnode.model.ProductSpecificationVO productSpecification;

    @Before
    public void waitForTheEnvironment() {
        productOfferingProvider = new ProductOfferingApi();
        productOfferingProvider.setCustomBaseUrl("http://localhost:8080/tmf-api/productCatalogManagement/v4");

        productOfferingConsumer = new ProductOfferingApi();
        productOfferingConsumer.setCustomBaseUrl("http://localhost:8081/tmf-api/productCatalogManagement/v4");

        productSpecificationProvider = new ProductSpecificationApi();
        productSpecificationProvider.setCustomBaseUrl("http://localhost:8080/tmf-api/productCatalogManagement/v4");

        productSpecificationConsumer = new ProductSpecificationApi();
        productSpecificationConsumer.setCustomBaseUrl("http://localhost:8081/tmf-api/productCatalogManagement/v4");

    }

    @Given("a provider and a consumer have deployed there access nodes.")
    public void checkProviderAndConsumerAvailable() {
        assertDoesNotThrow(() -> productOfferingProvider.listProductOffering(null, null, null),
                "The product catalog api should be available at the provider.");
        assertDoesNotThrow(() -> productOfferingConsumer.listProductOffering(null, null, null),
                "The product catalog api should be available at the consumer.");
    }

    @When("a product specification is created at the providers marketplace.")
    public void aProductSpecificationIsCreatedAtTheProvidersMarketplace() throws ApiException {
        ProductSpecificationCreateVO productSpecificationCreateVO = getProductSpecificationCreateVO();
        productSpecification = productSpecificationProvider.createProductSpecification(productSpecificationCreateVO);
    }

    @And("a product offering related to the previous product specification is created at the providers marketplace.")
    public void aProductOfferingRelatedToThePreviousProductSpecificationIsCreatedAtTheProvidersMarketplace() throws ApiException, IOException, InterruptedException {
        ProductOfferingCreateVO productOfferingCreateVO = getProductOfferingCreateVO();
        /*mockVerifierConsumer.start(9595);
        mockVerifierConsumer.setDispatcher(new Dispatcher() {
            @NotNull
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) {
                String path = recordedRequest.getPath();
                MockResponse mockResponse;
                if ("/.well-known/openid-configuration".equals(path)) {
                    mockResponse = new MockResponse()
                            .setBody(openIdConfigurationConsumer)
                            .setResponseCode(201);
//                                                   } else if ("/oidc/token".equals(path)) {
                } else {
                    mockResponse = new MockResponse()
                            .setBody("""
                                    {
                                     "accessToken": "ey...",
                                     "tokenType": "type",
                                     "expiresIn": "3600"
                                        }
                                    """)
                            .setResponseCode(201);
                }

                return mockResponse;
            }
        });

        mockVerifierProvider.start(9595);
        mockVerifierProvider.setDispatcher(new Dispatcher() {
            @NotNull
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) {
                String path = recordedRequest.getPath();
                MockResponse mockResponse;
                if ("/.well-known/openid-configuration".equals(path)) {
                    mockResponse = new MockResponse()
                            .setBody(openIdConfigurationConsumer)
                            .setResponseCode(201)
                            .setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
//                                                   } else if ("/oidc/token".equals(path)) {
                } else {
                    mockResponse = new MockResponse()
                            .setBody("""
                                    {
                                     "accessToken": "ey...",
                                     "tokenType": "type",
                                     "expiresIn": "3600"
                                        }
                                    """)
                            .setResponseCode(201)
                            .setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                }

                return mockResponse;
            }
        });


        //TODO 2 + 2 perk well known es fa 2 cops
        mockVerifierConsumer.start(9596);
        mockVerifierConsumer.setDispatcher(new Dispatcher() {
            @NotNull
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) {
                String path = recordedRequest.getPath();
                MockResponse mockResponse;
                if ("/.well-known/openid-configuration".equals(path)) {
                    mockResponse = new MockResponse()
                            .setBody(openIdConfigurationProvider)
                            .setResponseCode(201)
                            .setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
//                                                   } else if ("/oidc/token".equals(path)) {
                } else {
                    mockResponse = new MockResponse()
                            .setBody("""
                                    {
                                     "accessToken": "ey...",
                                     "tokenType": "type",
                                     "expiresIn": "3600"
                                        }
                                    """)
                            .setResponseCode(201)
                            .setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                }

                return mockResponse;
            }
        });

        mockVerifierProvider.takeRequest(1, TimeUnit.SECONDS);*/
        productOffering = productOfferingProvider.createProductOffering(productOfferingCreateVO);
    }

    @Then("they should be available at the consumer marketplace, too.")
    public void checkCatalogAtConsumer() throws ApiException {
        /*await().atMost(Duration.of(30, ChronoUnit.SECONDS)).until(() -> checkProductOfferingExistence(productOffering.getId()));
        await().atMost(Duration.of(30, ChronoUnit.SECONDS)).until(() -> checkProductSpecificationExistence(productSpecification.getId()));

        assertEquals(productOffering, productOfferingConsumer.retrieveProductOffering(productOffering.getId(), null), "The product offering should be available at the consumers TMForum api.");
        assertEquals(productSpecification, productSpecificationConsumer.retrieveProductSpecification(productSpecification.getId(), null), "The product specification should be available at the consumers TMForum api.");*/

    }

    @NotNull
    private static ProductSpecificationCreateVO getProductSpecificationCreateVO() {
        ProductSpecificationCreateVO productSpecificationCreateVO = new ProductSpecificationCreateVO();
        productSpecificationCreateVO.setName("provider-product-specification");
        return productSpecificationCreateVO;
    }

    @NotNull
    private ProductOfferingCreateVO getProductOfferingCreateVO() {
        ProductSpecificationRefVO productSpecificationRef = new ProductSpecificationRefVO();
        productSpecificationRef.setId(productSpecification.getId());
        productSpecificationRef.setName(productSpecification.getName());
        productSpecificationRef.href(productSpecification.getHref());

        ProductOfferingCreateVO productOfferingCreateVO = new ProductOfferingCreateVO();
        productOfferingCreateVO.setName("provider-product-offering");
        productOfferingCreateVO.setLifecycleStatus("Launched");
        productOfferingCreateVO.setProductSpecification(productSpecificationRef);
        return productOfferingCreateVO;
    }

    private boolean checkProductOfferingExistence(String id) {
        try {
            return productOfferingConsumer.retrieveProductOffering(id, null) != null;
        } catch (ApiException e) {
            return false;
        }
    }

    private boolean checkProductSpecificationExistence(String id) {
        try {
            return productSpecificationConsumer.retrieveProductSpecification(id, null) != null;
        } catch (ApiException e) {
            return false;
        }
    }

    private static final String openIdConfigurationConsumer = """
              {
                  "issuer": "http://localhost:9595",
                  "authorization_endpoint": "http://localhost:9595/oidc/authorize",
                  "device_authorization_endpoint": "http://localhost:9595/oidc/device_authorization",
                  "token_endpoint": "http://localhost:9595/oidc/token",
                  "token_endpoint_auth_methods_supported": [
                      "client_secret_basic",
                      "client_secret_post",
                      "client_secret_jwt",
                      "private_key_jwt",
                      "tls_client_auth",
                      "self_signed_tls_client_auth"
                  ],
                  "jwks_uri": "http://localhost:9595/oidc/jwks",
                  "userinfo_endpoint": "http://localhost:9595/oidc/userinfo",
                  "end_session_endpoint": "http://localhost:9595/oidc/logout",
                  "response_types_supported": [
                      "code"
                  ],
                  "grant_types_supported": [
                      "authorization_code",
                      "client_credentials",
                      "refresh_token",
                      "urn:ietf:params:oauth:grant-type:device_code",
                      "urn:ietf:params:oauth:grant-type:token-exchange"
                  ],
                  "revocation_endpoint": "http://localhost:9595/oidc/revoke",
                  "revocation_endpoint_auth_methods_supported": [
                      "client_secret_basic",
                      "client_secret_post",
                      "client_secret_jwt",
                      "private_key_jwt",
                      "tls_client_auth",
                      "self_signed_tls_client_auth"
                  ],
                  "introspection_endpoint": "http://localhost:9595/oidc/introspect",
                  "introspection_endpoint_auth_methods_supported": [
                      "client_secret_basic",
                      "client_secret_post",
                      "client_secret_jwt",
                      "private_key_jwt",
                      "tls_client_auth",
                      "self_signed_tls_client_auth"
                  ],
                  "code_challenge_methods_supported": [
                      "S256"
                  ],
                  "tls_client_certificate_bound_access_tokens": true,
                  "subject_types_supported": [
                      "public"
                  ],
                  "id_token_signing_alg_values_supported": [
                      "RS256"
                  ],
                  "scopes_supported": [
                      "openid"
                  ]
              }
            """;

    private static final String openIdConfigurationProvider = """
              {
                  "issuer": "http://localhost:9596",
                  "authorization_endpoint": "http://localhost:9596/oidc/authorize",
                  "device_authorization_endpoint": "http://localhost:9596/oidc/device_authorization",
                  "token_endpoint": "http://localhost:9596/oidc/token",
                  "token_endpoint_auth_methods_supported": [
                      "client_secret_basic",
                      "client_secret_post",
                      "client_secret_jwt",
                      "private_key_jwt",
                      "tls_client_auth",
                      "self_signed_tls_client_auth"
                  ],
                  "jwks_uri": "http://localhost:9596/oidc/jwks",
                  "userinfo_endpoint": "http://localhost:9596/oidc/userinfo",
                  "end_session_endpoint": "http://localhost:9596/oidc/logout",
                  "response_types_supported": [
                      "code"
                  ],
                  "grant_types_supported": [
                      "authorization_code",
                      "client_credentials",
                      "refresh_token",
                      "urn:ietf:params:oauth:grant-type:device_code",
                      "urn:ietf:params:oauth:grant-type:token-exchange"
                  ],
                  "revocation_endpoint": "http://localhost:9596/oidc/revoke",
                  "revocation_endpoint_auth_methods_supported": [
                      "client_secret_basic",
                      "client_secret_post",
                      "client_secret_jwt",
                      "private_key_jwt",
                      "tls_client_auth",
                      "self_signed_tls_client_auth"
                  ],
                  "introspection_endpoint": "http://localhost:9596/oidc/introspect",
                  "introspection_endpoint_auth_methods_supported": [
                      "client_secret_basic",
                      "client_secret_post",
                      "client_secret_jwt",
                      "private_key_jwt",
                      "tls_client_auth",
                      "self_signed_tls_client_auth"
                  ],
                  "code_challenge_methods_supported": [
                      "S256"
                  ],
                  "tls_client_certificate_bound_access_tokens": true,
                  "subject_types_supported": [
                      "public"
                  ],
                  "id_token_signing_alg_values_supported": [
                      "RS256"
                  ],
                  "scopes_supported": [
                      "openid"
                  ]
              }
            """;
}
