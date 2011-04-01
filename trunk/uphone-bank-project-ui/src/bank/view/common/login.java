package bank.view.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
public class login extends RelativeLayout{

	public login(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public login(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.xml.login, this, true);
	}

	public login(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
}
