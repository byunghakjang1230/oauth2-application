# Redis를 이용한 토큰 저장
### 예제 환경 설정
- Redis는 토커 환경에서 실행한다.
  - 레디스 내려받기
  ```shell
  $ docker pull redis
  ```
  - 레디스 컨테이너 실행
  ```shell
  $ docker run -d -p 6379:6379/tcp --name redis -d redis
  ```
- Redis Server에 접근
```shell
$ docker exec -it redis bash
> redis-cli -h 0.0.0.0 -p 6379
0.0.0.0:6379> keys *
```