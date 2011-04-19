package ubank.account_query;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import ubank.base.GeneralActivity;
import ubank.common.Account_Select;
import ubank.enum_type.EAccState;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.Login;
import ubank.main.R;
import ubank.webservice.ConnectWs;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class AccountQueryType extends GeneralActivity {
	private Account_Select accountInfo = null;
	private String[] accountType = null;
	private String[] accountValues = null;
	private Button btnComfirm;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addLayout(R.layout.account_type);
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("账户查询");
		
		accountInfo = (Account_Select)this.findViewById(R.id.account_select);
		//accountType = new String[]{"活期储蓄卡","定期储蓄卡","信用卡"};
		loaderData();

		loderValueData();
		
		btnComfirm = (Button)findViewById(R.id.account_type_comfirm).findViewById(R.id.button);
		btnComfirm.setText(R.string.cc_next);
		btnComfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent  intent = new Intent();
				intent.putExtra("accNumValue", accountInfo.getAccNumValue());
				intent.putExtra("accTypeValue", accountInfo.getAccTypValue());
				intent.setClass(AccountQueryType.this, AccountQuery.class);
				AccountQueryType.this.startActivity(intent);
			}
		});
		
	}
	
	private void loaderData(){
		if (EHelper.hasInternet(this)) {
		try {
			JSONObject json = ConnectWs.connect(this, EAccType.NULL, EOperation.GET_ACC_TYPE_ALL);
			List<String> name = EHelper.toList(json);
			accountType = new String[name.size()];
			for(int i = 0;i < accountType.length;i++){
				accountType[i] = name.get(i);
			}
		} catch (IOException e) {
			Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
			finish();
			e.printStackTrace();
		}
		accountInfo.AddTypeData(accountType);
		}else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
	
	private void loderValueData(){
		accountInfo.AccTypSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (EHelper.hasInternet(AccountQueryType.this)) {
				String type = accountInfo.getAccTypValue();
				try {
					JSONObject json = ConnectWs.connect(AccountQueryType.this, EAccType.NULL, EOperation.GET_ACC,Login.userId,type,EAccState.getStateName(EAccState.BIND));
					List<String> value = EHelper.toList(json);
					accountValues = new String[value.size()];
					for(int i = 0;i < accountValues.length;i++){
						accountValues[i] = value.get(i);
					}
					accountInfo.AddNumData(accountValues);
				} catch (IOException e) {
					Toast.makeText(AccountQueryType.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
				} else {
					Toast.makeText(AccountQueryType.this, "没有连接网络", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
