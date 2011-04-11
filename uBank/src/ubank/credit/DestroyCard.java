package ubank.credit;

import ubank.base.GeneralActivity;
import ubank.main.R;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Button;

public class DestroyCard extends GeneralActivity {

	private Button btnNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayout(R.layout.cc_destory_card);
		initializeData();// 初始化数据

		btnNext.setOnClickListener(onClick);
	}

	private void initializeData() {
		((TextView) (findViewById(R.id.cc_tv_openName)
				.findViewById(R.id.blue_Text_View))).setText("开户名");

		((TextView) (findViewById(R.id.cc_tv_ccNo)
				.findViewById(R.id.blue_Text_View))).setText("信用卡号");

		((TextView) (findViewById(R.id.cc_tv_idType)
				.findViewById(R.id.blue_Text_View))).setText("证件类型");

		((TextView) (findViewById(R.id.cc_tv_id)
				.findViewById(R.id.blue_Text_View))).setText("证件号");

		((TextView) (findViewById(R.id.cc_tv_phone)
				.findViewById(R.id.blue_Text_View))).setText("手机号");

		((TextView) (findViewById(R.id.cc_tv_pwd)
				.findViewById(R.id.blue_Text_View))).setText("账户密码");

		btnNext = (Button) (findViewById(R.id.cc_btn_next)
				.findViewById(R.id.button));
		btnNext.setText("确认销卡");
	}

	// 按钮监听
	private OnClickListener onClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// 弹出对话框
			// 设置对话框的布局
			View view = getLayoutInflater().inflate(R.xml.comdialog1, null);
			final Dialog dialog = new Dialog(DestroyCard.this, R.style.dialog);
			dialog.show();
			// 设置具体对话框布局的宽和高
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.MATCH_PARENT);

			// 将设置好的布局View加到对话框中
			dialog.addContentView(view, params);
			// 设置标题
			((TextView) view.findViewById(R.id.tv_comdlog_title))
					.setText("提示的标题");
			// 设置显示的信息
			((TextView) view.findViewById(R.id.tv_comdlog_con1))
					.setText("你要显示信息");
			Button Ok_btn = (Button) view.findViewById(R.id.btn_comdlog_ok);
			Ok_btn.setText("确认");
			Ok_btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
		}
	};
}
