package ubank.account_manager;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import ubank.base.GeneralListActivity;
import ubank.main.BankMain;
import ubank.main.R;

public class AccountReportLossSecond extends GeneralListActivity {
	private Button btnComfirm = null;
	private String[] name = null;
	private String[] value = null;
	private Resources res = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayoutBlow(R.layout.midle_btn);
		
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
		setListener(tvClassThird, this, AccountReportLoss.class);
		
		name = new String[]{"挂失账户","账户别名"};
		value = new String[]{"622202112","我的储蓄卡"};
		setListAdapter(createText_Text(name, value));
		
		btnComfirm = (Button)findViewById(R.id.midle_btn).findViewById(R.id.button);
		btnComfirm.setText(R.string.confirm);
	}
}
