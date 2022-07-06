package com.apptemplate.templatethree;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ListAdapterMenu extends BaseAdapter {

    private ArrayList<MenuItemT> mArrayData;
    private LayoutInflater mLayoutInflater;
    private SharedPreferences mPrefs;
    private int mSelectedItemPosition;

    public ListAdapterMenu(Context aContext, ArrayList<MenuItemT> aArrayData) {
        super();
        mArrayData = aArrayData;
        mLayoutInflater = (LayoutInflater)aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPrefs = (SharedPreferences) PreferenceManager.getDefaultSharedPreferences(aContext);
    }

    public ArrayList<MenuItemT> getArrayData() {
        return mArrayData;
    }

    public void setArrayData(ArrayList<MenuItemT> aArrayData) {
        mArrayData = aArrayData;
    }

    @Override
    public int getCount() {
        return mArrayData.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = mLayoutInflater.inflate(R.layout.listitem_menu_layout, parent, false);
        }
        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearLayoutItem);
        if(mSelectedItemPosition == position) {
            linearLayout.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColorSelected(mPrefs));
        } else {
            linearLayout.setBackgroundColor(PrefsHelper.getInstance().getMenuBackgroundColor(mPrefs));
        }
        TextView textViewItem = (TextView)view.findViewById(R.id.textViewItem);
        textViewItem.setText(mArrayData.get(position).getLabel());
        textViewItem.setTypeface(textViewItem.getTypeface(), ConstantsT.kMenuTextStyle);

        return view;
    }

    public void setSelectedItem(int aPosition) {
        mSelectedItemPosition = aPosition;
        notifyDataSetChanged();
    }
}
