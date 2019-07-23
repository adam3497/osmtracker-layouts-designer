package net.osmtracker.layoutsdesigner.utils;

import android.content.Context;

import net.osmtracker.layoutsdesigner.utils.xmlutils.RowXmlObject;

import java.util.ArrayList;

public class GridViewLayoutManager {

    private Context context;

    public GridViewLayoutManager(Context context){
        this.context = context;
    }

    public void showGridView(ArrayList<RowXmlObject> rowsArray){
        int columns = rowsArray.get(0).getXmlButtonsArray().size();
    }
}
