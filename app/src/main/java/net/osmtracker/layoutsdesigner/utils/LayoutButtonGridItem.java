package net.osmtracker.layoutsdesigner.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import net.osmtracker.layoutsdesigner.R;
import net.osmtracker.layoutsdesigner.utils.xmlutils.ButtonXmlObject;

public class LayoutButtonGridItem {
    private String itemName;
    private Uri imageURI;
    private String imagePath;
    private Drawable defaultIcon;

    public LayoutButtonGridItem(String name, Drawable icon){
        this.itemName = name;
        this.defaultIcon = icon;
        this.imageURI = null;
    }

    public LayoutButtonGridItem(String name) {
        this.itemName = name;
        this.defaultIcon = null;
        this.imageURI = null;
    }

    public LayoutButtonGridItem(String name, Uri imageURI, String imagePath){
        this.itemName = name;
        //TODO: CHANGE THIS TO -> this.imageUri = searchImageUri(imagePath)
        this.imageURI = imageURI;
        this.imagePath = imagePath;
    }

    public LayoutButtonGridItem(ButtonXmlObject buttonXml, Activity activity){
        this.itemName = buttonXml.getLabel();
        this.defaultIcon = isDefaultButton(buttonXml.getType(), activity);
        this.imagePath = buttonXml.getIcon();
        this.imageURI = searchImageUri(buttonXml.getIcon());
    }

    private Drawable isDefaultButton(String type, Activity activity){
        switch (type) {
            case "voicerec":
                return activity.getResources().getDrawable(R.drawable.voice_32x32);
            case "picture":
                return activity.getResources().getDrawable(R.drawable.camera_32x32);
            case "textnote":
                return activity.getResources().getDrawable(R.drawable.text_32x32);
            default:
                return null;
        }
    }

    private Uri searchImageUri(String imagePath){
        //TODO: MAKE THE FUNCTIONALITY TO SEARCH THE IMAGE IN THE /ICON FOLDER OF THE CURRENT LAYOUT
        return null;
    }

    public void setItemName(String name){
        itemName = name;
    }

    public void setImageURI(Uri uri){
        imageURI = uri;
    }

    public void setImagePath(String path){
        imagePath = path;
    }

    public Drawable getDefaultIcon(){
        return defaultIcon;
    }

    public String getItemName() {
        return itemName;
    }

    public Uri getImageURI() {
        return imageURI;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getId(){
        return itemName.hashCode();
    }
}
