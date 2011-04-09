package ubank.payment;

import ubank.base.GeneralActivity;
import ubank.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InputPsw extends GeneralActivity {

	private TextView txt=null;
	private Button ok_btn=null;
	private String account=null;
	private String money=null;
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
	        money=intent.getStringExtra("money"); 
	        //设置上面的字
	        txt=(TextView)findViewById(R.id.pws_txt_one).findViewById(R.id.Text_View_20);
	        txt.setText("您选择的账户为:");
	        txt=(TextView)findViewById(R.id.pws_txt_two).findViewById(R.id.Text_View_20);
	        txt.setText(account);
	        txt=(TextView)findViewById(R.id.pws_txt_three).findViewById(R.id.Text_View_20);
	        txt.setText("账户余额:");
	        txt=(TextView)findViewById(R.id.pws_txt_four).findViewById(R.id.Text_View_20);
	        txt.setText(money);
	        txt=(TextView)findViewById(R.id.pws_txt_five).findViewById(R.id.Text_View_20);
	        txt.setText("请输入账户密码");
	        //确认缴费按钮
	        ok_btn=(Button)findViewById(R.id.ok_btn).findViewById(R.id.button);
	        ok_btn.setText("确认缴费");
	  }
}


