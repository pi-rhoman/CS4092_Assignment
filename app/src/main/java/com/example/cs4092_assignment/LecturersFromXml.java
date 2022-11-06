package com.example.cs4092_assignment;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class LecturersFromXml {
    private Context context;

    public LecturerIterator getLecturers() {
        return lecturers;
    }

    private LecturerIterator lecturers;

    public LecturersFromXml(Context context){
        this.context = context;

        // get document
        // make the input stream
        InputStream stream = context.getResources().openRawResource(R.raw.lecturers);
        DocumentBuilder docBuilder = null;
        Document xmlDoc = null;

        try {
            docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmlDoc = docBuilder.parse(stream);

        } catch (Exception e){
            Log.e("xml", String.format("failed to build xml document from file %s", e.getMessage()));
            // crash the app
            assert false;
        }

        // get the lecturers element
        Element lecturersElement = xmlDoc.getElementById("lecturers");

        Log.d("xml", String.valueOf(lecturersElement.getFirstChild().getNextSibling().getTextContent()));
        Log.d("xml", "test");

        // todo check if there are any lecturers

        lecturers = new LecturerIterator(lecturersElement);

    }

    private class LecturerIterator implements Iterator {
        Node current;

        // initialize pointer to head of the list for iteration
        public LecturerIterator(Element lecturers)
        {
            current = lecturers.getFirstChild();
        }

        // returns false if next element does not exist
        public boolean hasNext()
        {
            return current != null;
        }

        // return current data and update pointer
        public Lecturer next()
        {
            while (true) {
                current = current.getNextSibling();
                if (current == null){
                    return null;
                }
                // if the child is not a Lecturer skip it
                else if (current.getNodeType() != Node.ELEMENT_NODE) {
                    // node could be comment, text etc
                } else if (!Objects.equals(current.getNodeName(), "Lecturer")) {
                    // node is some other node than lecturer
                    Log.d("xml", String.format("unexpected element in Lecturers, skipping all nodes with tag %s", current.getNodeName()));
                } else {
                    // node is the next lecturer
                    break;
                }
            }
            return parseLecturer(current);
        }

        // implement if needed
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }



    Lecturer parseLecturer(Node lecturer) {
        // Loop through the children of the "Lecturer"
        String name = null, department=null, field=null;
        ArrayList<String> researchAreas=null;

        // Todo: check lecturer has attrs
        NodeList lecturerAttributes = lecturer.getChildNodes();
        for (int i = 0; i <= lecturerAttributes.getLength(); i++) {
            // get the name etc. from the xml

            Node attribute = lecturerAttributes.item(i);
            switch (attribute.getNodeName()) {
                case "Name":
                    name = attribute.getTextContent();
                    break;
                case "Department":
                    department = attribute.getTextContent();
                    break;
                case "field":
                    field = attribute.getTextContent();
                    break;
                case "ResearchAreas":
                    researchAreas = parseResearchAreas(attribute);
            }
        }
        // todo check we have all attributes
        return new Lecturer(name, department, field, researchAreas);
    }

    ArrayList<String> parseResearchAreas(Node attribute){
        ArrayList<String> ret = new ArrayList<String>();
        NodeList researchAreas = attribute.getChildNodes();
        for (int i = 0; i <= researchAreas.getLength(); i++) {
            Node researchArea = researchAreas.item(i);
            if (Objects.equals(researchArea.getNodeName(), "ResearchArea")){
                ret.add(researchArea.getNodeName());
            }
        }
        return ret;
    }



}
