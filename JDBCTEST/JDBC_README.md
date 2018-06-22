# JDBC testcase

## 1. 설명

JDBC 테스트케이스가 늘어남에 따라, 각 메소드(클래스)만을 간단하게 구현한 java 코드들을 저장해 놓았다.

대다수 코드가 내부 서버 환경으로 만들어져 있지만, 호스트와 포트만 수정하면 바로 사용이 가능한 수준이다.(골디락스가 아닌 타 DBMS에도 몇몇 메소드를 제외하고 사용 가능하다.)

JDBC에 대한 개념이 잘 이해되지 않거나, 매뉴얼만으로 사용에 어려움을 느낄 때 참고하면 좋다.


## 2. 접속 방법

createConnectionByDriverManager  
createConnectionByDataSource

두 가지 방법으로 접속이 가능하다. 단, createConnectionByDataSource를 사용할 경우 환경에 따라 Class.forName(GOLDILOCKS_DRIVER_CLASS); 를 추가해 줘야 할 수도 있다.
