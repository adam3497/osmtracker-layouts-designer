package net.osmtracker.layoutsdesigner.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.GridView;

import net.osmtracker.layoutsdesigner.OsmtrackerLayoutsDesigner;
import net.osmtracker.layoutsdesigner.R;
import net.osmtracker.layoutsdesigner.utils.xmlutils.ButtonXmlObject;
import net.osmtracker.layoutsdesigner.utils.xmlutils.RowXmlObject;

import java.util.ArrayList;

public class GridViewLayoutManager {

    private Activity activity;
    private GridView gridView;
    private ArrayList<ButtonXmlObject> buttonsXmlArray;
    private static final String TAG = OsmtrackerLayoutsDesigner.Preferences.TAG + ".GridViewLayoutManager";

    public GridViewLayoutManager(Activity activity){
        this.activity = activity;
        gridView = activity.findViewById(R.id.editlayout_gridview);
        buttonsXmlArray = new ArrayList<>();
    }

    public void showGridView(ArrayList<RowXmlObject> rowsArray){
        int columns = rowsArray.get(0).getXmlButtonsArray().size();
        for(RowXmlObject row : rowsArray){
            buttonsXmlArray.addAll(row.getXmlButtonsArray());
        }

        for(ButtonXmlObject button : buttonsXmlArray){
            if(button.getType().equals("picture")) {
                button.setLabel(activity.getResources().getString(R.string.xml_default_button_picture));
            }
            else if(button.getType().equals("textnote")) {
                button.setLabel(activity.getResources().getString(R.string.xml_default_button_textnote));
            }
            else if(button.getType().equals("voicerec")) {
                button.setLabel(activity.getResources().getString(R.string.xml_default_button_voicerec));
            }
            Log.i(TAG, "showGridView: Button type: " + button.getType() + " button label: " + button.getLabel());
        }



    }
}
