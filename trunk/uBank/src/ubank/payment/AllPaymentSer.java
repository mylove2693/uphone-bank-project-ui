package ubank.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import ubank.base.GeneralListActivity;
import ubank.main.R;


public class AllPaymentSer extends GeneralListActivity {
	String[] s={"待缴费项目","便捷服务","最近一个月缴费","历史缴费记录","缴费项目管理"};
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        tvClassFirst.setVisibility(View.VISIBLE);
	        tvClassFirst.setText("首页>");
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("自助缴费");
	        
	        addLayout(R.layout.pay_all_list);
	        this.setListAdapter(createImg_Text_ImgAdapter(s));
	       
	  }
	  
	protected void onListItemClick(ListView l,View v,int position,long id){
		
		super.onListItemClick(l, v, position, id);
		if(id==0){//待缴费项目
			Intent payment_intent=new Intent();
			payment_intent.setClass(AllPaymentSer.this, WaitCost.class);
			AllPaymentSer.this.startActivity(payment_intent);
		}else if(id==1){
		}
	}
}
