package ubank.account_manager;

import ubank.base.GeneralActivity;
import ubank.main.BankMain;
import ubank.main.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetAccountAliasSecond extends GeneralActivity {
	private TextView aliss_txt = null;
	private EditText aliss_edit = null;
	private Button aliss_btn = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayout(R.layout.set_aliss);
		
		tvClassFirst.setVisibility(View.VISIBLE);
        //监听
        tvClassFirst.setText("首页>");
        setListener(tvClassFirst, this, BankMain.class);
        tvClassSecond.setVisibility(View.VISIBLE);
        tvClassSecond.setText("账户管理>");
       //监听
        setListener(tvClassSecond, this, ManagerHome.class);
        tvClassThird.setVisibility(View.VISIBLE);
        tvClassThird.setText("账户别名");
        setListener(tvClassThird, this, SetAccountAlias.class);
		
		aliss_txt = (TextView)findViewById(R.id.aliss_txt).findViewById(R.id.Text_View_18);
		aliss_txt.setText("请设置账户的别名");
		aliss_edit = (EditText)findViewById(R.id.aliss_edit).findViewById(R.id.et_user);
		aliss_edit.setText("我的储蓄卡");
		aliss_btn = (Button)findViewById(R.id.aliss_btn).findViewById(R.id.button);
		aliss_btn.setText(R.string.confirm);
	}
}
