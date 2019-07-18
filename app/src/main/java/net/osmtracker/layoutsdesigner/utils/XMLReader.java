package net.osmtracker.layoutsdesigner.utils;

import android.content.Context;

import net.osmtracker.layoutsdesigner.R;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLReader {

    private Context context;

    public XMLReader(Context context){
        this.context = context;
        readDocument();


    }

    private void readDocument() {
        try{
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = context.getResources().getXml(R.xml.default_buttons_layout);
            
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
