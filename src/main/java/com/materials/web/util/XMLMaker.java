package com.materials.web.util;


import com.materials.web.model.Author;
import com.materials.web.model.Document;
import com.materials.web.model.Specialty;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class XMLMaker {


    public static void buildXMLFile(List<Document> documentList, File file) throws JAXBException, IOException {
        Element rootElement = new Element("library");
        org.jdom2.Document jDomDocument = new org.jdom2.Document(rootElement);

        List<Element> elementList = documentListToElementList(documentList);
        elementList.forEach(rootElement::addContent);

        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(jDomDocument, new FileWriter(file));

    }

    private static Element documentToElement(Document document) throws JAXBException {

        Element documentElement = new Element("document");

        documentElement.setAttribute("id", String.valueOf(document.getId()));

        Element libraryKey = new Element("library_key");
        if(document.getLibraryKey() != null) {
            libraryKey.setText(String.valueOf(document.getLibraryKey()));
        }
        Element name = new Element("name");
        name.setText(document.getName());

        Element department = new Element("department");
        department.setText(document.getDepartment().getName());

        Element publishingYear = new Element("publishing_year");
        publishingYear.setText(String.valueOf(document.getPublishingYear()));

        Element uploadDate = new Element("upload_date");
        uploadDate.setText(document.getUploadDate().toString());

        Element specialties = specialtiesElement(document.getSpecialtySet());

        Element authors = authorsElement(document.getAuthorSet());

        documentElement.addContent(libraryKey);
        documentElement.addContent(name);
        documentElement.addContent(department);
        documentElement.addContent(publishingYear);
        documentElement.addContent(uploadDate);
        documentElement.addContent(specialties);
        documentElement.addContent(authors);

        return documentElement;
    }

    private static Element specialtiesElement(Set<Specialty> specialtySet) {
        Element specialties = new Element("specialties");
        for (Specialty specialty : specialtySet) {
            Element specialtyElement = new Element("specialty");
            specialtyElement.setText(specialty.getName());
            specialties.addContent(specialtyElement);
        }
        return specialties;
    }

    private static Element authorsElement(Set<Author> authorSet) {
        Element authors = new Element("authors");
        for (Author author : authorSet) {
            Element authorElement = new Element("author");

            Element surname = new Element("surname");
            surname.setText(author.getSurname());

            Element name = new Element("name");
            name.setText(author.getName());

            Element patronymic = new Element("patronymic");
            patronymic.setText(author.getPatronymic());

            authorElement.addContent(surname);
            authorElement.addContent(name);
            authorElement.addContent(patronymic);

            authors.addContent(authorElement);
        }
        return authors;
    }

    private static List<Element> documentListToElementList(List<Document> documentList) throws JAXBException {
        List<Element> elementList = new ArrayList<>();
        for (Document document : documentList) {
            elementList.add(documentToElement(document));
        }
        return elementList;
    }


}
