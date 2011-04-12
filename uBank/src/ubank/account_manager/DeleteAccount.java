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

public class DeleteAccount extends GeneralActivity{

	private Button continue_btn=null;
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
	        tvClassThird.setText("删除账户");
	        
	        //继续按钮
	        continue_btn=(Button)findViewById(R.id.account_type_comfirm).findViewById(R.id.button);
	        continue_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent();
					/**
					 * 将服务器上取得的值传给下一个Activity
					 */
					String[] value={"6212121","我是别名","定期存储卡"};
					intent.putExtra("value", value);
					intent.setClass(DeleteAccount.this, DelOK.class);
					DeleteAccount.this.startActivity(intent);
				}
			});
	  }
}
