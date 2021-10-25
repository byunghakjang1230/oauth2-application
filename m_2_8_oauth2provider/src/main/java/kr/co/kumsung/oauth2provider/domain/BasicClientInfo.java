package kr.co.kumsung.oauth2provider.domain;

public class BasicClientInfo {
    private String name;
    private String redirectUri;
    private String clientType;

    public BasicClientInfo() {

    }

    public BasicClientInfo(String name, String redirectUri, String clientType) {
        this.name = name;
        this.redirectUri = redirectUri;
        this.clientType = clientType;
    }

    public String getName() {
        return name;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getClientType() {
        return clientType;
    }

    public void setName(String name) {
        this.name = name;
    }

//    DTO로 사용되는 클래스의 경우 Setter를 꼭 만들어야 한다. 그래야 값 세팅이 된다.
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
}
