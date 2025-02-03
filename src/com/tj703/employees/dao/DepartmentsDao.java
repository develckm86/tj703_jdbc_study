package com.tj703.employees.dao;

import com.tj703.employees.CRUD;
import com.tj703.employees.dto.DepartmentsDto;
import com.tj703.employees.dto.DeptField;

import java.util.List;

public interface DepartmentsDao extends CRUD<DepartmentsDto,String> {
    DepartmentsDto findByDeptName(String deptName) throws Exception;
    List<DepartmentsDto> findByDeptNameLike(String deptName) throws Exception;
    List<DepartmentsDto> findAll(DeptField field, DeptField.Direct direct, int index, int size) throws Exception;
    //50분까지 정렬을 enum으로 구현하세요.
    //enum DeptField 와 enum DeptField.Direct 를 만드세요
    //DepartmentsDaoImp 에서 findAll(DeptField field, DeptField.Direct direct,int index, int size)을 구현하세요
    //findAll(DeptField field, DeptField.Direct direct,int index, int size) 을 실행하면 정렬이 되어야합니다!

    void close();
}
