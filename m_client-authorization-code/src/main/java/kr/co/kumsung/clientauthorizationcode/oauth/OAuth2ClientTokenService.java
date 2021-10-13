package kr.co.kumsung.clientauthorizationcode.oauth;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import kr.co.kumsung.clientauthorizationcode.domain.ClientUser;
import kr.co.kumsung.clientauthorizationcode.domain.ClientUserRepository;
import kr.co.kumsung.clientauthorizationcode.security.ClientUserDetails;

@Service
public class OAuth2ClientTokenService implements ClientTokenServices {
    private final ClientUserRepository clientUserRepository;

    public OAuth2ClientTokenService(ClientUserRepository clientUserRepository) {
        this.clientUserRepository = clientUserRepository;
    }

    private ClientUser getClientUser(Authentication authentication) {
        ClientUserDetails loggedUser = (ClientUserDetails) authentication.getPrincipal();
        Long userId = loggedUser.getClientUser().getId();
        return clientUserRepository.findById(userId).get();
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication) {
        ClientUser clientUser = getClientUser(authentication);
        String accessToken = clientUser.getAccessToken();
        if (Objects.isNull(accessToken)) {
            return null;
        }
        final LocalDateTime expirationDate = clientUser.getAccessTokenValidity();
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        defaultOAuth2AccessToken.setExpiration(new Date().from(expirationDate.atZone(ZoneId.systemDefault()).toInstant()));
        return defaultOAuth2AccessToken;
    }

    @Override
    public void saveAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication, OAuth2AccessToken accessToken) {
        LocalDateTime expirationDate = Instant.ofEpochMilli(accessToken.getExpiration().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        ClientUser clientUser = getClientUser(authentication);
        clientUser.setAccessToken(accessToken.getValue());
        clientUser.setAccessTokenValidity(expirationDate);
//        this.clientUserRepository.save(clientUser);
    }

    @Override
    public void removeAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication) {
        final ClientUser clientUser = getClientUser(authentication);
        clientUserRepository.delete(clientUser);
    }
}
