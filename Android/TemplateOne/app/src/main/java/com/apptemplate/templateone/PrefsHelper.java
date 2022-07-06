package com.apptemplate.templateone;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PrefsHelper {

    private static final String LOG = "PrefsHelper";
    private static final PrefsHelper mInstance = new PrefsHelper();

    private PrefsHelper() {
        super();
    }

    public static PrefsHelper getInstance() {
        return mInstance;
    }

    public void init(SharedPreferences aPrefs, Resources aResources) {
        boolean isFirstStart = aPrefs.getBoolean(ConstantsT.PREF_FIRST_START, true);
        if(isFirstStart) {
            String[] arrayMenuLabels = aResources.getStringArray(R.array.menu_item_labels);
            String[] arrayMenuUrls = aResources.getStringArray(R.array.menu_item_urls);
            JSONArray jsonArrayMenuItems = new JSONArray();
            int count = arrayMenuLabels.length;
            for(int x = 0; x < count; x++) {
                try {
                    JSONObject jsonObjectItem = new JSONObject();
                    jsonObjectItem.put(ConstantsT.API_KEY_LABEL, arrayMenuLabels[x]);
                    jsonObjectItem.put(ConstantsT.API_KEY_URL, arrayMenuUrls[x]);
                    jsonArrayMenuItems.put(jsonObjectItem);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            SharedPreferences.Editor editor = aPrefs.edit();
            editor.putBoolean(ConstantsT.PREF_FIRST_START, false);
            editor.putInt(ConstantsT.PREF_MENU_BACKGROUND_COLOR, ConstantsT.kMenuBackgroundColor);
            editor.putInt(ConstantsT.PREF_MENU_BACKGROUND_COLOR_SELECTEDITEM, ConstantsT.kMenuBackgroundColorSelectedItem);
            editor.putInt(ConstantsT.PREF_MENU_TEXT_COLOR, ConstantsT.kMenuTextColor);
            editor.putInt(ConstantsT.PREF_MENU_TEXT_SIZE, ConstantsT.kMenuTextSize);
            editor.putString(ConstantsT.PREF_MENU_ITEMS, jsonArrayMenuItems.toString());
            editor.commit();
        }
    }

    public ArrayList<MenuItemT> getMenuItems(SharedPreferences aPrefs) {
        ArrayList<MenuItemT> arrayMenuItems = new ArrayList<MenuItemT>();
        String strItems = aPrefs.getString(ConstantsT.PREF_MENU_ITEMS, ConstantsT.STR_EMPTY);
        try {
            JSONArray jsonArrayItems = new JSONArray(strItems);
            int count = jsonArrayItems.length();
            for(int x = 0; x < count; x++) {
                JSONObject object = jsonArrayItems.getJSONObject(x);
                String strLabel = ConstantsT.STR_EMPTY;
                String strUrl = ConstantsT.STR_EMPTY;
                if(!object.isNull(ConstantsT.API_KEY_LABEL)) {strLabel = object.getString(ConstantsT.API_KEY_LABEL);}
                if(!object.isNull(ConstantsT.API_KEY_URL)) {strUrl = object.getString(ConstantsT.API_KEY_URL);}

                MenuItemT menuItem = new MenuItemT(strLabel, strUrl);
                arrayMenuItems.add(menuItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayMenuItems;
    }

    public void setMenuItems(SharedPreferences aPrefs, JSONArray aJsonArrayMenuItems) {
        SharedPreferences.Editor editor = aPrefs.edit();
        editor.putString(ConstantsT.PREF_MENU_ITEMS, aJsonArrayMenuItems.toString());
        editor.commit();
    }

    public int getMenuBackgroundColor(SharedPreferences aPrefs) {
        return aPrefs.getInt(ConstantsT.PREF_MENU_BACKGROUND_COLOR, Color.TRANSPARENT);
    }

    public void setMenuBackgroundColor(SharedPreferences aPrefs, int aColor) {
        SharedPreferences.Editor editor = aPrefs.edit();
        editor.putInt(ConstantsT.PREF_MENU_BACKGROUND_COLOR, aColor);
        editor.commit();
    }

    public int getMenuBackgroundColorSelected(SharedPreferences aPrefs) {
        return aPrefs.getInt(ConstantsT.PREF_MENU_BACKGROUND_COLOR_SELECTEDITEM, Color.TRANSPARENT);
    }

    public void setMenuBackgroundColorSelected(SharedPreferences aPrefs, int aColor) {
        SharedPreferences.Editor editor = aPrefs.edit();
        editor.putInt(ConstantsT.PREF_MENU_BACKGROUND_COLOR_SELECTEDITEM, aColor);
        editor.commit();
    }

    public int getMenuTextColor(SharedPreferences aPrefs) {
        return aPrefs.getInt(ConstantsT.PREF_MENU_TEXT_COLOR, Color.BLACK);
    }

    public void setMenuTextColor(SharedPreferences aPrefs, int aColor) {
        SharedPreferences.Editor editor = aPrefs.edit();
        editor.putInt(ConstantsT.PREF_MENU_TEXT_COLOR, aColor);
        editor.commit();
    }

    public int getMenuTextSize(SharedPreferences aPrefs) {
        return aPrefs.getInt(ConstantsT.PREF_MENU_TEXT_SIZE, ConstantsT.kMenuTextSize);
    }

    public void setMenuTextSize(SharedPreferences aPrefs, int aTextSize) {
        SharedPreferences.Editor editor = aPrefs.edit();
        editor.putInt(ConstantsT.PREF_MENU_TEXT_SIZE, aTextSize);
        editor.commit();
    }

    public int getMenuSelectedPosition(SharedPreferences aPrefs) {
        return aPrefs.getInt(ConstantsT.PREF_MENU_SELECTED_POSITION, 0);
    }

    public void setMenuSelectedPosition(SharedPreferences aPrefs, int aPosition) {
        SharedPreferences.Editor editor = aPrefs.edit();
        editor.putInt(ConstantsT.PREF_MENU_SELECTED_POSITION, aPosition);
        editor.commit();
    }
}
