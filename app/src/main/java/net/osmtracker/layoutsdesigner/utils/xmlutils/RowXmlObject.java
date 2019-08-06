package net.osmtracker.layoutsdesigner.utils.xmlutils;

import android.widget.GridView;

import java.util.ArrayList;

public class RowXmlObject {
    private ArrayList<ButtonXmlObject> xmlButtonsArray;

    public RowXmlObject(ArrayList<ButtonXmlObject> buttons){
        this.setXmlButtonsArray(buttons);

    }


    public ArrayList<ButtonXmlObject> getXmlButtonsArray() {
        return xmlButtonsArray;
    }

    public void setXmlButtonsArray(ArrayList<ButtonXmlObject> rowXmlButtons) {
        this.xmlButtonsArray = rowXmlButtons;
    }
}
