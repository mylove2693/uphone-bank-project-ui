package ubank.credit;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import ubank.base.GeneralActivity;
import ubank.common.Account_Select;
import ubank.enum_type.EAccState;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;

public class SelectRepaymentAcc extends GeneralActivity {

	private Button btnNext;
	// private Spinner spnrAccType;
	// private Spinner spnrAccNum;
	private Account_Select accSelect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayout(R.layout.account_type);

		initializeData();// 初始化数据

		btnNext.setOnClickListener(btnClick);
	}

	private void initializeData() {
		// TODO 初始化数据
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("信用卡>");
		setListener(tvClassSecond, this, CreditCardMain.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("信用卡还款");

		btnNext = (Button) (findViewById(R.id.account_type_comfirm)
				.findViewById(R.id.button));
		accSelect = (Account_Select) findViewById(R.id.account_select);
		// 检查网络连接
		if (EHelper.hasInternet(this)) {
			// 连接服务器...
			JSONObject jsonObj = new JSONObject();
			try {
				jsonObj = ConnectWs.connect(SelectRepaymentAcc.this,
						EAccType.NULL, EOperation.GET_ACC_TYPE_ON_CREDITCARD,
						"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			}
			List<String> lstAcc = EHelper.toList(jsonObj);
			String[] strings = new String[lstAcc.size()];
			strings = lstAcc.toArray(strings);
			accSelect.AddTypeData(strings);
		} else {
			Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();
			finish();
		}
		accSelect.AccTypSpinner.setOnItemSelectedListener(itemSelected);
	}

	private OnClickListener btnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 检查是否有激活

			String accType = accSelect.getAccTypValue();
			String accNum = accSelect.getAccNumValue();
			Intent intent = new Intent(SelectRepaymentAcc.this,
					ConfrimRepayment.class);
			startActivity(intent);
		}
	};

	// 下拉列表
	private OnItemSelectedListener itemSelected = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO 获取父类

			Log.v("asdf", Integer.toString(parent.getId()));
			List<String> lstAcc;
			switch (parent.getId()) {
			case 0:

				String accTypeName = accSelect.AccTypSpinner.getSelectedItem()
						.toString();

				if (accTypeName.equals("活期储蓄卡")) {
					// 账户类型
					String accType = EAccType
							.getAccTypeName(EAccType.CURRENT_DEPOSIT);
					// 所需账户状态
					String accState = EAccState.getStateName(EAccState.BIND);
					// 检查网络连接
					if (EHelper.hasInternet(SelectRepaymentAcc.this)) {
						// 连接服务器...
						JSONObject jsonObj = new JSONObject();
						try {
							jsonObj = ConnectWs.connect(
									SelectRepaymentAcc.this, EAccType.NULL,
									EOperation.GET_ACC, "userid", accType,
									accState);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							Toast.makeText(SelectRepaymentAcc.this,
									"对不起，服务器未连接", Toast.LENGTH_SHORT).show();
							e.printStackTrace();
							return;
						}
						lstAcc = EHelper.toList(jsonObj);
					} else {
						Toast.makeText(SelectRepaymentAcc.this, "没有网络",
								Toast.LENGTH_SHORT).show();
						return;
					}

					String[] strings = new String[lstAcc.size()];
					strings = lstAcc.toArray(strings);

					if (lstAcc.size() != 0) {
						accSelect.AddNumData(strings);
						accSelect.AccNumSpinner.setClickable(true);
					} else {
						accSelect.AccNumSpinner.setClickable(false);
					}
				}

				break;
			case 1:

				break;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}

	};
}
