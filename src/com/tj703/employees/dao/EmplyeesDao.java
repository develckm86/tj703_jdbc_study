package com.tj703.employees.dao;

import com.tj703.employees.dto.EmployeesDto;

import java.util.List;

public interface EmplyeesDao {
    List<EmployeesDto> findWithSalaries(int start, int size) throws Exception;
}
