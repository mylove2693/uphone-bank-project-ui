package ubank.payment;

import ubank.base.GeneralListActivity;
import ubank.main.BankMain;
import ubank.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class Speedy extends GeneralListActivity {
	private String[] value;//={"手机充值","Q充值","网易充值"};
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
	        tvClassThird.setText("便捷服务");
	        //初始化数据
	        Intent intent=getIntent();
	        value=intent.getStringArrayExtra("value");
	        this.setListAdapter(createText_Img(value));
	  }
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if(id==0){//Q币充值 数据表中payId=2
			Intent intent=new Intent();
			Bundle bund=new Bundle();
			bund.putString("num", "QQ");
			bund.putString("payId", "2");
			intent.putExtras(bund);
			intent.setClass(Speedy.this, Cost.class);
			Speedy.this.startActivity(intent);
		}else if(id==1){//手机充值数据表中payId=1
			Intent intent=new Intent();
			Bundle bund=new Bundle();
			bund.putString("num", "手机");
			bund.putString("payId", "1");
			intent.putExtras(bund);
			intent.setClass(Speedy.this, Cost.class);
			Speedy.this.startActivity(intent);
		}
		else if(id==2){//Q网易充值数据表中payId=3
			Intent intent=new Intent();
			Bundle bund=new Bundle();
			bund.putString("num", "网易");
			bund.putString("payId", "3");
			intent.putExtras(bund);
			intent.setClass(Speedy.this, Cost.class);
			Speedy.this.startActivity(intent);
		}
	}
	  
}


