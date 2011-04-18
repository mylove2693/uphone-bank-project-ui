package ubank.credit;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import ubank.account_query.AccountCome;
import ubank.account_query.AccountInventory;
import ubank.account_query.AccountQuery;
import ubank.account_query.AccountQueryInfo;
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
import android.widget.Button;
import android.widget.Toast;

public class SelectCreditCard extends GeneralActivity {

	private Account_Select accSelect;
	private Button btnNext;
	private OnClickListener btnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String accType = EAccType.getAccTypeName(EAccType.CREDIT_CARD);
			String accNum = accSelect.getAccNumValue();
			Intent intent = getIntent();
			intent.putExtra("accTypeValue", accType);
			intent.putExtra("accNumValue", accNum);
			String toClass = intent.getStringExtra("toClass");
			if (toClass.equals("AccountCome")) {
				intent.setClass(SelectCreditCard.this, AccountCome.class);
			} else if (toClass.equals("AccountInventory")) {
				intent.setClass(SelectCreditCard.this, AccountInventory.class);
			} else {
				intent.setClass(SelectCreditCard.this, AccountQueryInfo.class);
			}
			startActivity(intent);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.cc_select_creditcard);
		addLayout(R.layout.account_type);
		initializeData();// 初始化数据
		btnNext.setOnClickListener(btnClick);
	}

	private void initializeData() {
		// TODO Auto-generated method stub
		accSelect = (Account_Select) findViewById(R.id.account_select);

		// // 检查网络连接
		if (EHelper.hasInternet(this)) {
			// 连接服务器...
			String accType = EAccType.getAccTypeName(EAccType.CREDIT_CARD);
			String accState = EAccState.getStateName(EAccState.BIND);
			JSONObject jsonObj = new JSONObject();
			try {
				jsonObj = ConnectWs.connect(SelectCreditCard.this, EAccType.NULL, EOperation.GET_ACC, Login.userId,
						accType, accState);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			}
			List<String> lstAcc = EHelper.toList(jsonObj);
			String[] numValue = new String[lstAcc.size()];
			numValue = lstAcc.toArray(numValue);
			// 给下面的spinner添加数据
			accSelect.AddNumData(numValue);
		} else {
			Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();
			finish();
		}
		String[] strings = new String[] { "信用卡" };
		accSelect.AddTypeData(strings);
		accSelect.AccTypSpinner.setClickable(false);

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("信用卡>");
		setListener(tvClassSecond, this, CreditCardMain.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("信用卡选择");

		btnNext = (Button) (findViewById(R.id.account_type_comfirm).findViewById(R.id.button));
	}

}
