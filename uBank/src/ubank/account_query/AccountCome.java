package ubank.account_query;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import ubank.base.GeneralActivity;
import ubank.common.Time_Select;
import ubank.main.Login;
import ubank.main.R;

public class AccountCome extends GeneralActivity {
	private Time_Select time_select = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayout(R.layout.query_time);
		
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, Login.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("账户查询>");
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("来账查询");
		
		Intent intent = this.getIntent();
		String[] name = new String[]{"accNumValue","accTypeValue"};
		String[] value = new String[]{intent.getStringExtra("accNumValue"),intent.getStringExtra("accTypeValue")};
		
		time_select = (Time_Select)findViewById(R.id.time_select);
		time_select.setParams(name, value);
		time_select.setButtonListener(this, AccountComeList.class);
	}
}
