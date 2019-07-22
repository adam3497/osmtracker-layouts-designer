package net.osmtracker.layoutsdesigner.utils.xmlutils;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class XMLReader {

    private static final String TAG = "XMLReader";
    // We don't use namespaces
    private static final String ns = null;
    private static String layoutName;

    /**
     * Prepare the input stream data parsing it before to start with the extracting of data
     * @param in input stream object that will be read to get the data needed
     * @return XmlPullParser object with the initialization parameters
     * @throws XmlPullParserException
     * @throws IOException
     */
    public static void parse(InputStream in) throws XmlPullParserException, IOException {
        Log.i(TAG, "parse: making the parser object");
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            Log.i(TAG, "parse: calling the recursive methods to get the data from the custom buttons layout");
            //TODO: CHANGE THE METHOD TO RETURN THE HASH
            readLayoutsTag(parser);
        } finally {
            in.close();
        }
    }

    /**
     * Return a HastTable object with all the layouts (sub screen) in the custom buttons layout xml
     * Take the global variable layoutName like a key (start with 'root') and set the first data between the 
     * <layout></layout> tags, do the same with the others <layout></layout> tags (it it has)   
     * @param parser XmlPullParser object that was before prepare
     * @return a HastTable object
     * @throws IOException
     * @throws XmlPullParserException
     */
    private static void readLayoutsTag(XmlPullParser parser)
            throws IOException, XmlPullParserException {

        Hashtable<String, ArrayList<RowXmlObject>> layoutXmlHash = new Hashtable<>();
        Log.i(TAG, "readLayoutsTag: begin the reading of the data from the xml");
        int eventType = parser.getEventType();
        while(eventType != XmlPullParser.END_DOCUMENT){
            if(eventType == XmlPullParser.START_TAG){
                String tagName = parser.getName();
                //Start by looking for the layout tag
                if(tagName.equals(XmlSchema.TAG_LAYOUT)){
                    Log.i(TAG, "readLayoutsTag: current tag: " + tagName + " name= " + parser.getAttributeValue(null, "name"));
                    String nameAttrVal = parser.getAttributeValue(null, XmlSchema.ATTR_NAME);
                    layoutXmlHash.put(nameAttrVal, readLayoutTag(parser));
                }
            }
            eventType = parser.next();
        }
    }

    /**
     * Read the content between each <layout></layout> tag, like <row></row> and <button></button> tags
     * @param parser XmlPullParser object that currently is in the <layouts> tag
     * @return an ArrayList object of RowXmlObject objects in it, each RowXmlObject has a field of ArrayList<ButtonXmlObject>
     * @throws IOException
     * @throws XmlPullParserException
     */
    private static ArrayList<RowXmlObject> readLayoutTag(XmlPullParser parser) throws IOException, XmlPullParserException {
        ArrayList<RowXmlObject> rows = new ArrayList<>();
        String currentTagName = null;
        while(!XmlSchema.TAG_LAYOUT.equals(currentTagName)){
            int eventType = parser.next();
            if(eventType == XmlPullParser.START_TAG){
                String tagName = parser.getName();
                //Looking for the row tag
                if(tagName.equals(XmlSchema.TAG_ROW)){
                    Log.i(TAG, "readLayoutTag: tag row found, call readRow to get the buttons data");
                    rows.add(readRow(parser));
                }
            }
            else if(eventType == XmlPullParser.END_TAG){
                currentTagName = parser.getName();
            }


        }
        Log.i(TAG, "readLayoutTag: all the rows in the current layout tag were read");
        return rows;

    }

    /**
     * Read the elements (button tags) between the <row></row> tags
     * @param parser XmlPullParser object that currently is in the <layout> tag
     * @return a RowXmlObject object that has an ArrayList<ButtonXmlObject> field that contains all the button elements of the current row
     * @throws IOException
     * @throws XmlPullParserException
     */
    private static RowXmlObject readRow(XmlPullParser parser) throws IOException, XmlPullParserException {
        ArrayList<ButtonXmlObject> buttonXmlObjects = new ArrayList<>();
        String currentTagName = null;
        while(!XmlSchema.TAG_ROW.equals(currentTagName)){
            int eventType = parser.next();
            if(eventType == XmlPullParser.START_TAG){
                String tagName = parser.getName();
                //Looking for the button tag
                if(tagName.equals(XmlSchema.TAG_BUTTON)){
                    Log.i(TAG, "readRow: the current tag name is equals to button, calling the method to read the attributes in it");
                    buttonXmlObjects.add(readButtonXml(parser));
                }
            }
            else if(eventType == XmlPullParser.END_TAG){
                currentTagName = parser.getName();
            }
        }
        Log.i(TAG, "readRow: all the buttons in the current row were read");
        return new RowXmlObject(buttonXmlObjects);
    }

    /**
     * Read the attributes in the button tag, like label, icon, type and targetLayout
     * @param parser XmlPullParser object that currently is in the row tag
     * @return a ButtonXmlObject object that has all the attributes in it
     * @throws IOException
     * @throws XmlPullParserException
     */
    private static ButtonXmlObject readButtonXml(XmlPullParser parser) throws IOException, XmlPullParserException {
        String type = "";
        String label = "";
        String icon = "";
        String targetLayout = "";
        //get all the attributes that currently has the button
        int i = parser.getAttributeCount();
        Log.i(TAG, "readButtonXml: the buttons has " + i + " attributes");
        while(i > 0){
            String attributeName = parser.getAttributeName(i-1);
            Log.i(TAG, "readButtonXml: the attribute name in the position " + i + " is: " + attributeName);
            if(attributeName.equals(XmlSchema.ATTR_TYPE)){
                type = parser.getAttributeValue(null, XmlSchema.ATTR_TYPE);
            }
            else if(attributeName.equals(XmlSchema.ATTR_LABEL)){
                label = parser.getAttributeValue(null, XmlSchema.ATTR_LABEL);
            }
            else if(attributeName.equals(XmlSchema.ATTR_ICON)){
                icon = parser.getAttributeValue(null, XmlSchema.ATTR_ICON);
            }
            else if(attributeName.equals(XmlSchema.ATTR_TARGETLAYOUT)){
                targetLayout = parser.getAttributeValue(null, XmlSchema.ATTR_TARGETLAYOUT);
            }
            i--;
        }
        Log.i(TAG, "readButtonXml: Creating a new ButtonXmlObject object with: \n"
                + "Type: " + type + "\n"
                + "Label: " + label + "\n"
                + "Icon: " + icon + "\n"
                + "TargetLayout: " + targetLayout + "\n");
        return new ButtonXmlObject(type, label, icon, targetLayout);
    }

    /**
     * XML Schema
     */
    private static final class XmlSchema {
        public static final String TAG_LAYOUT = "layout";
        public static final String TAG_ROW = "row";
        public static final String TAG_BUTTON = "button";

        public static final String ATTR_NAME = "name";
        public static final String ATTR_TYPE = "type";
        public static final String ATTR_LABEL = "label";
        public static final String ATTR_TARGETLAYOUT = "targetlayout";
        public static final String ATTR_ICON = "icon";
        public static final String ATTR_ICONPOS = "iconpos";

        public static final String ATTR_VAL_TAG = "tag";
        public static final String ATTR_VAL_PAGE = "page";
        public static final String ATTR_VAL_VOICEREC = "voicerec";
        public static final String ATTR_VAL_TEXTNOTE = "textnote";
        public static final String ATTR_VAL_PICTURE = "picture";
    }
}
