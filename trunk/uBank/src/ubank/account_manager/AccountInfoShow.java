package ubank.account_manager;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;

public class AccountInfoShow extends GeneralListActivity{
	private Intent intent = null;
	private Resources res = null;
	private String accType = "";
	private String account = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		res = this.getBaseContext().getResources();
		intent  = this.getIntent();
		this.setNavigation();
		this.setAccInfo();
	}

	/**
	 * 为有预约换卡的做一个监听
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if(id==5){
			Intent intent = new Intent();
			intent.putExtra("accTypeValue", accType);
			intent.putExtra("accNumValue", account);
			intent.setClass(AccountInfoShow.this, TradeCardDetail.class);
			AccountInfoShow.this.startActivity(intent);
		}
	}
	
	private void setAccInfo() {
		String[] name;
		String[] value;
		JSONObject json = null;
		accType = intent.getStringExtra("accTypeValue");
		account = intent.getStringExtra("accNumValue");
		if ("信用卡".equals(accType)) {
			name = new String[] { "账户", "账户别名", "账户类型", "币种", "账户状态", "是否绑定",
					"开户行", "开户日" };
			value = new String[name.length];
			json = ConnectWs.connect(this, EAccType.CREDIT_CARD,
					EOperation.GET_ACC_INFO, account);
			try {
				for (int i = 0; i < name.length; i++) {
					value[i] = json.getString(name[i]);
				}
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
		} else if ("定期储蓄卡".equals(accType)) {
			name = new String[] { "账户", "账户别名", "账户类型", "币种", "余额", "账户状态",
					"是否绑定", "开户行", "开户日" };
			value = new String[name.length];
			json = ConnectWs.connect(this, EAccType.TIME_DEPOSITS,
					EOperation.GET_ACC_INFO, account);
			try {
				for (int i = 0; i < name.length; i++) {
					value[i] = json.getString(name[i]);
				}
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
		} else {
			name = new String[] { "账户", "账户别名", "账户类型", "币种", "余额", "账户状态",
					"是否绑定", "开户行", "开户日" };
			value = new String[name.length];
			json = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,
					EOperation.GET_ACC_INFO, account);
			try {
				for (int i = 0; i < name.length; i++) {
					value[i] = json.getString(name[i]);
				}
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
		}

		this.setListAdapter(createText_Text_GrayText(name, value, this
				.getShowGrayText()));
	}
	
	private List<Integer> getShowGrayText()
	{
		
		List<Integer> items= new ArrayList<Integer>();
		items.add(new Integer(5));
		return items;
	}
	
	//设置导航栏
	private void setNavigation()
	{
		String temp = ">";
		// 设置导航栏“首页”
		this.tvClassFirst.setText(res.getString(R.string.home));
		this.tvClassFirst.setVisibility(View.VISIBLE);
		setListener(tvClassFirst, this, BankMain.class);
		// 设置导航栏“>账户管理”
		this.tvClassSecond.setText(temp + res.getString(R.string.account_manager));	
		this.tvClassSecond.setVisibility(View.VISIBLE);
		setListener(tvClassSecond, this, ManagerHome.class);	
		// 设置导航栏“账户信息”
		this.tvClassThird.setText(temp + res.getString(R.string.account_info));	
		this.tvClassThird.setVisibility(View.VISIBLE);
	}
}
