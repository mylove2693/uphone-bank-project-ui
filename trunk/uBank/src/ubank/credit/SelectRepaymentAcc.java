package ubank.credit;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import ubank.base.GeneralActivity;
import ubank.base.MyDialogOne;
import ubank.base.MyDialogTwo;
import ubank.common.Account_Select;
import ubank.enum_type.EAccState;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.Login;
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

		accSelect = (Account_Select) findViewById(R.id.account_select);
		// 检查网络连接
		if (EHelper.hasInternet(this)) {
			// 连接服务器...
			JSONObject jsonObj = new JSONObject();
			try {
				jsonObj = ConnectWs.connect(SelectRepaymentAcc.this, EAccType.NULL,
						EOperation.GET_ACC_TYPE_ON_CREDITCARD, "");
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
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("信用卡>");
		setListener(tvClassSecond, this, CreditCardMain.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("信用卡还款");

		btnNext = (Button) (findViewById(R.id.account_type_comfirm).findViewById(R.id.button));
	}

	private OnClickListener btnClick = new OnClickListener() {
		MyDialogTwo dialogTwo;

		@Override
		public void onClick(View v) {

			boolean isActive = false;
			// 检查是否有激活
			final String accType = accSelect.getAccTypValue();
			final String accNum = accSelect.getAccNumValue();
			if (EHelper.hasInternet(SelectRepaymentAcc.this)) {
				// 如果有网络
				try {
					JSONObject jsonObj = ConnectWs.connect(SelectRepaymentAcc.this,
							EAccType.getEAccTypeByName(accType), EOperation.ACC_IS_ACTIVE, accNum);
					isActive = jsonObj.getBoolean("result");
				} catch (IOException e) {
					Toast.makeText(SelectRepaymentAcc.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
					return;
				} catch (JSONException e) {
					e.printStackTrace();
				}
				if (!isActive) {
					// 如果没有激活
					dialogTwo = new MyDialogTwo(SelectRepaymentAcc.this, R.style.dialog).setTitleAndInfo("提示信息",
							"此账户第一次使用，是否激活").setPwdVisibility();
					dialogTwo.setOkToService(new OnClickListener() {

						@Override
						public void onClick(View v) {
							String pwd = dialogTwo.getPwd();
							try {
								JSONObject jsonObj = ConnectWs.connect(SelectRepaymentAcc.this,
										EAccType.getEAccTypeByName(accType), EOperation.SET_ACC_ACTIVE, accNum, pwd);
								boolean flag = jsonObj.getBoolean("result");
								dialogTwo.dismiss();
								if (flag) {
									// 如果激活成功
									Toast.makeText(SelectRepaymentAcc.this, "激活成功", Toast.LENGTH_SHORT).show();
								} else {
									Toast.makeText(SelectRepaymentAcc.this, "激活不成功，请检查密码", Toast.LENGTH_SHORT).show();
									return;
								}
							} catch (IOException e) {
								Toast.makeText(SelectRepaymentAcc.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
								e.printStackTrace();
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					}).show();

				}

				Intent intent = getIntent();
				intent.setClass(SelectRepaymentAcc.this, ConfrimRepayment.class);
				// 添加还款账户
				Bundle bundle = new Bundle();
				bundle.putString("accType", accType);
				bundle.putString("fromAcc", accNum);
				intent.putExtras(bundle);
				startActivity(intent);
			} else {
				// 如果没有网络直接返回
				Toast.makeText(SelectRepaymentAcc.this, "没有网络", Toast.LENGTH_SHORT).show();
				return;
			}
		}
	};

	// 下拉列表
	private OnItemSelectedListener itemSelected = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// TODO 获取父类

			List<String> lstAcc;
			switch (parent.getId()) {
			case 0:

				String accTypeName = accSelect.AccTypSpinner.getSelectedItem().toString().trim();

				if (accTypeName.equals("活期储蓄卡")) {
					// 账户类型
					String accType = EAccType.getAccTypeName(EAccType.CURRENT_DEPOSIT);
					// 所需账户状态
					String accState = EAccState.getStateName(EAccState.BIND);
					// 检查网络连接
					if (EHelper.hasInternet(SelectRepaymentAcc.this)) {
						// 连接服务器...
						JSONObject jsonObj = new JSONObject();
						try {
							jsonObj = ConnectWs.connect(SelectRepaymentAcc.this, EAccType.NULL, EOperation.GET_ACC,
									Login.userId, accType, accState);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							Toast.makeText(SelectRepaymentAcc.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
							e.printStackTrace();
							return;
						}
						lstAcc = EHelper.toList(jsonObj);
					} else {
						Toast.makeText(SelectRepaymentAcc.this, "没有网络", Toast.LENGTH_SHORT).show();
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
