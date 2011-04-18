package ubank.transfer;

import java.io.IOException;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ubank.base.GeneralActivity;
import ubank.base.MyDialogOne;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;

/**
 * 杨勇 手机到手机的转账 或者是到签约账户
 * 
 * @author Administrator
 * 
 */
public class TransferPhToSignedAcc extends GeneralActivity {
	// 导航栏三级标题
	String title = null;// 标题
	String acc_num = null;// 帐号
	String to_acc_num = null;// 转入的帐号
	String to_amt = null;// 金额
	String psd = null;
	Double acc_balance;
	EditText amt_tv;// 金额框
	EditText num_tv;// 目标号框
	EditText psd_tv;// 密码框

	/**
	 *这个里面要注意的就是有三个输入框 要取的验证才行
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent up_intent = getIntent();
		/**
		 * 获得传过来的导航栏标题 帐号 便于比对密码
		 */
		// 标题
		title = up_intent.getStringExtra("title");
		acc_num = up_intent.getStringExtra("acc_num");
		acc_balance = Double.valueOf(up_intent.getStringExtra("acc_balance"));
		/**
		 * 设置相应的导航栏和监听
		 */
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("转账汇款>");
		// 转账汇款的监听
		setListener(tvClassSecond, this, TransferMain.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText(title);

		// 添加布局文件
		addLayout(R.layout.transfer_phtosigacc);
		/**
		 * 获取各个提示文本框的id 并设置内容
		 */
		TextView transfer_num_txtview = (TextView) findViewById(
				R.id.transfer_amt_txt).findViewById(R.id.Text_View_18);
		transfer_num_txtview.setText("请输入转账金额:");
		TextView transfer_acc_txtview = (TextView) findViewById(
				R.id.transfer_acc_txt).findViewById(R.id.Text_View_18);
		// 根据标题来判断是提示输入什么号
		if (title.indexOf("手机到手机") != -1) {
			transfer_acc_txtview.setText("请输入目标手机号:");
		}
		if (title.indexOf("签约账户") != -1) {
			transfer_acc_txtview.setText("请输入转入账户:");
		}

		TextView transfer_psd_txtview = (TextView) findViewById(
				R.id.transfer_psd_txt).findViewById(R.id.Text_View_18);
		transfer_psd_txtview.setText("请输入账户密码:");

		/**
		 * 获取几个输入框的ID 便于从中获取数据
		 */
		// 金额框
		amt_tv = (EditText) findViewById(R.id.transfer_amt_edit).findViewById(
				R.id.et_amt);
		// 目标号框
		num_tv = (EditText) findViewById(R.id.transfer_acc_edit).findViewById(
				R.id.et_acc);

		// 密码框
		psd_tv = (EditText) findViewById(R.id.transfer_psd_edit).findViewById(
				R.id.et_psd);

		// 获取下一步的按钮 设置文本值
		Button next_btn = (Button) findViewById(R.id.next_btn).findViewById(
				R.id.button);
		next_btn.setText("下一步");

		next_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// // TODO Auto-generated method stub
				// 密码框中的密码
				psd = psd_tv.getText().toString();
				// 目标号框中的号
				to_acc_num = num_tv.getText().toString();
				// 金额框中的金额
				to_amt = amt_tv.getText().toString();
				/**
				 * 信息输出的检测
				 */
				String msg = isNumPsdAmt(acc_num, psd, to_acc_num, to_amt);
//System.out.println(msg+"1111111111111111111111111111111");
				MyDialogOne dialog = new MyDialogOne(
						TransferPhToSignedAcc.this, R.style.dialog);
				if (msg.equals("转账成功")) {

					dialog.setTitleAndInfo("成功提示", msg);
					dialog.Listener(TransferPhToSignedAcc.this,
							TransferMain.class);

				}
				if (msg.equals("账号不存在")) {

					dialog.setTitleAndInfo("失败提示", msg);
					dialog.Listener(TransferPhToSignedAcc.this,
							null);

				}
				if (acc_balance - (Double.valueOf(to_amt)) < 0) {
					dialog.setTitleAndInfo("失败提示", "余额不足");
					dialog.Listener(TransferPhToSignedAcc.this, null);

				}
				if (msg.equals("密码错误"))

				{
					dialog.setTitleAndInfo("失败提示", msg);
					dialog.Listener(TransferPhToSignedAcc.this, null);
				}

				dialog.show();
			}
		});

	}

	/**
	 * 判断三个文本框的值 是否合法和正确 输入账户是否存在 手机是否开通 密码是否正确 转账金额是否大于余额
	 * 
	 */

	private String isNumPsdAmt(String NUM, String PSD, String amtph,
			String amtnum) {
		JSONObject jsonObj = null;
		try {
			if (title.indexOf("手机到手机") != -1) {//手机到手机的
				System.out.println("手机到手机");
				jsonObj = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,
						EOperation.TRANSFE_ACC, NUM, PSD, amtph, amtnum);
			}
			if (title.indexOf("签约账户") != -1) {//手机到签约账户的
				System.out.println("签约账户");
				jsonObj = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,
						EOperation.TRANSFE_ACC_ACC, NUM, PSD, amtph, amtnum);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> tt = EHelper.toMap(jsonObj);
		String result = tt.get("result");
//		System.out.println(result+"_-----------------------------------------------");
		return result;

	}

}
