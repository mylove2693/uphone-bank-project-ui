package ubank.account_manager;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ubank.base.GeneralActivity;
import ubank.main.BankMain;
import ubank.main.R;

public class AccountBind extends GeneralActivity {

	private Resources res;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 设置当前界面的主体部分
		res = this.getBaseContext().getResources();
		this.setNavigation();
		this.setBody();
	}

	// 设置导航栏
	private void setNavigation() {
		String temp = ">";
		// 设置导航栏“首页”
		this.tvClassFirst.setText(res.getString(R.string.home));
		this.tvClassFirst.setVisibility(View.VISIBLE);
		setListener(tvClassFirst, this, BankMain.class);
		// 设置导航栏“>账户管理”
		this.tvClassSecond.setText(temp
				+ res.getString(R.string.account_manager));
		this.tvClassSecond.setVisibility(View.VISIBLE);
		setListener(tvClassSecond, this, ManagerHome.class);
		// 设置导航栏“>账户绑定”
		this.tvClassThird.setText(temp + res.getString(R.string.bind_account));
		this.tvClassThird.setVisibility(View.VISIBLE);
	}

	// 设置body
	private void setBody() {
		this.addLayout(R.layout.acc_bind_body);

		TextView psdText = (TextView) findViewById(R.id.text_psd).findViewById(
				R.id.Text_View_18);
		psdText.setText(res.getString(R.string.input_password));
		EditText pdsEdit = (EditText) findViewById(R.id.psd).findViewById(
				R.id.et_psd);
		Button bind = (Button) findViewById(R.id.bind)
				.findViewById(R.id.button);
		bind.setText(res.getString(R.string.bind));
		bind.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}

		});
	}

}
