package com.tj703.employees.dao;

import com.tj703.employees.CRUD;
import com.tj703.employees.EmployeesDB;
import com.tj703.employees.dto.DepartmentsDto;
import com.tj703.employees.dto.EmployeesDto;
import com.tj703.employees.dto.SalariesDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeesDaoImp implements CRUD<EmployeesDto,Integer>,EmplyeesDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public EmployeesDaoImp() throws Exception {
        conn=EmployeesDB.getConnection();
    }
    @Override
    public List<EmployeesDto> findAll() throws Exception {
        List<EmployeesDto> empList=null;
        String sql="select * from employees limit 0,100";
        ps=conn.prepareStatement(sql);
        rs=ps.executeQuery();
        empList=new ArrayList<EmployeesDto>();
        while(rs.next()){
            EmployeesDto emp=new EmployeesDto();
            emp.setHireDate(rs.getDate("hire_date"));
            emp.setGender((char) rs.getByte("gender"));
            emp.setFirstName(rs.getString("first_name"));
            emp.setLastName(rs.getString("last_name"));
            emp.setEmpNo(rs.getInt("emp_no"));
            emp.setBirthDate(rs.getDate("birth_date"));
            empList.add(emp);
        }
        return empList;
    }

    @Override
    public EmployeesDto findById(Integer id) throws Exception {
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
    public int delete(Integer id) throws Exception {
        return 0;
    }

    //emp : sal = 1 : N
    //사원이 복수의 급여 정보를 가지고 있다.
    @Override
    public List<EmployeesDto> findWithSalaries(int start, int size) throws Exception{
        List<EmployeesDto> empList=null;
        String sql=
                "SELECT * FROM employees e " +
                    "INNER JOIN salaries s " +
                    "ON e.emp_no=s.emp_no " +
                    "LIMIT ?,?";
        ps=conn.prepareStatement(sql);
        ps.setInt(1,start);
        ps.setInt(2,size);
        rs=ps.executeQuery();
        empList=new ArrayList<>();//////////////////////////////////////
        while (rs.next()){
            int empNo=rs.getInt("emp_no");
            List<EmployeesDto> emps=empList.stream()
                    .filter(emp->emp.getEmpNo()==empNo)
                    .collect(Collectors.toList());
            int length=emps.size();
            EmployeesDto emp=null; //없으면 null
            List<SalariesDto> salList=null;
            if(length==0){
                emp=new EmployeesDto();
                emp.setHireDate(rs.getDate("hire_date"));
                emp.setGender((char) rs.getByte("gender"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setEmpNo(rs.getInt("emp_no"));
                emp.setBirthDate(rs.getDate("birth_date"));
                salList=new ArrayList<>();
                empList.add(emp);
            }else{
                emp=emps.get(0);
                salList=emp.getSalariesList();
            }
            SalariesDto sal=new SalariesDto();
            sal.setEmpNo(rs.getInt("emp_no"));
            sal.setSalary(rs.getInt("salary"));
            sal.setFromDate(rs.getDate("from_date"));
            sal.setToDate(rs.getDate("to_date"));
            salList.add(sal);
            emp.setSalariesList(salList);
        }
        return empList;
    }
    public static void main(String[] args) throws Exception {
        //jdbc
        EmployeesDaoImp dao=new EmployeesDaoImp();
        //System.out.println(dao.findById(10002));
        //System.out.println(dao.findAll());
        System.out.println(dao.findWithSalaries(0,50));
    }
}
