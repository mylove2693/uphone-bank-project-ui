package ubank.payment;

import ubank.base.GeneralListActivity;
import ubank.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class WaitCostItem extends GeneralListActivity {
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
			String[] name={"项目名称:","缴费金额:","收费方:","缴费合同号:","缴费期限:"};
			String[] value={"三月份水费","30.00元","无锡自来水公司","s323454","2011-07-12"};
			water_intent.putExtra("name", name);
			water_intent.putExtra("value", value);
			water_intent.setClass(WaitCostItem.this, WaitCost.class);
			WaitCostItem.this.startActivity(water_intent);
		}else if(id==1){//三月份电费
			Intent electricity_intent=new Intent();
			String[] name={"项目名称:","缴费金额:","收费方:","缴费合同号:","缴费期限:"};
			String[] value={"三月份电费","100.00元","无锡电力公司","s342","2011-07-10"};
			electricity_intent.putExtra("name", name);
			electricity_intent.putExtra("value", value);
			electricity_intent.setClass(WaitCostItem.this, WaitCost.class);
			WaitCostItem.this.startActivity(electricity_intent);
		}else if(id==2){//三月份煤气费
			Intent gas_intent=new Intent();
		    String[] name={"项目名称:","缴费金额:","收费方:","缴费合同号:","缴费期限:"};
		    String[] value={"三月份煤气费","78.00元","无锡能源公司","s42526","2011-07-08"};
			gas_intent.putExtra("name", name);
			gas_intent.putExtra("value", value);
			gas_intent.setClass(WaitCostItem.this, WaitCost.class);
			WaitCostItem.this.startActivity(gas_intent);
		}
		else if(id==3){//三月份房租费
			Intent rent_intent=new Intent();
			String[] name={"项目名称:","缴费金额:","收费方:","缴费合同号:","缴费期限:"};
			String[] value={"三月份房租费","200.00元","无锡市房产公司","s34561","2011-07-29"};
			rent_intent.putExtra("name", name);
			rent_intent.putExtra("value", value);
			rent_intent.setClass(WaitCostItem.this, WaitCost.class);
			WaitCostItem.this.startActivity(rent_intent);
		}
		
	}
}


