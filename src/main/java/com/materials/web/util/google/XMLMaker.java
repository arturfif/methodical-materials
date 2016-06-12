package com.materials.web.util.google;


import com.materials.web.model.Document;
import com.materials.web.model.enumeration.Status;
import org.jdom2.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.List;


public class XMLMaker {

    private static Element rootElement = new Element("documentList");

    public static Element documentToElement(Document document) throws JAXBException {

        return null;
    }

    public static List<Element> documentListToElementList(List<Document> documentList) {
        return null;
    }

    public static void main(String[] args) throws JAXBException {

        Document document = new Document();
        document.setId(11L);
        document.setStatus(Status.CHECKED);


        JAXBContext context = JAXBContext.newInstance(Document.class);

        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        m.marshal(document, System.out);
    }

}
