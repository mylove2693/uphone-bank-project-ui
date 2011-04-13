package ubank.account_manager;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import ubank.base.GeneralListActivity;
import ubank.main.BankMain;
import ubank.main.R;

public class TradeCardDetail  extends GeneralListActivity {
	private Resources res = null;
	String[] name={"s","d","ds","das"};
	String[] value={"dsad","dsadas","sad","dsad"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		res = this.getBaseContext().getResources();
		this.setNavigation();
		this.setListAdapter(createText_Text(name, value));
		
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

}
