package ubank.account_manager;

import java.util.ArrayList;
import java.util.List;

import ubank.base.GeneralActivity;
import ubank.main.R;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ReservationChangeCardSecond extends GeneralActivity{
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
				ReservationChangeCardSecond.this.finish();
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
		
	}
	
}
