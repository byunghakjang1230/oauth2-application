package kr.co.kumsung.rdbmserver.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthClientDetailsRepository extends JpaRepository<OAuthClientDetails, String> {
}
