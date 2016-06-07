package com.materials.web.dao.inter;

import com.materials.web.model.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student student);

    Student get(long id);

    List<Student> list();

    List<String> listBySpecialtyId(long id);

}
