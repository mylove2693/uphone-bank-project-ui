package ubank.account_manager;

import android.os.Bundle;
import ubank.base.GeneralActivity;
import ubank.main.R;

public class AccountBind extends GeneralActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//设置当前界面的主体部分
		this.addLayout(R.layout.acc_bind_body);
	}

}
