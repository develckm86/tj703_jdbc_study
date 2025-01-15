package com.tj708.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

class DetpEmp{
    private String url="jdbc:mysql://localhost:3306/employees";
    private String user="root";
    private String pwd="mysqlmysql";
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    //DQL => find
    //throws Exception : 이 함수를 사용하는 쪽에서 try 예외처리를 하라 (예외 위임)
    public String findAll() throws Exception{
        String findAll=null;
        String sql="select * from dept_emp limit 0,100";
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn= DriverManager.getConnection(url,user,pwd);
        stmt=conn.createStatement();
        rs=stmt.executeQuery(sql);
        //여기까지 실행되었다는 것은 db에 문제 없이 접속 및 쿼리 실행을 했다.
        findAll="";
        System.out.println(rs);
        while(rs.next()){
            int empNo=rs.getInt("emp_no");
            String deptNo=rs.getString("dept_no");
            Date fromDate=rs.getDate("from_date");
            Date toDate=rs.getDate("to_date");
            findAll+=empNo+","+deptNo+","+fromDate+","+toDate+"\n";
            //문자열 더하기는 메모리를 많이 사용
        }
        return findAll;
    }
    public String findDept(int limit)throws Exception{
        String findDept=null;
        String sql="select * from dept_emp NATURAL JOIN departments limit 0,"+limit;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn= DriverManager.getConnection(url,user,pwd);
        stmt=conn.createStatement();
        rs=stmt.executeQuery(sql);
        while(rs.next()){
            int empNo=rs.getInt("emp_no");
            String deptNo=rs.getString("dept_no");
            Date fromDate=rs.getDate("from_date");
            Date toDate=rs.getDate("to_date");
            String deptName=rs.getString("dept_name");
            findDept+=empNo+","+deptNo+","+deptName+","+fromDate+","+toDate+"\n";
            //문자열 더하기는 메모리를 많이 사용
        }
        return findDept;
    }
    //findDeptEmp   구현~
}
public class L03JoinJtable {
    public static void main(String[] args) {
        DetpEmp detpEmp=new DetpEmp();
        try {
            //System.out.println(detpEmp.findAll());
            System.out.println(detpEmp.findDept(20));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
