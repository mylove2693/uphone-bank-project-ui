package ubank.account_manager;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ubank.base.GeneralActivity;
import ubank.common.Account_Select;
import ubank.main.BankMain;
import ubank.main.R;

public class AccountInfo extends GeneralActivity {

	private Resources res = null;
	private Account_Select accountSelect = null;
	private Button butContinue = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		res = this.getBaseContext().getResources();
		this.setNavigation();
		// 设置账户信息界面主体部分
		this.addLayout(R.layout.acc_info_body);
		this.setBody(new String[] { "zhong", "xiao", "hui" }, new String[] {
				"zhong", "xiao", "hui" });
	}

	// 设置导航栏
	private void setNavigation() {
		String temp = ">";
		// 设置导航栏“首页”
		this.tvClassFirst.setText(res.getString(R.string.home));
		this.tvClassFirst.setVisibility(View.VISIBLE);
		setListener(tvClassFirst, this, BankMain.class);
		// 设置导航栏“账户管理”
		this.tvClassSecond.setText(temp
				+ res.getString(R.string.account_manager));
		this.tvClassSecond.setVisibility(View.VISIBLE);
		setListener(tvClassSecond, this, ManagerHome.class);
		// 设置导航栏“账户信息”
		this.tvClassThird.setText(temp + res.getString(R.string.account_info));
		this.tvClassThird.setVisibility(View.VISIBLE);
	
	}

	// 设置body
	private void setBody(String[] types, String[] nums) {
		accountSelect = (Account_Select) findViewById(R.id.account_select);
		accountSelect.AddTypeData(types);
		accountSelect.AddNumData(nums);
		butContinue = (Button) findViewById(R.id.but_continue);
		butContinue.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(AccountInfo.this, AccountInfoShow.class);
				AccountInfo.this.startActivity(intent);
			}
		});
	}

}
