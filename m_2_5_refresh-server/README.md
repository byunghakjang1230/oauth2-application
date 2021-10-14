# 리프레시 토큰 Grant Type
- authorization_code로 토큰을 일단 발급 받는다. 리플레시 토큰이 왔는지 확인.
- 시간이 지난 뒤 아래 요청.

```shell
$ curl -X GET [서버URL][자원경로] -H "Authorization: Bearer [토큰]"
```

- expired 에러 확인 후 리프레시 토큰으로 아래 요청.
```shell
$ curl -X POST -u [CLIENT_ID]:[CLIENT_SECRET] [서버URL]/oauth/token -H "content-type: application/x-www-form-urlencoded" -d "grant_type=refresh_token&refresh_token=[REFRESH_TOKEN]&scope=profile"
```
