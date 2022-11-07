package com.example.cs4092_assignment;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class LecturersFromXML {

    public static final String TAG = "LecturersFromXML";
    private Context context;

    /**
     *
     * @return an array of lecturers generated from the input file
     */
    public Lecturer[] getLecturers() {
        return lecturers;
    }

    private Lecturer[] lecturers;

    /**
     * Parse an xml file full with elements of the form
     *         <Lecturer>
     *             <Name>Graham Kirby</Name>
     *             <Department>Computer Science</Department>
     *             <Field>Cloud Computing</Field>
     *             <ResearchAreas>
     *                 <ResearchArea>Cognitive Science</ResearchArea>
     *                 <ResearchArea>Computational Linguistics</ResearchArea>
     *             </ResearchAreas>
     *             <Image>derek.png</Image>
     *             <Url>http://www.example.com</Url>
     *             <Bio>He is best known for his work in Case-Based Reasoning and Recommender Systems. For more information, see the pages that describe his Publications, his Research Interests, his Research Projects and his Research Recognition.
     *                 He moved to Cork to take up his Senior Lectureship in September 1997. Before that he spent eight years as a lecturer in the Department of Computer Science at the University of York. Over the years, his teaching has covered many areas of Computer Science and Artificial Intelligence.
     *                 Outside working hours, he can be found in pubs, in music venues, in book shops, walking up mountains, walking down mountains, in swimming pools, on aeroplanes to distant locations, and in transit between the aforementioned.
     *                 His favourite fish is the haddock.</Bio>
     *         </Lecturer>
     * And generate an array of lecturers accessible via getLecturers()
     * @param context the context of the application
     * @throws IOException if IO errors occur getting the xml file
     * @throws SAXException if parse errors occur parsing the xml file
     */
    public LecturersFromXML(Context context) throws IOException, SAXException {
        this.context = context;

        // make the input stream
        InputStream stream = context.getResources().openRawResource(R.raw.lecturers);
        DocumentBuilder docBuilder = null;
        Document xmlDoc = null;

        try {
            docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmlDoc = docBuilder.parse(stream);
        } catch (IOException | SAXException e){
            throw e;
        } catch (ParserConfigurationException ignored){};

        // slice xmlDoc
        NodeList nameList = xmlDoc.getElementsByTagName("Name");
        NodeList departmentList = xmlDoc.getElementsByTagName("Department");
        NodeList fieldList = xmlDoc.getElementsByTagName("Field");
        NodeList researchAreasList = xmlDoc.getElementsByTagName("ResearchAreas");
        NodeList bioList = xmlDoc.getElementsByTagName("Bio");
        NodeList imageList = xmlDoc.getElementsByTagName("Image");
        NodeList urlList = xmlDoc.getElementsByTagName("Url");

        // make sure all the lecturers have the right attributes
        for(NodeList l: new ArrayList<>(Arrays.asList(departmentList, fieldList, researchAreasList, imageList, urlList, bioList))) {
            if (nameList.getLength() != l.getLength()){
                Log.e(TAG, l.toString());
                assert false;
            }
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
            String bio = bioList.item(i).getFirstChild().getNodeValue();
            String image = imageList.item(i).getFirstChild().getNodeValue();

            String url = urlList.item(i).getFirstChild().getNodeValue();
            try{
                new URL(url).toURI();
            } catch (Exception e){
                url = null;
            }
            try{
                lecturers[i] = new Lecturer(context,
                        name,
                        department,
                        field,
                        image,
                        bio,
                        url,
                        researchAreas);
            } catch (MalformedURLException | URISyntaxException ignored) {}


            Log.d(TAG, lecturers[i].toString());
            Log.d(TAG, Arrays.toString(lecturers[i].getResearchAreas()));
        }

    }
}
