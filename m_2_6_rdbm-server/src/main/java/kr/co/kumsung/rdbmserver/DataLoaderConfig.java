package kr.co.kumsung.rdbmserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import kr.co.kumsung.rdbmserver.domain.*;

@Component
public class DataLoaderConfig implements CommandLineRunner {
    private final OAuthClientDetailsRepository oAuthClientDetailsRepository;

    public DataLoaderConfig(OAuthClientDetailsRepository oAuthClientDetailsRepository) {
        this.oAuthClientDetailsRepository = oAuthClientDetailsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Client 정보 저장.
        OAuthClientDetails oAuthClientDetails = new OAuthClientDetails();
        oAuthClientDetails.setClientId("clientapp");
        oAuthClientDetails.setScope("read_profile");
        oAuthClientDetails.setAuthorizedGrantTypes("authorization_code,refresh_token");
        oAuthClientDetails.setWebServerRedirectUri("http://localhost:9000/callback");
        oAuthClientDetails.setAccessTokenValidity(120);
        oAuthClientDetails.setRefreshTokenValidity(-1);
        oAuthClientDetails.setAutoapprove("false");
        oAuthClientDetails.setClientSecret("$2a$04$zOpWQtP3HwXAcYzFTTjm7e.7JyR6gtc8WZfNxjVN4k2V1EsijC1fO");
        this.oAuthClientDetailsRepository.save(oAuthClientDetails);
    }
}
