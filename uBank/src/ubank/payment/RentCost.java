package ubank.payment;

import android.os.Bundle;
import android.view.View;
import ubank.base.GeneralListActivity;
import ubank.main.R;

public class RentCost extends GeneralListActivity {
	String[] s={"项目名称","缴费金额","收费方式","缴费合同号","缴费期限"};
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
	        
	        addLayout(R.layout.pay_all_list);
	        this.setListAdapter(createImg_Text_ImgAdapter(s));
	  }
}
