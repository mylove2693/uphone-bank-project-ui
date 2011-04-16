package ubank.account_query;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.webservice.ConnectWs;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AccountQueryInfo extends GeneralListActivity {
	private Intent intent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("账户查询>");
		setListener(tvClassSecond, this, AccountQueryType.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("账户信息及余额查询");
		intent = this.getIntent();

		setAccInfo();
	}

	private void setAccInfo() {
		String[] name;
		String[] value;
		JSONObject json = null;
		String accType = intent.getStringExtra("accTypeValue");
		if (EHelper.hasInternet(this)) {
			try {
				if ("信用卡".equals(accType)) {
					name = new String[] { "账户", "账户类型", "币种", "信用额度", "可用度",
							"预借现金可用度", "每月的账单日", "本期还款额", "最低还款额", "到期还款日" };
					value = new String[name.length];
					json = ConnectWs.connect(this, EAccType.CREDIT_CARD,
							EOperation.GET_ACC_INFO, intent
									.getStringExtra("accNumValue"));
					try {
						for (int i = 0; i < name.length; i++) {
							value[i] = json.getString(name[i]);
						}
					} catch (JSONException ex) {
						ex.printStackTrace();
					}
				} else if ("定期储蓄卡".equals(accType)) {
					name = new String[] { "账户", "账户类型", "币种", "余额", "存期",
							"起息月", "利率" };
					value = new String[name.length];
					json = ConnectWs.connect(this, EAccType.TIME_DEPOSITS,
							EOperation.GET_ACC_INFO, intent
									.getStringExtra("accNumValue"));
					try {
						for (int i = 0; i < name.length; i++) {
							value[i] = json.getString(name[i]);
						}
					} catch (JSONException ex) {
						ex.printStackTrace();
					}
				} else {
					name = new String[] { "账户", "账户类型", "币种", "余额" };
					value = new String[name.length];
					json = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,
							EOperation.GET_ACC_INFO, intent
									.getStringExtra("accNumValue"));
					try {
						for (int i = 0; i < name.length; i++) {
							value[i] = json.getString(name[i]);
						}
					} catch (JSONException ex) {
						ex.printStackTrace();
					}
				}

				setListAdapter(createText_Text(name, value));
			} catch (IOException e) {
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			}
		} else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
}
