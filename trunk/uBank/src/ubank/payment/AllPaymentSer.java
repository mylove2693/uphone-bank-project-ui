package ubank.payment;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
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
			try {
				jsonObj = ConnectWs.connect(this, EAccType.NULL,
						EOperation.GET_PAYMENT_NAME, "1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map = EHelper.toMap(jsonObj);
			name = new String[map.size()];// 获取名字
			value = new String[map.size()];// 获取值

			for (Entry<String, String> kv : map.entrySet()) {
				name[i] = kv.getKey();
				value[i++] = kv.getValue() + "元";
			}
			if (name == null || value == null) {
				// String[] name1={"水费","房租费","煤气费","电费"};
				// String[] value1={"30.00元","200.00元","150.00元","80.00元"};
				// name=name1;
				// value=value1;
				Log.e("--class-AllPaymentSer",
						"id==0 is name and value is null");
			}
			intent = new Intent();
			intent.putExtra("name", name);
			intent.putExtra("value", value);
			intent.setClass(AllPaymentSer.this, WaitCostItem.class);
			AllPaymentSer.this.startActivity(intent);
			break;

		case 1://便捷服务
//			jsonObj = ConnectWs.connect(this, EAccType.NULL,
//					EOperation.GET_PAYMENT_NAME, "1");
//			map = EHelper.toMap(jsonObj);
//			name = new String[map.size()];// 获取名字
//			value = new String[map.size()];// 获取值
//
//			for (Entry<String, String> kv : map.entrySet()) {
//				name[i] = kv.getKey();
//				value[i++] = kv.getValue() + "元";
//			}
			if (name == null || value == null) {
				 
				String[] value1={"手机充值","Q充值","网易充值"};
				value=value1;
				Log.e("--class-AllPaymentSer",
						"id==0 is name and value is null");
			}
			intent = new Intent();
			intent.putExtra("value", value);
			intent.setClass(AllPaymentSer.this, Speedy.class);
			AllPaymentSer.this.startActivity(intent);
			break;

		default:
			break;
		}
		// if(id==0){//待缴费项目
		// String[] name=null;
		// String[] value=null;
		// Intent payment_intent=new Intent();
		// JSONObject jsonObj = ConnectWs.connect(this,
		// EAccType.NULL,EOperation.GET_PAYMENT_NAME, "1");
		// Map<String,String> map = EHelper.toMap(jsonObj);
		// name=new String[map.size()];//获取名字
		// value=new String[map.size()];//获取值
		// int i=0;
		// for (Entry<String, String> kv : map.entrySet()) {
		// name[i]=kv.getKey();
		// value[i++]=kv.getValue()+"元";
		// }
		// if(name==null||value==null){
		// Log.e("--class-AllPaymentSer", "id==0 is name and value is null");
		// }
		// payment_intent.putExtra("name",name);
		// payment_intent.putExtra("value",value);
		// payment_intent.setClass(AllPaymentSer.this, WaitCostItem.class);
		// AllPaymentSer.this.startActivity(payment_intent);
		// }else if(id==1){//便捷服务
		// Intent speedy_intent=new Intent();
		// speedy_intent.setClass(AllPaymentSer.this, Speedy.class);
		// AllPaymentSer.this.startActivity(speedy_intent);
		// }else if(id==2){//最近一个月缴费
		// Intent lately_intent=new Intent();
		// lately_intent.setClass(AllPaymentSer.this, Lately.class);
		// AllPaymentSer.this.startActivity(lately_intent);
		// }else if(id==3){//历史缴费记录
		// Intent history_intent=new Intent();
		// history_intent.setClass(AllPaymentSer.this, HistoryCost.class);
		// AllPaymentSer.this.startActivity(history_intent);
		// }else if(id==4){//缴费项目管理
		// Intent manage_intent=new Intent();
		// manage_intent.setClass(AllPaymentSer.this, ManageCost.class);
		// AllPaymentSer.this.startActivity(manage_intent);
		// }
	}
}
