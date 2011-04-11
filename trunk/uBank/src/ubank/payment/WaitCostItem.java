package ubank.payment;

import ubank.base.GeneralListActivity;
import ubank.main.BankMain;
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
	      //监听
	        tvClassFirst.setText("首页>");
	        setListener(tvClassFirst, this, BankMain.class);
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
			Intent intent=new Intent();
			String[] name={"项目名称:","缴费金额:","收费方:","缴费合同号:","缴费期限:"};
			String[] value={"三月份水费","30.00元","无锡自来水公司","s323454","2011-07-12"};
			intent.putExtra("name", name);
			intent.putExtra("value", value);
			intent.setClass(WaitCostItem.this, WaitCost.class);
			WaitCostItem.this.startActivity(intent);
		}else if(id==1){//三月份电费
			Intent intent=new Intent();
			String[] name={"项目名称:","缴费金额:","收费方:","缴费合同号:","缴费期限:"};
			String[] value={"三月份电费","100.00元","无锡电力公司","s342","2011-07-10"};
			intent.putExtra("name", name);
			intent.putExtra("value", value);
			intent.setClass(WaitCostItem.this, WaitCost.class);
			WaitCostItem.this.startActivity(intent);
		}else if(id==2){//三月份煤气费
			Intent intent=new Intent();
			String[] name={"项目名称:","缴费金额:","收费方:","缴费合同号:","缴费期限:"};
		    String[] value={"三月份煤气费","78.00元","无锡能源公司","s42526","2011-07-08"};
		    intent.putExtra("name", name);
		    intent.putExtra("value", value);
		    intent.setClass(WaitCostItem.this, WaitCost.class);
			WaitCostItem.this.startActivity(intent);
		}
		else if(id==3){//三月份房租费
			Intent intent=new Intent();
			String[] name={"项目名称:","缴费金额:","收费方:","缴费合同号:","缴费期限:"};
			String[] value={"三月份房租费","200.00元","无锡市房产公司","s34561","2011-07-29"};
			intent.putExtra("name", name);
			intent.putExtra("value", value);
			intent.setClass(WaitCostItem.this, WaitCost.class);
			WaitCostItem.this.startActivity(intent);
		}
		
	}
}


