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

public class ReservationChangeCardSecond extends GeneralActivity{
	private Account_Select select_type = null;
	private Button btnComfirm;
	private String[] type = null;
	private String[] values = null;
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
		setListener(tvClassThird, this, ReservationChangeCard.class);
		
		select_type = (Account_Select)findViewById(R.id.account_select);
		type = new String[]{"卡损坏","卡过期","卡丢失"};
		values = new String[]{"省行营业厅","市行营业厅"};
		select_type.AddTypeData(type);
		select_type.AddNumData(values);
		
		btnComfirm = (Button)findViewById(R.id.account_type_comfirm).findViewById(R.id.button);
		btnComfirm.setText(R.string.cc_next);
		btnComfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ReservationChangeCardSecond.this, ReservationChangeCardThird.class);
				ReservationChangeCardSecond.this.startActivity(intent);
			}
		});
	}
}
