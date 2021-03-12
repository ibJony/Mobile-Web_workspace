package com.jon.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JON on 7/13/2015.
 */
public class NavAdapter extends ArrayAdapter {
    Context context;
    String[] str,code,accont;

    public NavAdapter(Context context, String[] str){
        super(context, R.layout.drawer, str);
        this.context = context;
        this.str = str;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.drawer, parent, false);

        ImageView img = (ImageView) v.findViewById(R.id.drawer_item_icons);
        TextView tv = (TextView) v.findViewById(R.id.drawer_item_labels);

        TypedArray iconsTypedArray= context.getResources().obtainTypedArray(R.array.drawer_icons);

        img.setImageResource(iconsTypedArray.getResourceId(position, -1));
        tv.setText(str[position]);

        return v;
    }
}
