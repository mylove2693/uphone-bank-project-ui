package ubank.account_manager;

import java.io.IOException;

import org.json.JSONObject;

import ubank.base.GeneralListActivity;
import ubank.base.MyDialogOne;
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
import android.widget.Button;
import android.widget.Toast;

public class ReservationChangeCardThird extends GeneralListActivity {
	private Button confirm = null;
	private String[] name = null;
	private String[] value = null;
	private Resources res = null;
	private Intent intent = null;
	private String type = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayoutBlow(R.layout.midle_btn);
		intent = this.getIntent();
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

		loaderValue();

		confirm = (Button) findViewById(R.id.midle_btn).findViewById(
				R.id.button);
		confirm.setText(R.string.confirm);
		confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (EHelper.hasInternet(ReservationChangeCardThird.this)) {
					JSONObject json = null;
					try {
						if ("信用卡".equals(type)) {
							json = ConnectWs.connect(
									ReservationChangeCardThird.this,
									EAccType.CREDIT_CARD,
									EOperation.SET_ORDER_CARD, value[0],
									value[1], value[2], value[3], value[4],
									value[5]);
						} else if ("定期储蓄卡".equals(type)) {
							json = ConnectWs.connect(
									ReservationChangeCardThird.this,
									EAccType.TIME_DEPOSITS,
									EOperation.SET_ORDER_CARD, value[0],
									value[1], value[2], value[3], value[4],
									value[5]);
						} else {
							json = ConnectWs.connect(
									ReservationChangeCardThird.this,
									EAccType.CURRENT_DEPOSIT,
									EOperation.SET_ORDER_CARD, value[0],
									value[1], value[2], value[3], value[4],
									value[5]);
						}
						
						boolean result = EHelper.toBoolean(json);
						MyDialogOne  d1=new MyDialogOne(ReservationChangeCardThird.this,R.style.dialog);
						if(result){
							d1.setTitleAndInfo("提示", "预约成功！");
						}else{
							d1.setTitleAndInfo("提示", "预约失败！");
						}
						d1.Listener(ReservationChangeCardThird.this,ManagerHome.class);
						d1.show();
					} catch (IOException e) {
						Toast.makeText(ReservationChangeCardThird.this,
								"对不起，服务器未连接", Toast.LENGTH_SHORT).show();
						e.printStackTrace();
					}

				} else {
					Toast.makeText(ReservationChangeCardThird.this, "没有连接网络",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private void loaderValue() {
		name = new String[] { "换卡账户", "账户别名", "更换原因", "领卡网点", "网点地址", "工本费用" };
		value = new String[6];
		value[0] = intent.getStringExtra("accNumValue");
		type = intent.getStringExtra("accTypeValue");
		if (EHelper.hasInternet(this)) {
			JSONObject json = null;
			try {
				if ("信用卡".equals(type)) {
					json = ConnectWs.connect(this, EAccType.CREDIT_CARD,
							EOperation.GET_NICK_NAME, value[0]);
				} else if ("定期储蓄卡".equals(type)) {
					json = ConnectWs.connect(this, EAccType.TIME_DEPOSITS,
							EOperation.GET_NICK_NAME, value[0]);
				} else {
					json = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,
							EOperation.GET_NICK_NAME, value[0]);
				}
			} catch (IOException e) {
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			}
			value[1] = EHelper.toList(json).get(0).toString();
			value[2] = intent.getStringExtra("because");
			value[3] = intent.getStringExtra("net");
			System.out.println(value[2]);
			System.out.println(value[3]);
			try {
				json = ConnectWs.connect(this, EAccType.NULL,
						EOperation.GET_NET_ADDRESS, value[3]);
			} catch (IOException e) {
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			}
			value[4] = EHelper.toList(json).get(0).toString();
			try {
				json = ConnectWs.connect(this, EAccType.NULL,
						EOperation.GET_COST, "1");
			} catch (IOException e) {
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			}
			value[5] = EHelper.toList(json).get(0).toString();

			setListAdapter(createText_Text(name, value));
		} else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
}
