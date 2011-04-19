package ubank.payment;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.webservice.ConnectWs;

/**
 * 
 * @author gsm 自助缴费主界面
 */
public class AllPaymentSer extends GeneralListActivity {
	private String[] s = { "待缴费项目", "便捷服务", "最近一个月缴费", "历史缴费记录", "缴费项目管理" };

	private String[] name;
	private String[] value;
	private Intent intent;
	private JSONObject jsonObj;
	private Map<String, String> map;
	private int i = 0;// map遍历时需要用到的变量
	private Bundle bundle;
	private String start_time = "2011-4-14";
	private String end_time = "2011-5-14";
	private String[] field;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tvClassFirst.setVisibility(View.VISIBLE);
		// 监听
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("自助缴费");

		this.setListAdapter(createImg_Text_ImgAdapter(s));
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);
		switch ((int) id) {
		case 0:// 待缴费项目
			if (EHelper.hasInternet(AllPaymentSer.this)) {
				try {
					jsonObj = ConnectWs.connect(this, EAccType.NULL,
							EOperation.GET_PAYMENT_NAME, "1");
					map = EHelper.toMap(jsonObj);
					name = new String[map.size()];// 获取名字
					value = new String[map.size()];// 获取值
					i = 0;// 使用i之前要初始化为0
					for (Entry<String, String> kv : map.entrySet()) {
						name[i] = kv.getKey();
						value[i++] = kv.getValue() + "元";
					}
					intent = new Intent();
					intent.putExtra("name", name);
					intent.putExtra("value", value);
					intent.setClass(AllPaymentSer.this, WaitCostItem.class);
					AllPaymentSer.this.startActivity(intent);
				} catch (IOException e) {

					Toast.makeText(AllPaymentSer.this, "对不起，服务器未连接",
							Toast.LENGTH_SHORT).show();
					finish();
					e.printStackTrace();
				}

			} else {
				Toast
						.makeText(AllPaymentSer.this, "没有连接网络",
								Toast.LENGTH_SHORT).show();
				finish();
			}
			break;

		case 1:// 便捷服务
			if (EHelper.hasInternet(AllPaymentSer.this)) {
				try {
					jsonObj = ConnectWs.connect(this, EAccType.NULL,
							EOperation.GET_SEL_SERVICE_NAME, "");
					List<String> list = EHelper.toList(jsonObj);
					value = new String[list.size()];// 获取值 设置value的大小
					for (int i = 0; i < list.size(); i++) {
						value[i] = list.get(i);
					}
					intent = new Intent();
					intent.putExtra("value", value);
					intent.setClass(AllPaymentSer.this, Speedy.class);
					AllPaymentSer.this.startActivity(intent);
				} catch (IOException e) {
					Toast.makeText(AllPaymentSer.this, "对不起，服务器未连接",
							Toast.LENGTH_SHORT).show();
					finish();
					e.printStackTrace();
				}
			} else {
				Toast
						.makeText(AllPaymentSer.this, "没有连接网络",
								Toast.LENGTH_SHORT).show();
				finish();
			}
			break;

		case 2:// 最近一个月缴费
			if (EHelper.hasInternet(AllPaymentSer.this)) {
				try {
					jsonObj = ConnectWs.connect(this, EAccType.NULL,
							EOperation.GET_PAYMENT_HISTORY, "1");
					map = EHelper.toMap(jsonObj);
					String s = null;// 获取值
					for (Entry<String, String> kv : map.entrySet()) {
						s = kv.getValue();
					}
					String[] ss = s.split("#");
					field = new String[ss.length / 2];
					value = new String[ss.length / 2];
					for (int i = 1, j = 0; i < ss.length; i += 2, j++) {
						field[j] = ss[i - 1];// 双数赋给字段数组
						value[j] = ss[i];// 单数赋给字值数组
					}
					intent = new Intent();
					intent.putExtra("field", field);
					intent.putExtra("value", value);
					intent.putExtra("start_time", start_time);
					intent.putExtra("end_time", end_time);
					intent.putExtra("bundle", bundle);
					intent.setClass(AllPaymentSer.this, Lately.class);
					AllPaymentSer.this.startActivity(intent);
				} catch (IOException e) {
					Toast.makeText(AllPaymentSer.this, "对不起，服务器未连接",
							Toast.LENGTH_SHORT).show();
					finish();
					e.printStackTrace();
				}
			} else {
				Toast
						.makeText(AllPaymentSer.this, "没有连接网络",
								Toast.LENGTH_SHORT).show();
				finish();
			}
			break;

		case 3:// 历史缴费记录
			intent = new Intent();
			intent.setClass(AllPaymentSer.this, HistoryCost.class);
			AllPaymentSer.this.startActivity(intent);
			break;

		case 4:// 缴费项目管理
			if (EHelper.hasInternet(AllPaymentSer.this)) {
				try {
					jsonObj = ConnectWs.connect(this, EAccType.NULL,
							EOperation.GET_PAYMENT_NAME_ON_MANA, "");
					String str = EHelper.toStr(jsonObj);
					String[] state = str.split("#");
					Intent manage_intent = new Intent();
					manage_intent.putExtra("state", state);
					manage_intent
							.setClass(AllPaymentSer.this, ManageCost.class);
					AllPaymentSer.this.startActivity(manage_intent);
				} catch (IOException e) {
					Toast.makeText(AllPaymentSer.this, "对不起，服务器未连接",
							Toast.LENGTH_SHORT).show();
					finish();
					e.printStackTrace();
				}

			} else {
				Toast
						.makeText(AllPaymentSer.this, "没有连接网络",
								Toast.LENGTH_SHORT).show();
				finish();
			}
			break;

		default:
			break;
		}
	}
}
