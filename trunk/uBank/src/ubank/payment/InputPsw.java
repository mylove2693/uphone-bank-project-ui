package ubank.payment;

import java.util.ArrayList;
import java.util.List;
import ubank.base.GeneralActivity;
import ubank.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputPsw extends GeneralActivity {

	private TextView txt=null;
	private Button ok_btn=null;
	private String account=null;
	private String acc_balance=null;
	private EditText pws=null;
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        addLayout(R.layout.input_psw);
	        
	        tvClassFirst.setVisibility(View.VISIBLE);
	        tvClassFirst.setText("首页>");
	        tvClassSecond.setVisibility(View.VISIBLE);
	        tvClassSecond.setText("自助缴费>");
	       //监听
	        setListener(tvClassSecond, this, AllPaymentSer.class);
	        tvClassThird.setVisibility(View.VISIBLE);
	        tvClassThird.setText("密码输入");
	        
	        Intent intent=getIntent();
	        account=intent.getStringExtra("account"); 
	        acc_balance=intent.getStringExtra("money"); 
	        //设置上面的字
	        txt=(TextView)findViewById(R.id.pws_txt_one).findViewById(R.id.Text_View_20);
	        txt.setText("您选择的账户为:");
	        txt=(TextView)findViewById(R.id.pws_txt_two).findViewById(R.id.Text_View_20);
	        txt.setText(account);
	        txt=(TextView)findViewById(R.id.pws_txt_three).findViewById(R.id.Text_View_20);
	        txt.setText("账户余额:");
	        txt=(TextView)findViewById(R.id.pws_txt_four).findViewById(R.id.Text_View_20);
	        txt.setText(acc_balance);
	        txt=(TextView)findViewById(R.id.pws_txt_five).findViewById(R.id.Text_View_20);
	        txt.setText("请输入账户密码");
	        //密码输入框
	        pws=(EditText)findViewById(R.id.pws).findViewById(R.id.et_psd);
	        //确认缴费按钮
	        ok_btn=(Button)findViewById(R.id.ok_btn).findViewById(R.id.button);
	        ok_btn.setText("确认缴费");
	        //确认缴费按钮的监听
	        ok_btn.setOnClickListener(new View.OnClickListener(){
	        	public void onClick(View v){
	        		String pwsStr=pws.getText().toString().trim();
//	        		String acc_balance="100";
	        		String balance="1000000";
	        		//从服务上验证密码是否正确
	        		/**
	        		 * 先判断密码框是否为空  当不为空时在验证密码是否正确 中间用 & 连接
	        		 * & 表示前面为true的情况下后面还要执行 知道前后都为true时菜返回true
	        		 */
	        		if(!pwsStr.equals("") & pwsStr.equals("123456")){
	        			System.out.println("------");
	        			//计算余额
	        			Double balanceValue=Double.parseDouble(balance)-Double.parseDouble(acc_balance);
	        			if(balanceValue>0){//检查余额
	        			Intent btnok_intent = new Intent();	
	        			btnok_intent.putExtra("flag", "成功提示");
	        			btnok_intent.putExtra("info", "缴费成功，余额为:"+balanceValue+".00元");
	        			
	        			btnok_intent.setClass(InputPsw.this, PaymentResult.class);
	        			InputPsw.this.startActivity(btnok_intent);
	        			}
//	        			else {
//	            			Intent btnok_intent = new Intent();
//	            			btnok_intent.putExtra("pay_name", payName);
//	            			btnok_intent.putExtra("pay_num",item_num+".00元");
//	            			btnok_intent.putExtra("flag", "失败提示");
//	            			btnok_intent.putExtra("info", "缴费账户的余额不足！");
//	            			btnok_intent.setClass(InputPsw.this, PaymentFailResultTwo.class);
//	            			InputPsw.this.startActivity(btnok_intent);
//	            			        			
//	            		}    			
	        		}else{
	        			Intent btnok_intent = new Intent();
	        		    btnok_intent.putExtra("flag", "失败提示");
	        			btnok_intent.putExtra("info", "密码错误！");
	        			btnok_intent.putExtra("btnText", "重输密码");
	        			btnok_intent.setClass(InputPsw.this,PaymentFailResultOne.class);
	        			InputPsw.this.startActivity(btnok_intent);
	        		}
	        	}
	        });
	  }
}


