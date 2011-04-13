package ubank.credit;

import ubank.account_query.AccountQueryType;
import ubank.base.GeneralActivity;
import ubank.main.BankMain;
import ubank.main.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

public class OpenCard extends GeneralActivity {
	private Button btnNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayout(R.layout.cc_open_card);
		initializeData();// 初始化数据
		btnNext.setOnClickListener(onClick);
	}

	private void initializeData() {
		// TODO 初始化数据

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("信用卡>");
		setListener(tvClassSecond, this, CreditCardMain.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("开卡");

		((TextView) (findViewById(R.id.cc_tv_openName)
				.findViewById(R.id.blue_Text_View))).setText("开户名");

		((TextView) (findViewById(R.id.cc_tv_ccNo)
				.findViewById(R.id.blue_Text_View))).setText("信用卡号");

		((TextView) (findViewById(R.id.cc_tv_noValid)
				.findViewById(R.id.blue_Text_View))).setText("有效期");

		((TextView) (findViewById(R.id.cc_tv_idType)
				.findViewById(R.id.blue_Text_View))).setText("证件类型");

		((TextView) (findViewById(R.id.cc_tv_id)
				.findViewById(R.id.blue_Text_View))).setText("证件号");

		((TextView) (findViewById(R.id.cc_tv_phone)
				.findViewById(R.id.blue_Text_View))).setText("手机号");

		((TextView) (findViewById(R.id.cc_tv_tel)
				.findViewById(R.id.blue_Text_View))).setText("固定电话");

		((TextView) (findViewById(R.id.cc_tv_pwd)
				.findViewById(R.id.blue_Text_View))).setText("账户密码");

		btnNext = (Button) (findViewById(R.id.cc_btn_next)
				.findViewById(R.id.button));
		btnNext.setText("确认开卡");
	}

	// 按钮监听
	private OnClickListener onClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			boolean flag = false;
			// 弹出对话框
			// 设置对话框的布局
			View view = getLayoutInflater().inflate(R.xml.comdialog1, null);
			final Dialog dialog = new Dialog(OpenCard.this, R.style.dialog);
			dialog.show();
			// 设置具体对话框布局的宽和高
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.MATCH_PARENT);
			// 将设置好的布局View加到对话框中
			dialog.addContentView(view, params);
			Button Ok_btn = (Button) view.findViewById(R.id.btn_comdlog_ok);
			Ok_btn.setText("确定");
			Ok_btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			if (flag) {
				((TextView) view.findViewById(R.id.tv_comdlog_title))
						.setText("成功提示");
				((TextView) view.findViewById(R.id.tv_comdlog_con1))
						.setText("开卡成功，此卡在绑定之前，还不能在手机银行里操作");

			} else {
				((TextView) view.findViewById(R.id.tv_comdlog_title))
						.setText("失败提示");
				((TextView) view.findViewById(R.id.tv_comdlog_con1))
						.setText("开卡失败，请验证输入的信息是否正确");
			}

		}
	};
}
