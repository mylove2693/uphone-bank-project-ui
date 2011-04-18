package ubank.account_manager;


import java.io.IOException;

import org.json.JSONObject;

import ubank.base.GeneralActivity;
import ubank.base.MyDialogOne;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DelOK extends GeneralActivity {

	private TextView txt=null;
	private TextView txt1 = null;
	private TextView txt2 = null;
	private TextView txt3 = null;
	private TextView txt4 = null;
	private TextView txt5 = null;
	private Intent intent = null;
	private Button next_btn=null;
	private String account = "";
	private String type = "";
	private String nick_name = "";
//	private String[] value=null;
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        addLayout(R.layout.del_acc_info);
	        
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
	        /**
	         * 接收上一个Activity穿过来的 值
	         */
	        intent=getIntent();
//	        value=intent.getStringArrayExtra("value"); 
	        //设置上面的字
	        txt=(TextView)findViewById(R.id.txt_one).findViewById(R.id.Text_View_20);
	        txt.setText("账户:");
	        txt1=(TextView)findViewById(R.id.txt_two).findViewById(R.id.Text_View_20);
	       
	        txt2=(TextView)findViewById(R.id.txt_three).findViewById(R.id.Text_View_20);
	        txt2.setText("账户别名:");
	        txt3=(TextView)findViewById(R.id.txt_four).findViewById(R.id.Text_View_20);
	        
	        txt4=(TextView)findViewById(R.id.txt_five).findViewById(R.id.Text_View_20);
	        txt4.setText("账户类型:");
	        txt5=(TextView)findViewById(R.id.txt_six).findViewById(R.id.Text_View_20);
//	        txt5.setText(value[2]);
	        setAccInfo();
	        
	        //下一步按钮
	        next_btn=(Button)findViewById(R.id.ok_btn).findViewById(R.id.button);
	        next_btn.setText(R.string.confirm);
	        next_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (EHelper.hasInternet(DelOK.this)) {
						try {
							JSONObject json = new JSONObject();
							if("信用卡".equals(type)){
								json = ConnectWs.connect(DelOK.this, EAccType.CREDIT_CARD, EOperation.DELE_ACC, account);
							}else if("定期储蓄卡".equals(type)){
								json = ConnectWs.connect(DelOK.this, EAccType.TIME_DEPOSITS, EOperation.DELE_ACC, account);
							}else{
								json = ConnectWs.connect(DelOK.this, EAccType.CURRENT_DEPOSIT, EOperation.DELE_ACC, account);
							}
							boolean result = EHelper.toBoolean(json);
							MyDialogOne  d1=new MyDialogOne(DelOK.this,R.style.dialog);
							if(result){
								d1.setTitleAndInfo("提示", "删除账户成功！");
							}else{
								d1.setTitleAndInfo("提示", "删除账户失败！");
							}
							d1.Listener(DelOK.this,ManagerHome.class);
							d1.show();
							} catch (IOException e) {
								Toast.makeText(DelOK.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
								finish();
								e.printStackTrace();
							}
					}else {
						Toast.makeText(DelOK.this, "没有连接网络", Toast.LENGTH_SHORT).show();
					}
				}
	  });
	}
	
	private void setAccInfo(){
		account = intent.getStringExtra("accNumValue");
        type = intent.getStringExtra("accTypeValue");
        txt1.setText(account);
        txt3.setText(type);
        if (EHelper.hasInternet(this)) {
        	try {
				JSONObject json = new JSONObject();
				if("信用卡".equals(type)){
					json = ConnectWs.connect(this, EAccType.CREDIT_CARD, EOperation.GET_NICK_NAME, account);
				}else if("定期储蓄卡".equals(type)){
					json = ConnectWs.connect(this, EAccType.TIME_DEPOSITS, EOperation.GET_NICK_NAME, account);
				}else{
					json = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT, EOperation.GET_NICK_NAME, account);
				}
				
				nick_name = EHelper.toList(json).get(0).toString();
				txt5.setText(nick_name);
				
			} catch (IOException e) {
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			}
        	
        }else {
			Toast.makeText(DelOK.this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
        
	}
	
	
}


