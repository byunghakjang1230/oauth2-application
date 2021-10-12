package kr.co.kumsung.clientauthorizationcode.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientUserRepository extends JpaRepository<ClientUser, Long> {
    Optional<ClientUser> findByUserName(String userName);
}
