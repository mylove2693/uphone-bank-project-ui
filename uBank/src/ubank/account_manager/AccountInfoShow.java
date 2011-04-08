package ubank.account_manager;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import ubank.base.GeneralListActivity;
import ubank.main.R;

public class AccountInfoShow extends GeneralListActivity{

	private Resources res = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		res = this.getBaseContext().getResources();
		this.setNavigation();
		this.setListAdapter(this.createText_Text_Img(this.getShowIdentifier(), this.getShowValue(), this.getShowImg()));
	}

	private String[] getShowIdentifier()
	{
		
		String[] items = new String[9];
		items[0] = res.getString(R.string.accounNum);
		items[1] = res.getString(R.string.accountType);
		items[2] = res.getString(R.string.currency);
		items[3] = res.getString(R.string.balance);
		items[4] = res.getString(R.string.account_state);
		items[5] = res.getString(R.string.is_activation);
		items[6] = res.getString(R.string.opening_bank);
		items[7] = res.getString(R.string.account_opening_day);
		items[8] = res.getString(R.string.account_alias);
		return items;
	}
	
	private String[] getShowValue()
	{
		
		String[] items = new String[9];
		
		return items;
	}
	
	private List<Integer> getShowImg()
	{
		
		List<Integer> items= new ArrayList<Integer>();
		items.add(new Integer(4));
		return items;
	}
	
	//设置导航栏
	private void setNavigation()
	{
		String temp = ">";
		this.tvClassFirst.setText(res.getString(R.string.home));
		this.tvClassFirst.setVisibility(View.VISIBLE);
		this.tvClassSecond.setText(temp + res.getString(R.string.account_manager));	
		this.tvClassSecond.setVisibility(View.VISIBLE);
		this.tvClassThird.setText(temp + res.getString(R.string.account_info));	
		this.tvClassSecond.setVisibility(View.VISIBLE);
	}
}
