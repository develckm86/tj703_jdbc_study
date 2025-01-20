package com.tj703.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class S01DeptSelect {
    //employees.departments 를 출력하세요.
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "mysqlmysql";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select * from departments";
        String str="";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String deptNo = rs.getString("dept_no");
                String deptName = rs.getString("dept_name");
                System.out.println(deptNo+"\t"+deptName);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
