package com.tj703.employees.dao;

import com.tj703.employees.dto.EmployeesDto;

import java.util.List;

public interface EmployeesDao {
    //1:N One To Many
    //N:N Many To Many
    List<EmployeesDto> findWithSalaries(int start, int size) throws Exception;
    //findWithDepartment (join dept_emp join department)
    //emp.DepartmentDto
    //1:1 One To One : 1개 table을 2개로 분리한 경우인데 거의없다.

    //1:N 이지만 1:1인거 처럼출력
    List<EmployeesDto> findWithSalary(int start, int size, String orderBy) throws Exception;

    //N:1 Many To One : dept To emp
    List<EmployeesDto> findWithDept(int start,int size, String orderBy) throws Exception;

    List<EmployeesDto> findWithDeptAndSalary(int start,int size, String orderBy) throws Exception;

}
