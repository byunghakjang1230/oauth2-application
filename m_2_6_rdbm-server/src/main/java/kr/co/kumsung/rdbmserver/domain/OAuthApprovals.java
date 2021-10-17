package kr.co.kumsung.rdbmserver.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_approvals")
public class OAuthApprovals {
    @Id
    @Column(name = "userid")
    private String userId;
    @Column(name = "clientid")
    private String clientId;
    private String scope;
    @Column(length = 10)
    private String status;
    @Column(name = "expiresat")
    private LocalDateTime expiresAt;
    @Column(name = "lastmodifiedat")
    private LocalDateTime lastModifiedAt;

    protected OAuthApprovals() {
    }

    public OAuthApprovals(String userId, String clientId, String scope, String status, LocalDateTime expiresAt, LocalDateTime lastModifiedAt) {
        this.userId = userId;
        this.clientId = clientId;
        this.scope = scope;
        this.status = status;
        this.expiresAt = expiresAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
