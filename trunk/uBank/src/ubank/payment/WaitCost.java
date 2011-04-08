package ubank.payment;

import ubank.base.GeneralListActivity;
import ubank.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class WaitCost extends GeneralListActivity {
	private String[] name={"三月份水费","三月份电费","三月份煤气费","三月份房租费"};
	private String[] value={"30.00元","100.00元","78.00元","2000.00元"};
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
	        tvClassThird.setText("待缴费项目");
	        
	        this.setListAdapter(createText_Text_Img(name,value));
	  }
	protected void onListItemClick(ListView l,View v,int position,long id){
		
		super.onListItemClick(l, v, position, id);
		if(id==0){//三月份水费
			Intent water_intent=new Intent();
			water_intent.setClass(WaitCost.this, WaterCost.class);
			WaitCost.this.startActivity(water_intent);
		}else if(id==1){//三月份电费
			Intent electricity_intent=new Intent();
			electricity_intent.setClass(WaitCost.this, ElectricityCost.class);
			WaitCost.this.startActivity(electricity_intent);
		}else if(id==2){//三月份煤气费
			Intent gas_intent=new Intent();
			gas_intent.setClass(WaitCost.this, GasCost.class);
			WaitCost.this.startActivity(gas_intent);
		}
		else if(id==3){//三月份房租费
			Intent rent_intent=new Intent();
			rent_intent.setClass(WaitCost.this, RentCost.class);
			WaitCost.this.startActivity(rent_intent);
		}
		
	}
}


