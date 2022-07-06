package com.apptemplate.templatetwo;

public class MenuItemT {

    private String mLabel;
    private String mUrl;

    public MenuItemT(String aLabel, String aUrl) {
        super();
        mLabel = aLabel;
        mUrl = aUrl;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String aLabel) {
        mLabel = aLabel;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String aUrl) {
        mUrl = aUrl;
    }
}
