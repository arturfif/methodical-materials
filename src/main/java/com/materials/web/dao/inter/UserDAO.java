package com.materials.web.dao.inter;

import com.materials.web.model.User;

import java.util.List;

public interface UserDAO {

    public void save(User user);

    public void delete(long personId);

    public User get(long personId);

    public User get(String username);

    public List list();

}
