package ubank.transfer;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import ubank.base.GeneralActivity;
import ubank.main.R;
/**
 * 转账的密码输入界面
 * @author Administrator
 *
 */
public class Transfer_inpsd extends GeneralActivity {
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        tvClassFirst.setVisibility(View.VISIBLE);
	        tvClassFirst.setText("首页>");
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("转账汇款>");
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("手机到什么转账"); 
	        
	        //添加布局
	        addLayout(R.layout.transfer_inspd);
	        
	        TextView transfer_acc_mation = (TextView) findViewById(
					R.id.acc_mation).findViewById(R.id.Text_View_18);
	        transfer_acc_mation.setText("您选择的转账账户为：");
			
			//这个是类型   是小字
			TextView transfer_acctype = (TextView) findViewById(
					R.id.acc_type).findViewById(R.id.Text_View_16);
			transfer_acctype.setText("活期什么的");
			
			//这个是帐号   是小字
			TextView transfer_accnum = (TextView) findViewById(
					R.id.acc_num).findViewById(R.id.Text_View_16);
			transfer_accnum.setText("62254634");
			
			//提示输入密码的文本框
			TextView transfer_psd_txtview = (TextView) findViewById(
					R.id.acc_inpsd_mation).findViewById(R.id.Text_View_18);
			transfer_psd_txtview.setText("请输入账户密码:");
			
			
			Button transfer_next=(Button)findViewById(R.id.transfer_inpsd_next).findViewById(R.id.button);
			transfer_next.setText("下一步");
			
			
			transfer_next.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
			
	 }

}
