package ubank.account_manager;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import ubank.base.GeneralActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ReservationChangeCardSecond extends GeneralActivity{
	private TextView changerCard_txt = null;
	private TextView net_txt = null;
	private Spinner changerCard_spinner = null;
	private Spinner net_spinner = null;
	private Button btnComfirm;
	private Resources res = null;
	private ArrayAdapter<String> adapter;
	private Intent intent = null;
	private String accNumValue = "";
	private String accTypeValue = "";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.addLayout(R.layout.changer_card);
		intent = this.getIntent();
		accNumValue = intent.getStringExtra("accNumValue");
		accTypeValue = intent.getStringExtra("accTypeValue");
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
		
		setValue();
		
		btnComfirm = (Button)findViewById(R.id.account_type_comfirm).findViewById(R.id.button);
		btnComfirm.setText(R.string.cc_next);
		btnComfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println(changerCard_spinner.getSelectedItem().toString());
				System.out.println(net_spinner.getSelectedItem().toString());
				Intent intent = new Intent();
				intent.putExtra("accNumValue", accNumValue);
				intent.putExtra("accTypeValue", accTypeValue);
				intent.putExtra("because", changerCard_spinner.getSelectedItem().toString());
				intent.putExtra("net", net_spinner.getSelectedItem().toString());
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
		if (EHelper.hasInternet(this)) {
		String[] bacuse = new String[]{"卡损坏","卡过期","卡丢失"};
		try {
			String[] nets;
			JSONObject json = null;
			json = ConnectWs.connect(this, EAccType.NULL,
					EOperation.GET_NET);
			List<String> net_list = EHelper.toList(json);
			nets = new String[net_list.size()];
			int i = 0;
			for(String name : net_list){
				nets[i++] = name;
			}
			changerCard_spinner.setAdapter(CreAda(bacuse));
			net_spinner.setAdapter(CreAda(nets));
		} catch (IOException e) {
			Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		}else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
		}
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
