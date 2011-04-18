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
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FirstAccount extends GeneralActivity {

	private TextView txt = null;
	private Button next_btn = null;
	private String num;
	private Spinner select_acc = null;
	private String userid = "1";
	private ArrayAdapter<String> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addLayout(R.layout.acc_first_body);

		tvClassFirst.setVisibility(View.VISIBLE);
		// 监听
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("账户管理>");
		// 监听
		setListener(tvClassSecond, this, ManagerHome.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("首选账户");
		/**
		 * 接收上一个Activity穿过来的 值
		 */
		// Intent intent=getIntent();
		// num=intent.getStringExtra("num");
		// 设置上面的字
		txt = (TextView) findViewById(R.id.txt_one).findViewById(
				R.id.Text_View_20);
		// txt.setText("当前首选账户为:"+num);
		showPreAcc();
		txt = (TextView) findViewById(R.id.txt_two).findViewById(
				R.id.Text_View_20);
		txt.setText("重新选择首选账户号:");

		select_acc = (Spinner) findViewById(R.id.spinner_one).findViewById(
				R.id.Spinner);
		setSelectAcc();
		// 下一步按钮
		next_btn = (Button) findViewById(R.id.ok_btn).findViewById(R.id.button);
		next_btn.setText(R.string.confirm);
		next_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (EHelper.hasInternet(FirstAccount.this)) {
					try {
						JSONObject json = new JSONObject();
						json = ConnectWs.connect(FirstAccount.this, EAccType.NULL,
								EOperation.SET_PRE_ACC, "2",select_acc.getSelectedItem().toString());
						boolean result = EHelper.toBoolean(json);
						MyDialogOne  d1=new MyDialogOne(FirstAccount.this,R.style.dialog);
						if(result){
							d1.setTitleAndInfo("提示", "首选账户设置成功！");
						}else{
							d1.setTitleAndInfo("提示", "首选账户设置失败！");
						}
						d1.Listener(FirstAccount.this,ManagerHome.class);
						d1.show();
					} catch (IOException e) {
						Toast.makeText(FirstAccount.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
						e.printStackTrace();
					}
				} else {
					Toast.makeText(FirstAccount.this, "没有连接网络", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}

	private void showPreAcc() {
		if (EHelper.hasInternet(this)) {
			try {
				JSONObject json = new JSONObject();
				json = ConnectWs.connect(this, EAccType.NULL,
						EOperation.GET_PRE_ACC, userid);
				String preAcc = EHelper.toList(json).get(0).toString();
				num = preAcc.split("#")[1];
				txt.setText("当前首选账户为:" + num);
			} catch (IOException e) {
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			}
		} else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
	}

	private void setSelectAcc() {
		if (EHelper.hasInternet(this)) {
			try {
				JSONObject json = new JSONObject();
				String[] account = null;
				json = ConnectWs.connect(this, EAccType.NULL,
						EOperation.GET_ACC, userid, EAccType
								.getAccTypeName(EAccType.CURRENT_DEPOSIT),
						EAccState.getStateName(EAccState.BIND));
				List<String> value = EHelper.toList(json);
				account = new String[value.size()];
				for(int i = 0;i < value.size();i++){
					account[i] = value.get(i);
				}
				select_acc.setAdapter(CreAda(account));
			} catch (IOException e) {
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			}
		} else {
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
