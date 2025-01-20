package com.tj703.employees.dao;

import com.tj703.employees.EmployeesDB;
import com.tj703.employees.dto.DetpartmentsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartmentsDaoImp implements DepartmentsDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    //함수에서 매번 커넥션을 호출하는 것 보다 생성할때 한번 호출하는 것 이 더 좋다.
    public DepartmentsDaoImp() throws Exception {
        conn= EmployeesDB.getConnection();
    }

    @Override
    public DetpartmentsDto findByDeptName(String deptName) throws Exception {
        DetpartmentsDto dto=null;
        String sql="select * from departments";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        if(rs.next()){
            dto=new DetpartmentsDto();
            dto.setDeptNo(rs.getString("dept_no"));
            dto.setDeptName(rs.getString("dept_name"));
        }
        return dto;
    }

    @Override
    public DetpartmentsDto findByDeptNameLike(String deptName) throws Exception {
        return null;
    }

    @Override
    public List<DetpartmentsDto> findAll() throws Exception {
        List<DetpartmentsDto> findAll=null;
        String sql="select * from departments";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        findAll=new ArrayList<DetpartmentsDto>();
        while(rs.next()){
            DetpartmentsDto dto=new DetpartmentsDto();
            dto.setDeptNo(rs.getString("dept_no"));
            dto.setDeptName(rs.getString("dept_name"));
            findAll.add(dto);
        }
        return findAll;
    }

    @Override
    public DetpartmentsDto findById(int id) throws Exception {
        return null;
    }

    @Override
    public int create(DetpartmentsDto obj) throws Exception {
        return 0;
    }

    @Override
    public int update(DetpartmentsDto obj) throws Exception {
        return 0;
    }

    @Override
    public int delete(int id) throws Exception {
        return 0;
    }
    public void close(){
        try {
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            if(conn!=null)conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DepartmentsDaoImp dao=null;
        try {
            dao=new DepartmentsDaoImp();
            System.out.println(dao.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            dao.close();
        }
    }

}
