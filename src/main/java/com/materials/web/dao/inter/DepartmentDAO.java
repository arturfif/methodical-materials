package com.materials.web.dao.inter;

import com.materials.web.model.Department;

import java.util.List;

public interface DepartmentDAO {

    void save(Department department);
    Department get(long id);
    Department get(String name);
    List<Department> list();


}
