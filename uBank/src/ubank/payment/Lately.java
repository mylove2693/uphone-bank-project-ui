package ubank.payment;

import java.io.IOException;
import java.util.List;

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
	private String[] name={"2011-03-08","2011-03-09","2011-03-10",
						   "2011-03-11","2011-03-12","2011-03-13","2011-03-14"};
	private String[] value={"水费","电费","房租","天然气","手机充值","Q币充值","网易充值"};
	private TextView txt=null;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        tvClassFirst.setVisibility(View.VISIBLE);
	        //监听
	        tvClassFirst.setText("首页>");
	        setListener(tvClassFirst, this, BankMain.class);
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("自助缴费>");
	       //监听
	        setListener(tvClassSecond, this, AllPaymentSer.class);
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("历史缴费记录");
	        addLayout(R.layout.above_list_txt);
	        txt=(TextView)findViewById(R.id.above_list_txt).findViewById(R.id.Text_View_16_Gray);
	        txt.setText("从2011-07-04到2011-07-14的历史缴费记录:");
	        this.setListAdapter(createText_Text_Img(name,value));
	  }
	protected void onListItemClick(ListView l,View v,int position,long id){
		
		super.onListItemClick(l, v, position, id);
		if(id==0){//水费
			
			//传入的时间为
			String startDate="2011-7-4";
			String endDate="2011-7-14";
			String userId="1";
			List<String> list=null;
			Intent water_intent=new Intent();
			try {
				JSONObject jsonObj = ConnectWs.connect(this, EAccType.NULL,
						EOperation.GET_PAYMENT_HISTORY,userId, startDate, endDate);
				list=EHelper.toList(jsonObj);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//[2, 无锡电力公司, s0101001, 80.00, 电费, 0, 1, 2011-03-05, 2011-7-12]
			String[] value={list.get(8),list.get(4),"110",list.get(3),list.get(2)};
			water_intent.putExtra("value", value);
			water_intent.setClass(Lately.this, LatelyCost.class);
			Lately.this.startActivity(water_intent);
		}else if(id==1){//电费
			//传入的时间为null:0116:4:2011-7-4:2011-7-14
			String startDate="2011-7-4";
			String endDate="2011-7-14";
			String userId="2";
			List<String> list=null;
			Intent electricity_intent=new Intent();
			try {
				JSONObject jsonObj = ConnectWs.connect(this, EAccType.NULL,
						EOperation.GET_PAYMENT_HISTORY,userId, startDate, endDate);
				list=EHelper.toList(jsonObj);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//[2, 无锡电力公司, s0101001, 80.00, 电费, 0, 1, 2011-03-05, 2011-7-12]
			String[] value={list.get(8),list.get(4),"110",list.get(3),list.get(2)};
			electricity_intent.putExtra("value", value);
			electricity_intent.setClass(Lately.this, LatelyCost.class);
			Lately.this.startActivity(electricity_intent);
		}else if(id==2){//房租费
			//传入的时间为
			String startDate="2011-7-4";
			String endDate="2011-7-14";
			String userId="4";
			List<String> list=null;
			Intent rent_intent=new Intent();
			try {
				JSONObject jsonObj = ConnectWs.connect(this, EAccType.NULL,
						EOperation.GET_PAYMENT_HISTORY,userId, startDate, endDate);
				list=EHelper.toList(jsonObj);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//[2, 无锡电力公司, s0101001, 80.00, 电费, 0, 1, 2011-03-05, 2011-7-12]
			String[] value={list.get(8),list.get(4),"110",list.get(3),list.get(2)};
			if(value==null){
				String[] value1={"2011-07-19","房租","111111","200.00","s23421"};
				value=value1;
			}
//			Intent rent_intent=new Intent();
//			String[] value={"2011-07-19","房租","111111","200.00","s23421"};
			rent_intent.putExtra("value", value);
			rent_intent.setClass(Lately.this, LatelyCost.class);
			Lately.this.startActivity(rent_intent);
		}
		
	}
}


