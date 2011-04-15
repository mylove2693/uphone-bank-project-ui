package ubank.account_manager;

import ubank.base.GeneralActivity;
import ubank.main.BankMain;
import ubank.main.R;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ReservationChangeCardSecond extends GeneralActivity{
	private TextView changerCard_txt = null;
	private TextView net_txt = null;
	private Spinner changerCard_spinner = null;
	private Spinner net_spinner = null;
	private Button btnComfirm;
	private String[] type = null;
	private String[] values = null;
	private Resources res = null;
	private ArrayAdapter<String> adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.addLayout(R.layout.changer_card);
		
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
		
//		select_type = (Account_Select)findViewById(R.id.account_select);
//		type = new String[]{"卡损坏","卡过期","卡丢失"};
//		values = new String[]{"省行营业厅","市行营业厅"};
//		select_type.AddTypeData(type);
//		select_type.AddNumData(values);
		setValue();
		
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
	
	private void setValue(){
		changerCard_txt = (TextView)findViewById(R.id.changerCard_txt).findViewById(R.id.Text_View_18);
		net_txt = (TextView)findViewById(R.id.net_txt).findViewById(R.id.Text_View_18);
		changerCard_spinner = (Spinner)findViewById(R.id.changerCard_spinner).findViewById(R.id.Spinner);
		net_spinner = (Spinner)findViewById(R.id.net_spinner).findViewById(R.id.Spinner);
		changerCard_txt.setText("换卡的原因");
		net_txt.setText("请选择换卡的网点");
		
		String[] bacuse = new String[]{"卡损坏","卡过期","卡丢失"};
		String[] nets = new String[]{"省行营业厅","市行营业厅"};
		
		changerCard_spinner.setAdapter(CreAda(bacuse));
		net_spinner.setAdapter(CreAda(nets));
		
	}
	
	// 创建一个有数据的适配器
	private ArrayAdapter<String> CreAda(String[] data) {
		/**
		 * 实例化一个适配器，为适配器添加样式 此处采用内部的
		 */
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 向适配器中间加数据
		for (int i = 0; i < data.length; i += 1) {
			adapter.add(data[i]);
		}
		return adapter;
	}
}
