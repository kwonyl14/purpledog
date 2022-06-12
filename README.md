# Description

백엔드 엔지니어 채용 과제입니다. 현재는 DB 서버 운영 종료로 인해 바로 작동은 되지 않습니다.
주요 기능 : 회원 가입, 수정, 삭제, 조회 api
사용 기술 스택 : Spring Boot, JPA

# Getting Started

### Version Info

Spring Boot 2.6.4  

JDK 1.8

gradle 7.4.1  

mysql 8.0.28  



### Prerequisite

JDK 1.8 이상 설치되어 있어야 합니다.

DB는 개인 EC2에 실행중인 mysql 서버로 연결이 되어있어 바로 실행 가능합니다.  

정정사항 : DB정보가 바뀌어 더이상 실행되지 않습니다. application.properties 파일 내에 DB정보를 개인 로컬 DB 정보로 바꾸어주면 작동가능합니다.



실행 후 스웨거를 통해 API 문서를 확인할 수 있습니다.   

**스웨거 주소 :**  http://localhost:8080/swagger-ui/



### Note

ddl-auto=create 옵션에 의해 웹서버를 실행 할 때마다 DB가 초기화 됩니다.  



