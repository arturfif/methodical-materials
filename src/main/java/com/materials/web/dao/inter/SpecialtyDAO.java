package com.materials.web.dao.inter;

import com.materials.web.model.Specialty;

import java.util.List;

public interface SpecialtyDAO {

    void save(Specialty specialty);

    Specialty get(long id);

    Specialty get(String name);

    List<Specialty> list();

}
