package net.osmtracker.layoutsdesigner.utils;

import android.os.Environment;
import android.util.Log;

import net.osmtracker.layoutsdesigner.OsmtrackerLayoutsDesigner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;

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

    private final String ROO_TAG = "layouts";
    private final String LAYOUT_SCREEN_TAG = "layout";
    private final String ROW_TAG = "row";
    private final String BUTTON_TAG = "button";

    //object that represent the xml document
    private Document document;
    private File file;
    private String layoutName;
    private ArrayList<ArrayList> screens;
    private int[] length;

    public XMLMaker(ArrayList<ArrayList> screens, String layoutName, int[] length) throws ParserConfigurationException {

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
        this.screens = screens;
        this.layoutName = layoutName;
        this.length = length;
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

    /**
     * To create the file and save it in the storage
     */
    public void writeFile(){

        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            String path = Environment.getExternalStorageDirectory() + OsmtrackerLayoutsDesigner.Preferences.VAL_STORAGE_DIR+
                    File.separator+OsmtrackerLayoutsDesigner.Preferences.LAYOUTS_SUBDIR + File.separator;

            createDir(path);

            file = new File(path + layoutName);
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void createDir(String dirPath) {

        //Get File if SD card is present
        File apkStorage = null;
        if (isSDCardPresent()) {
            apkStorage = new File(dirPath);
        }
        //If File is not present create directory
        if (!apkStorage.exists()) {
            apkStorage.mkdirs();
            Log.e("#", "Directory Created.");
        }
    }

    private boolean isSDCardPresent() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    private Element mainTag;
    private Element mainLayout;
    private Element currentRow;

    public void createDocumentTemplate(){
        //<layouts></layouts>
        mainTag = document.createElement(ROO_TAG);
        document.appendChild(mainTag);
        //<layout name="root"></layout>
        mainLayout = document.createElement(LAYOUT_SCREEN_TAG);
        mainLayout.setAttribute("name", "root");
        mainTag.appendChild(mainLayout);
    }

    public void createRow(){
        Element newRow = document.createElement(ROW_TAG);
        mainLayout.appendChild(newRow);
        currentRow = newRow;
    }

    public void insertButton(String type, String icon){
        //<button type="" icon=""/>
        Element newSpecialButton = document.createElement(BUTTON_TAG);
        newSpecialButton.setAttribute("type", type);
        newSpecialButton.setAttribute("icon", icon);
        currentRow.appendChild(newSpecialButton);
    }

    public void insertButton(String type, String label, String icon){
        //<button type="tag" label="" icon=""
        Element newButton = document.createElement(BUTTON_TAG);
        newButton.setAttribute("type", type);
        newButton.setAttribute("label", label);
        newButton.setAttribute("icon", icon);
        currentRow.appendChild(newButton);
    }

}
