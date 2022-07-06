package com.apptemplate.templatetwo;

import android.graphics.Color;


public class ConstantsT {

    private ConstantsT() {
        super();
    }

    public static final String STR_EMPTY = "";
    public static final String STR_BLANK = " ";

    public static final String API_KEY_LABEL = "label";
    public static final String API_KEY_URL = "url";

    public static final String PREF_FIRST_START = "firststart";
    public static final String PREF_MENU_BACKGROUND_COLOR = "menubackgroundcolor";
    public static final String PREF_MENU_BACKGROUND_COLOR_SELECTEDITEM = "menubackgroundcolorselecteditem";
    public static final String PREF_MENU_ITEMS = "menuitems";
    public static final String PREF_MENU_SELECTED_POSITION = "menuselectedposition";

    //Templated values
    /**
     * Color for the background of the menu
     * The values can be 0-255 for each of the parameters of the used method - Color.argb(alpha, red, green, blue).
     */
    public static final int kMenuBackgroundColor = Color.argb(255, 0, 153, 204);

    /**
     * Color for the background of the menu item that is currently selected
     * The values can be 0-255 for each of the parameters of the used method - Color.argb(alpha, red, green, blue).
     */
    public static final int kMenuBackgroundColorSelectedItem = Color.argb(255, 0, 100, 255);

    /** Content that will be displayed if there is an error when loading a html content page. */
    public static final String kErrorPageUrl = "file:///android_asset/htmlroot/error_page.html";
}
