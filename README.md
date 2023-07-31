# Spring Boot API

## 🚀 프로젝트 기본 정보
- Spring Boot 2.7.12
- JDK : open jdk 11
- DB : mariaDB

## 🚀 프로젝트 패키지 기본 구조
```js
com.api.app
    ├ common  // 설정 
    │   └ config
    └ online  // API 
        ├ common  // 공통
        │   ├ controller
        │   │   └ CommomController.java
        │   └ vo
        │      └ DefaultResponse.java
        ├ notice // 게시판
        │   ├ controller
        │   │   └ NoticeController.java
        │   ├ mapper
        │   │   └ NoticeMapper.java  // 매퍼 인터페이스
        │   ├ service
        │   │   └ NoticeService.java
        │   │   └ NoticeServiceImpl.java
        └ ...
// mapper
src/main/resources
    └ mapper
        ├ notice 
        │   └ NoticeMapper_SQL.xml  
        └ ...
```
## 🚀 기본 응답 데이터 객체 (DefaultResponse)
- 응답 코드 (code)
- 응답 메세지 (msg)
- 응답 데이터 (result)

 ## 🚀 에러코드 
- 400 : You check the required fields (필수 필드 입력 체크 필요)
- 401 : You entered incorrect values (맞지 않는 데이터 입력)
- 402 : You check the file (name or size) (파일 이름, 크기 체크 필요)
- 403 : content does not exist (해당 데이터 조회 불가)

---