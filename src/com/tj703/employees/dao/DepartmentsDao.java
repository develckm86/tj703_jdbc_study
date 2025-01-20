package com.tj703.employees.dao;

import com.tj703.employees.CRUD;
import com.tj703.employees.dto.DetpartmentsDto;

public interface DepartmentsDao extends CRUD<DetpartmentsDto> {
    DetpartmentsDto findByDeptName(String deptName) throws Exception;
    DetpartmentsDto findByDeptNameLike(String deptName) throws Exception;
    void close();
}
