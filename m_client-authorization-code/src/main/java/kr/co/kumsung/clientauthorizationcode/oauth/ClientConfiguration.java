package kr.co.kumsung.clientauthorizationcode.oauth;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class ClientConfiguration {
    private final ClientTokenServices clientTokenServices;
    private final OAuth2ClientContext oAuth2ClientContext;

    public ClientConfiguration(ClientTokenServices clientTokenServices, OAuth2ClientContext oAuth2ClientContext) {
        this.clientTokenServices = clientTokenServices;
        this.oAuth2ClientContext = oAuth2ClientContext;
    }

    @Bean
    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {
        AuthorizationCodeResourceDetails resourceDetails = new AuthorizationCodeResourceDetails();
        resourceDetails.setId("oauth2server");
        resourceDetails.setTokenName("clientapp");
        resourceDetails.setClientSecret("123456");
        resourceDetails.setAccessTokenUri("http://localhost:8080/oauth/token");
        resourceDetails.setUserAuthorizationUri("http://localhost:8080/oauth/authorize");
        resourceDetails.setScope(Arrays.asList("read_profile"));
        resourceDetails.setPreEstablishedRedirectUri("http://localhost:9000/callback");
        resourceDetails.setUseCurrentUri(false);
        resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
        return resourceDetails;
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        OAuth2ProtectedResourceDetails resourceDetails = oAuth2ProtectedResourceDetails();
        OAuth2RestTemplate template = new OAuth2RestTemplate(resourceDetails, oAuth2ClientContext);
        AccessTokenProviderChain provider = new AccessTokenProviderChain(Arrays.asList(new AuthorizationCodeAccessTokenProvider()));
        provider.setClientTokenServices(clientTokenServices);
        template.setAccessTokenProvider(provider);
        return template;
    }
}
