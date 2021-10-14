# 클라이언트 자격증명 Grant Type

## 개요
이번 자격증명은 클라이언트 서버에서 리소스 서버가 아닌 자체 리소스에 접근할 떄 사용한다. 간단하 설정 방법만 알아두자.

1. 요청 URL
    ```shell
    $ curl -X POST "[서버URL]/oauth/token" -u [CLIENT_ID]:[CLIENT_SECRET] -d "grant_type=[GRANT_TYPE]&scope=[SCOPE]"
    ```
    - `CLIENT_ID` & `CLIENT_SECRET` : 인가 서버에 등록된 클라이언트 인증 정보
    - `GRANT_TYPE` : 인가 유형. 클라이언트 자격증명 Grant Type에서는 `client_credentials`를 사용.
    - `SCOPE` : 인가 범위. 여기서는 AuthorizationServer에 등록한 범위 중 `admin`으로 선택.

2. Token 응답(아래 데이터는 예제값)
   ```json
   {
      "access_token": "1565d73b-b3f3-4865-af16-8b58ddf90519",
      "token_type": "bearer",
      "expires_in": 43199,
      "scope": "admin"
   }
   ```

3. 토큰으로 리소스 접근 요청
   ```shell
   $ curl -X GET [서버주소]/[자원경로] -H "Authorization: Bearer [TOKEN]"
   ```
    - `서버주소`/`자원경로` : 접근하려는 최종 주소이다.
    - `TOKEN` : 2번에서 받은 토큰.

4. OAuth2가 아닌 HTTP Basic Authentication으로 보호되는 자원 접근.

   - WebSecurityConfiguration 클래스를 보면 `/user` 경로는 `.httpBasic()` 메소드를 적용하여 기본 HTTP 인증으로 보호한다. 그렇기 때문에 OAuth2 인증 방식으로 해당 URL
     접근이 불가능하다. 아래는 위 조건에 대한 요청과 결과이다.

   ```shell
   curl -X GET "http://localhost:8083/user" -H "Authorization: Bearer 9fde1406-03db-4f59-977b-b20e9d459af2"
   ```

   ```json
   {
     "timestamp": "2021-10-14T14:42:49.910+00:00",
     "status": 401,
     "error": "Unauthorized",
     "path": "/user"
   }
   ```

   - 기본 HTTP 인증일 경우 아래와 같이 ID와 PW로 요청한다.
   ```shell
   $ curl -X GET "http://localhost:8083/user" -u test:123
   ```