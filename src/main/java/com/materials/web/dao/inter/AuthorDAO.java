package com.materials.web.dao.inter;

import com.materials.web.model.Author;
import com.materials.web.model.Document;

import java.util.List;

/**
 * Created by arturk on 04.06.2016.
 */
public interface AuthorDAO {

    Author get(long id);
    Author getByName(String name);
    Author getBySurname(String surname);
    Author getByPatronymic(String patronymic);
    void save(Author author);
    List<Author> list();

}
