package kr.co.kumsung.oauth2provider.domain;

import javax.persistence.*;

@Entity
@Table(name = "oauth_access_token")
public class OAuthAccessToken {
    @Id
    @Column(name = "authentication_id")
    private String authenticationId;
    @Column(name = "token_id")
    private String tokenId;
    @Lob
    private Byte token;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "client_id")
    private String clientId;
    @Lob
    private Byte authentication;
    @Column(name = "refresh_token")
    private String refreshToken;

    protected OAuthAccessToken() {

    }

    public OAuthAccessToken(String authenticationId, String tokenId, Byte token, String userName, String clientId, Byte authentication, String refreshToken) {
        this.authenticationId = authenticationId;
        this.tokenId = tokenId;
        this.token = token;
        this.userName = userName;
        this.clientId = clientId;
        this.authentication = authentication;
        this.refreshToken = refreshToken;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Byte getToken() {
        return token;
    }

    public void setToken(Byte token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Byte getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Byte authentication) {
        this.authentication = authentication;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
