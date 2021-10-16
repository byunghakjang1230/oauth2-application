package kr.co.kumsung.refreshserver.api;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
    private String userName;
    private String password;
    private List<GrantedAuthority> grantedAuthorityList;

    public User(String userName, String password, List<GrantedAuthority> grantedAuthorityList) {
        this.userName = userName;
        this.password = password;
        this.grantedAuthorityList = grantedAuthorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
