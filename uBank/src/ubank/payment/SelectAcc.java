package ubank.payment;

import ubank.base.GeneralListActivity;
import ubank.main.R;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SelectAcc extends GeneralListActivity {
	private String[] value={"首选账户","其他账户"};
	private TextView txt=null;
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
	        tvClassThird.setText("账户类型选择");
	        
	        addLayout(R.layout.above_list_txt);
	        txt=(TextView)findViewById(R.id.above_list_txt).findViewById(R.id.Text_View_16_Gray);
	        txt.setText("缴费账户类型选择");
	        this.setListAdapter(createText_Img(value));
	  }
}


