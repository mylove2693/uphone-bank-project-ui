package bank.view.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.GridView;


public class bankmain extends GridView{
	
	
	
	public bankmain(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public bankmain(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		LayoutInflater.from(context).inflate(R.xml.bankmain, this, true);
		
		 //this.setAdapter(new ImageAdapter(context, mThumbIds));

	}

	public bankmain(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
}