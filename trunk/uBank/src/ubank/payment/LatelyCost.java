package ubank.payment;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;

public class LatelyCost extends GeneralListActivity {
	private String[] name={"缴费时间:","缴费项目:","缴费账号:","缴费金额:","项目合同号:"};
	private String[] value=null;
	private Button btn=null;

	private JSONObject jsonObj;
	private Map<String, String> map;
	private Bundle bundle;
	private String start_time = "2011-4-14";
	private String end_time = "2011-5-14";
	private String[] field;
	private int item = 1;// 点击项
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        /**
	         * 接收上一个Activity的值
	         */
	        final Intent intent=getIntent();
	        value=intent.getStringArrayExtra("value");
	        
	        tvClassFirst.setVisibility(View.VISIBLE);
	        //监听
	        tvClassFirst.setText("首页>");
	        setListener(tvClassFirst, this, BankMain.class);
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("自助缴费>");
	       //监听
	        setListener(tvClassSecond, this, AllPaymentSer.class);
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("最近一个月缴费");
	        this.setListAdapter(createText_Text(name,value));
	        
//	        addLayoutBlow(R.layout.midle_btn);
//	        btn=(Button)findViewById(R.id.midle_btn).findViewById(R.id.button);
//	        btn.setText("返回");
//	        btn.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					if (EHelper.hasInternet(LatelyCost.this)) {
//						try {
//							jsonObj = ConnectWs.connect(LatelyCost.this, EAccType.NULL,
//									EOperation.GET_PAYMENT_HISTORY, "1");
//							map = EHelper.toMap(jsonObj);
//							String s = null;// 获取值
//							for (Entry<String, String> kv : map.entrySet()) {
//								s = kv.getValue();
//							}
//							String[] ss = s.split("#");
//							field = new String[ss.length / 2];
//							value = new String[ss.length / 2];
//							for (int i = 1, j = 0; i < ss.length; i += 2, j++) {
//								field[j] = ss[i - 1];// 双数赋给字段数组
//								value[j] = ss[i];// 单数赋给字值数组
//							}
//							intent.putExtra("field", field);
//							intent.putExtra("value", value);
//							intent.putExtra("start_time", start_time);
//							intent.putExtra("end_time", end_time);
//							intent.putExtra("bundle", bundle);
//							intent.putExtra("title", "最近一个月缴费");
//							intent.setClass(LatelyCost.this, Lately.class);
//							LatelyCost.this.startActivity(intent);
//						} catch (IOException e) {
//							Toast.makeText(LatelyCost.this, "对不起，服务器未连接",
//									Toast.LENGTH_SHORT).show();
//							finish();
//							e.printStackTrace();
//						}
//					} else {
//						Toast
//								.makeText(LatelyCost.this, "没有连接网络",
//										Toast.LENGTH_SHORT).show();
//						finish();
//					}
//				}
//			});
	  }
	  private void lately(){//跳转到时间段查询界面
				// 连接数据库查询
				// System.out.println("连接数据库查询");
				// System.out.println("start_time=" + start_time + "end_time="
				// + end_time);
			  start_time = "2011-4-14";
			  end_time = "2011-4-17";
	  }
}
