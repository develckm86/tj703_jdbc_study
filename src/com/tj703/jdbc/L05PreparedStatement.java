package com.tj703.jdbc;

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
    //PreparedStatement: 쿼리를 실행하기 위해 파리미터 준비를 도와 주는 객체,sql injection 해킹 을 방지
    //"select * form user user='경민' AND pw='1234'"
    //String sql="select * form user user="+user+" AND pw="+pw+";"
    //user="경민 or 1+1=2;" or ";drop table departments;"

    String sql="select * form user user=경민 OR 1+1=2; AND pw=1111;";
    String sql2="select * form user user=;drop table departments;";
    String sql3="select * form user user=';drop table departments;'";

    private ResultSet rs;
    public String[][] findDeptEmp(int start, int size) throws Exception{
        //limit start,size
        String[][] findDeptEmp=null; //{{"d007",10001,"최경민","dev"},{"d006",10003,"최둘리","re"}}
        findDeptEmp=new String[size][];
        //String sql="select * from dept_emp limit "+start+","+size;

        String sql= "select * " +
                        "from dept_emp de " +
                            "INNER JOIN departments d  " +
                            "INNER JOIN employees e " +
                        "ON de.dept_no=d.dept_no AND de.emp_no=e.emp_no" +
                        " ORDER BY de.emp_no"+
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
            String firstName=rs.getString("first_name");
            String lastName=rs.getString("last_name");
            String [] arr={ Integer.toString(empNo), deptNo, deptName ,firstName, lastName };
            findDeptEmp[i++]=arr;
        }
        return findDeptEmp;
    }
}
public class L05PreparedStatement {
    public static void main(String[] args) {
        DeptEmpDao dao=new DeptEmpDao();
        try {
            String data [][]=dao.findDeptEmp(0,1000);
            JFrame frame=new JFrame();
            JTable table=new JTable(data,new String[]{"부서번호","사원번호","부서이름","사원의이름","사원의성"});
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
