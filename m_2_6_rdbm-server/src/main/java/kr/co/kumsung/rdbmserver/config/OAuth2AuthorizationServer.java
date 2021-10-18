package kr.co.kumsung.rdbmserver.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    private final DataSource dataSource;

    public OAuth2AuthorizationServer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(this.dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.approvalStore(approvalStore())
                .tokenStore(tokenStore())
                .userDetailsService(userDetailsManager());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.passwordEncoder(passwordEncoder());
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        // 사용자 정보 저장
        UserDetails user = User.builder()
                .username("test")
                .password("$2a$04$V3BNB2VTP87wx9hOLSZszebQYGiyfxrAFKNYN5Y2T61l9ZJ/TDR.W")
                .roles("USER")
                .build();
        final JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(this.dataSource);
        jdbcUserDetailsManager.createUser(user);
        return jdbcUserDetailsManager;
    }

    @Bean
    public TokenStore tokenStore() {
        // 데이터베이스에 토큰을 저장하는 토큰 서비스 구현.
        return new JdbcTokenStore(this.dataSource);
    }

    @Bean
    public ApprovalStore approvalStore() {
        // 사용자 승인 저장, 검색 및 취소를 위한 인터페이스(클라이언트별, 범위별).
        return new JdbcApprovalStore(this.dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }
}
