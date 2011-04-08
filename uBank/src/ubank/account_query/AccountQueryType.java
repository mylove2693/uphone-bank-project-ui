package ubank.account_query;

import ubank.base.GeneralActivity;
import ubank.common.Account_Select;
import ubank.main.Login;
import ubank.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AccountQueryType extends GeneralActivity {
	private Account_Select accountInfo = null;
	private String[] accountType = null;
	private String[] accountValues = null;
	private Button btnComfirm;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addLayout(R.layout.account_type);
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, Login.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("账户查询");
		
		accountInfo = (Account_Select)findViewById(R.id.account_select);
		accountType = new String[]{"活期储蓄卡","定期储蓄卡","信用卡"};
		accountValues = new String[]{"622113356744","633668832124"};
		accountInfo.AddTypeData(accountType);
		accountInfo.AddNumData(accountValues);
		
		btnComfirm = (Button)findViewById(R.id.account_type_comfirm).findViewById(R.id.button);
		btnComfirm.setText(R.string.confirm);
		btnComfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent  intent = new Intent();
				intent.putExtra("accNumValue", accountInfo.getAccNumValue());
				intent.putExtra("accTypeValue", accountInfo.getAccTypValue());
				intent.setClass(AccountQueryType.this, AccountQuery.class);
				AccountQueryType.this.startActivity(intent);
			}
		});
	}
	
}
