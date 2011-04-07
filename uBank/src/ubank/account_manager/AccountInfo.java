package ubank.account_manager;

import android.os.Bundle;
import ubank.base.GeneralActivity;
import ubank.main.R;

public class AccountInfo extends GeneralActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//设置账户信息界面主体部分
		this.addLayout(R.layout.acc_info_body);
	}

}
