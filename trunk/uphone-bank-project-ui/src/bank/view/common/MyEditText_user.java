package bank.view.common;

import android.content.Context;
import android.util.AttributeSet;

public class MyEditText_user extends android.widget.EditText{

	public MyEditText_user(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyEditText_user(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.setHeight(20);
		this.setWidth(80);
		this.setSingleLine(true);
		this.setMaxEms(12);
		this.setMinEms(4);
		//this.set
	}

	public MyEditText_user(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

}
