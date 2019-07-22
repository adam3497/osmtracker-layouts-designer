package net.osmtracker.layoutsdesigner.utils.xmlutils;

public class ButtonXmlObject {
    private String type;
    private String label;
    private String icon;
    private String targetLayout;

    /**
     * Constructor to set up normal (default/custom) button
     * @param type voicerec, picture, textnote (<- default buttons), tag (to indicate a custom button), page (to indicate a sub screen link button)
     * @param label name that will be present in the final UI
     * @param icon path to the icon (if it had one)
     */
    public ButtonXmlObject(String type, String label, String icon) {
        this.setType(type);
        this.setLabel(label);
        this.setIcon(icon);
        this.setTargetLayout(null);
    }

    /**
     * Constructor to set up a target layout button (sub screen)
     * @param type voicerec, picture, textnote (<- default buttons), tag (to indicate a custom button), page (to indicate a sub screen link button)
     * @param label name that will be present in the final UI
     * @param icon path to the icon (if it had one)
     * @param targetLayout name of the next layout tag (sub screen)
     */
    public ButtonXmlObject(String type, String label, String icon, String targetLayout) {
        this.setType(type);
        this.setLabel(label);
        this.setIcon(icon);
        this.setTargetLayout(targetLayout);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTargetLayout() {
        return targetLayout;
    }

    public void setTargetLayout(String targetLayout) {
        this.targetLayout = targetLayout;
    }
}
