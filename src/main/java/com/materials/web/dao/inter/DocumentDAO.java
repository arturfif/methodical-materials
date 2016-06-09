package com.materials.web.dao.inter;

import com.materials.web.model.Document;

import java.util.List;

public interface DocumentDAO {

    Document get(long id);

    Document getByLibraryKey(int libraryKey);

    void save(Document document);

    List<Document> list();

    List<Document> listChecked();

    List<Document> listByUserId(long id);

    List<Document> listUnchecked();
}
