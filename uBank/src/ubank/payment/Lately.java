package ubank.payment;

import ubank.base.GeneralListActivity;
import ubank.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class Lately extends GeneralListActivity {
	private String[] name={"2011-03-08","2011-03-09","2011-03-10",
						   "2011-03-11","2011-03-12","2011-03-13","2011-03-14"};
	private String[] value={"水费","电费","天然气","房租","手机充值","Q币充值","网易充值"};
	private TextView txt=null;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        tvClassFirst.setVisibility(View.VISIBLE);
	        tvClassFirst.setText("首页>");
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("自助缴费>");
	       //监听
	        setListener(tvClassSecond, this, AllPaymentSer.class);
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("最近一个月缴费");
	        addLayout(R.layout.above_list_txt);
	        txt=(TextView)findViewById(R.id.above_list_txt);
	        txt.setText("从2011-03-01到2011-03-19的历史缴费记录:");
	        this.setListAdapter(createText_Text_Img(name,value));
	  }
	protected void onListItemClick(ListView l,View v,int position,long id){
		
		super.onListItemClick(l, v, position, id);
		if(id==0){//三月份水费
			Intent water_intent=new Intent();
			water_intent.setClass(Lately.this, WaterCost.class);
			Lately.this.startActivity(water_intent);
		}else if(id==1){//三月份电费
			Intent electricity_intent=new Intent();
			electricity_intent.setClass(Lately.this, ElectricityCost.class);
			Lately.this.startActivity(electricity_intent);
		}else if(id==2){//三月份煤气费
			Intent gas_intent=new Intent();
			gas_intent.setClass(Lately.this, GasCost.class);
			Lately.this.startActivity(gas_intent);
		}
		else if(id==3){//三月份房租费
			Intent rent_intent=new Intent();
			rent_intent.setClass(Lately.this, RentCost.class);
			Lately.this.startActivity(rent_intent);
		}
		
	}
}


