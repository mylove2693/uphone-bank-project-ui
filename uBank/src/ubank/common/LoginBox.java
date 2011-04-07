package ubank.common;

import ubank.main.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
public class LoginBox extends RelativeLayout{

	public LoginBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public LoginBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.xml.login, this, true);
	}

	public LoginBox(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
}
