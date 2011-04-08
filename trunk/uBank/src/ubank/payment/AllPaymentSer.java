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
	        
	        this.setListAdapter(createImg_Text_ImgAdapter(s));
	  }
	  
	protected void onListItemClick(ListView l,View v,int position,long id){
		
		super.onListItemClick(l, v, position, id);
		if(id==0){//待缴费项目
			Intent payment_intent=new Intent();
			payment_intent.setClass(AllPaymentSer.this, WaitCostItem.class);
			AllPaymentSer.this.startActivity(payment_intent);
		}else if(id==1){//便捷服务
			Intent speedy_intent=new Intent();
			speedy_intent.setClass(AllPaymentSer.this, Speedy.class);
			AllPaymentSer.this.startActivity(speedy_intent);
		}else if(id==2){//最近一个月缴费
			Intent lately_intent=new Intent();
			lately_intent.setClass(AllPaymentSer.this, Lately.class);
			AllPaymentSer.this.startActivity(lately_intent);
		}else if(id==3){//历史缴费记录
			Intent history_intent=new Intent();
			history_intent.setClass(AllPaymentSer.this, HistoryCost.class);
			AllPaymentSer.this.startActivity(history_intent);
		}else if(id==4){//缴费项目管理
			Intent manage_intent=new Intent();
			manage_intent.setClass(AllPaymentSer.this, ManageCost.class);
			AllPaymentSer.this.startActivity(manage_intent);
		}
	}
}
