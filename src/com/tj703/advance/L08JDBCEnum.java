package com.tj703.advance;
//java.sql.* jdbc package : java에서 제공하는 db 접속 클래스 제공
import com.tj703.employees.dto.EmployeesDto;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//import com.mysql.cj.jdbc.Driver;

public class L08JDBCEnum {
    enum EmployeeField{
        EMP_NO,FIRST_NAME,LAST_NAME,BIRTH_DATE,HIRE_DATE,GENDER;

        public static final String empNo = "EMP_NO"; //상수
    }
    enum Direct{
        DESC,ASC
    }
    public static void  enumTest(EmployeeField field){
        System.out.println(field);
    }
    public static List<EmployeesDto> findEmpByGender(String gender, EmployeeField order, Direct direct) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "mysqlmysql";
        DriverManager driverManager = null;
        Connection conn = null;
        //String sql="select * from employees where gender='"+gender+"' limit 0,10"; //질의어 => table(ResultSet)
        String sql="select * from employees where gender=? ORDER BY "+order+" "+direct+" limit 0,10"; //질의어 => table(ResultSet)
        //ORDER BY 다음에는 칼럼명을 사용해야하는데 칼럼명에 문자열을 표기하면 칼럼명이 아닌 것으로 인지합니다.
        //==>preparedStatement 사용불가


        Statement stmt = null;
        PreparedStatement pstmt = null;

        ResultSet rs = null;
        List<EmployeesDto> empList = null;
        System.out.println(sql);
        try{
            Class.forName(driver);
            conn= DriverManager.getConnection(url, username, password);
            //stmt = conn.createStatement();
            //rs = stmt.executeQuery(sql);
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,gender);
            rs=pstmt.executeQuery();
            empList = new ArrayList<>();
            while(rs.next()){
                EmployeesDto emp=new EmployeesDto();
                emp.setEmpNo(rs.getInt("emp_no"));
                emp.setBirthDate(rs.getDate("birth_date"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setGender((char) rs.getByte("gender"));
                emp.setHireDate(rs.getDate("hire_date"));
                empList.add(emp);
            }
            System.out.println(empList);
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
        return empList;
    }

    public static void main(String[] args) {
        System.out.println(3*2*Math.PI);
        //정보로 사용되는 상수 public(누구나 접근) static (처음 객체로 만들어지기 때문에 또 만들필요 없는) final(바뀌지 않는)
        //new Frame().add(new Label("아이디"),"East");
        //new Frame().add(new Label("아이디"),"Est");
        //문제가 있다면 컴파일 시 오류가 발생하는 것이 베스트다! => enum

        //enumTest("emp_no");
        //enumTest(13);
        enumTest(EmployeeField.EMP_NO);


        //System.out.println(findEmpByGender("m'; drop table employees; select date where 2024-01-01' "));
        //해킹 : sql injection 예방하기 위해서 preparedStatement 를 사용할 수 있다.
        //"m\'; drop table employees; select date where 2024-01-01\' "
        String s=""; //"" 자바에서 문자열을 생성하는 예약어
        //select * from emp where g='m'  : '' 문자열을 생성하는 예약어
        //select * from emp where g='\'m\'' :  \' -> '   문자
        System.out.println(findEmpByGender("F",EmployeeField.FIRST_NAME, Direct.DESC));
        //=>enum을 이용하면 더 안전하게 사용가능
    }
}
