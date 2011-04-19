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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetAccountAliasSecond extends GeneralActivity {
	private TextView aliss_txt = null;
	private EditText aliss_edit = null;
	private Button aliss_btn = null;
	private Intent intent = null;
	protected boolean flag = true;
	private String accType = "";
	private String accNum = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayout(R.layout.set_aliss);
		intent = this.getIntent();
		
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
        setListener(tvClassThird, this, SetAccountAlias.class);
		
		aliss_txt = (TextView)findViewById(R.id.aliss_txt).findViewById(R.id.Text_View_18);
		aliss_txt.setText("请设置账户的别名");
		aliss_edit = (EditText)findViewById(R.id.aliss_edit).findViewById(R.id.et_user);
		//aliss_edit.setText("我的储蓄卡");
		
		getAliss();
		aliss_btn = (Button)findViewById(R.id.aliss_btn).findViewById(R.id.button);
		aliss_btn.setText(R.string.confirm);
		aliss_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				boolean result = setAliss(aliss_edit.getText().toString());
				MyDialogOne  d1=new MyDialogOne(SetAccountAliasSecond.this,R.style.dialog);
				if(result){
					d1.setTitleAndInfo("提示", "别名设置成功！");
				}else{
					d1.setTitleAndInfo("提示", "别名设置失败！");
				}
				d1.Listener(SetAccountAliasSecond.this,ManagerHome.class);
				d1.show();
			}
		});
	}
	
	private void getAliss(){
		accType = intent.getStringExtra("accTypeValue");
		accNum = intent.getStringExtra("accNumValue");
		if (EHelper.hasInternet(this)) {
			try {
				JSONObject json = new JSONObject();
				if("信用卡".equals(accType)){
					json = ConnectWs.connect(this, EAccType.CREDIT_CARD, EOperation.GET_NICK_NAME, accNum);
				}else if("定期储蓄卡".equals(accType)){
					json = ConnectWs.connect(this, EAccType.TIME_DEPOSITS, EOperation.GET_NICK_NAME, accNum);
				}else{
					json = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT, EOperation.GET_NICK_NAME, accNum);
				}
				aliss_edit.setText(EHelper.toList(json).get(0));
				
			} catch (IOException e) {
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			}
		}else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
	
	private boolean setAliss(String nickName){
		if (EHelper.hasInternet(this)) {
			try {
				JSONObject json = new JSONObject();
				if("信用卡".equals(accType)){
				json = ConnectWs.connect(this, EAccType.CREDIT_CARD, EOperation.SET_NICK_NAME, accNum,nickName);
				}else if("定期储蓄卡".equals(accType)){
					json = ConnectWs.connect(this, EAccType.TIME_DEPOSITS, EOperation.SET_NICK_NAME, accNum,nickName);
				}else{
					json = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT, EOperation.SET_NICK_NAME, accNum,nickName);
				}
				return EHelper.toBoolean(json);
			} catch (IOException e) {
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
		}else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
		}
		
		return false;
	}
}
