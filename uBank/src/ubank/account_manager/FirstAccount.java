package ubank.account_manager;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import ubank.base.GeneralActivity;
import ubank.main.R;

public class FirstAccount extends GeneralActivity{

	private Resources res = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		res = this.getBaseContext().getResources();
		this.setNavigation();
		
		//测试数据
		List<String> firstList = new ArrayList<String>();
		firstList.add("zhong");
		firstList.add("xiao");
		firstList.add("hui");
		List<String> defaultList = new ArrayList<String>();
		defaultList.add("zhong");
		defaultList.add("xiao");
		defaultList.add("hui");
		
		this.setBody(firstList, defaultList);
		
	}
	
	public void setNavigation()
	{
		String temp = ">";
		//设置导航栏“首页”
		this.tvClassFirst.setText(res.getString(R.string.home));
		this.tvClassFirst.setVisibility(View.VISIBLE);
		this.tvClassFirst.setOnClickListener(new TextView.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
		//设置导航栏“账户管理”
		this.tvClassSecond.setText(temp + res.getString(R.string.account_manager));	
		this.tvClassSecond.setVisibility(View.VISIBLE);
		this.tvClassSecond.setOnClickListener(new TextView.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FirstAccount.this.finish();
			}
			
		});
		//设置导航栏“首选账户设置”
		this.tvClassThird.setText(temp + res.getString(R.string.first_acc_set));	
		this.tvClassThird.setVisibility(View.VISIBLE);
		this.tvClassThird.setOnClickListener(new TextView.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public void setBody(List<String> firstList, List<String> defaultList)
	{
		
		this.addLayout(R.layout.acc_first_body);
		
		TextView currFirstAccTag = (TextView)findViewById(R.id.text_curr_first_acc)
			.findViewById(R.id.Text_View_18);
		currFirstAccTag.setText(R.string.current_first_acc);
		
		Spinner currFirstAcc = (Spinner)findViewById(R.id.spinner_curr_first_acc)
			.findViewById(R.id.Spinner);
		ArrayAdapter<String> firstAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, firstList);
		firstAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		currFirstAcc.setAdapter(firstAdapter);
		currFirstAcc.setPrompt(res.getString(R.string.first_acc));
		currFirstAcc.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		TextView currDefAccTag = (TextView)findViewById(R.id.text_curr_default_acc)
			.findViewById(R.id.Text_View_18);
		currDefAccTag.setText(R.string.current_default_acc);
		
		Spinner currDefAcc = (Spinner)findViewById(R.id.spinner_curr_default_acc)
			.findViewById(R.id.Spinner);
		ArrayAdapter<String> defaultAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, defaultList);
		defaultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		currDefAcc.setAdapter(defaultAdapter);
		currDefAcc.setPrompt(res.getString(R.string.default_acc));
		currDefAcc.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		Button butNext = (Button)findViewById(R.id.acc_first_next);
		butNext.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

}
