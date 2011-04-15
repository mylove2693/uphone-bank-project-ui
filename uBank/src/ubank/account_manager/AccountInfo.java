package ubank.account_manager;

import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import ubank.account_query.AccountQueryType;
import ubank.base.GeneralActivity;
import ubank.common.Account_Select;
import ubank.enum_type.EAccState;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;

public class AccountInfo extends GeneralActivity {

	private Resources res = null;
	private Account_Select accountSelect = null;
	private Button butContinue = null;
	private String[] accountType = null;
	private String[] accountValues = null;
	private String userid = "1";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		res = this.getBaseContext().getResources();
		this.setNavigation();
		// 设置账户信息界面主体部分
		this.addLayout(R.layout.acc_info_body);
//		this.setBody(new String[] { "zhong", "xiao", "hui" }, new String[] {
//				"zhong", "xiao", "hui" });
		setBody();
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
	private void setBody() {
		accountSelect = (Account_Select) findViewById(R.id.account_select);
		JSONObject json = ConnectWs.connect(this, EAccType.NULL, EOperation.GET_ACC_TYPE_ALL);
		List<String> name = EHelper.toList(json);
		accountType = new String[name.size()];
		for(int i = 0;i < accountType.length;i++){
			accountType[i] = name.get(i);
		}
		accountSelect.AddTypeData(accountType);
		loderValueData();
		butContinue = (Button) findViewById(R.id.but_continue);
		butContinue.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("accNumValue", accountSelect.getAccNumValue());
				intent.putExtra("accTypeValue", accountSelect.getAccTypValue());
				intent.setClass(AccountInfo.this, AccountInfoShow.class);
				AccountInfo.this.startActivity(intent);
			}
		});
	}
		
		private void loderValueData(){
			
			accountSelect.AccTypSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					String type = accountSelect.getAccTypValue();
					JSONObject json = ConnectWs.connect(AccountInfo.this, EAccType.NULL, EOperation.GET_ACC,userid,type,EAccState.getStateName(EAccState.BIND));
					List<String> value = EHelper.toList(json);
					accountValues = new String[value.size()];
					for(int i = 0;i < accountValues.length;i++){
						accountValues[i] = value.get(i);
					}
					accountSelect.AddNumData(accountValues);
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
	}

}
