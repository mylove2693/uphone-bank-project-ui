package ubank.account_manager;

import ubank.base.GeneralActivity;
import ubank.common.Account_Select;
import ubank.main.BankMain;
import ubank.main.R;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ReservationChangeCard extends GeneralActivity{
	private Account_Select accountInfo = null;
	private Button btnComfirm;
	private String[] accountType = null;
	private String[] accountValues = null;
	private Resources res = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.addLayout(R.layout.account_type);
		
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
		
		accountInfo = (Account_Select)findViewById(R.id.account_select);
		accountType = new String[]{"活期储蓄卡","定期储蓄卡","信用卡"};
		accountValues = new String[]{"622113356744","633668832124"};
		accountInfo.AddTypeData(accountType);
		accountInfo.AddNumData(accountValues);
		
		btnComfirm = (Button)findViewById(R.id.account_type_comfirm).findViewById(R.id.button);
		btnComfirm.setText(R.string.cc_next);
		btnComfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ReservationChangeCard.this, ReservationChangeCardSecond.class);
				ReservationChangeCard.this.startActivity(intent);
			}
		});
	}
}
