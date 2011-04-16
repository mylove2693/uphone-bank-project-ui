package ubank.account_manager;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import ubank.base.GeneralListActivity;
import ubank.base.MyDialogOne;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AccountReportLossSecond extends GeneralListActivity {
	private Button btnComfirm = null;
	private String[] name = null;
	private String[] value = null;
	private Resources res = null;
	private Intent intent = null;
	private String accType = "";
	private String account = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayoutBlow(R.layout.midle_btn);
		intent = this.getIntent();
		res = this.getBaseContext().getResources();
		String flag = ">";
		
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText(res.getString(R.string.home) + flag);
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText(res.getString(R.string.account_manager) + flag);
		setListener(tvClassSecond, this, ManagerHome.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText(R.string.acc_report_loss);
		setListener(tvClassThird, this, AccountReportLoss.class);
		
		setAccInfo();
		
		btnComfirm = (Button)findViewById(R.id.midle_btn).findViewById(R.id.button);
		btnComfirm.setText(R.string.confirm);
		btnComfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (EHelper.hasInternet(AccountReportLossSecond.this)) {
				try {
					JSONObject json = null;
					if ("信用卡".equals(accType)) {
						json = ConnectWs.connect(AccountReportLossSecond.this,
								EAccType.CREDIT_CARD, EOperation.LOSS_REGISTER, account);
					} else if ("定期储蓄卡".equals(accType)) {
						json = ConnectWs.connect(AccountReportLossSecond.this,
								EAccType.TIME_DEPOSITS, EOperation.LOSS_REGISTER,
								account);
					} else {
						json = ConnectWs.connect(AccountReportLossSecond.this,
								EAccType.CURRENT_DEPOSIT, EOperation.LOSS_REGISTER,
								account);
					}
					boolean result = EHelper.toBoolean(json);
					MyDialogOne  d1=new MyDialogOne(AccountReportLossSecond.this,R.style.dialog);
					if(result){
						d1.setTitleAndInfo("提示", "挂失成功！");
					}else{
						d1.setTitleAndInfo("提示", "挂失失败！");
					}
					
					d1.Listener(AccountReportLossSecond.this,ManagerHome.class);
					d1.show();
				} catch (IOException e) {
					Toast.makeText(AccountReportLossSecond.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
				}else {
					Toast.makeText(AccountReportLossSecond.this, "没有连接网络", Toast.LENGTH_SHORT).show();
				}
			}
				
		});
	}
	
	private void setAccInfo() {
		name = new String[]{"账户","账户别名"};
		value = new String[name.length];
		JSONObject json = null;
		accType = intent.getStringExtra("accTypeValue");
		account = intent.getStringExtra("accNumValue");
		if (EHelper.hasInternet(this)) {
		try {
			if ("信用卡".equals(accType)) {
				json = ConnectWs.connect(this, EAccType.CREDIT_CARD, EOperation.GET_ACC_INFO,
						intent.getStringExtra("accNumValue"));
			} else if ("定期储蓄卡".equals(accType)) {
				json = ConnectWs.connect(this, EAccType.TIME_DEPOSITS, EOperation.GET_ACC_INFO,
						intent.getStringExtra("accNumValue"));
			} else {
				json = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT, EOperation.GET_ACC_INFO,
						intent.getStringExtra("accNumValue"));
			}
			try {
				for (int i = 0; i < name.length; i++) {
					value[i] = json.getString(name[i]);
				}
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
		} catch (IOException e) {
			Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
			finish();
			e.printStackTrace();
		}
		setListAdapter(createText_Text(name, value));
		} else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
}
