package com.apptemplate.templatethree;

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
    public static final String PREF_MENU_TEXT_COLOR = "menutextcolor";
    public static final String PREF_MENU_TEXT_SIZE = "menutextsize";
    public static final String PREF_MENU_ITEMS = "menuitems";
    public static final String PREF_MENU_SELECTED_POSITION = "menuselectedposition";

    public static final int TEXT_STYLE_NORMAL = 0;
    public static final int TEXT_STYLE_BOLD = 1;
    public static final int TEXT_STYLE_ITALIC = 2;

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

    /**
     * Color for the text in the menu
     * The values can be 0-255 for each of the parameters of the used method - Color.argb(alpha, red, green, blue).
     */
    public static final int kMenuTextColor = Color.argb(255, 0, 0, 0);

    /** Size of the text in the menu */
    public static final int kMenuTextSize = 18;

    /**
     * Style of the text in the menu.
     * One of defined in ConstantsT values:
     * TEXT_STYLE_NORMAL
     * TEXT_STYLE_BOLD
     * TEXT_STYLE_ITALIC
     */
    public static final int kMenuTextStyle = TEXT_STYLE_NORMAL;

    /** Content that will be displayed if there is an error when loading a html content page. */
    public static final String kErrorPageUrl = "file:///android_asset/htmlroot/error_page.html";

    /** Duration for the menu open animation in milliseconds. */
    public static final int kMenuOpenAnimationDuration = 250;

    /** Duration for the menu close animation in milliseconds. */
    public static final int kMenuCloseAnimationDuration = 250;
}
