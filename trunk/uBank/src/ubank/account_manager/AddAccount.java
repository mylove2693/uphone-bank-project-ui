package ubank.account_manager;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import ubank.base.GeneralActivity;
import ubank.base.MyDialogOne;
import ubank.enum_type.EAccState;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddAccount extends GeneralActivity{
	// 导航栏三级标题
	private TextView txt=null;
	private Button next_btn =null;
	private String userid = "5";
	private Spinner accType = null;
	private EditText account = null;
	private EditText nickName = null;
	private EditText accPwd = null;
	private ArrayAdapter<String> adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 获得传过来数据
		Intent intent = getIntent();
//		title = intent.getStringExtra("title");

		tvClassFirst.setVisibility(View.VISIBLE);
		//首页监听
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("账户管理>");
		 //账户管理的监听
        setListener(tvClassSecond, this, ManagerHome.class);
		
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("添加账户");
		//布局文件
		addLayout(R.layout.add_acc);
		
		txt=(TextView)findViewById(R.id.above_txt).findViewById(R.id.Text_View_16_Gray);
        txt.setText("用户号"+userid);
        txt = (TextView) findViewById(R.id.txt_acc_type).findViewById(R.id.Text_View_18);
        txt.setText("请选择账户类型:");
        txt = (TextView) findViewById(R.id.txt1).findViewById(R.id.Text_View_18);
		txt.setText("请输入账户号:");
		txt = (TextView) findViewById(R.id.txt2).findViewById(R.id.Text_View_18);
		txt.setText("请设置账户别名:");
		txt = (TextView) findViewById(R.id.psd_txt).findViewById(R.id.Text_View_18);
		txt.setText("请输入账户密码:");
		
		accType = (Spinner)findViewById(R.id.spinner_acc_type).findViewById(R.id.Spinner);
		account = (EditText)findViewById(R.id.edit1).findViewById(R.id.et_acc);
		nickName = (EditText)findViewById(R.id.edit2).findViewById(R.id.et_user);
		accPwd = (EditText)findViewById(R.id.psd_edit).findViewById(R.id.et_psd);
		setAccType();
		
		next_btn= (Button) findViewById(R.id.next_btn).findViewById(R.id.button);
		next_btn.setText(R.string.confirm);
		next_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (EHelper.hasInternet(AddAccount.this)) {
					try {
						JSONObject json = new JSONObject();
						json = ConnectWs.connect(AddAccount.this,
								EAccType.NULL, EOperation.ADD_ACC, userid,
								account.getText().toString(), accType
										.getSelectedItem().toString(), nickName
										.getText().toString(), accPwd.getText()
										.toString());
						boolean result = EHelper.toBoolean(json);
						MyDialogOne  d1=new MyDialogOne(AddAccount.this,R.style.dialog);
						if(result){
							d1.setTitleAndInfo("提示", "添加账户成功！");
						}else{
							d1.setTitleAndInfo("提示", "添加账户失败！");
						}
						d1.Listener(AddAccount.this,ManagerHome.class);
						d1.show();

					} catch (IOException e) {
						Toast.makeText(AddAccount.this, "对不起，服务器未连接",
								Toast.LENGTH_SHORT).show();
						e.printStackTrace();
					}
				} else {
					Toast.makeText(AddAccount.this, "没有连接网络",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	private void setAccType(){
		if (EHelper.hasInternet(this)) {
			try {
				JSONObject json = new JSONObject();
				String[] account = null;
				json = ConnectWs.connect(this, EAccType.NULL,
						EOperation.GET_ACC_TYPE_ALL);
				List<String> value = EHelper.toList(json);
				account = new String[value.size()];
				for(int i = 0;i < value.size();i++){
					account[i] = value.get(i);
				}
				accType.setAdapter(CreAda(account));
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
	
	// 创建一个有数据的适配器
	private ArrayAdapter<String> CreAda(String[] data) {
		/**
		 * 实例化一个适配器，为适配器添加样式 此处采用内部的
		 */
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 向适配器中间加数据
		for (int i = 0; i < data.length; i += 1) {
			adapter.add(data[i]);
		}
		return adapter;
	}
}
