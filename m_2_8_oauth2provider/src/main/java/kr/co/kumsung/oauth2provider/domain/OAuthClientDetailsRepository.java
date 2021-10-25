package kr.co.kumsung.oauth2provider.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthClientDetailsRepository extends JpaRepository<OAuthClientDetails, String> {
}
