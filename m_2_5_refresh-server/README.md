# 리프레시 토큰 Grant Type

1. 요청 URL : `서버URL`/oauth/authorize?client_id=`CLIENT_ID`&redirect_uri=`REDIRECT_URI`&response_type=`RESPONSE_TYPE`
   ```text
    [서버URL]/oauth/authorize?client_id=[CLIENT_ID]&redirect_uri=[REDIRECT_URI]&response_type=[RESPONSE_TYPE]&scope=[SCOPE]
    ```
    - Parameter
        - `CLIENT_ID` : 등록된 CLIENT ID. 예제에서는 InMemory에 등록된 클라이언트 정보
        - `REDIRECT_URI` : 응답을 보낼 리다이랙트 URI
        - `RESPONSE_TYPE` : 응답 유형. 암시적 Grant Type에서는 값으로 `code`으로 사용.
        - `SCOPE` : 인가 범위. 여기서는 AuthorizationServer에 등록한 범위 중 `read_profile`으로 선택.

2. 응답 URL : `콜백URL`?code=`CODE`
    - `콜백URL` : 인가 서버에 등록한 콜백 URL
    - `CODE` : Token 요청에 필요한 Code

3. Token 요청
    ```shell
    $ curl -X POST -u [CLIENT_ID]:[CLIENT_SECRET] [서버URL]/oauth/token -H "content-type: application/x-www-form-urlencoded" -d "code=[CODE]&grant_type=[GRANT_TYPE]&redirect_uri=[콜백URL]&scope=[SCOPE]"
    ```
    - `CLIENT_ID` & `CLIENT_SECRET` : 인가 서버에 등록된 클라이언트 인증 정보
    - `서버URL` : 인가서버 주소(포트 포함)
    - `CODE` : 2번 응답에서 받은 코드
    - `GRANT_TYPE` : 인가 유형. 인가 코드 Grant Type에서는 `authorization_code`를 사용.
    - `콜백URL` : 1, 2번과 동일한 콜백URL.
    - `SCOPE` : 인가 범위. 여기서는 AuthorizationServer에 등록한 범위 중 `read_profile`으로 선택.

4. Token 응답(아래 데이터는 예제값)
   ```json
   {
     "access_token": "72666c1e-b254-479a-939a-512405b56832",
     "token_type": "bearer",
     "refresh_token": "4b517af6-096a-4728-b91e-691de3530f71",
     "expires_in": 119,
     "scope": "read_profile"
   }
   ```
---
5. 토큰으로 리소스 접근 요청
    ```shell
    $ curl -X GET `서버주소`/`자원경로` -H "authorization: Bearer `ACCESS_TOKEN`"
    ```
    - `서버주소`/`자원경로` : 접근하려는 최종 주소이다.
    - `ACCESS_TOKEN` : 5번에서 받은 토큰.

6. 토큰 유요기간 이후 다시 리소스 접근 요청
    - 5번 진행
    - 결과 확인 : 접근 토큰 만료 에러 확인.
    ```json
    {
      "error": "invalid_token",
      "error_description": "Access token expired: [ACCESS_TOKEN]"
    }
    ```
    - `ACCESS_TOKEN` : 4번에서 받은 접근 토큰
7. Refresh Token으로 새로운 Access Token 발급 요청
    - 발급 요청
        - `GRANT_TYPE` : 인가 유형. 이번에는 `refresh_token`을 값으로 사용.
        - `REFRESH_TOKEN` : 4번에서 받은 `refresh_token`을 셋팅.
        - `SCOPE` : `read_profile` 사용
    ```shell
    $ curl -X POST -u [CLIENT_ID]:[CLIENT_SECRET] [서버URL]/oauth/token -H "content-type: application/x-www-form-urlencoded" -d "grant_type=[GRANT_TYPE]&refresh_token=[REFRESH_TOKEN]&scope=[SCOPE]"
    ```
    - 요청 결과
   ```json
   {
     "access_token": "6ac1f26b-cf10-4148-b08c-04b569e29192",
     "token_type": "bearer",
     "refresh_token": "4b517af6-096a-4728-b91e-691de3530f71",
     "expires_in": 119,
     "scope": "read_profile"
   }
   ```
   - 4번과 같은 결과가 반환된다. 최초 Token 발급 이후에는 5~7번 과정이 반복된다.
