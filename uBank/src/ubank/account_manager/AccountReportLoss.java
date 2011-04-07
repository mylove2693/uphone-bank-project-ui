package ubank.account_manager;

import android.os.Bundle;
import ubank.base.GeneralActivity;
import ubank.main.R;

public class AccountReportLoss extends GeneralActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//设置当前界面主体
		this.addLayout(R.layout.acc_report_loss_body);
	}

}
