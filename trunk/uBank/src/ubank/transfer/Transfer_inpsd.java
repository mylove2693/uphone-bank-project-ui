package ubank.transfer;

import org.json.JSONObject;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import ubank.base.GeneralActivity;
import ubank.base.MyDialogOne;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.main.R;
import ubank.webservice.ConnectWs;

/**
 * 转账的密码输入界面
 * 
 * @author Administrator
 * 
 */
public class Transfer_inpsd extends GeneralActivity {
	// 导航栏三级标题
	String title = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		JSONObject jsonObj = ConnectWs.connect(this, EAccType.NULL,
				EOperation.GET_PAYMENT_NAME, "1");

		System.out.println("后台数据"+jsonObj.toString());

		
		Intent up_intent = getIntent();
		// 获得传过来的导航栏标题
		title = up_intent.getStringExtra("title");

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("转账汇款>");
		// 转账汇款的监听
		setListener(tvClassSecond, this, TransferMain.class);

		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText(title);

		// 添加布局
		addLayout(R.layout.transfer_inspd);

		TextView transfer_acc_mation = (TextView) findViewById(R.id.acc_mation)
				.findViewById(R.id.Text_View_18);
		transfer_acc_mation.setText("您选择的转账账户为：");

		/**
		 * 从数据库中取得数据
		 */
		// 这个是类型 是小字
		TextView transfer_acctype = (TextView) findViewById(R.id.acc_type)
				.findViewById(R.id.Text_View_16);
		transfer_acctype.setText("活期什么的");

		// 这个是帐号 是小字
		TextView transfer_accnum = (TextView) findViewById(R.id.acc_num)
				.findViewById(R.id.Text_View_16);
		transfer_accnum.setText("str");

		// 提示输入密码的文本框
		TextView transfer_psd_txtview = (TextView) findViewById(
				R.id.acc_inpsd_mation).findViewById(R.id.Text_View_18);
		transfer_psd_txtview.setText("请输入账户密码:");

		Button transfer_next = (Button) findViewById(R.id.transfer_inpsd_next)
				.findViewById(R.id.button);
		transfer_next.setText("下一步");

		Dialog myD = new Dialog(this, R.style.dialog);
		transfer_next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		
				MyDialogOne dialog=new MyDialogOne(Transfer_inpsd.this, R.style.dialog);
				dialog.setTitleAndInfo("密码成功失败提示", "成功");
				dialog.Listener(Transfer_inpsd.this, Transfer_information.class);
				dialog.show();
			}
		});

	}
}
