package ubank.payment;

import ubank.base.GeneralActivity;
import ubank.base.MyDialogOne;
import ubank.main.BankMain;
import ubank.main.R;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputPsw extends GeneralActivity {

	private TextView txt = null;
	private Button ok_btn = null;
	private String account = null;
	private Dialog dialog=null;
	private String acc_balance = null;
	private EditText pws = null;
	String title = "chenggon";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addLayout(R.layout.input_psw);

		tvClassFirst.setVisibility(View.VISIBLE);
		// 监听
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		// 监听
		tvClassSecond.setText("自助缴费>");
		setListener(tvClassSecond, this, AllPaymentSer.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("密码输入");

		Intent intent = getIntent();
		account = intent.getStringExtra("account");
		acc_balance = intent.getStringExtra("money");
		// 设置上面的字
		txt = (TextView) findViewById(R.id.pws_txt_one).findViewById(
				R.id.Text_View_20);
		txt.setText("您选择的账户为:");
		txt = (TextView) findViewById(R.id.pws_txt_two).findViewById(
				R.id.Text_View_20);
		txt.setText(account);
		txt = (TextView) findViewById(R.id.pws_txt_three).findViewById(
				R.id.Text_View_20);
		txt.setText("账户余额:");
		txt = (TextView) findViewById(R.id.pws_txt_four).findViewById(
				R.id.Text_View_20);
		txt.setText(acc_balance);
		txt = (TextView) findViewById(R.id.pws_txt_five).findViewById(
				R.id.Text_View_20);
		txt.setText("请输入账户密码");
		// 密码输入框
		pws = (EditText) findViewById(R.id.pws).findViewById(R.id.et_psd);
		// 确认缴费按钮
		ok_btn = (Button) findViewById(R.id.ok_btn).findViewById(R.id.button);
		ok_btn.setText("确认缴费");
		// 确认缴费按钮的监听
		ok_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String pwsStr = pws.getText().toString().trim();
				View view;
				
				String balance = "1000000";
				// 从服务上验证密码是否正确
				/**
				 * 先判断密码框是否为空 当不为空时在验证密码是否正确 中间用 & 连接 & 表示前面为true的情况下后面还要执行
				 * 知道前后都为true时菜返回true
				 */
				if (!pwsStr.equals("") & pwsStr.equals("123456")) {
					// 计算余额
					Double balanceValue = Double.parseDouble(balance)
							- Double.parseDouble(acc_balance);
					if (balanceValue > 0) {// 检查余额
						Intent btnok_intent = new Intent();
						btnok_intent.putExtra("flag", "成功提示");
						btnok_intent.putExtra("info", "缴费成功，余额为:"
								+ balanceValue + ".00元");

						//成功缴费提示
						view= getLayoutInflater().inflate(
								R.xml.comdialog1, null);
						/**
						 * 设置对话框的样式
						 */
						dialog= new Dialog(InputPsw.this,
								R.style.dialog);
						/**
						 * 显示对话框
						 */
						dialog.show();
						// 设置具体对话框布局的宽和高
						LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
						// 将设置好的布局View加到对话框中
						dialog.addContentView(view, params);
						// 设置标题
						((TextView) view.findViewById(R.id.tv_comdlog_title))
								.setText("成功提示");
						// 设置显示的信息
						((TextView) view.findViewById(R.id.tv_comdlog_con1))
								.setText("缴费成功，余额为:" + balanceValue + ".00元");
						Button Ok_btn = (Button) view
								.findViewById(R.id.btn_comdlog_ok);
						Ok_btn.setText("返回");
						Ok_btn.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Intent intent = new Intent();
								intent.setClass(InputPsw.this,
										AllPaymentSer.class);
								InputPsw.this.startActivity(intent);
								dialog.dismiss();//返回是要消除对话框
							/**
							 * 缴费成功要消除当前Activity
							 */
								InputPsw.this.finish();
							}
						});
					}else{
						//金额不足提示
					}
					
				} else {
					// 密码错误提示对话框
					MyDialogOne  d1=new MyDialogOne(InputPsw.this,R.style.dialog);
					d1.setTitleAndInfo("错误提示", "密码错误!");
					d1.Listener(InputPsw.this,null);
//					d1.sett
//					d1.show();
				}
			}
		});
	}
}
