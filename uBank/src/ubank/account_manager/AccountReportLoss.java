package ubank.account_manager;

import java.util.List;

import org.json.JSONObject;

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
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemSelectedListener;

public class AccountReportLoss extends GeneralActivity{
	private Account_Select accountInfo = null;
	private Button btnComfirm;
	private String[] accountType = null;
	private String[] accountValues = null;
	private String userid = "2";
	private Resources res = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//设置当前界面主体
		this.addLayout(R.layout.account_type);
		
		res = this.getBaseContext().getResources();
		String flag = ">";
		
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText(res.getString(R.string.home) + flag);
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText(res.getString(R.string.account_manager) + flag);
		setListener(tvClassSecond, this, ManagerHome.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText(R.string.acc_report_loss);
		
		accountInfo = (Account_Select)findViewById(R.id.account_select);
		loaderData();
		loderValueData();
		
		btnComfirm = (Button)findViewById(R.id.account_type_comfirm).findViewById(R.id.button);
		btnComfirm.setText(R.string.cc_next);
		btnComfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("accTypeValue", accountInfo.getAccTypValue());
				intent.putExtra("accNumValue", accountInfo.getAccNumValue());
				intent.setClass(AccountReportLoss.this, AccountReportLossSecond.class);
				AccountReportLoss.this.startActivity(intent);
			}
		});
	}
	
	private void loaderData(){
		JSONObject json = ConnectWs.connect(this, EAccType.NULL, EOperation.GET_ACC_TYPE_ALL);
		List<String> name = EHelper.toList(json);
		accountType = new String[name.size()];
		for(int i = 0;i < accountType.length;i++){
			accountType[i] = name.get(i);
		}
		accountInfo.AddTypeData(accountType);
	}
	
	private void loderValueData(){
		accountInfo.AccTypSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String type = accountInfo.getAccTypValue();
				JSONObject json = ConnectWs.connect(AccountReportLoss.this, EAccType.NULL, EOperation.GET_ACC,userid,type,EAccState.getStateName(EAccState.UNLOSS));
				List<String> value = EHelper.toList(json);
				accountValues = new String[value.size()];
				for(int i = 0;i < accountValues.length;i++){
					accountValues[i] = value.get(i);
				}
				accountInfo.AddNumData(accountValues);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
