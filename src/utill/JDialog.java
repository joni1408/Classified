package utill;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

public class JDialog extends Dialog {

	
	public JDialog(Context context, int theme) {

		super(context, theme);

		getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));

	}
}
