package ubank.credit;

import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;

public class CreditCardInfo extends GeneralListActivity {

	private Button btnNext;
	private String creditcard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// 接收数据
		Intent intent = getIntent();
		creditcard = intent.getStringExtra("creditcard");

		addLayoutBlow(R.layout.midle_btn);
		initializeData();

		btnNext.setOnClickListener(btnOnClick);
	}

	private void initializeData() {
		// TODO 初始化
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("信用卡>");
		setListener(tvClassSecond, this, CreditCardMain.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("信用卡还款");
		if (EHelper.hasInternet(this)) {
			// 连接服务器...
			JSONObject jsonObj = ConnectWs.connect(CreditCardInfo.this,
					EAccType.CREDIT_CARD, EOperation.GET_ACC_INFO, creditcard);
			String[] name = new String[] { "信用卡账户", "持卡人姓名", "本期应还款额",
					"本期最低还款额", "本期到期还款日" };
			String[] value = new String[5];
			value[0] = creditcard;// 信用卡号
			value[1] = "张三";
			for (int i = 2; i < 5; i++) {
				try {
					value[i] = jsonObj.getString(name[i]);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					int len = jsonObj.length();
					Log.e("CreditCardInfo", Integer.toString(len));
					e.printStackTrace();
				}
			}
			SimpleAdapter adapter = createText_Text(name, value);
			setListAdapter(adapter);
		} else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
		btnNext = (Button) findViewById(R.id.button);
		btnNext.setText("下一步");
	}

	private OnClickListener btnOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(CreditCardInfo.this,
					SelectRepaymentAcc.class);
			startActivity(intent);
		}
	};
}
