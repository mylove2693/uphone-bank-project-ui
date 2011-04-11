package ubank.transfer;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import ubank.base.GeneralActivity;
import ubank.main.R;

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
		transfer_accnum.setText("62254634");

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
				// TODO Auto-generated method stub
				// 密码的判断--->
				// 设置对话框的布局
				View view = getLayoutInflater().inflate(R.xml.comdialog1, null);
				/**
				 * 设置对话框的样式
				 */
				Dialog dialog = new Dialog(Transfer_inpsd.this, R.style.dialog);
				/**
				 * 显示对话框
				 */
				dialog.show();
				// 设置具体对话框布局的宽和高
				LayoutParams params = new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);

				// 将设置好的布局View加到对话框中
				dialog.addContentView(view, params);
              //设置标题
				((TextView) view.findViewById(R.id.tv_comdlog_title))
          						.setText("提示的标题");
				//设置显示的信息
				((TextView) view.findViewById(R.id.tv_comdlog_con1))
					.setText("你要显示信息");
				Button Ok_btn = (Button) view.findViewById(R.id.btn_comdlog_ok);
				Ok_btn.setText("下一步");
				Ok_btn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						System.out.println("dasd");
						Intent payment_intent = new Intent();
						payment_intent.putExtra("title",
								Transfer_inpsd.this.title);
						payment_intent.setClass(Transfer_inpsd.this,
								Transfer_information.class);
						Transfer_inpsd.this.startActivity(payment_intent);
					}
				});
			}
		});

	}
}
