package com.classified.adapter;

import java.util.List;

import com.android.classified.R;
import model.ClassifiedSearchList;
import model.ClassifiedSearchListData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ClassifiedSearchAdapter  extends ArrayAdapter<ClassifiedSearchListData> {

	
	Context context;
	List<ClassifiedSearchListData> list;

	public ClassifiedSearchAdapter(Context context,
			List<ClassifiedSearchListData> list) {
		super(context, R.layout.aditemlist, list);
		this.context = context;
		this.list = list;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = new ViewHolder();
		 if (convertView == null) {
			 
			 LayoutInflater inflater;
				inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				convertView = inflater.inflate(R.layout.aditemlist, parent, false);
				
				holder.producttitle = (TextView) convertView.findViewById(R.id.producttitle);
				holder.price = (TextView) convertView.findViewById(R.id.price);
				holder.yearnmodel = (TextView) convertView.findViewById(R.id.yearnmodel);
				convertView.setTag(holder);
	        	
	        }
	        else
	        {
	        	holder = (ViewHolder) convertView.getTag();
	        }
	          
		 holder.producttitle.setText(list.get(position).name);
		 holder.price.setText("Price : "+list.get(position).price);
		 holder.yearnmodel.setText("Year : "+list.get(position).year +"  Model : "+list.get(position).model);
	      
		return convertView;
	}
	
	
	private class ViewHolder
	{
		private TextView producttitle,price,yearnmodel;
    }

}
