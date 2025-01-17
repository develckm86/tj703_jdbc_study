package com.tj708.jdbc;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class DeptEmpDao{
    //DB Access Object  DAO
    private String url="jdbc:mysql://localhost:3306/employees";
    private String user="root";
    private String password="mysqlmysql";
    private String driverClass="com.mysql.cj.jdbc.Driver";
    private Connection conn;
    private PreparedStatement ps;
    //Statement: 쿼리를 실행하는 객체
    //PreparedStatement: 쿼리를 실행하기 위해 파리미터 준비를 도와 주는 객체
    private ResultSet rs;
    public String[][] findDeptEmp(int start, int size) throws Exception{
        //limit start,size
        String[][] findDeptEmp=null; //{{"d007",10001,"최경민","dev"},{"d006",10003,"최둘리","re"}}
        findDeptEmp=new String[size][];
        //String sql="select * from dept_emp limit "+start+","+size;
        String sql=
                "select * " +
                        "from dept_emp de INNER JOIN departments d  " +
                        "ON de.dept_no=d.dept_no" +
                        " limit ?,?";

        Class.forName(driverClass);
        conn= DriverManager.getConnection(url,user,password);
        //conn.createStatement().executeQuery("select * from employees");
        ps=conn.prepareStatement(sql);
        ps.setInt(1,start); // 첫번째 ? <=start
        ps.setInt(2,size);  // 두번째 ? <=size
        rs=ps.executeQuery();
        int i=0;
        while(rs.next()){
            int empNo=rs.getInt("emp_no");
            String deptNo=rs.getString("dept_no");
            String deptName=rs.getString("dept_name");
            String [] arr={ Integer.toString(empNo), deptNo, deptName };
            findDeptEmp[i++]=arr;
        }
        return findDeptEmp;
    }
}
public class L05PreparedStatement {
    public static void main(String[] args) {
        DeptEmpDao dao=new DeptEmpDao();
        try {
            String data [][]=dao.findDeptEmp(0,10);
            JFrame frame=new JFrame();

            JTable table=new JTable(data,new String[]{"부서번호","사원번호","부서이름"});
            JScrollPane jsp=new JScrollPane(table);
            JPanel panel=new JPanel();
            panel.add(jsp);
            frame.add(panel, BorderLayout.CENTER);
            frame.setBounds(100, 100, 450, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
