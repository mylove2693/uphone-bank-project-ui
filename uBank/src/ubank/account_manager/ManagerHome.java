package ubank.account_manager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import ubank.base.GeneralActivity;
import ubank.base.GeneralListActivity;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.payment.AllPaymentSer;
import ubank.payment.HistoryCost;
import ubank.payment.Lately;
import ubank.payment.ManageCost;
import ubank.payment.Speedy;
import ubank.payment.WaitCostItem;

public class ManagerHome extends GeneralListActivity{
	
	private static final int ACC_INFO = 0;
	private static final int ACC_BIND = 1;
	private static final int ACC_LOSS = 2;	
	private static final int RES_CHANGE_CARD = 3;
	private static final int FIRST_ACC_SET = 4;
	private static final int ADD_ACC = 5;
	private static final int DEL_ACC = 6;
	private static final int SET_ACC_ALIAS = 7;

	private ListView managerList = null;
	private Resources res = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		res = this.getBaseContext().getResources();
		this.setNavigation();
		String[] strs = this.getManagerItem();
		this.setListAdapter(this.createImg_Text_ImgAdapter(strs));
			
	}
	
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		switch(position)
		{
			case ACC_INFO:
			{
				Intent intent = new Intent();
				intent.setClass(ManagerHome.this, AccountInfo.class);
				ManagerHome.this.startActivity(intent);
				break;
			}
			case ACC_BIND:
			{
				Intent intent = new Intent();
				intent.setClass(ManagerHome.this, AccountBind.class);
				ManagerHome.this.startActivity(intent);
				break;
			}
			case FIRST_ACC_SET:
			{
				Intent intent = new Intent();
				String num="62220323211";
				intent.putExtra("num",num);
				intent.setClass(ManagerHome.this, FirstAccount.class);
				ManagerHome.this.startActivity(intent);
				break;
			}
			case RES_CHANGE_CARD:
			{
				Intent intent = new Intent();
				intent.setClass(ManagerHome.this, ReservationChangeCard.class);
				ManagerHome.this.startActivity(intent);
				break;
			}
			case ACC_LOSS:
			{
				Intent intent = new Intent();
				intent.setClass(ManagerHome.this, AccountReportLoss.class);
				ManagerHome.this.startActivity(intent);
				break;
			}
			case ADD_ACC:
			{
				Intent intent = new Intent();
				intent.setClass(ManagerHome.this, AddAccount.class);
				ManagerHome.this.startActivity(intent);
				break;
			}
			case DEL_ACC:
			{
				Intent intent = new Intent();
				intent.setClass(ManagerHome.this, DeleteAccount.class);
				ManagerHome.this.startActivity(intent);
				break;
			}
			case SET_ACC_ALIAS:
			{
				Intent intent = new Intent();
				intent.setClass(ManagerHome.this, SetAccountAlias.class);
				ManagerHome.this.startActivity(intent);
			}
		}
	}



	private String[] getManagerItem()
	{
		
		String[] items = new String[8];
		items[0] = res.getString(R.string.account_info);
		items[1] = res.getString(R.string.bind_account);
		items[2] = res.getString(R.string.acc_report_loss);
		items[3] = res.getString(R.string.reservation_change_card);
		items[4] = res.getString(R.string.first_acc_set);
		items[5] = res.getString(R.string.add_account);
		items[6] = res.getString(R.string.delete_account);
		items[7] = res.getString(R.string.set_account_alias);
		return items;
	}
	
	private void setNavigation()
	{
		String temp = ">";
		//设置导航栏“首页”
		this.tvClassFirst.setText(res.getString(R.string.home));
		this.tvClassFirst.setVisibility(View.VISIBLE);
		setListener(tvClassFirst, this, BankMain.class);
		//设置导航栏“账户管理”
		this.tvClassSecond.setText(temp + res.getString(R.string.account_manager));
		this.tvClassSecond.setVisibility(View.VISIBLE);
		this.tvClassSecond.setOnClickListener(new TextView.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}	
}
