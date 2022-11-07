package com.example.cs4092_assignment;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class LecturersFromXML {

    public static final String TAG = "LecturersFromXML";
    private Context context;

    public Lecturer[] getLecturer() {
        return lecturers;
    }

    private Lecturer[] lecturers;
    // todo comment this monstrisity
    public LecturersFromXML(Context context){
        this.context = context;

        // make the input stream
        InputStream stream = context.getResources().openRawResource(R.raw.lecturers);
        DocumentBuilder docBuilder = null;
        Document xmlDoc = null;

        try {
            docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmlDoc = docBuilder.parse(stream);
        } catch (Exception e){
            //todo
        }

        // slice xmlDoc
        NodeList nameList = xmlDoc.getElementsByTagName("Name");
        NodeList departmentList = xmlDoc.getElementsByTagName("Department");
        NodeList fieldList = xmlDoc.getElementsByTagName("Field");
        NodeList researchAreasList = xmlDoc.getElementsByTagName("ResearchAreas");
        NodeList imageList = xmlDoc.getElementsByTagName("Image");
        NodeList urlList = xmlDoc.getElementsByTagName("Url");

        // make sure all the lecturers have the right attributes
        for(NodeList l: new ArrayList<>(Arrays.asList(departmentList, fieldList, researchAreasList, imageList, urlList))) {
            assert nameList.getLength() == l.getLength();
        }

        lecturers = new Lecturer[nameList.getLength()];

        // populate people
        for(int i=0; i<lecturers.length; i++){
            String name = nameList.item(i).getFirstChild().getNodeValue();
            String department = departmentList.item(i).getFirstChild().getNodeValue();
            String field = fieldList.item(i).getFirstChild().getNodeValue();
            NodeList researchAreaNodes = researchAreasList.item(i).getChildNodes();
            ArrayList<String> researchAreasArrayList = new ArrayList<String>();
            for(int j=0; j<researchAreaNodes.getLength(); j++) {
                Node node_j = researchAreaNodes.item(j);
                if(node_j.getNodeType() == Node.ELEMENT_NODE){
                    if(Objects.equals(node_j.getNodeName(), "ResearchArea")){
                        researchAreasArrayList.add(node_j.getFirstChild().getNodeValue());
                    }
                }
            }
            String[] researchAreas = new String[researchAreasArrayList.size()];

            // ArrayList to Array Conversion
            for (int r = 0; r < researchAreasArrayList.size(); r++)
                researchAreas[r] = researchAreasArrayList.get(r);

            String image = imageList.item(i).getFirstChild().getNodeValue();
            String url = urlList.item(i).getFirstChild().getNodeValue();
            lecturers[i] = new Lecturer(name,
                    department,
                    field,
                    image,
                    url,
                    researchAreas);

            Log.d(TAG, lecturers[i].toString());
            Log.d(TAG, Arrays.toString(lecturers[i].getResearchAreas()));
        }

    }
}
