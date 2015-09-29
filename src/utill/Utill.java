package utill;

import java.util.ArrayList;

import model.GetschemaList;

import com.android.classified.AddAdActivity;
import com.android.classified.R;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Utill {
	
	public static ArrayList<GetschemaList> schemalist;
	static Dialog checkoutDialog;
	
	
	
	
	

	public static void showToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	public static boolean ChechInternetAvalebleOrNot(Context context) {
		if (context == null)
			return false;
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	public static void showToastMessage(Context act) {

		final Dialog d = new JDialog(act, R.style.MDialog);
		d.requestWindowFeature(Window.FEATURE_NO_TITLE);
		d.setCancelable(false);
		d.setContentView(R.layout.no_internet_dialog);
		Button Ok = (Button) d.findViewById(R.id.ok);
		Ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				d.dismiss();
			}
		});

		d.show();

	}
	
	public static void showAdStatus(Context context,String txt1, String txt2) {
	final Dialog d = new JDialog(
			context, R.style.MDialog);
	d.requestWindowFeature(Window.FEATURE_NO_TITLE);
	d.setContentView(R.layout.note);
	
	TextView note1 = (TextView) d
			.findViewById(R.id.note1);
	TextView note2 = (TextView) d
			.findViewById(R.id.note2);
	Button ok = (Button) d.findViewById(R.id.ok);
	note1.setText(txt1);
	note2.setText(txt2);

	ok.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			d.dismiss();

		}
	});
	d.show();
	}
	public static void showCheckOutProgressDialog(final Context ctx, String msg) {
		checkoutDialog = new JDialog(ctx, R.style.MDialog);
		checkoutDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		checkoutDialog.setCancelable(false);
		checkoutDialog.setContentView(R.layout.check_out_dialog);
		TextView textView = (TextView) checkoutDialog.findViewById(R.id.msg);
		textView.setText(msg);
		checkoutDialog.show();

	}

	public static void closeCheckOutProgressDialog() {

		if (checkoutDialog != null)
			checkoutDialog.dismiss();
	}

}
