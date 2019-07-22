package net.osmtracker.layoutsdesigner.utils.xmlutils;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class ReadXmlTask extends AsyncTask<String, Void, String> {

    private static final String TAG = "ReadXmlTask";

    @Override
    protected String doInBackground(String... strings) {
        try {
            Log.i(TAG, "onOptionsItemSelected: making the InputStream object");
            String path = strings[0];
            InputStream in = new FileInputStream(path);
            Log.i(TAG, "onOptionsItemSelected: Calling the XmlReader.parse(XmlPullParser object) method to start the reading of the XML file");
            XMLReader.parse(in);
            return "success";
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
