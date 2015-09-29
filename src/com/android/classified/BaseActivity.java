package com.android.classified;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity {
	protected FrameLayout frameLayout;
	static ListView mDrawerList;
	ArrayList<String> listArray;
	protected static int position;
	public static boolean isLaunch = true;
	TextView ui_hot;
	Button notifCountbutton;
	SearchView mSearchView;
	TextView notifCount;
	static View view;
	public SensorManager mSensorManager;
	Handler h ;

	@SuppressLint({ "NewApi", "InlinedApi" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.base_activity);
		h = new Handler();
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

			window.setStatusBarColor(Color.parseColor("#3B7D9F"));

		}
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#0B5D88")));
		actionBar.setTitle("Classified");

		actionBar.setDisplayShowCustomEnabled(true);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		// actionBar.setLogo(R.drawable.menu_home);
		// actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);

	}

}
