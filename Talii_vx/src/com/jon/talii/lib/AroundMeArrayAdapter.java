package com.jon.talii.lib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jon.talii.R;
import com.jon.talii.lib.AroundMe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class AroundMeArrayAdapter extends ArrayAdapter<AroundMe> {
	private static final String tag = "AroundMeArrayAdapter";
	private static final String ASSETS_DIR = "images/";
	private Context context;

	private ImageView listIcon;
	private TextView listName;
	private TextView listAbbrev;
	private List<AroundMe> list = new ArrayList<AroundMe>();

	public AroundMeArrayAdapter(Context context, int textViewResourceId,
			List<AroundMe> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.list = objects;
	}

	public int getCount() {
		return this.list.size();
	}

	public AroundMe getItem(int index) {
		return this.list.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			Log.d(tag, "Starting XML Row Inflation ... ");
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.list_item, parent, false);
			Log.d(tag, "Successfully completed XML Row Inflation!");
		}

		// Get item
		AroundMe around = getItem(position);
		listIcon = (ImageView) row.findViewById(R.id.icon);
		listName = (TextView) row.findViewById(R.id.label);
	//	listAbbrev = (TextView) row.findViewById(R.id.country_abbrev);


		listName.setText(around.name);
		String imgFilePath = ASSETS_DIR + around.resourceId;
		try {
			Bitmap bitmap = BitmapFactory.decodeStream(this.context.getResources().getAssets()
					.open(imgFilePath));
			listIcon.setImageBitmap(bitmap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Log.d(tag, "Image File: " + imgFilePath + " " + "Size: " +
		// bitmap.getHeight());

//		listAbbrev.setText(around.abbreviation);

		return row;
	}

}
