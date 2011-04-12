package ubank.account_manager;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ubank.base.GeneralActivity;
import ubank.main.R;

public class AccountBind extends GeneralActivity{

	private Resources res;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//设置当前界面的主体部分
		res = this.getBaseContext().getResources();
		this.setNavigation();
		this.setBody();
	}
	
	//设置导航栏
	private void setNavigation()
	{
		String temp = ">";
		this.tvClassFirst.setText(res.getString(R.string.home));
		this.tvClassFirst.setVisibility(View.VISIBLE);
		this.tvClassFirst.setOnClickListener(new TextView.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
		this.tvClassSecond.setText(temp + res.getString(R.string.account_manager));	
		this.tvClassSecond.setVisibility(View.VISIBLE);
		this.tvClassSecond.setOnClickListener(new TextView.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AccountBind.this.finish();
			}
			
		});
		this.tvClassThird.setText(temp + res.getString(R.string.bind_account));	
		this.tvClassThird.setVisibility(View.VISIBLE);
		this.tvClassThird.setOnClickListener(new TextView.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	//设置body
	private void setBody()
	{
		this.addLayout(R.layout.acc_bind_body);
		
		TextView psdText=(TextView)findViewById(R.id.text_psd).findViewById(R.id.Text_View_18);
		psdText.setText(res.getString(R.string.input_password));
		EditText pdsEdit=(EditText)findViewById(R.id.psd).findViewById(R.id.et_psd);
		Button bind=(Button)findViewById(R.id.bind).findViewById(R.id.button);
		bind.setText(res.getString(R.string.bind));
		bind.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

}
