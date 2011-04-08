package ubank.payment;

import ubank.base.GeneralActivity;
import ubank.common.Time_Select;
import ubank.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HistoryCost extends GeneralActivity {
	private Time_Select time=null;
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        tvClassFirst.setVisibility(View.VISIBLE);
	        tvClassFirst.setText("首页>");
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("自助缴费>");
	        addLayout(R.layout.query_time);
	        
	       //监听
	        setListener(tvClassSecond, this, AllPaymentSer.class);
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("历史缴费记录");
	        
	        time=(Time_Select)findViewById(R.id.time_select);
	        time.setButtonListener(new Intent(), this, Lately.class);
	        
	  }
}


