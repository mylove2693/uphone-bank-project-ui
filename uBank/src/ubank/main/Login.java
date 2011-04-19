package ubank.main;

import java.io.IOException;
import java.util.Map;

import org.json.JSONObject;

import ubank.base.MyDialogOne;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.webservice.ConnectWs;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	
	private String extraCode;
	private String passWord;
	private String InputCode;
	private boolean loginflag=false;
	
	private ImageView bankmain;
	private ImageView bankhelp;
	private EditText userid;
	private EditText password;
	private EditText extracode;
	private TextView showec;
	public static String userName = "张三";
	public static String userId="1";
	private Button btn_login;
	
	private Intent intent=new Intent();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_login);

		//获取附加码
		loaderData();
		
		//显示附加码
		showec = (TextView)findViewById(R.id.loginbox).findViewById(R.id.extraCode);
		showec.setText(extraCode);
		
		userid = (EditText)findViewById(R.id.login_box).findViewById(R.id.nameEdit);
		password = (EditText)findViewById(R.id.login_box).findViewById(R.id.passwdEdit);
		extracode = (EditText)findViewById(R.id.login_box).findViewById(R.id.pyramidEdit);
		
		// 登录按钮
		btn_login = (Button) findViewById(R.id.login_box).findViewById(R.id.btn_userlogin);
		btn_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userId = userid.getText().toString();
				passWord = password.getText().toString();
				InputCode = extracode.getText().toString();
				if (InputCode.equals(extraCode)) {
				
					if (userId.equals("")) {

						//用Dialog提示用户名为空
						MyDialogOne dialog = new MyDialogOne(Login.this,R.style.dialog);
						dialog.setTitleAndInfo("登录手机银行","用户号不能为空！");
						dialog.show();
					}else {
						
						if (passWord.equals("")) {
							
							//用Dialog提示密码为空
							MyDialogOne dialog = new MyDialogOne(Login.this,R.style.dialog);
							dialog.setTitleAndInfo("登录手机银行","密码不能为空！");
							dialog.show();
						}else {
							
							if (EHelper.hasInternet(Login.this)) {
								try {
									//将用户名和密码发送到服务端进行验证
									JSONObject json = ConnectWs.connect(Login.this, EAccType.NULL, EOperation.LOGIN,userId,passWord,InputCode);
									//将JSON数据转换为boolean型
									loginflag = EHelper.toBoolean(json);
									
								} catch (IOException e) {
									Toast.makeText(Login.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
									finish();
									e.printStackTrace();
								}
								
								}else {
									Toast.makeText(Login.this, "没有连接网络", Toast.LENGTH_SHORT).show();
									finish();
								}
							}
							if(loginflag){
								//登录成功
								FinanceAss.loginstatus = true;
								
								intent.setClass(Login.this, BankMain.class);
								Login.this.startActivity(intent);
								
								if (EHelper.hasInternet(Login.this)) {
									try {
										//将用户名和密码发送到服务端进行验证
										JSONObject json = ConnectWs.connect(Login.this, EAccType.NULL, EOperation.GET_USER_INFO,userId);
										//将JSON数据转换为MAP型
										Map<String, String>userinfo = EHelper.toMap(json);
										
										userName = userinfo.get("userName").toString();
										
										
									} catch (IOException e) {
										Toast.makeText(Login.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
										finish();
										e.printStackTrace();
									}
									
									}else {
										Toast.makeText(Login.this, "没有连接网络", Toast.LENGTH_SHORT).show();
										finish();
									}
								
							}else {
								//登录失败
								MyDialogOne dialog = new MyDialogOne(Login.this,R.style.dialog);
								dialog.setTitleAndInfo("登录手机银行","登录失败！\n用户名或密码输入错误！");
								dialog.show();
							}
						}
				}else {
					//用Dialog提示附加码不正确
					MyDialogOne dialog = new MyDialogOne(Login.this,R.style.dialog);
					dialog.setTitleAndInfo("登录手机银行","附加码不正确！");
					dialog.show();
				}
			}
		});
		
		//设置底部选项卡
        bankmain = (ImageView)findViewById(R.id.mainbelow).findViewById(R.id.btnMain);
//        bankmain.setImageResource(R.drawable.main_sjyh);
        bankmain.setImageResource(R.drawable.main_sjyh2);
        bankmain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        
        bankhelp = (ImageView)findViewById(R.id.mainbelow).findViewById(R.id.btnHelper);
//        bankhelp.setImageResource(R.drawable.main_jrzs);
        bankhelp.setImageResource(R.drawable.main_jrzs2);
        bankhelp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				intent = new Intent(Login.this,FinanceAss.class);
				intent.addFlags(intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				Login.this.startActivity(intent);
				
			}
		});
	}
	
	//当不再需要时finish该页面
    @Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Login.this.finish();
	}
	
	//从服务器读取附加码
    private void loaderData(){
		if (EHelper.hasInternet(this)) {
		try {
			//从服务器取出所有币种
			JSONObject json = ConnectWs.connect(this, EAccType.NULL, EOperation.GET_EXTRA_CODE,"");
			//将JSON数据转换为MAP型
			extraCode = EHelper.toStr(json);
			
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
}