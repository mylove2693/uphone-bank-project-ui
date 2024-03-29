package ubank.account_manager;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import ubank.base.GeneralActivity;
import ubank.base.MyDialogOne;
import ubank.common.Account_Select;
import ubank.enum_type.EAccState;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.Login;
import ubank.main.R;
import ubank.webservice.ConnectWs;

public class AccountBind extends GeneralActivity {
	private Account_Select accountSelect = null;
	private Resources res;
	private String[] accountType = null;
	private String[] accountValues = null;
	private EditText pdsEdit = null;
	private String password = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 设置当前界面的主体部分
		res = this.getBaseContext().getResources();
		this.setNavigation();
		this.setBody();
	}

	// 设置导航栏
	private void setNavigation() {
		String temp = ">";
		// 设置导航栏“首页”
		this.tvClassFirst.setText(res.getString(R.string.home));
		this.tvClassFirst.setVisibility(View.VISIBLE);
		setListener(tvClassFirst, this, BankMain.class);
		// 设置导航栏“>账户管理”
		this.tvClassSecond.setText(temp
				+ res.getString(R.string.account_manager));
		this.tvClassSecond.setVisibility(View.VISIBLE);
		setListener(tvClassSecond, this, ManagerHome.class);
		// 设置导航栏“>账户绑定”
		this.tvClassThird.setText(temp + res.getString(R.string.bind_account));
		this.tvClassThird.setVisibility(View.VISIBLE);
	}

	// 设置body
	private void setBody() {
		this.addLayout(R.layout.acc_bind_body);
		accountSelect = (Account_Select) findViewById(R.id.account_select);
		if (EHelper.hasInternet(this)) {
			JSONObject json;
			try {
				json = ConnectWs.connect(this, EAccType.NULL,
						EOperation.GET_ACC_TYPE_ALL);
				List<String> name = EHelper.toList(json);
				accountType = new String[name.size()];
				for (int i = 0; i < accountType.length; i++) {
					accountType[i] = name.get(i);
				}
			} catch (IOException e) {
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			}
		} else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
		accountSelect.AddTypeData(accountType);
		loderValueData();
		TextView psdText = (TextView) findViewById(R.id.text_psd).findViewById(
				R.id.Text_View_18);
		psdText.setText(res.getString(R.string.input_password)+":");
		pdsEdit = (EditText) findViewById(R.id.psd).findViewById(R.id.et_psd);
		Button bind = (Button) findViewById(R.id.bind)
				.findViewById(R.id.button);
		bind.setText(res.getString(R.string.bind));

		bind.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				String accType = accountSelect.getAccTypValue();
				String account = accountSelect.getAccNumValue();
				password = pdsEdit.getText().toString();
				if (account != "" && password != "") {
					if (EHelper.hasInternet(AccountBind.this)) {
						JSONObject json = null;
						try {
							if ("信用卡".equals(accType)) {
								json = ConnectWs.connect(AccountBind.this,
										EAccType.CREDIT_CARD,
										EOperation.SET_BIND, account, password);
							} else if ("定期储蓄卡".equals(accType)) {
								json = ConnectWs.connect(AccountBind.this,
										EAccType.TIME_DEPOSITS,
										EOperation.SET_BIND, account, password);
							} else {
								json = ConnectWs.connect(AccountBind.this,
										EAccType.CURRENT_DEPOSIT,
										EOperation.SET_BIND, account, password);
							}
							boolean result = EHelper.toBoolean(json);
							MyDialogOne d1 = new MyDialogOne(AccountBind.this,
									R.style.dialog);
							if (result) {
								d1.setTitleAndInfo("提示", "绑定成功！");
							} else {
								d1.setTitleAndInfo("提示", "绑定失败！");
							}
							d1.Listener(AccountBind.this, ManagerHome.class);
							d1.show();
						} catch (IOException e) {
							Toast.makeText(AccountBind.this, "对不起，服务器未连接",
									Toast.LENGTH_SHORT).show();
							e.printStackTrace();
						}
					} else {
						Toast.makeText(AccountBind.this, "没有连接网络",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(AccountBind.this, "对不起，您未选择账号或未输入密码！",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

	}

	private void loderValueData() {

		accountSelect.AccTypSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						if (EHelper.hasInternet(AccountBind.this)) {
							String type = accountSelect.getAccTypValue();
							try {
								JSONObject json = ConnectWs
										.connect(
												AccountBind.this,
												EAccType.NULL,
												EOperation.GET_ACC,
												Login.userId,
												type,
												EAccState
														.getStateName(EAccState.UNBIND));
								List<String> value = EHelper.toList(json);
								accountValues = new String[value.size()];
								for (int i = 0; i < accountValues.length; i++) {
									accountValues[i] = value.get(i);
								}
								accountSelect.AddNumData(accountValues);
							} catch (IOException e) {
								Toast.makeText(AccountBind.this, "对不起，服务器未连接",
										Toast.LENGTH_SHORT).show();
								e.printStackTrace();
							}
						} else {
							Toast.makeText(AccountBind.this, "没有连接网络",
									Toast.LENGTH_SHORT).show();
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
	}

}
