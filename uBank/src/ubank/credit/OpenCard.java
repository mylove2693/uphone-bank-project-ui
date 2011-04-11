package ubank.credit;

import ubank.base.GeneralActivity;
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
			// 弹出对话框
			 View view = getLayoutInflater().inflate(R.xml.comdialog1, null);
			// new AlertDialog.Builder(OpenCard.this).setView(view).show();
			Dialog dialog = new Dialog(OpenCard.this, R.style.dialog);
			dialog.show();
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.MATCH_PARENT);
			
			dialog.addContentView(view, params);
			
			((TextView)view.findViewById(R.id.tv_comdlog_title)).setText("标题");
			

		}
	};
}
