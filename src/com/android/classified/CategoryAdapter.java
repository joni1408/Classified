package com.android.classified;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class CategoryAdapter extends ArrayAdapter<String> {

	Context context;
	ArrayList<String> categorylist;

	public CategoryAdapter(Context context, ArrayList<String> categorylist) {
		super(context, R.layout.row_one,categorylist);

		this.categorylist = categorylist;
		this.context = context;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v;

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = inflater.inflate(R.layout.row_one, null);

		Button main = (Button) v.findViewById(R.id.categorybutton);
		v.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		return v;
	}
}
