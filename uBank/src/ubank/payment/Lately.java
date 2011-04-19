package ubank.payment;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class Lately extends GeneralListActivity {
	
	private String[] name;
	private String[] value;
	private TextView txt = null;
	private String start_time;// 上一个页面传来的时间
	private String end_time;// 上一个页面传来的时间

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
//		Bundle bundle=intent.getBundleExtra("bundle");
		start_time = intent.getStringExtra("start_time");
		end_time = intent.getStringExtra("end_time");
		name=intent.getStringArrayExtra("field");
		value=intent.getStringArrayExtra("value");
		if (start_time == null || end_time == null||name==null||value==null) {
			start_time="2011-3-14";
			end_time="2011-4-14";
			String[] field2={"2011-3-17","2011-4-01"};
			String[] value2={"水费","电费"};
			name=field2;
			value=value2;
		}
		tvClassFirst.setVisibility(View.VISIBLE);
		// 监听
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("自助缴费>");
		// 监听
		setListener(tvClassSecond, this, AllPaymentSer.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("历史缴费记录");
		addLayout(R.layout.above_list_txt);
		txt = (TextView) findViewById(R.id.above_list_txt).findViewById(
				R.id.Text_View_16_Gray);
		txt.setText("从" + start_time + "到" + end_time + "的历史缴费记录:");
		this.setListAdapter(createText_Text_Img(name, value));
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);
		if (id == 0) {// 水费

			Intent water_intent = new Intent();
			String[] value1=null;// 获取值
			try {
				JSONObject jsonObj = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,
						EOperation.GET_PAYMENT_HIS_INFO,"1");
				Map<String, String> map = EHelper.toMap(jsonObj);
				System.out.println(jsonObj.toString());
				value1 = new String[map.size()];// 获取值
				int i = 0;// 使用i之前要初始化为0
				for (Entry<String, String> kv : map.entrySet()) {
					value1[i++] = kv.getValue();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			water_intent.putExtra("value", value1);
			water_intent.setClass(Lately.this, LatelyCost.class);
			Lately.this.startActivity(water_intent);
		} else if (id == 1) {// 电费
			Intent electricity_intent = new Intent();
			String[] value1=null;// 获取值
			try {
				JSONObject jsonObj = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,
						EOperation.GET_PAYMENT_HIS_INFO,"2");
				Map<String, String> map = EHelper.toMap(jsonObj);
				value1 = new String[map.size()];// 获取值
				int i = 0;// 使用i之前要初始化为0
				for (Entry<String, String> kv : map.entrySet()) {
					value1[i++] = kv.getValue();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			electricity_intent.putExtra("value", value1);
			electricity_intent.setClass(Lately.this, LatelyCost.class);
			Lately.this.startActivity(electricity_intent);
		} else if (id == 2) {// 房租费
			Intent rent_intent = new Intent();
			String[] value1=null;// 获取值
			try {
				JSONObject jsonObj = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,
						EOperation.GET_PAYMENT_HIS_INFO,"3");
				Map<String, String> map = EHelper.toMap(jsonObj);
				value1 = new String[map.size()];// 获取值
				int i = 0;// 使用i之前要初始化为0
				for (Entry<String, String> kv : map.entrySet()) {
					value1[i++] = kv.getValue();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rent_intent.putExtra("value", value1);
			rent_intent.setClass(Lately.this, LatelyCost.class);
			Lately.this.startActivity(rent_intent);
		}

	}
}
