package ubank.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import ubank.base.GeneralListActivity;
import ubank.main.R;

public class LatelyCost extends GeneralListActivity {
	private String[] name={"缴费时间:","缴费项目:","缴费账号:","缴费金额:","项目合同号:"};
	private String[] value=null;
	private Button btn=null;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        /**
	         * 接收上一个Activity的值
	         */
	        Intent intent=getIntent();
	        value=intent.getStringArrayExtra("value");
	        tvClassFirst.setVisibility(View.VISIBLE);
	        
	        tvClassFirst.setVisibility(View.VISIBLE);
	        tvClassFirst.setText("首页>");
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("自助缴费>");
	       //监听
	        setListener(tvClassSecond, this, AllPaymentSer.class);
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("最近一个月缴费");
	        
	        addLayoutBlow(R.layout.midle_btn);
	        btn=(Button)findViewById(R.id.midle_btn).findViewById(R.id.button);
	        btn.setText("返回");
	        this.setListAdapter(createText_Text(name,value));
	  }
}
