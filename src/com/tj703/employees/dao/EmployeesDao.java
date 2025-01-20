package com.tj703.employees.dao;

import com.tj703.employees.CRUD;
import com.tj703.employees.EmployeesDB;
import com.tj703.employees.dto.EmployeesDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class EmployeesDao implements CRUD<EmployeesDto> {
    @Override
    public List<EmployeesDto> findAll() throws Exception {
        return List.of();
    }

    @Override
    public EmployeesDto findById(int id) throws Exception {
        EmployeesDto dto=null;
        String sql="select * from employees where emp_no=?";
        Connection conn= EmployeesDB.getConnection();
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            dto=new EmployeesDto();
            dto.setEmpNo(rs.getInt("emp_no"));
            dto.setBirthDate(rs.getDate("birth_date"));
            dto.setFirstName(rs.getString("first_name"));
            dto.setLastName(rs.getString("last_name"));
            dto.setGender((char) rs.getByte("gender"));
            //char 1byte 짜리 문자표 번호, byte 1byte 숫자 ('a'==97)
            dto.setHireDate(rs.getDate("hire_date"));
        }
        return dto;
    }

    @Override
    public int create(EmployeesDto obj) throws Exception {
        return 0;
    }

    @Override
    public int update(EmployeesDto obj) throws Exception {
        return 0;
    }

    @Override
    public int delete(int id) throws Exception {
        return 0;
    }

    public static void main(String[] args) throws Exception {
        //jdbc
        CRUD dao=new EmployeesDao();
        System.out.println(dao.findById(10002));
    }

}
