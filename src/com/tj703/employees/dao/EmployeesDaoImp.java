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

public class EmployeesDaoImp implements CRUD<EmployeesDto,Integer>, EmployeesDao {
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
        empList=new ArrayList<>();
        while(rs.next()){
            //10001
            //10001
            int empNo=rs.getInt("emp_no");
            boolean exist=false;
            EmployeesDto emp=null;
            for(EmployeesDto dto:empList){
                if(dto.getEmpNo()==empNo){
                    emp=dto;
                    exist=true;
                    break; //반복문을 종료
                }
            }
            List<SalariesDto> salariesList=null;
            if(!exist){//해당 객체의최초 등록
                emp=new EmployeesDto();
                emp.setEmpNo(empNo);
                emp.setBirthDate(rs.getDate("birth_date"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setGender((char) rs.getByte("gender"));
                emp.setHireDate(rs.getDate("hire_date"));
                salariesList=new ArrayList<>();
                emp.setSalariesList(salariesList);
                empList.add(emp);
            }else{
                salariesList=emp.getSalariesList();
            }
            SalariesDto sal=new SalariesDto();
            sal.setEmpNo(empNo);
            sal.setSalary(rs.getInt("salary"));
            sal.setFromDate(rs.getDate("from_date"));
            sal.setToDate(rs.getDate("to_date"));
            salariesList.add(sal);
        }

        return empList;
    }

    @Override
    public List<EmployeesDto> findWithSalary(int start, int size, String orderBy) throws Exception {
        List<EmployeesDto> empList=null;
        String sql="SELECT * FROM employees NATURAL JOIN salaries ORDER BY ? LIMIT ?,?";
        ps=conn.prepareStatement(sql);
        ps.setString(1,orderBy);
        ps.setInt(2,start);
        ps.setInt(3,size);
        rs=ps.executeQuery();
        empList=new ArrayList<>();
        while(rs.next()){
            EmployeesDto emp=new EmployeesDto();
            emp.setEmpNo(rs.getInt("emp_no"));
            emp.setBirthDate(rs.getDate("birth_date"));
            emp.setFirstName(rs.getString("first_name"));
            emp.setLastName(rs.getString("last_name"));
            emp.setGender((char) rs.getByte("gender"));
            emp.setHireDate(rs.getDate("hire_date"));
            SalariesDto sal=new SalariesDto();
            sal.setEmpNo(rs.getInt("emp_no"));
            sal.setSalary(rs.getInt("salary"));
            sal.setFromDate(rs.getDate("from_date"));
            sal.setToDate(rs.getDate("to_date"));
            emp.setSalary(sal);
            empList.add(emp);
        }
        return empList;
    }

    @Override
    public List<EmployeesDto> findWithDept(int start, int size, String orderBy) throws Exception {
        List<EmployeesDto> empList=null;
        String sql=
                "SELECT e.*,d.* FROM employees e " +
                    "NATURAL JOIN dept_emp de " +
                    "NATURAL JOIN departments d " +
                    "ORDER BY ? LIMIT ?,?";
        ps=conn.prepareStatement(sql);
        ps.setString(1,orderBy);
        ps.setInt(2,start);
        ps.setInt(3,size);
        rs=ps.executeQuery();
        empList=new ArrayList<>();
        while(rs.next()){
            EmployeesDto emp=new EmployeesDto();
            emp.setEmpNo(rs.getInt("emp_no"));
            emp.setBirthDate(rs.getDate("birth_date"));
            emp.setFirstName(rs.getString("first_name"));
            emp.setLastName(rs.getString("last_name"));
            emp.setGender((char) rs.getByte("gender"));
            emp.setHireDate(rs.getDate("hire_date"));
            DepartmentsDto dept=new DepartmentsDto();
            dept.setDeptNo(rs.getString("dept_no"));
            dept.setDeptName(rs.getString("dept_name"));
            emp.setDepartment(dept);
            empList.add(emp);
        }
        return empList;
    }

    @Override
    public List<EmployeesDto> findWithDeptAndSalary(int start, int size, String orderBy) throws Exception {
        List<EmployeesDto> empList=null;
        //
        return empList;
    }

    public static void main(String[] args) throws Exception {
        //jdbc
        EmployeesDaoImp dao=new EmployeesDaoImp();
        //System.out.println(dao.findById(10002));
        //System.out.println(dao.findAll());
        //System.out.println(dao.findWithSalaries(0,50));
        //System.out.println(dao.findWithSalary(0,10,"emp_no"));
        System.out.println(dao.findWithDept(0,100,"dept_no"));

    }
}
