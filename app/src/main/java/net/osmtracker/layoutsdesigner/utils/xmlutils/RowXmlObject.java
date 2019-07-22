package net.osmtracker.layoutsdesigner.utils.xmlutils;

import java.util.ArrayList;

public class RowXmlObject {
    private ArrayList<ButtonXmlObject> rowXmlButtons;

    public RowXmlObject(ArrayList<ButtonXmlObject> buttons){
        this.setRowXmlButtons(buttons);
    }


    public ArrayList<ButtonXmlObject> getRowXmlButtons() {
        return rowXmlButtons;
    }

    public void setRowXmlButtons(ArrayList<ButtonXmlObject> rowXmlButtons) {
        this.rowXmlButtons = rowXmlButtons;
    }
}
