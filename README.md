# otDB
##개요
인터넷db 기말프로젝트<br>
오라클 데이터베이스의 샘플 데이터를 JPA로 옮겨서 가상으로 존재하는 컴퓨터 부품 판매점의 재고 관리를 웹으로 구현한 것 입니다. <br>
<br>
이 저장소는 기존 저장소를 private 처리 후 다시 생성된 저장소 입니다. <br>


사번은 1~107 사이 아무거나, 비밀번호는 0 <br>
~~http://kimsungsu.iptime.org:3301/~~
<br>
~~침대 옆에서 힘들게 돌아가는 중고 노트북에 도커 컨테이너로 올린 서버라서 언제 뻗을지 몰라요...~~<br>
본가로 올라와서 오라클 클라우드 프리티어로 옮겼습니다. <br>
http://146.56.45.175:8080 <br>
db도 같은 서버에서 도커 mariadb로 돌아가는 중입니다.

##기술스택
* SpringBoot 2.4.5
* MariaDB
* Linux(ubuntu, Oracle Cloud)
* Docker
* Thymeleaf

##데이터베이스 다이어그램
[오라클 튜토리얼 페이지 참고](https://www.oracletutorial.com/getting-started/oracle-sample-database/)
<br><br>
![Oracle-Sample-Database](https://user-images.githubusercontent.com/61815697/155689295-c9a21430-4891-416a-979f-e6f240e0c595.png)
