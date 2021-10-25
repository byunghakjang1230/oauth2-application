package kr.co.kumsung.oauth2provider.domain;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(length = 50, nullable = false)
    private String username;
    @Lob
    @Column(length = 500, nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean enabled;

    protected Users() {
    }

    public Users(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
