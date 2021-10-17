package kr.co.kumsung.rdbmserver.domain;

import javax.persistence.*;

@Entity
@Table(name = "oauth_refresh_token")
public class OAuthRefreshToken {
    @Id
    @Column(name = "token_id")
    private String tokenId;
    @Lob
    private Byte token;
    @Lob
    private Byte authentication;

    protected OAuthRefreshToken() {
    }

    public OAuthRefreshToken(String tokenId, Byte token, Byte authentication) {
        this.tokenId = tokenId;
        this.token = token;
        this.authentication = authentication;
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

    public Byte getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Byte authentication) {
        this.authentication = authentication;
    }
}
