package ubank.account_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import ubank.base.GeneralActivity;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.payment.AllPaymentSer;
import ubank.payment.ElseAcc;
import ubank.payment.InputPsw;

public class SetAccountAlias extends GeneralActivity{

	private TextView txt=null;
	private Button next_btn=null;
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        addLayout(R.layout.account_type);
	        
	        tvClassFirst.setVisibility(View.VISIBLE);
	        //监听
	        tvClassFirst.setText("首页>");
	        setListener(tvClassFirst, this, BankMain.class);
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("账户管理>");
	       //监听
	        setListener(tvClassSecond, this, ManagerHome.class);
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("账户别名");
	        
	        //确认缴费按钮
	        next_btn=(Button)findViewById(R.id.account_type_comfirm).findViewById(R.id.button);
//	        next_btn.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					Intent intent=new Intent();
//					/**
//					 * 将服务器上取得的值传给下一个Activity
//					 */
//					String account="222222";
//					String money="20";
//					intent.putExtra("account", account);
//					intent.putExtra("money", money);
//					intent.setClass(SetAccountAlias.this, InputPsw.class);
//					SetAccountAlias.this.startActivity(intent);
//				}
//			});
	  }

}
