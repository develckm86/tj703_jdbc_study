package com.tj703.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DepartmentDao {
    //static(정적) : 메소드 메모리 영역에 저장되어서 사용하지 않는 객체도 정리하지 않음.
    //none static(==heap 동적) : 모든 객체는 heap 메모리에 저장되고 사용하지 않는 객체는 GC가 정리.
    private static Connection conn;
    private static String user="root";
    private static String password="mysqlmysql";
    private static String url="jdbc:mysql://localhost:3306/employees";
    public static Connection getConn() throws Exception {
        //디자인패턴 : 코드의 구조를 디자인해서 기능을 추가하는 것
        //싱글톤패턴 : 객체를 한번 만들고 재사용하는 형태의 디자인 패턴
        if(conn!=null  && !conn.isClosed()){ return conn; }
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn= DriverManager.getConnection(url,user,password);
        return conn;
    }
    //DB 접속해서 하는 일 4가지 읽고 read, 쓰고 create ,수정하고 update, 삭제 delete CRUD
    //DataTransferObject ==(유사 get,set) Beans
    //Beans : 생성된 데이터를 처리하는 목적 (코드가 복잡 get(if),set,toString,equals,.....)
    //Dto : 다른 서버(db)에 존재하는 데이터를 java 의 데이터 타입으로 변경후 전송하기 위한 목적의 객체(get,set,toString)
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
            return "{" +
                    "deptNo:'" + deptNo + '\'' +
                    ", deptName'" + deptName + '\'' +
                    "}\n";
        }
    }//dto : Data Transfer Object 데이터 전송시 타입을 명확하게 하는 용도로 사용
    PreparedStatement ps=null;
    ResultSet rs=null;

    public List<DepartmentDto> findAll() throws Exception {
        String sql="select * from departments order by dept_no";
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
    //pk로 한개 read : (findByID, findOne,findByPk), findByDeptNo
    public DepartmentDto findByID(String deptNo) throws Exception {
        String sql="select * from departments WHERE dept_no=? order by dept_no DESC";
        DepartmentDto findID=null;
        conn=getConn();
        ps=conn.prepareStatement(sql);
        ps.setString(1,deptNo);
        rs=ps.executeQuery();

        if(rs.next()){
            findID=new DepartmentDto();
            findID.setDetpNo(rs.getString("dept_no"));
            findID.setDeptName(rs.getString("dept_name"));
        }
        return findID;
    }

    public int create(DepartmentDto departmentDto) throws Exception {
        int create=0;
        String sql="insert into departments (dept_no,dept_name) values(?,?)";
        conn=getConn();
        ps=conn.prepareStatement(sql);
        ps.setString(1,departmentDto.getDeptNo());
        ps.setString(2,departmentDto.getDeptName());
        create=ps.executeUpdate(); //executeUpdate : dml (insert, update, delete)
        return create;
    }
    public int update(DepartmentDto departmentDto) throws Exception {
        int update=0;
        String sql="update departments set dept_name=? where dept_no=?";
        conn=getConn();
        ps=conn.prepareStatement(sql);
        ps.setString(1,departmentDto.getDeptName());
        ps.setString(2,departmentDto.getDeptNo());
        update=ps.executeUpdate();
        return update;
    }
    public int delete(String deptNo) throws Exception {
        int delete=0;
        String sql="delete from departments where dept_no=?";
        conn=getConn();
        ps=conn.prepareStatement(sql);
        ps.setString(1,deptNo);
        delete=ps.executeUpdate();
        return delete;
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
            //만들어서 조회
            DepartmentDao.DepartmentDto dto=dao.new DepartmentDto();
            dto.setDeptName("더좋은컴퓨터아카데미");
            dto.setDetpNo("d010");
            int create=dao.create(dto);
            System.out.println(dao.findByID("d010"));

            //수정하고 조회
            dto.setDeptName("tj703교육장");
            System.out.println(dao.update(dto));
            System.out.println(dao.findByID("d010"));

            //삭제하고 전체 조회
            System.out.println(dao.delete("d010"));
            System.out.println(dao.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
