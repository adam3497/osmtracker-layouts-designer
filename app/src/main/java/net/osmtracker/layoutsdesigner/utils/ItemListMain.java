package net.osmtracker.layoutsdesigner.utils;

import java.io.Serializable;

public class ItemListMain implements Serializable {
    private String layoutCreatedName;
    private String layoutCreatedDescription;
    private String layoutFileName;

    /**
     * This method initialize a list item with two attributes (name, description)
     * @param name
     * @param description
     * @param layoutFileName
     */
    public ItemListMain(String name, String description, String layoutFileName){
        this.layoutCreatedName = name;
        this.layoutCreatedDescription = description;
        this.layoutFileName = layoutFileName;
    }

    public String getLayoutCreatedName() {
        return layoutCreatedName;
    }

    public String getLayoutCreatedDescription() {
        return layoutCreatedDescription;
    }

    public String getLayoutFileName(){
        return layoutFileName;
    }
}
