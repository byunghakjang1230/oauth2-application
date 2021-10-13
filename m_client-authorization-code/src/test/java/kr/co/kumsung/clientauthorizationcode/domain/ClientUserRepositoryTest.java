package kr.co.kumsung.clientauthorizationcode.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientUserRepositoryTest {
    @Autowired
    private ClientUserRepository clientUserRepository;

    @Test
    void name() {
        // given
        ClientUser clientUser = new ClientUser("test", "test", "sdfsdf", LocalDateTime.now(), "hihihi");

        // when
        ClientUser savedClientUser = this.clientUserRepository.save(clientUser);

        // then
        assertThat(savedClientUser.getId()).isNotNull();
    }
}
