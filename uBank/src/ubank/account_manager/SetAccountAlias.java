package ubank.account_manager;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import ubank.account_query.AccountQueryType;
import ubank.base.GeneralActivity;
import ubank.common.Account_Select;
import ubank.enum_type.EAccState;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.Login;
import ubank.main.R;
import ubank.payment.AllPaymentSer;
import ubank.payment.ElseAcc;
import ubank.payment.InputPsw;
import ubank.webservice.ConnectWs;

public class SetAccountAlias extends GeneralActivity{
	private Account_Select accountInfo = null;
	private String[] accountType = null;
	private String[] accountValues = null;
	private TextView txt=null;
	private Button next_btn=null;
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        addLayout(R.layout.account_type);
	        
	        tvClassFirst.setVisibility(View.VISIBLE);
	        //监听
	        tvClassFirst.setText("首页>");
	        setListener(tvClassFirst, this, BankMain.class);
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("账户管理>");
	       //监听
	        setListener(tvClassSecond, this, ManagerHome.class);
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("设置账户别名");
	        
	        accountInfo = (Account_Select)this.findViewById(R.id.account_select);
	        
	        loaderData();
	        loderValueData();
	        
	        //确认缴费按钮
	        next_btn=(Button)findViewById(R.id.account_type_comfirm).findViewById(R.id.button);
	        next_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				if (accountInfo.getAccTypValue() != "") {
					Intent intent = new Intent();
					/**
					 * 将服务器上取得的值传给下一个Activity
					 */
					intent
							.putExtra("accNumValue", accountInfo
									.getAccNumValue());
					intent.putExtra("accTypeValue", accountInfo
							.getAccTypValue());
					intent.setClass(SetAccountAlias.this,
							SetAccountAliasSecond.class);
					SetAccountAlias.this.startActivity(intent);
				}else{
					Toast.makeText(SetAccountAlias.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				}
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
				if (EHelper.hasInternet(SetAccountAlias.this)) {
				String type = accountInfo.getAccTypValue();
				try {
					JSONObject json = ConnectWs.connect(SetAccountAlias.this, EAccType.NULL, EOperation.GET_ACC,Login.userId,type,EAccState.getStateName(EAccState.BIND));
					List<String> value = EHelper.toList(json);
					accountValues = new String[value.size()];
					for(int i = 0;i < accountValues.length;i++){
						accountValues[i] = value.get(i);
					}
					accountInfo.AddNumData(accountValues);
				} catch (IOException e) {
					Toast.makeText(SetAccountAlias.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
				} else {
					Toast.makeText(SetAccountAlias.this, "没有连接网络", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
