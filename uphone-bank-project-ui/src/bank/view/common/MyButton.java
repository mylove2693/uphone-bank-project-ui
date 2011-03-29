package bank.view.common;

import android.content.Context;
import android.util.AttributeSet;

public class MyButton extends android.widget.Button{

	public MyButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.setText("自定义按钮");
		this.setTextSize(16);
		this.setHeight(55);
		this.setWidth(180);
		this.setBackgroundResource(R.color.selector);
		
		
	}

	public MyButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
}
