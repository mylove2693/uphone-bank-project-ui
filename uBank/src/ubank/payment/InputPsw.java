package ubank.payment;

import java.util.Map;

import org.json.JSONObject;

import ubank.base.GeneralActivity;
import ubank.base.MyDialogOne;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;
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

	private TextView txt;
	private Button ok_btn;
	private String account;
	private String acc_balance;
	private EditText pws;
	private String pwsStr;
	//从WaitCostItema类传来的信息如需付款金额
	private String payname;//要交费的名称
	private String paymoney;//要交费的金额
	private String payaddress;//收费方
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addLayout(R.layout.input_psw);
		inintData();// 初始化数据
		inint();// 初始化界面

		// 确认缴费按钮
		ok_btn = (Button) findViewById(R.id.ok_btn).findViewById(R.id.button);
		ok_btn.setText("确认缴费");
		// 确认缴费按钮的监听
		ok_btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				pwsStr = pws.getText().toString().trim();
				// 从服务上验证密码是否正确
				/**
				 * 先判断密码框是否为空 当不为空时在验证密码是否正确 中间用 & 连接 & 表示前面为true的情况下后面还要执行
				 * 知道前后都为true时菜返回true
				 */
				if (!pwsStr.equals("")) {
					// 计算余额
					Double balanceValue = Double.parseDouble(acc_balance)
							- Double.parseDouble(paymoney);
					if (balanceValue >=0) {// 检查余额
						
						//开始缴费
						/**
						 * 缴费格式cd:0210:水费:30:110:123456:运营商
						 * 参数     "水费","30","110","123456","无锡自来水公司"
						 */
						JSONObject jsonObj = ConnectWs.connect(InputPsw.this, EAccType.CURRENT_DEPOSIT,EOperation.PAYMENT, payname,paymoney,account,pwsStr,payaddress);
						
						//缴费成功提示
						MyDialogOne d1 = new MyDialogOne(InputPsw.this,
								R.style.dialog);
						d1.setTitleAndInfo("成功提示", "缴费成功,余额为:"+ balanceValue + ".00元");
						d1.Listener(InputPsw.this, AllPaymentSer.class);
						d1.show();
					} else {
						// 金额不足提示
						MyDialogOne d1 = new MyDialogOne(InputPsw.this,
								R.style.dialog);
						d1.setTitleAndInfo("失败提示", "余额不足,余额为:"+ acc_balance + ".00元");
						d1.Listener(InputPsw.this, null);
						d1.show();
					}

				} else {
					// 密码错误提示对话框
					MyDialogOne d1 = new MyDialogOne(InputPsw.this,
							R.style.dialog);
					d1.setTitleAndInfo("错误提示", "密码错误!");
					d1.Listener(InputPsw.this, null);
					d1.show();
				}
			}
		});
	}

	/**
	 * 初始化数据
	 * 
	 * @author gsm 2011-4-15
	 */
	private void inintData() {
		/**
		 * 从上一个Activity中取得 账号 和余额 需付款金额 初始化数据
		 */
		Intent intent = getIntent();
		account = intent.getStringExtra("account");//首选账号
		acc_balance = intent.getStringExtra("money");//余额
		Bundle bundle = intent.getExtras();
		// 从WaitCostItema类传来的信息如需付款金额
		payname=bundle.getString("payname");//要交费的名称
		paymoney =bundle.getString("paymoney");//要交费的金额
		payaddress=bundle.getString("payaddress");//收费方
	}

	/**
	 * 初始化界面
	 * 
	 * @author gsm 2011-4-15
	 */
	private void inint() {
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
	}
}
