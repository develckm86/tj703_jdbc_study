package com.tj708.jdbc;

import java.sql.*;

public class L07AutoClose {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/employees";
        String user="root";
        String password="mysqlmysql";
        String driverClass="com.mysql.cj.jdbc.Driver";
        String sql = "select * from employees";
        //try( 접속 객체 생성 ){}catch(){} : try 가 종료되면 소괄호 안에서 만든 객체를 자동으로 close 한다.
        try(
                Connection conn=DriverManager.getConnection(url,user,password);
                PreparedStatement ps=conn.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                //String  str="경민"; //AutoClosable(close 함수를 가진 타입) 객체만 생성가능
            )
        {
            Class.forName(driverClass);
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
