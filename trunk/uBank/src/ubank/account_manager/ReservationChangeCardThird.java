package ubank.account_manager;

import ubank.base.GeneralListActivity;
import ubank.main.BankMain;
import ubank.main.R;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReservationChangeCardThird extends GeneralListActivity{
	private Button confirm = null;
	private String[] name = null;
	private String[] value = null;
	private Resources res = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayoutBlow(R.layout.midle_btn);
		
		res = this.getBaseContext().getResources();
		String flag = ">";
		
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText(res.getString(R.string.home) + flag);
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText(res.getString(R.string.account_manager) + flag);
		setListener(tvClassSecond, this, ManagerHome.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText(R.string.reservation_change_card);
		setListener(tvClassThird, this, ReservationChangeCard.class);
		
		name = new String[]{"预约换卡的账户：","账户别名：","更换原因","领卡网点","网点地址","工本费用"};
		value = new String[]{"622202113","我的卡","卡损坏","省行营业厅","江苏无锡","10元"};
		setListAdapter(createText_Text(name, value));
		
		confirm = (Button)findViewById(R.id.midle_btn).findViewById(R.id.button);
		confirm.setText(R.string.confirm);
	}
}
