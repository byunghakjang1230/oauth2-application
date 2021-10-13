package kr.co.kumsung.clientauthorizationcode.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import kr.co.kumsung.clientauthorizationcode.user.Entry;

@Entity
public class ClientUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    private String password;
    @Column(name = "access_token")
    private String accessToken;
    @Column(name = "access_token_validity")
    private LocalDateTime accessTokenValidity;
    @Column(name = "refresh_token")
    private String refreshToken;
    @Transient
    private List<Entry> entries = new ArrayList<>();

    protected ClientUser() {
    }

    public ClientUser(String userName, String password, String accessToken, LocalDateTime accessTokenValidity, String refreshToken) {
        this.userName = userName;
        this.password = password;
        this.accessToken = accessToken;
        this.accessTokenValidity = accessTokenValidity;
        this.refreshToken = refreshToken;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public LocalDateTime getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setAccessTokenValidity(LocalDateTime accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }
}
