package ubank.credit;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;

public class ConfrimRepayment extends GeneralListActivity {

	private Button btnOk;
	private Button btnCancel;
	private EditText etMoney;
	private EditText etPwd;
	private String balance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayoutBlow(R.layout.cc_repayment);
		initializeData();// 初始化

		btnOk.setOnClickListener(btnClick);
		btnCancel.setOnClickListener(btnClick);

	}

	private OnClickListener btnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			String pwd;
			String money;
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case 1:// 确认
				boolean flag = false;
				// 获取密码
				pwd = etPwd.getText().toString().trim();
				// 转账金额
				money = etMoney.getText().toString().trim();
				if (EHelper.hasInternet(ConfrimRepayment.this)) {
					// 如果没有网络
					try {
						JSONObject jsonObj = ConnectWs.connect(ConfrimRepayment.this, EAccType.CREDIT_CARD,
								EOperation.CREDIT_REPAYMENT, fromAcc, pwd, toAcc, money);
						flag = jsonObj.getBoolean("result");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Toast.makeText(ConfrimRepayment.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
						e.printStackTrace();
						return;
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					// 如果没有网络直接返回
					return;
				}

				// 弹出对话框
				// 设置对话框的布局
				View view = getLayoutInflater().inflate(R.xml.comdialog1, null);
				final Dialog dialog = new Dialog(ConfrimRepayment.this, R.style.dialog);
				dialog.show();
				// 设置具体对话框布局的宽和高
				LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
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
					// 如果还款成功
					((TextView) view.findViewById(R.id.tv_comdlog_title)).setText("成功提示");
					((TextView) view.findViewById(R.id.tv_comdlog_con1)).setText("还款成功");

				} else {
					((TextView) view.findViewById(R.id.tv_comdlog_title)).setText("失败提示");
					((TextView) view.findViewById(R.id.tv_comdlog_con1)).setText("还款失败，请验证输入的信息是否正确");
				}
				break;
			case 0:// 取消
				Intent intent = new Intent(ConfrimRepayment.this, CreditCardMain.class);
				startActivity(intent);
				break;
			default:
				break;
			}
		}
	};
	private Intent intent;
	private String fromAcc;
	private String toAcc;
	private String accType;
	private Bundle bundle;

	private void initializeData() {
		// TODO 初始化
		// 接收数据
		intent = getIntent();
		bundle = intent.getExtras();
		fromAcc = bundle.getString("fromAcc");
		toAcc = bundle.getString("toAcc");
		accType = bundle.getString("accType");

		// 检查网络连接
		if (EHelper.hasInternet(this)) {
			try {
				JSONObject jsonObject = ConnectWs.connect(ConfrimRepayment.this, EAccType.getEAccTypeByName(accType),
						EOperation.GET_ACC_INFO, fromAcc);
				balance = jsonObject.getString("余额");
			} catch (IOException e) {
				Toast.makeText(this, "服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();
			finish();
		}

		String[] value = new String[2];
		value[0] = bundle.getString("fromAcc");
		value[1] = balance;
		SimpleAdapter adapter = createText_Text(new String[] { "还款账户", "账户余额" }, value);
		setListAdapter(adapter);

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("信用卡>");
		setListener(tvClassSecond, this, CreditCardMain.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("信用卡还款");

		TextView tvMoney = (TextView) findViewById(R.id.cc_tv_amt).findViewById(R.id.Text_View_18);
		tvMoney.setText("请输入还款金额");
		TextView tvPwd = (TextView) findViewById(R.id.cc_tv_psd).findViewById(R.id.Text_View_18);
		tvPwd.setText("请输入账户密码");

		btnOk = (Button) findViewById(R.id.btn1).findViewById(R.id.button);
		btnOk.setText("确认还款");
		btnOk.setId(1);
		btnCancel = (Button) findViewById(R.id.btn2).findViewById(R.id.button);
		btnCancel.setText("取消");
		btnCancel.setId(0);
		etMoney = (EditText) (findViewById(R.id.ccc_et_amt).findViewById(R.id.et_amt));
		etPwd = (EditText) (findViewById(R.id.ccc_et_pwd).findViewById(R.id.et_psd));

	}
}
