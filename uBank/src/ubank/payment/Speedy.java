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
	private Intent intent;
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
	        intent=getIntent();
	        value=intent.getStringArrayExtra("value");
	        this.setListAdapter(createText_Img(value));
	  }
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if(id==0){//手机充值
			Intent intent=new Intent();
			intent.putExtra("num", "QQ");
			intent.setClass(Speedy.this, Cost.class);
			Speedy.this.startActivity(intent);
		}else if(id==1){//Q币充值
			Intent intent=new Intent();
			intent.putExtra("num", "手机");
			intent.setClass(Speedy.this, Cost.class);
			Speedy.this.startActivity(intent);
		}
		else if(id==2){//Q网易充值
			Intent intent=new Intent();
			intent.putExtra("num", "网易");
			intent.setClass(Speedy.this, Cost.class);
			Speedy.this.startActivity(intent);
		}
	}
	  
}


