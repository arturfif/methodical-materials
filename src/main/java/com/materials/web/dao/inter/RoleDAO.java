package com.materials.web.dao.inter;

import com.materials.web.model.Role;

/**
 * Created by arturk on 03.06.2016.
 */
public interface RoleDAO {

    Role get(long id);

    Role get(String name);
}
