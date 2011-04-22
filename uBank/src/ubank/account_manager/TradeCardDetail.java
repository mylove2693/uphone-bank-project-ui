package ubank.account_manager;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class TradeCardDetail extends GeneralListActivity {
	private Resources res = null;
	private Intent intent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		res = this.getBaseContext().getResources();
		intent = this.getIntent();
		this.setNavigation();
		// this.setListAdapter(createText_Text(name, value));
		setAccInfo();
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

	private void setAccInfo() {
		String[] name = new String[] { "账号", "账户别名", "更换原因", "领卡网点", "网点地址",
				"工本费用" };
		String[] value = new String[name.length];
		if (EHelper.hasInternet(this)) {
			try {
				JSONObject json = null;
				String accType = intent.getStringExtra("accTypeValue");
				if ("信用卡".equals(accType)) {
					json = ConnectWs.connect(this, EAccType.CREDIT_CARD,
							EOperation.GET_ORDER_INFO, intent
									.getStringExtra("accNumValue"));
				} else if ("定期储蓄卡".equals(accType)) {

					json = ConnectWs.connect(this, EAccType.TIME_DEPOSITS,
							EOperation.GET_ORDER_INFO, intent
									.getStringExtra("accNumValue"));
				} else {
					json = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,
							EOperation.GET_ORDER_INFO, intent
									.getStringExtra("accNumValue"));
				}
				if(json.length() > 0){
				try {
					for (int i = 0; i < name.length; i++) {
						value[i] = json.getString(name[i]);
					}
				} catch (JSONException ex) {
					ex.printStackTrace();
				}
				setListAdapter(createText_Text(name, value));
				}else{
					Toast.makeText(this, "对不起，该账户没有预约信息！", Toast.LENGTH_SHORT).show();
					finish();
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
	}

}
