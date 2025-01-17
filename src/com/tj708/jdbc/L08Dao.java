package com.tj708.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DepartmentDao {
    //static : 메소드 메모리 영역에 저장되어서 사용하지 않는 객체도 정리하지 않음.
    //none static : 모든 객체는 heap 메모리에 저장되고 사용하지 않는 객체는 GC가 정리.
    private static Connection conn;
    private static String user="root";
    private static String password="mysqlmysql";
    private static String url="jdbc:mysql://localhost:3306/employees";
    public static Connection getConn() throws Exception {
        if(conn!=null  && !conn.isClosed()){ return conn; }
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn= DriverManager.getConnection(url,user,password);
        return conn;
    }
    //DB 접속해서 하는 일 4가지 읽고 read, 쓰고 create ,수정하고 update, 삭제 delete CRUD
    class DepartmentDto { //==Beans
        //mysql에 저장된 데이터의 타입과 유사한 java을 골라서 정의 (ResultSet 에서 형변환 후 대입)
        //char(4),varchar(10) => String
        //mysql Date => java.util.Date
        private String deptNo;
        private String deptName;
        public String getDeptNo(){
            return this.deptNo;
        }
        public void setDetpNo(String deptNo){
            this.deptNo=deptNo;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        @Override
        public String toString() {
            return "DepartementDto{" +
                    "deptNo='" + deptNo + '\'' +
                    ", deptName='" + deptName + '\'' +
                    '}';
        }
    }//dto : Data Transfer Object 데이터 전송시 타입을 명확하게 하는 용도로 사용
    PreparedStatement ps=null;
    ResultSet rs=null;

    public List<DepartmentDto> findAll() throws Exception {
        String sql="select * from departments";
        List<DepartmentDto> findAll=null;
        conn=getConn();
        ps=conn.prepareStatement(sql);
        rs=ps.executeQuery();
        findAll=new ArrayList<>();
        while(rs.next()){
            DepartmentDto dto=new DepartmentDto();
            dto.setDetpNo(rs.getString("dept_no"));
            //String deptNo=rs.getString("deptNo");
            dto.setDeptName(rs.getString("dept_name"));
            findAll.add(dto);
        }
        return findAll;
    }
    public int create(DepartmentDto departmentDto) throws Exception {
        return 0;
    }
    public int update(DepartmentDto departmentDto) throws Exception {
        return 0;
    }
    public int delete(int dept_no) throws Exception {
        return 0;
    }
//    public Object[][] findAll(){
//        Object[][] findAll=null;
//        return findAll;
//    }
//    public int create(Object [] data){
//        int create=0;
//        return create;
//    }
//    public int update(Object [] data){
//        int update=0;
//        return update;
//    }
//    public int delete(int emp_no){
//        int delete=0;
//        return delete;
//    }
}

//deptDao
//empDao
public class L08Dao {
    public static void main(String[] args) {
        DepartmentDao dao=new DepartmentDao();
        try {
            System.out.println(dao.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
