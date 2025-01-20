package com.tj703.employees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class EmployeesDB {
    private static final String URL="jdbc:mysql://localhost:3306/employees";
    private static final String USER="root";
    private static final String PASSWORD="mysqlmysql";
    private static final String DRIVER_CLASS="com.mysql.cj.jdbc.Driver";
    private static Connection connection;
    //**Connection 은 접속을 계속 유지하는 Connection pool 에 접합한 클래스가 아니다.


    public static Connection getConnection() throws Exception {
        if(connection!=null && !connection.isClosed()){ return connection; }


        //singleton pattern 에서 꼭 구현해야하는 코드
        Class.forName(DRIVER_CLASS);
        connection = DriverManager.getConnection(URL,USER,PASSWORD);
        return connection;
    }

    public static void main(String[] args) throws Exception {
        //test main :tdd 를 도와주는 도구 없이 main 에서 진행
        //test 를 하면서 개발을 진행 하는 것을  TDD Test Driven Development 라 부른다.
        Connection conn = EmployeesDB.getConnection();
        System.out.println(conn); //Connection test

    }
}
