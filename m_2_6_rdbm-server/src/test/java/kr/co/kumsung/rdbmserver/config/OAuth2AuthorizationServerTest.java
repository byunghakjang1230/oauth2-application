package kr.co.kumsung.rdbmserver.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class OAuth2AuthorizationServerTest {
    private PasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        this.encoder = new BCryptPasswordEncoder(4);
    }

    @Test
    void name() {
        final String encode = encoder.encode("123");
        System.out.println(encode);
        assertThat(encoder.matches("123", encode));
    }
}