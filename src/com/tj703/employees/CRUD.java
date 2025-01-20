package com.tj703.employees;

import java.util.List;

public interface CRUD<T> {
    List<T> findAll() throws Exception;
    T findById(int id) throws Exception;
    int create(T obj) throws Exception;
    int update(T obj) throws Exception;
    int delete(int id) throws Exception;
}
