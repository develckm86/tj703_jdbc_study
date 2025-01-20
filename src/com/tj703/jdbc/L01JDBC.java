package com.tj703.jdbc;
//JDBC : java로 db 를 접속하는 방법, 클래스, 패키지, 라이브러리
//mysql-connector-j : 자바에서 db 에 접속할때 mysql db 접속에 필요한 패키지들
//java.sql  : JDBC 패키지 jdk
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import java.lang.*; //jvm이 실행될때 필요한 기본클래스들의 집합 자동으로 import 해서 사용
//Object,Integer(랩퍼클래스),String,Exception,[],System...
import java.util.*;
//개발자에게 유용한 클래스를 제공하는 패키지 List, ArrayList, Date, Map ....

public class L01JDBC {
    //jvm 을 호출해서 실행되는 실질적이 어플
    public static void main(String[] args){
        List list=new ArrayList();
        System.out.println("jdbc 시작");
        DriverManager driverManager = null;
        Connection conn=null;
        //네트워크상의 접속은 항상 오류를 동반 => 예외 처리
        //1.물리적(번개)으로 접속이 끊어지는 현상
        //2.시스템적인 문제
        // (주소를 찾지 못하거나, 데이터를 잘게 쪼개서 보낸 팻킷이 도착하지 않는 현상)
        //3.접속하려는 네트워크의 보안에 걸리는 일 (방화벽,비밀번호 오류)
        //localhost : 내컴퓨터에 켜져있는 서버에 접속
        //3306 : port 번호는 os 에서 프로그램에 제공하는 고유번호
        //localhost:3306 : 내컴퓨터에 설치된 mysql 서버프로그램
        //http:// or  jdbc:mysql://
        // 자바로 db 를 접속하는 통신규약을 사용하면서 mysql 에 접속하겠다.
        String user="root";
        //String pw="mysql";
        String pw="mysqlmysql";
        String url="jdbc:mysql://localhost:3306/employees";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //DriverManager 가 mysql 에 접속하려할 때 객체를 만들려고 미리 지정
            //=>동적 로딩
            conn=DriverManager.getConnection(url,user,pw);
            // java로 mysql에 접속
            System.out.println(conn);
            //com.mysql.cj.jdbc.ConnectionImpl@2a3b5b47
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
