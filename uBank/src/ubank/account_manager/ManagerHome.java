package ubank.account_manager;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import ubank.base.GeneralActivity;
import ubank.base.GeneralListActivity;
import ubank.main.R;

public class ManagerHome extends GeneralListActivity{

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
	
	private String[] getManagerItem()
	{
		
		String[] items = new String[7];
		items[0] = res.getString(R.string.account_info);
		items[1] = res.getString(R.string.bind_account);
		items[2] = res.getString(R.string.first_acc_set);
		items[3] = res.getString(R.string.reservation_change_card);
		items[4] = res.getString(R.string.acc_report_loss);
		items[5] = res.getString(R.string.add_account);
		items[6] = res.getString(R.string.delete_account);
		return items;
	}
	
	private void setNavigation()
	{
		String temp = ">";
		this.tvClassFirst.setText(res.getString(R.string.home));
		this.tvClassSecond.setText(temp + res.getString(R.string.account_manager));
		this.tvClassFirst.setVisibility(View.VISIBLE);
		this.tvClassSecond.setVisibility(View.VISIBLE);
	}

	
	
}
