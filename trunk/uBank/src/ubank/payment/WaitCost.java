package ubank.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ubank.base.GeneralListActivity;
import ubank.main.BankMain;
import ubank.main.R;


public class WaitCost extends GeneralListActivity {//待缴费的项目
	private String[] name=null;
	private String[] value=null;
	private Button btn=null;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        addLayoutBlow(R.layout.midle_btn);
	        /**
	         * 接收上一个Activity的值
	         */
	        Intent intent=getIntent();
	        name=intent.getStringArrayExtra("name");
	        value=intent.getStringArrayExtra("value");
	        tvClassFirst.setVisibility(View.VISIBLE);
	        
	        //监听
	        tvClassFirst.setText("首页>");
	        setListener(tvClassFirst, this, BankMain.class);
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("自助缴费>");
	        this.setListAdapter(createText_Text(name,value));
	       
	        //导航栏监听
	        setListener(tvClassSecond, this, AllPaymentSer.class);
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("待缴费项目");
	        
	        //前往缴费按钮
	        btn=(Button)findViewById(R.id.midle_btn).findViewById(R.id.button);
	        btn.setText("前往缴费");
	        btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent();
					intent.setClass(WaitCost.this, SelectAcc.class);
					WaitCost.this.startActivity(intent);
				}
			});
	        
	  }
	  
}
