package ubank.account_manager;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import ubank.base.GeneralListActivity;
import ubank.main.BankMain;
import ubank.main.R;

public class AccountInfoShow extends GeneralListActivity{

	private Resources res = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		res = this.getBaseContext().getResources();
		this.setNavigation();
		this.setListAdapter(this.createText_Text_GrayText(this.getShowIdentifier(), this.getShowValue(), this.getShowGrayText()));
	}

	private String[] getShowIdentifier()
	{
		
		String[] items = new String[9];
		//帐号
		items[0] = res.getString(R.string.accounNum);
		//账户别名
		items[1] = res.getString(R.string.account_alias);
		//账户类型
		items[2] = res.getString(R.string.accountType);
//		币种
		items[3] = res.getString(R.string.currency);
		//余额
		items[4] = res.getString(R.string.balance);
		//账户状态
		items[5] = res.getString(R.string.account_state);
		//是否绑定
		items[6] = res.getString(R.string.is_activation);
		//开户行
		items[7] = res.getString(R.string.opening_bank);
		//开户日
		items[8] = res.getString(R.string.account_opening_day);
		return items;
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
			intent.setClass(AccountInfoShow.this, TradeCardDetail.class);
			AccountInfoShow.this.startActivity(intent);
			
			
		}
	}
	private String[] getShowValue()
	{	
		String[] items = new String[9];
		for(int i=0;i<9;i++)
			{
			items[i]=String.valueOf(i);			
			}
		return items;
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
		this.tvClassSecond.setVisibility(View.VISIBLE);
	}
}
