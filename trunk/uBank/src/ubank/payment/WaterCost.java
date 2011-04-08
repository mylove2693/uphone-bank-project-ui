package ubank.payment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import ubank.base.GeneralListActivity;
import ubank.main.R;

public class WaterCost extends GeneralListActivity {
	private String[] name={"项目名称:","缴费金额:","收费方:","缴费合同号:","缴费期限:"};
	private String[] value={"三月份水费","30.00元","无锡自来水公司","s323454","2011-07-12"};
	private Button btn=null;
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
	        
	        addLayoutBlow(R.layout.midle_btn);
	        btn=(Button)findViewById(R.id.midle_btn).findViewById(R.id.button);
	        btn.setText("前往缴费");
	        this.setListAdapter(createText_Text(name,value));
	  }
}