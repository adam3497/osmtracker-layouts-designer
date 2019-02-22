package net.osmtracker.layoutsdesigner.utils;

import android.util.Log;

import org.w3c.dom.Document;

import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static net.osmtracker.layoutsdesigner.OsmtrackerLayoutsDesigner.Preferences.TAG;

public class XMLMaker {

    //object that represent the xml document
    Document document;

    public XMLMaker() throws ParserConfigurationException {

        try{
            //objects to create the xml file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();

        }
        catch (ParserConfigurationException perseException) {
            Log.d(TAG + "XMLMaker", perseException.getMessage());
            perseException.printStackTrace();
        }
    }

    public String convertToString() throws TransformerException {
        try{
            //create the transformer object
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            //create the string writer
            StringWriter writer = new StringWriter();
            //Our data source is the xml document
            DOMSource source = new DOMSource(document);
            //the result will storage in the writer object
            StreamResult result = new StreamResult(writer);

            //transform the xml document to a simple string without break lines
            transformer.transform(source, result);
            return writer.getBuffer().toString();

        }
        catch(TransformerException transformerExcep) {
            Log.e(TAG + "XMLMaker", "convertToString: " + transformerExcep.getMessage() );
            transformerExcep.printStackTrace();
            return null;
        }
    }

    public void writeFile(String layoutName){

        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            File file = new File(layoutName);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

    }

}
