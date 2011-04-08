package ubank.account_query;

import ubank.base.GeneralListActivity;
import ubank.main.Login;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccountQueryInfo extends GeneralListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, Login.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("账户查询>");
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("账户信息及余额查询");
		
		Intent intent = this.getIntent();
		String[] name = new String[]{"账户","账户类型","币种","余额","存期","起息月","利率"};
		String[] value = new String[]{intent.getStringExtra("accNumValue"),
									  intent.getStringExtra("accTypeValue"),
									  "人民币","100000","三个月","2010-11",
									  "1.5%"};
		
		setListAdapter(createText_Text(name,value));
	}
}
