package ubank.account_manager;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import ubank.base.GeneralActivity;
import ubank.common.Account_Select;
import ubank.enum_type.EAccState;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.Login;
import ubank.main.R;
import ubank.webservice.ConnectWs;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ReservationChangeCard extends GeneralActivity {
	private Account_Select accountInfo = null;
	private Button btnComfirm;
	private String[] accountType = null;
	private String[] accountValues = null;
	private Resources res = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.addLayout(R.layout.account_type);

		res = this.getBaseContext().getResources();
		String flag = ">";

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText(res.getString(R.string.home) + flag);
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText(res.getString(R.string.account_manager) + flag);
		setListener(tvClassSecond, this, ManagerHome.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText(R.string.reservation_change_card);

		accountInfo = (Account_Select) findViewById(R.id.account_select);
		loaderData();
		loderValueData();

		btnComfirm = (Button) findViewById(R.id.account_type_comfirm)
				.findViewById(R.id.button);
		btnComfirm.setText(R.string.cc_next);
		btnComfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(accountInfo.getAccNumValue() != null){
					Intent intent = new Intent();
					intent.putExtra("accTypeValue", accountInfo.getAccTypValue());
					intent.putExtra("accNumValue", accountInfo.getAccNumValue());
					intent.setClass(ReservationChangeCard.this,
							ReservationChangeCardSecond.class);
					ReservationChangeCard.this.startActivity(intent);
				}else{
					Toast.makeText(ReservationChangeCard.this,
							"对不起，您未选择账号！", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
	}

	private void loaderData() {
		if (EHelper.hasInternet(this)) {
			try {
				JSONObject json = ConnectWs.connect(this, EAccType.NULL,
						EOperation.GET_ACC_TYPE_ALL);
				List<String> name = EHelper.toList(json);
				accountType = new String[name.size()];
				for (int i = 0; i < accountType.length; i++) {
					accountType[i] = name.get(i);
				}
			} catch (IOException e) {
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			}
			accountInfo.AddTypeData(accountType);
		} else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
	}

	private void loderValueData() {
		accountInfo.AccTypSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						if (EHelper.hasInternet(ReservationChangeCard.this)) {
							try {
								String type = accountInfo.getAccTypValue();
								JSONObject json = ConnectWs
										.connect(
												ReservationChangeCard.this,
												EAccType.NULL,
												EOperation.GET_ACC,
												Login.userId,
												type,
												EAccState
														.getStateName(EAccState.UNORDER));
								List<String> value = EHelper.toList(json);
								accountValues = new String[value.size()];
								for (int i = 0; i < accountValues.length; i++) {
									accountValues[i] = value.get(i);
								}
							} catch (IOException e) {
								Toast.makeText(ReservationChangeCard.this,
										"对不起，服务器未连接", Toast.LENGTH_SHORT)
										.show();
								e.printStackTrace();
							}

							accountInfo.AddNumData(accountValues);
						} else {
							Toast.makeText(ReservationChangeCard.this,
									"没有连接网络", Toast.LENGTH_SHORT).show();
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
	}
}
