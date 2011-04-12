package ubank.account_manager;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import ubank.base.GeneralActivity;
import ubank.common.Account_Select;
import ubank.main.BankMain;
import ubank.main.R;

public class AccountReportLoss extends GeneralActivity{
	private Account_Select accountInfo = null;
	private Button btnComfirm;
	private String[] accountType = null;
	private String[] accountValues = null;
	private Resources res = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//设置当前界面主体
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
		tvClassThird.setText(R.string.acc_report_loss);
		
		accountInfo = (Account_Select)findViewById(R.id.account_select);
		accountType = new String[]{"活期储蓄卡","定期储蓄卡","信用卡"};
		accountValues = new String[]{"622113356744","633668832124"};
		accountInfo.AddTypeData(accountType);
		accountInfo.AddNumData(accountValues);
		
		btnComfirm = (Button)findViewById(R.id.account_type_comfirm).findViewById(R.id.button);
		btnComfirm.setText(R.string.cc_next);
	}

}
