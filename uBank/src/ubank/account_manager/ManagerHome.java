package ubank.account_manager;

import android.os.Bundle;
import ubank.base.GeneralActivity;
import ubank.main.R;

public class ManagerHome extends GeneralActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//设置导航栏
		
		this.tvClassFirst.setText(R.string.account_manager);
		
		//添加账户管理主体部分
		this.addLayout(R.layout.acc_manger_body);
	}
	
	
}
