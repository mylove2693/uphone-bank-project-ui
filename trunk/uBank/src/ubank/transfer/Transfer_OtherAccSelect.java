package ubank.transfer;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import ubank.account_query.AccountQueryType;
import ubank.base.GeneralActivity;
import ubank.common.Account_Select;
import ubank.enum_type.EAccState;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.payment.AllPaymentSer;
import ubank.payment.ElseAcc;
import ubank.payment.InputPsw;
import ubank.webservice.ConnectWs;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Transfer_OtherAccSelect extends GeneralActivity {
	private Account_Select accountInfo = null;
	private TextView txt = null;
	private Button next_btn = null;
	private String[] accountType = null;
	private String[] accountValues = null;
	String userid = "";

	// 导航栏三级标题
	String title = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent up_intent = getIntent();
		// 获得传过来的导航栏标题

		title = up_intent.getStringExtra("title");
		addLayout(R.layout.account_type);
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("转账汇款>");
		// 监听
		setListener(tvClassSecond, this,TransferMain.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText(title);

		accountInfo = (Account_Select) findViewById(R.id.account_select);
		userid = "3";
		/**
		 * 添加数据的方法
		 */
		loaderData();// 添加类型
		loderValueData();// 添加帐号

		// 下一步按钮的监听
		next_btn = (Button) findViewById(R.id.account_type_comfirm)
				.findViewById(R.id.button);
		next_btn.setText("下一步");
		next_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/**
				 * 将服务器上取得的值传给下一个Activity 首先将此Activity上的数据提取传到下一个 主要是帐号和类型
				 */
				String acc_type,acc_num;
				//提取账户类型
				acc_type = Transfer_OtherAccSelect.this.accountInfo.AccTypSpinner
						.getSelectedItem().toString();
				//提取帐号
				acc_num = Transfer_OtherAccSelect.this.accountInfo.AccNumSpinner
						.getSelectedItem().toString();
				Intent elseAcc_intent = new Intent();
				elseAcc_intent.putExtra("acc_type", acc_type);
				elseAcc_intent.putExtra("acc_num", acc_num);
				elseAcc_intent.putExtra("title",
						Transfer_OtherAccSelect.this.title);
				elseAcc_intent.setClass(Transfer_OtherAccSelect.this,
						Transfer_inpsd.class);
				Transfer_OtherAccSelect.this.startActivity(elseAcc_intent);
			}
		});
	}

	// 添加账户类型
	private void loaderData() {
		JSONObject json=null;
		try {
			json = ConnectWs.connect(this, EAccType.NULL,
					EOperation.GET_ACC_TYPE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json.toString()+"--------------");
		List<String> name = EHelper.toList(json);
		accountType = new String[name.size()];
		for (int i = 0; i < accountType.length; i++) {
			accountType[i] = name.get(i);
		}
		accountInfo.AddTypeData(accountType);
	}

	// 添加帐号
	private void loderValueData() {
		accountInfo.AccTypSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						String type = accountInfo.getAccTypValue();
						JSONObject json=null;
						try {
							json = ConnectWs.connect(
									Transfer_OtherAccSelect.this, EAccType.NULL,
									EOperation.GET_ACC, userid, type, EAccState
											.getStateName(EAccState.BIND));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						List<String> value = EHelper.toList(json);
						accountValues = new String[value.size()];
						for (int i = 0; i < accountValues.length; i++) {
							accountValues[i] = value.get(i);
						}
						accountInfo.AddNumData(accountValues);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
	}
}
