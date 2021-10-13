# 인가 코드 Grant Type

1. 요청
   URL : http://localhost:8080/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:9000/callback&response_type=code&scope=read_profile
    - URL : http://localhost:8080/oauth/authorize
    - Parameter
        - client_id = clientapp : InMemory에 등록한 Client
        - redirect_uri = http://localhost:9000/callback : 요청 후 리다이렉트할 주소
        - response_type = code : 응답 종류. 코드 응답.
        - scope = read_profile : 요청 범위. OAuth2AuthorizationServer에 등록한 범위 중 선택
        - 
2. 응답 URL : [콜백URL]?code=[CODE]
   - [콜백URL] : InMemory에 등록한 콜백 URL
   - [CODE] : Token 요청에 필요한 Code
   
3. Token 요청
    ```shell
    $ curl -X POST -u [클라이언트이름]:[클라이언트Secret] [서버주소]/oauth/token -H "content-type: application/x-www-form-urlencoded" -d "code=[CODE]&grant_type=authorization_code&redirect_uri=[콜백URL]&scope=read_profile"
    ```
    - [클라이언트이름] & [클라이언트Secret] : OAuth2AuthorizationServer에 등록한 정보
    - [서버주소] : 인가서버 주소(포트 포함)
    - [CODE] : 2번 응답에서 받은 코드
    - [콜백URL] : 1, 2번과 동일한 콜백URL.

4. Token 응답(아래 데이터는 예제값)
   ```json
   {
     "access_token": "4e1dc56b-896a-40f6-9776-21a578057673",
     "token_type": "bearer",
     "expires_in": 43199,
     "scope": "read_profile"
   }
   ```

5. 토큰으로 리소스 접근 요청
    ```shell
    $ curl -X GET [서버주소]/[자원경로] -H "authorization: Bearer [TOKEN]"
    ```
    - [서버주소]/[자원경로] : 접근하려는 최종 주소이다.
    - [TOKEN] : 3번에서 받은 토큰.