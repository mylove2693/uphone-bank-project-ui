package ubank.account_manager;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
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

public class ReservationChangeCard extends GeneralActivity{

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
		List<String> secondList = new ArrayList<String>();
		secondList.add("zhong");
		secondList.add("xiao");
		secondList.add("hui");
		
		this.setBody(firstList, secondList);
		
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
				ReservationChangeCard.this.finish();
			}
			
		});
		//设置导航栏“预约换卡”
		this.tvClassThird.setText(temp + res.getString(R.string.reservation_change_card));	
		this.tvClassThird.setVisibility(View.VISIBLE);
		this.tvClassThird.setOnClickListener(new TextView.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	
	public void setBody(List<String> firstList, List<String> secondList)
	{
		this.addLayout(R.layout.acc_res_change_card_first);
		
		TextView accTypeTag = (TextView)findViewById(R.id.text_acc_type)
			.findViewById(R.id.Text_View_18);
		accTypeTag.setText(R.string.account_type_select);
		
		Spinner accType = (Spinner)findViewById(R.id.spinner_acc_type)
			.findViewById(R.id.Spinner);
		ArrayAdapter<String> firstAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, firstList);
		firstAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		accType.setAdapter(firstAdapter);
		accType.setPrompt(res.getString(R.string.accountType));
		accType.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

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
		
		TextView accNoTag = (TextView)findViewById(R.id.text_acc_no)
			.findViewById(R.id.Text_View_18);
		accNoTag.setText(R.string.account_no_select);
		
		Spinner accNO = (Spinner)findViewById(R.id.spinner_acc_no)
			.findViewById(R.id.Spinner);
		ArrayAdapter<String> defaultAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, secondList);
		defaultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		accNO.setAdapter(defaultAdapter);
		accNO.setPrompt(res.getString(R.string.accounNum));
		accNO.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

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
		
		Button butNext = (Button)findViewById(R.id.acc_res_first_next);
		butNext.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ReservationChangeCard.this, ReservationChangeCardSecond.class);
				ReservationChangeCard.this.startActivity(intent);
			}
			
		});
	}
	

}
