package ubank.payment;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import ubank.account_query.AccountQuery;
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
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ElseAcc extends GeneralActivity {
	private Account_Select accountInfo = null;
	private String[] accountType = null;
	private String[] accountValues = null;
	private Button btnComfirm;
	String userid = "";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addLayout(R.layout.account_type);
		tvClassFirst.setVisibility(View.VISIBLE);
		// 监听
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		// 监听
		tvClassSecond.setText("自助缴费>");
		setListener(tvClassSecond, this, AllPaymentSer.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("其他账户选择");
		
		accountInfo = (Account_Select)this.findViewById(R.id.account_select);
		//accountType = new String[]{"活期储蓄卡","定期储蓄卡","信用卡"};
		userid = "1";
		loaderData();

		loderValueData();
		
		btnComfirm = (Button)findViewById(R.id.account_type_comfirm).findViewById(R.id.button);
		btnComfirm.setText(R.string.cc_next);
		btnComfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent  intent =getIntent();//需要传Bundle数据
				intent.putExtra("accType", accountInfo.getAccTypValue());//类型
				intent.putExtra("account", accountInfo.getAccNumValue());//账号
				intent.setClass(ElseAcc.this, InputPswElse.class);
				ElseAcc.this.startActivity(intent);
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
				if (EHelper.hasInternet(ElseAcc.this)) {
				String type = accountInfo.getAccTypValue();
				try {
					JSONObject json = ConnectWs.connect(ElseAcc.this, EAccType.NULL, EOperation.GET_ACC,userid,type,EAccState.getStateName(EAccState.BIND));
					List<String> value = EHelper.toList(json);
					accountValues = new String[value.size()];
					for(int i = 0;i < accountValues.length;i++){
						accountValues[i] = value.get(i);
					}
					accountInfo.AddNumData(accountValues);
				} catch (IOException e) {
					Toast.makeText(ElseAcc.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
				} else {
					Toast.makeText(ElseAcc.this, "没有连接网络", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}


