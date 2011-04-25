package ubank.payment;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
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
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Lately extends GeneralListActivity {

	private String[] name;
	private String[] value;
	private TextView txt = null;
	private String start_time;// 上一个页面传来的时间
	private String end_time;// 上一个页面传来的时间
	private int item = 1;// 点击项
	private String title;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		start_time = intent.getStringExtra("start_time");
		end_time = intent.getStringExtra("end_time");
		name = intent.getStringArrayExtra("field");
		value = intent.getStringArrayExtra("value");
		title = intent.getStringExtra("title");
		if (title == null) {
			title = "历史缴费记录";
		}
		if (name == null || value == null) {
			if (start_time == null || end_time == null) {
				start_time = "2011-4-14";
				end_time = "2011-4-17";
				String[] field2 = { "2011-4-16" };
				String[] value2 = { "煤气费" };
				name = field2;
				value = value2;

				Toast.makeText(this, "请您选择查询的时间！", Toast.LENGTH_SHORT).show();
				finish();
				return;

			} else if (Date.valueOf(start_time).after(Date.valueOf(end_time))) {
				Toast.makeText(this, "起始时间要在结束时间之前！", Toast.LENGTH_SHORT)
						.show();
				finish();
				return;
			} else {
				// 连接数据库查询
				// System.out.println("连接数据库查询");
				// System.out.println("start_time=" + start_time + "end_time="
				// + end_time);
				if (EHelper.hasInternet(Lately.this)) {
					try {
						JSONObject jsonObj = ConnectWs.connect(this,
								EAccType.NULL, EOperation.GET_PAYMENT_HISTORY,
								"1", start_time, end_time);
						if (jsonObj.length() != 0) {// 判断后台传来的是否为空
							Map<String, String> map = EHelper.toMap(jsonObj);
							String s = null;// 获取值
							for (Entry<String, String> kv : map.entrySet()) {
								s = kv.getValue();
							}
							String[] ss = s.split("#");
							String[] nameField = new String[ss.length / 2];
							String[] nameValue = new String[ss.length / 2];
							for (int i = 1, j = 0; i < ss.length; i += 2, j++) {
								nameField[j] = ss[i - 1];// 双数赋给字段数组
								nameValue[j] = ss[i];// 单数赋给字值数组
							}
							name = nameField;
							value = nameValue;
							item = 2;
						} else {
							start_time = "2011-4-14";
							end_time = "2011-4-17";
							String[] field2 = { "2011-4-16" };
							String[] value2 = { "煤气费" };
							name = field2;
							value = value2;
							Toast.makeText(Lately.this, "对不起此时间段没有数据",
									Toast.LENGTH_SHORT).show();
							finish();// 如果没有数据就直接finish()
						}
					} catch (IOException e) {
						Toast.makeText(Lately.this, "对不起，服务器未连接",
								Toast.LENGTH_SHORT).show();
						finish();
						e.printStackTrace();
					}
				} else {
					Toast.makeText(Lately.this, "没有连接网络", Toast.LENGTH_SHORT)
							.show();
					finish();
				}
			}
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
		tvClassThird.setText(title);
		addLayout(R.layout.above_list_txt);
		txt = (TextView) findViewById(R.id.above_list_txt).findViewById(
				R.id.Text_View_16_Gray);
		txt.setText("从" + start_time + "到" + end_time + "的历史缴费记录:");
		this.setListAdapter(createText_Text_Img(name, value));
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {

		Intent water_intent = new Intent();
		String[] value1 = null;// 获取值
		if (EHelper.hasInternet(Lately.this)) {
			try {
				JSONObject jsonObj = ConnectWs.connect(this,
						EAccType.CURRENT_DEPOSIT,
						EOperation.GET_PAYMENT_HIS_INFO, String
								.valueOf((int) id + item));
				Map<String, String> map = EHelper.toMap(jsonObj);
				value1 = new String[map.size()];// 获取值
				int i = 0;// 使用i之前要初始化为0
				for (Entry<String, String> kv : map.entrySet()) {
					value1[i++] = kv.getValue();
				}
				water_intent.putExtra("value", value1);
				water_intent.setClass(Lately.this, LatelyCost.class);
				Lately.this.startActivity(water_intent);
			} catch (IOException e) {
				Toast.makeText(Lately.this, "对不起,服务器未连接", Toast.LENGTH_SHORT)
						.show();
				finish();
				e.printStackTrace();
			}
		} else {
			Toast.makeText(Lately.this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
}
