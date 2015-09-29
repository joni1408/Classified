package com.android.classified;

import org.json.JSONException;
import org.json.JSONObject;

import utill.JDialog;

import net.Response;
import net.ResponseListener;
import net.ServerRequest;
import connection.CommonUtilities;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;


public class Check extends ActionBarActivity implements ResponseListener {

	Handler h = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check);

		ServerRequest.postRequest(CommonUtilities.adPost, getJsonObject(),"10",
				Check.this, ResponseListener.REQUEST_LISTCATEGORY);

	}

	private JSONObject getJsonObject() {
		JSONObject jsonObject = new JSONObject();

		try {
			//jsonObject.put("uid", 8);
			jsonObject.put("name", "joni");
			jsonObject.put("price", "50");
			jsonObject.put("details", "jonidfdgdg");
			jsonObject.put("user_permissions", "1");
			jsonObject.put("cid", "10");
			jsonObject.put("L2", "11");
			jsonObject.put("L3", "13");
			jsonObject.put("model", "2012");
			jsonObject.put("year", "2012");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject;
	}

	@Override
	public void onResponse(Response response, int rid) {
		// TODO Auto-generated method stub
		h.post(new Runnable() {

			@Override
			public void run() {

		
		final Dialog d = new JDialog(Check.this,R.style.MDialog);
		d.requestWindowFeature(Window.FEATURE_NO_TITLE);
		d.setContentView(R.layout.note);
		String adsucess =  getResources().getString(R.string.adsucess);
		String shareddetails = getResources().getString(R.string.shareddetails);
		TextView note1 = (TextView) d.findViewById(R.id.note1);
		TextView note2 = (TextView) d.findViewById(R.id.note2);
		Button ok = (Button) d.findViewById(R.id.ok);
		note1.setText(adsucess);
		note2.setText(shareddetails);
		
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			d.dismiss();
				
			}
		});
		d.show();

	}

		
	});
}}
