# 암시적 Grant Type

1. 요청 URL : `서버URL`/oauth/authorize?client_id=`CLIENT_ID`&redirect_uri=`REDIRECT_URI`&response_type=`RESPONSE_TYPE`&scope=`SCOPE`&state=`STATE`
    - Parameter
      - `CLIENT_ID` : 등록된 CLIENT ID. 예제에서는 InMemory에 등록된 클라이언트 정보
      - `REDIRECT_URI` : 응답을 보낼 리다이랙트 URI
      - `RESPONSE_TYPE` : 응답 유형. 암시적 Grant Type에서는 값으로 `token`으로 사용.
      - `SCOPE` : 인가 범위. 여기서는 AuthorizationServer에 등록한 범위 중 `read_profile`으로 선택. 
      - `STATE` : 인가 end-point로 리다이렉트 될 때 전달될 값.

2. 응답 URL : `콜백URL`#access_token=`TOKEN`&token_type=`TOKEN_TYPE`&state=`STATE`&expires_in=`EXPIRES_IN`
   - `콜백URL` : InMemory에 등록한 콜백 URL
   - `TOKEN` : Token 요청에 필요한 Code
   - `TOKEN_TYPE` : 토큰 인증 타입. 기본값으로 `bearer`
   - `STATE` : 요청 URL에 전달된 값
   - `EXPIRES_IN` : 토큰 만료기간
   
3. 토큰으로 리소스 접근 요청
    ```shell
    $ curl -X GET `서버주소`/`자원경로` -H "authorization: Bearer `TOKEN`"
    ```
   - `서버주소`/`자원경로` : 접근하려는 최종 주소이다.
   - `TOKEN` : 2번에서 받은 토큰.