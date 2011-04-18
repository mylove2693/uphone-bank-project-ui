package ubank.payment;

import java.io.IOException;
import java.util.List;
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
			i=0;//使用i之前要初始化为0
			for (Entry<String, String> kv : map.entrySet()) {
				name[i] = kv.getKey();
				value[i++] = kv.getValue() + "元";
			}
			intent = new Intent();
			intent.putExtra("name", name);
			intent.putExtra("value", value);
			intent.setClass(AllPaymentSer.this, WaitCostItem.class);
			AllPaymentSer.this.startActivity(intent);
			break;

		case 1://便捷服务
			try {
				jsonObj = ConnectWs.connect(this, EAccType.NULL,
						EOperation.GET_SEL_SERVICE_NAME,"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<String> list = EHelper.toList(jsonObj);
			value = new String[list.size()];// 获取值 设置value的大小
			for(int i=0;i<list.size();i++){
				value[i] =list.get(i);
			}
			intent = new Intent();
			intent.putExtra("value", value);
			intent.setClass(AllPaymentSer.this, Speedy.class);
			AllPaymentSer.this.startActivity(intent);
			break;

		case 2://最近一个月缴费
			 Intent lately_intent=new Intent();
			 lately_intent.setClass(AllPaymentSer.this, Lately.class);
			 AllPaymentSer.this.startActivity(lately_intent);
			break;
			
		case 3://历史缴费记录
			 Intent history_intent=new Intent();
			 history_intent.setClass(AllPaymentSer.this, HistoryCost.class);
			 AllPaymentSer.this.startActivity(history_intent);
			break;
			
		case 4://缴费项目管理
			try {
				jsonObj = ConnectWs.connect(this, EAccType.NULL,
						EOperation.GET_PAYMENT_NAME_ON_MANA,"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 String str = EHelper.toStr(jsonObj);
			 String[] state=str.split("#");
			 Intent manage_intent=new Intent();
			 manage_intent.putExtra("state", state);
			 manage_intent.setClass(AllPaymentSer.this, ManageCost.class);
			 AllPaymentSer.this.startActivity(manage_intent);
			break;
		default:
			break;
		}
	}
}
