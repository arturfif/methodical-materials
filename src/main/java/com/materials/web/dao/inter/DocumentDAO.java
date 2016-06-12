package com.materials.web.dao.inter;

import com.materials.web.model.Document;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DocumentDAO {

    Document get(long id);

    Document getByLibraryKey(long libraryKey);

    void save(Document document);

    @Transactional
    void setCheckedStatus(long id);

    @Transactional
    void remove(long id);

    List<Document> list();

    List<Document> listChecked();

    List<Document> listByUserId(long id);

    List<Document> listUnchecked();

    List<Document> listByLibraryKey(int libraryKey);

    List<Document> listByName(String name);

    List<Document> listByDepartment(String departmentName);

    List<Document> listByPublishingYear(short publishingYear);

    List<Document> listByAuthorSurname(String surname);
}
