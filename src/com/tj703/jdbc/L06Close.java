package com.tj703.jdbc;

import java.sql.*;

public class L06Close {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/employees";
        String user="root";
        String password="mysqlmysql";
        String driverClass="com.mysql.cj.jdbc.Driver";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //서버에 자바가 접속을 하면 서버는 자바의 접속 내역을 객체로 만들어서 유지하고 있다.
        //connection pool : 매번 새롭게 접속 객체를 만들지 않고 유지하는 기술
        //=> connection pool close()는 접속을 완전히 종료하지 않고 접속 객체만 반환

        //자바가 db에 접속해서 필요한 데이터를 호출해서 가져옴 =>
        // 자동으로 서버와 자바에서 접속을 끊고 객체를 삭제 x => 직접 작성
        //예) mysql 의 동시 접속이 150명이 가능하다 50명이 일이 끝났는데도 close 하지 않음 x3
        // mysql 에서 생성할 수 있는 접속 객체 150개를 모두 소모해서 아무도 접속할 수 없다.

        try{
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url,user,password);
            String sql = "select * from employees";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            //conn.close();
            //ps.close();
            //rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally { //오류가 발생하든 발생하지 않든간에 무조건 실행되는 블럭
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
