package com.android.classified;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import model.Categorylist;
import model.SubCategoryList;
import model.SubSubCategoryList;
import net.Response;
import net.ResponseListener;
import net.ServerRequest;
import connection.CommonUtilities;

import utill.Utill;

import android.support.v7.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class AddAdActivity extends BaseActivity implements OnClickListener,
		ResponseListener ,OnItemSelectedListener{

	HorizontalScrollView yearList;
	LinearLayout years;
	ActionBar actionbar;
	Button need, sell, publish;
	String uid = "10";
	EditText title,description,model,make,price,publishdate;
	String selectedyear;
	Spinner cat,subcat,subsubcat;
	ArrayList<Categorylist> categorylists = new ArrayList<Categorylist>();
	ArrayList<SubCategoryList> subCategorylists = new ArrayList<SubCategoryList>();
	ArrayList<SubSubCategoryList> subSubCategorylists = new ArrayList<SubSubCategoryList>();
	 ArrayAdapter<SubCategoryList> adapter1;
	 ArrayAdapter<SubSubCategoryList> adapter2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_ad);
		new Footer(this);

		ActionBar actionBar = getSupportActionBar();

		actionBar.setTitle("Submit Free Ad");
		for (int i = 0; i < Utill.schemalist.size(); i++) {
			System.out.println(Utill.schemalist.get(i).category);

		}

		need = (Button) findViewById(R.id.need);
		sell = (Button) findViewById(R.id.sell);
		publish = (Button) findViewById(R.id.publish);
		title = (EditText) findViewById(R.id.title);
		description = (EditText) findViewById(R.id.description);
		model = (EditText) findViewById(R.id.model);
		make = (EditText) findViewById(R.id.make);
		price = (EditText) findViewById(R.id.price);
		publishdate = (EditText) findViewById(R.id.publishdate);
		need.setOnClickListener(this);
		sell.setOnClickListener(this);
		publish.setOnClickListener(this);
       cat = (Spinner) findViewById(R.id.cat);
       subcat = (Spinner) findViewById(R.id.subcat);
       subsubcat = (Spinner) findViewById(R.id.subsubcat);
       cat.setOnItemSelectedListener(this);
       subcat.setOnItemSelectedListener(this);
       subsubcat.setOnItemSelectedListener(this);
       
       
       Categorylist category = new Categorylist();
       category.catTitle = "Please Select Category";
       categorylists.add(category);
       categorylists.addAll(Utill.schemalist.get(0).category);
       
       ArrayAdapter<Categorylist> adapter = new ArrayAdapter<Categorylist>(this,
				android.R.layout.select_dialog_item, categorylists);
        cat.setAdapter(adapter);
    	////////
        
       /* SubCategoryList subCategory = new SubCategoryList();
        subCategory.catTitle ="Please Select SubCategory" ;
        subCategorylists.add(subCategory);
        //subCategorylists.addAll(Utill.schemalist.get(0).category.get(0).subcategory);
        
        
        adapter1 = new ArrayAdapter<SubCategoryList>(this,
				android.R.layout.select_dialog_item, subCategorylists);
         subcat.setAdapter(adapter1);*/
		
	
       
       
      
    
		yearList = (HorizontalScrollView) findViewById(R.id.yearlist);
		years = (LinearLayout) findViewById(R.id.years);
		getYearList();

	}

	private void getYearList() {
		final ArrayList<String> year = new ArrayList<>();

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year1 = cal.get(Calendar.YEAR);
		for (int i = 1989; i <= year1; i++) {
			year.add(String.valueOf(i));
			// year1++;
		}
		for (int i = 0; i < year.size(); i++) {

			final View vv;

			LayoutInflater inflater = (LayoutInflater) AddAdActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vv = inflater.inflate(R.layout.row_one, null);

			final Button main = (Button) vv.findViewById(R.id.categorybutton);
			// main.setBackgroundColor(Color.BLUE);
			if (i == 1) {
				main.setBackgroundColor(Color.WHITE);
				selectedyear = year.get(1);
				main.setTextColor(Color.parseColor("#000000"));
			}
			main.setTag(i);
			main.setText(year.get(i));
			main.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int pos = (Integer) v.getTag();
					
					Toast.makeText(AddAdActivity.this,
							year.get(pos) + "Selected", Toast.LENGTH_SHORT)
							.show();
					selectedyear =year.get(pos);
					for (int i = 0; i < year.size(); i++) {
						if (i == pos) {
							v.setBackgroundColor(Color.parseColor("#ffffff"));
							main.setTextColor(Color.parseColor("#000000"));
						} else {
							years.getChildAt(i)
									.findViewWithTag(i)
									.setBackgroundColor(
											Color.parseColor("#0B5D88"));
							
							
							

						}

					}

				}
			});

			years.addView(vv);

		}
	}

	@Override
	public void onClick(View v) {
		try {
			int viewID = v.getId();

			switch (viewID) {

			case R.id.need:

				need.setBackgroundColor(Color.parseColor("#0B5D88"));
				sell.setBackgroundColor(Color.parseColor("#116C91"));
				break;

			case R.id.sell:
				need.setBackgroundColor(Color.parseColor("#116C91"));
				sell.setBackgroundColor(Color.parseColor("#0B5D88"));

				break;
			case R.id.publish:
				
				if (title.getText().toString().equals("")) {
				Utill.showToast(this,
						"Please Enter The Title");
				return;
			} else if (description.getText().toString().equals("")) {
				Utill.showToast(this,
						"Please Enter The Description");
				return;
			} else if (model.getText().toString().equals("")) {
				Utill.showToast(this, "Please Enter The Model");
				return;
			} else if (make.getText().toString().equals("")) {
				Utill.showToast(this, "Please Enter The  Make");
				return;
			
			} else if (price.getText().toString().equals("")) {
				Utill.showToast(this, "Please Enter The  Price");
				return;
			
			}
			 else if (publishdate.getText().toString().equals("")) {
					Utill.showToast(this, "Please Enter The Publish Date");
					return;
				
				}
			
				publishAd();

			}
		} catch (Exception e) {

		}

	}

	private void publishAd() {
		if (Utill.ChechInternetAvalebleOrNot(this)) {
			Utill.showCheckOutProgressDialog(this, "");
			ServerRequest.postRequest(CommonUtilities.adPost,
					getJsonToPostAd(), uid, AddAdActivity.this,
					ResponseListener.REQUEST_POSTAD);
		} else {

			Utill.showToastMessage(this);
			return;
		}

	}

	private JSONObject getJsonToPostAd() {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("name", title.getText().toString());
			jsonObject.put("price", price.getText().toString());
			jsonObject.put("details", description.getText().toString());
			jsonObject.put("user_permissions", "1");
			jsonObject.put("cid", "10");
			jsonObject.put("L2", "11");
			jsonObject.put("L3", "13");
			jsonObject.put("model", model.getText().toString());
			jsonObject.put("year", selectedyear);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject;
	}

	@Override
	public void onResponse(final Response response, final int rid) {
		Utill.closeCheckOutProgressDialog();
		h.post(new Runnable() {

			@Override
			public void run() {

				
				if (rid == REQUEST_POSTAD) {
             //System.out.println(response.getData());
					
						try {
							JSONObject jsonObject = new JSONObject(response
									.getData());
							int errorcode = jsonObject.getInt("errorCode");

							if (errorcode == 100) {
								String adsucess = getResources().getString(
										R.string.adsucess);
								String shareddetails = getResources()
										.getString(R.string.shareddetails);
								Utill.showAdStatus(AddAdActivity.this, adsucess, shareddetails);

							} else {
								
								String adfailure = getResources().getString(
										R.string.adfailure);
								
								Utill.showAdStatus(AddAdActivity.this, adfailure, "");
							}
						} catch (Exception e) {

						}

					}
				
			}
		});
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int position,
			long arg3) {
		switch (parent.getId()) {
		
		
		case R.id.cat:
			cat.setSelected(true);
			
			setsubcategoryadapter(position);
			
			 
			break;
			
		case R.id.subcat:
			
			setsubsubcategoryadapter(position);
			
			
			 
			break;
			
		case R.id.subsubcat:
			break;
			
		default:
			break;
		
	}}

	private void setsubsubcategoryadapter(int position) {
		
		
		subSubCategorylists.clear();
		SubSubCategoryList subsubCategory = new SubSubCategoryList();
		subsubCategory.catTitle ="SubSubCategory";
		subSubCategorylists.add(subsubCategory);
		if(position!=0)
		subSubCategorylists.addAll(Utill.schemalist.get(0).category.get(position-1).subcategory.get(position-1).subsubcategory);
		 adapter2 = new ArrayAdapter<SubSubCategoryList>(this,
					android.R.layout.select_dialog_item, subSubCategorylists);
	    subsubcat.setAdapter(adapter2);
	}

	private void setsubcategoryadapter(int position) {
		subCategorylists.clear();
		SubCategoryList subCategory = new SubCategoryList();
        subCategory.catTitle ="SubCategory" ;
        subCategorylists.add(subCategory);
		if(position!=0)
		subCategorylists.addAll(Utill.schemalist.get(0).category.get(position-1).subcategory);
		 adapter1 = new ArrayAdapter<SubCategoryList>(this,
					android.R.layout.select_dialog_item, subCategorylists);
        subcat.setAdapter(adapter1);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
