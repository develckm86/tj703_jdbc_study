package com.tj708.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class L02Select {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/employees";
        String user = "root";
        String pw = "mysqlmysql";
        DriverManager  driverManager = null;
        Connection conn = null;
        Statement stmt = null; //쿼리를 실행하는 객체
        ResultSet rs = null; //db 에서 반환하는 table 데이터의 type
        String sql="select * from employees limit 0,10"; //DQL => ResultSet
        //insert update delete DML => 몇개 성공했다는 정수 반환
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pw);
            stmt=conn.createStatement(); //접속 객체게 쿼리를 실행할 준비
            rs=stmt.executeQuery(sql);
            System.out.println(rs);//com.mysql.cj.jdbc.result.ResultSetImpl@5bda8e08
            while(rs.next()){
                //int emp_no=rs.getInt(1);
                int emp_no=rs.getInt("emp_no");
                Date birth_date=rs.getDate("birth_date");
                String first_name=rs.getString("first_name");
                String last_name=rs.getString("last_name");
                String gender=rs.getString("gender");
                Date hire_date=rs.getDate("hire_date");
                String str=emp_no+"|"
                        +birth_date+"|"
                        +first_name+"|"
                        +last_name+"|"
                        +gender+"|"
                        +hire_date+"|";
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace(); //콘솔창에 오류 출력
        }
    }
}
