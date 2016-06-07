package com.materials.web.dao.inter;

import com.materials.web.model.Faculty;

import java.util.List;

public interface FacultyDAO {

    void save(Faculty faculty);

    Faculty get(long id);

    Faculty get(String name);

    List<Faculty> list();
}
