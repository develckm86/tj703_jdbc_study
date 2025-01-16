package com.tj708.jdbc;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class L04Jtable {
    public static void main(String[] args) {
        //jdbc : 자바로 db 서버에 접속
        //수업 : 자바로 내컴퓨터에 설치한 mysql db 서버에 접속
        //다른 서버 컴퓨터에 접속하는 수업을 하려면 방화벽 + 계정 권한
        //intelliJ에서 제공하는 데이터소스 : 수업내용과 동일하게 구현되어 있다.
        String url="jdbc:mysql://localhost:3306/employees";
        String username="root";
        String password="mysqlmysql";
        String driver="com.mysql.cj.jdbc.Driver";
        //jdbc(java.sql) class,package: 자바로 db 서버에 접속을 돕는 클래스
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        String sql="select * from departments";
        //빨간색은 오류가 아니고 스키마 선택후 자동완성하라는 뜻
        try {
            Class.forName(driver);
            //DriverManager : url 에 작성된 db에 접속하기 위해서 필요한 드라이버를
            //동적로딩 하는 jdbc 클래스 (사용자[DriverManager]가 필요할때 명시된 클래스를
            // 객체로 생성, 사용자에게 필요한 객체를 매개변수로 전달하는 것이 일반적 상황)
            conn=DriverManager.getConnection(url,username,password);
            //mysql 서버 프로그램에 접속하면 쿼리를 실행할 수 있는 객체를 따로 생성해야함
            //mysql 서버 프로그램에 동시 접속이 150명 정도 가능하지만 쿼리를 실행하는 것은 2000개 정도
            //쿼리 실행객체 => Statement
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);//SELECT
            //{{dept_no:"d6",dept_name:"b"},{dept_no:"d8",dept_name:"a"},...}
            //ResultSet 은 iterator 를 이용해서 자료를 반복 순회해야한다.
            //hasNext(): 이다음 자료가 있어?, next() : 포인터를 이동해서 자료를 출력
            //ResultSet.next() : 이 다음에 자료가 있으면 true + 포인터 이동후 자료출력
            //ResultSet.next() : 이 다음에 자료가 없으면 false + 포인터 이동 x
            //Set : 중복을 허용하지 않는 자료
            //String [][] data={};
            List<String[]> data=new ArrayList();
            while (rs.next()) {
               String deptNo=rs.getString("dept_no");
               String deptName=rs.getString("dept_name");
               System.out.println(deptNo+"\t"+deptName);
               String [] rowData={deptNo,deptName};
               data.add(rowData);
            }
            String [][] data2=data.toArray(new String[0][0]);
            String [] columns={"dept_no","dept_name"};
            JFrame frame=new JFrame("부서 리스트");
            JTable table=new JTable(data2,columns);
            JScrollPane jsp=new JScrollPane(table);
            JPanel p=new JPanel();
            p.add(jsp);
            frame.add(p);



            frame.setBounds(500,100,600,600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
