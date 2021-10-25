package kr.co.kumsung.oauth2provider.domain;

import javax.persistence.*;

@Entity
@Table(name = "authorities", indexes = {
        @Index(name = "ix_auth_username", columnList = "username, authority", unique = true)
})
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", foreignKey = @ForeignKey(name = "fk_authorities_users"), nullable = false)
    private Users users;
    @Column(length = 50, nullable = false)
    private String authority;

    protected Authorities() {
    }

    public Authorities(Users users, String authority) {
        this.users = users;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public Users getUsers() {
        return users;
    }

    public String getAuthority() {
        return authority;
    }
}
