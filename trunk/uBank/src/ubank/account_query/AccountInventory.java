package ubank.account_query;

import ubank.base.GeneralActivity;
import ubank.common.Time_Select;
import ubank.main.BankMain;
import ubank.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccountInventory extends GeneralActivity {
	private Time_Select time_select = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addLayout(R.layout.query_time);
		
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("账户查询>");
		setListener(tvClassSecond, this, AccountQueryType.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("明细查询");
		
		Intent intent = this.getIntent();
		String[] name = new String[]{"accNumValue","accTypeValue"};
		String[] value = new String[]{intent.getStringExtra("accNumValue"),intent.getStringExtra("accTypeValue")};
		
		time_select = (Time_Select)findViewById(R.id.time_select);
		time_select.setParams(name, value);
		time_select.setButtonListener(this, AccountInventoryList.class);
	}
}
