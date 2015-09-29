package com.android.classified;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Footer implements OnClickListener{
	
	 View vv;
	 Context context;
	public Footer(final Context context) {
		this.context = context;
			footer();
			
			
			
}
	
	private void footer() {
		ImageButton  favourite = (ImageButton) ((Activity) context).findViewById(android.R.id.content).findViewById(R.id.favourite);
		ImageButton  myads = (ImageButton) ((Activity) context).findViewById(android.R.id.content).findViewById(R.id.myads);
		ImageButton  myneeds = (ImageButton) ((Activity) context).findViewById(android.R.id.content).findViewById(R.id.myneeds);
		ImageButton  myitems = (ImageButton) ((Activity) context).findViewById(android.R.id.content).findViewById(R.id.myitems);
		ImageButton  home = (ImageButton) ((Activity) context).findViewById(android.R.id.content).findViewById(R.id.home);
		
		favourite.setOnClickListener(this);
		myads.setOnClickListener(this);
		myneeds.setOnClickListener(this);
		myitems.setOnClickListener(this);
		home.setOnClickListener(this);
		
		/*favourite.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
				if (!context.getClass().getSimpleName().equals("AddAdActivity")) 
				{
				Intent  intent =new Intent(context,AddAdActivity.class);
				context.startActivity(intent);
				}
			}

			
		});*/
		
		
	}

	@Override
	public void onClick(View v) {
		try {
			int viewID = v.getId();

			switch (viewID) {
			
			case R.id.favourite:


				if (!context.getClass().getSimpleName().equals("FavouriteActivity")) 
				{
				Intent  intent =new Intent(context,FavouriteActivity.class);
				context.startActivity(intent);
				}
				break;

			case R.id.myads:
				if (!context.getClass().getSimpleName().equals("MyAdsActivity")) 
				{
				Intent  intent =new Intent(context,MyAdsActivity.class);
				context.startActivity(intent);
				}
				
				break;
			case R.id.myneeds:
				if (!context.getClass().getSimpleName().equals("MyNeedsActivity")) 
				{
				Intent  intent =new Intent(context,MyNeedsActivity.class);
				context.startActivity(intent);
				}
				break;
				
			case R.id.myitems:
				if (!context.getClass().getSimpleName().equals("MyItemsActivity")) 
				{
				Intent  intent =new Intent(context,MyItemsActivity.class);
				context.startActivity(intent);
				}
				break;
				
			case R.id.home:
				if (!context.getClass().getSimpleName().equals("AddAdActivity")) 
				{
				Intent  intent =new Intent(context,AddAdActivity.class);
				context.startActivity(intent);
				}
				break;
		   default:
			   break;
		   
			
			}
		} catch (Exception e) {

		}
	}
	
}

