package ubank.credit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import ubank.base.GeneralActivity;
import ubank.common.Account_Select;
import ubank.main.BankMain;
import ubank.main.R;

public class SelectRepaymentAcc extends GeneralActivity {

	private Button btnNext;
	// private Spinner spnrAccType;
	// private Spinner spnrAccNum;
	private Account_Select accSelect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayout(R.layout.account_type);

		initializeData();// 初始化数据

		btnNext.setOnClickListener(btnClick);
	}

	private void initializeData() {
		// TODO 初始化数据
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("信用卡>");
		setListener(tvClassSecond, this, CreditCardMain.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("信用卡还款");

		btnNext = (Button) (findViewById(R.id.account_type_comfirm)
				.findViewById(R.id.button));
		accSelect = (Account_Select) findViewById(R.id.account_select);
		accSelect.AddTypeData(new String[] { "储蓄卡", "信用卡" });
		accSelect.AddNumData(new String[] { "asdf", "123" });
	}

	private OnClickListener btnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 检查是否有激活

			String accType = accSelect.getAccTypValue();
			String accNum = accSelect.getAccNumValue();
			Intent intent = new Intent(SelectRepaymentAcc.this,
					ConfrimRepayment.class);
			startActivity(intent);
		}
	};
}
