package ubank.account_query;

import ubank.base.GeneralActivity;
import ubank.common.Account_Select;
import ubank.main.Login;
import ubank.main.R;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemSelectedListener;

public class AccountQueryType extends GeneralActivity {
	private Account_Select accountInfo = null;
	private String[] accountType = null;
	private String[] accountValues = null;
	private Button btnComfirm;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addLayout(R.layout.account_querytype);
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
	}
	
	class SpinnerListener implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
