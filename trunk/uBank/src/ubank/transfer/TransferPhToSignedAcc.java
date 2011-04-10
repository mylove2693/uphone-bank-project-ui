package ubank.transfer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ubank.base.GeneralActivity;
import ubank.main.R;

/**
 * 手机到签约账户转账
 * 
 * @author Administrator
 * 
 */
public class TransferPhToSignedAcc extends GeneralActivity {
	// 导航栏三级标题
	String title = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent up_intent = getIntent();
		// 获得传过来的导航栏标题
		title = up_intent.getStringExtra("title");

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("转账汇款>");
		 //转账汇款的监听
        setListener(tvClassSecond, this, TransferMain.class);
		
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText(title);

		addLayout(R.layout.transfer_phtosigacc);
		TextView transfer_num_txtview = (TextView) findViewById(
				R.id.transfer_amt_txt).findViewById(R.id.Text_View_18);
		transfer_num_txtview.setText("请输入转账金额:");

		TextView transfer_acc_txtview = (TextView) findViewById(
				R.id.transfer_acc_txt).findViewById(R.id.Text_View_18);
		//根据标题来判断是提示输入什么号
		if (title.equals("手机到手机转账")) {
			transfer_acc_txtview.setText("请输入目标手机号:");
		} else {
			transfer_acc_txtview.setText("请输入转入账户:");
		}

		TextView transfer_psd_txtview = (TextView) findViewById(
				R.id.transfer_psd_txt).findViewById(R.id.Text_View_18);
		transfer_psd_txtview.setText("请输入账户密码:");

		Button next_btn = (Button) findViewById(R.id.next_btn).findViewById(
				R.id.button);
		next_btn.setText("下一步");
	}

}
