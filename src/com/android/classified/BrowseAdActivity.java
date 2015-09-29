package com.android.classified;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.classified.adapter.ClassifiedSearchAdapter;

import parser.ResponseParser;
import utill.Utill;

import connection.CommonUtilities;

import model.Categorylist;
import model.ClassifiedSearchList;
import model.ClassifiedSearchListData;
import model.GetschemaList;
import model.SubCategoryList;
import model.SubSubCategoryList;
import net.Response;
import net.ResponseListener;
import net.ServerRequest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

@SuppressLint("InflateParams")
public class BrowseAdActivity extends BaseActivity implements ResponseListener {

	ArrayList<String> categoryName;

	LinearLayout ll, categrorymainsub, categrorymainsubInSub;
	int newpos;
	ListView list;
	View v;
	ArrayList<GetschemaList> schemalist;
	ClassifiedSearchList classfiedsearch;
	
	String uid ="8";
	String cid="";
	ClassifiedSearchAdapter adapter;


	@SuppressLint({ "CutPasteId", "InflateParams" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.brouse_ad);
		new Footer(this);
       list = (ListView) findViewById(R.id.list);
		ll = (LinearLayout) findViewById(R.id.categrorymain);
		categrorymainsubInSub = (LinearLayout) findViewById(R.id.categrorymainsubInSub);

		categrorymainsub = (LinearLayout) findViewById(R.id.categrorymainsub);

		if (Utill.ChechInternetAvalebleOrNot(this)) {
			Utill.showCheckOutProgressDialog(this, "");
			ServerRequest.getRequest(CommonUtilities.listCategories, "",
					BrowseAdActivity.this,
					ResponseListener.REQUEST_LISTCATEGORY);
		} else {

			Utill.showToastMessage(this);
			return;
		}
		categoryName = new ArrayList<String>();

	}

	@Override
	public void onResponse(final Response response, final int rid) {

		

		h.post(new Runnable() {

			@Override
			public void run() {

				if (response.isError()) {

					return;
				}
				Utill.closeCheckOutProgressDialog();

				if (rid == REQUEST_LISTCATEGORY) {

					if (response.getData() != null) {

						schemalist = ResponseParser.getSchemaList(response
								.getData());
						final ArrayList<Categorylist> list = schemalist.get(0).category;
                      setlist();
						System.out.println();
						for (int i = 0; i < list.size(); i++) {
							categoryName.add(list.get(i).catTitle);

						}

						for (int i = 0; i < categoryName.size(); i++) {

							final View vv;

							LayoutInflater inflater = (LayoutInflater) BrowseAdActivity.this
									.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
							vv = inflater.inflate(R.layout.row_one, null);

							final Button main = (Button) vv
									.findViewById(R.id.categorybutton);
							// main.setBackgroundColor(Color.BLUE);

							if (i == 0) {
								main.setBackgroundColor(Color
										.parseColor("#116C91"));
							}
							main.setText(categoryName.get(i));
							main.setTag(i);
							main.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									int pos = (Integer) v.getTag();
                                     cid =list.get(pos).catId;
									for (int i = 0; i < categoryName.size(); i++) {
										if (i == pos) {
											v.setBackgroundColor(Color
													.parseColor("#116C91"));
										} else {
											ll.getChildAt(i)
													.findViewWithTag(i)
													.setBackgroundColor(
															Color.parseColor("#0B5D88"));
										}
									}
									SubCategoryvalue(pos,
											list.get(pos).subcategory);
								}

							});

							ll.addView(vv);
							SubCategoryvalue(0, list.get(0).subcategory);
						}
					}
				}else if (rid==REQUEST_CLASSIFIEDSEARCH) {
					
					classfiedsearch = ResponseParser.getClassifiedSearch(response.getData());
					System.out.println();
					
					setlistadapter(classfiedsearch.listdata);
					
				}
			}

			
		});
	}

	private void setlistadapter(
			ArrayList<ClassifiedSearchListData> listdata) {
		adapter = new ClassifiedSearchAdapter(BrowseAdActivity.this, listdata);
		list.setAdapter(adapter);
		
		
	}
	private void SubCategoryvalue(int pos,
			final ArrayList<SubCategoryList> subcategory) {
		categrorymainsub.removeAllViews();
		final ArrayList<String> subName = new ArrayList<String>();

		for (int j = 0; j < subcategory.size(); j++) {
			subName.add(subcategory.get(j).catTitle);
		}

		for (int i = 0; i < subName.size(); i++) {

			final View vv;

			LayoutInflater inflater = (LayoutInflater) BrowseAdActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vv = inflater.inflate(R.layout.row_one, null);

			final Button main = (Button) vv.findViewById(R.id.categorybutton);
			// main.setBackgroundColor(Color.BLUE);
			if (i == 0) {
				main.setBackgroundColor(Color.parseColor("#116C91"));
			}
			main.setText(subName.get(i));
			main.setTag(i);
			main.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int pos = (Integer) v.getTag();

					for (int i = 0; i < subName.size(); i++) {
						if (i == pos) {
							v.setBackgroundColor(Color.parseColor("#116C91"));
						} else {
							categrorymainsub
									.getChildAt(i)
									.findViewWithTag(i)
									.setBackgroundColor(
											Color.parseColor("#0B5D88"));
						}
					}

					subinSub(pos, subcategory.get(pos).subsubcategory);

				}

			});

			categrorymainsub.addView(vv);
		}
		subinSub(0, subcategory.get(0).subsubcategory);

	}

	private void subinSub(int pos, ArrayList<SubSubCategoryList> subsubcategory) {

		final ArrayList<String> subNameinSub = new ArrayList<String>();

		for (int j = 0; j < subsubcategory.size(); j++) {
			subNameinSub.add(subsubcategory.get(j).catTitle);

		}

		categrorymainsubInSub.removeAllViews();
		for (int i = 0; i < subNameinSub.size(); i++) {

			View v;

			LayoutInflater inflater = (LayoutInflater) BrowseAdActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.row_one, null);

			Button main = (Button) v.findViewById(R.id.categorybutton);
			main.setText(subNameinSub.get(i));
			if (i == 0) {
				main.setBackgroundColor(Color.parseColor("#116C91"));
			}
			main.setTag(i);
			main.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					int pos = (int) v.getTag();

					for (int i = 0; i < subNameinSub.size(); i++) {
						if (i == pos) {
							v.setBackgroundColor(Color.parseColor("#116C91"));
						} else {
							categrorymainsubInSub
									.getChildAt(i)
									.findViewWithTag(i)
									.setBackgroundColor(
											Color.parseColor("#0B5D88"));

						}
					}

				}
			});

			categrorymainsubInSub.addView(v);

		}

		//setlist();
	}

	private void setlist() {
		
		
		if (Utill.ChechInternetAvalebleOrNot(this)) {
			Utill.showCheckOutProgressDialog(this, "");
			ServerRequest.postRequest(CommonUtilities.Classifiedsearch,
					getJsonToSearchClassified(), uid, BrowseAdActivity.this,
					ResponseListener.REQUEST_CLASSIFIEDSEARCH);
		} else {

			Utill.showToastMessage(this);
			return;
		}

		 
		
	}

	private JSONObject getJsonToSearchClassified() {

      JSONObject jObject  = new JSONObject();
      
      try {
		jObject.put("", "");
	} catch (JSONException e) {
		e.printStackTrace();
	}
      
	return jObject;
	}

}
