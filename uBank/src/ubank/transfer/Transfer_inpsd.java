package ubank.transfer;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
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
 * 转账的密码输入界面
 * 
 * @author Administrator
 * 
 */
public class Transfer_inpsd extends GeneralActivity {
	// 导航栏三级标题
	String title = null;

	String acc_type = null;
	String acc_num = null;
	EditText inPsd;//密码框
	TextView transfer_accnum;//帐号

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// JSONObject jsonObj = ConnectWs.connect(this, EAccType.NULL,
		// EOperation.GET_PAYMENT_NAME, "1");
		//
		// System.out.println("后台数据"+jsonObj.toString());

		Intent up_intent = getIntent();
		// 获得传过来的导航栏标题
		title = up_intent.getStringExtra("title");
		// 帐号类型
		acc_type = up_intent.getStringExtra("acc_type");
		// 帐号
		acc_num = up_intent.getStringExtra("acc_num");

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);

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
		transfer_acctype.setText(acc_type);

		// 这个是帐号 是小字
		transfer_accnum = (TextView) findViewById(R.id.acc_num)
				.findViewById(R.id.Text_View_16);
		transfer_accnum.setText(acc_num);

		// 提示输入密码的文本框
		TextView transfer_psd_txtview = (TextView) findViewById(
				R.id.acc_inpsd_mation).findViewById(R.id.Text_View_18);
		transfer_psd_txtview.setText("请输入账户密码:");
		//密码输入框  便于取数据
		inPsd=(EditText)findViewById(R.id.transfer_inpsd_edit).findViewById(R.id.et_psd);
		
		
		

		Button transfer_next = (Button) findViewById(R.id.transfer_inpsd_next)
				.findViewById(R.id.button);
		transfer_next.setText("下一步");

		Dialog myD = new Dialog(this, R.style.dialog);
		transfer_next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//System.out.println(acc_num);
//System.out.println(inPsd.getText().toString());
				if (psdIsRight(acc_num, inPsd.getText().toString())) {

					// 传递数据到后台 在后台返回结果后 再执行相应的操作
					// 如果密码错误 就弹出提示的对话框		
					Intent payment_intent = new Intent();
					/**
					 * 将服务器上取得的值传给下一个Activity
					 */
					payment_intent.putExtra("title", Transfer_inpsd.this.title);
					payment_intent.putExtra("acc_num", acc_num);//放入帐号  便于下一界面查询数据
					payment_intent.putExtra("acc_type", acc_type);
					payment_intent.setClass(Transfer_inpsd.this,Transfer_information.class);
					//创建一个新的dialog出来  用来提示信息
					MyDialogOne dialog = new MyDialogOne(Transfer_inpsd.this,R.style.dialog);
					dialog.setTitleAndInfo("成功提示", "成功");
					dialog.Listener(payment_intent, Transfer_inpsd.this);
					dialog.show();
				}
				else{
					
					Intent payment_intent = new Intent();
					/**
					 * 将服务器上取得的值传给下一个Activity
					 */
					payment_intent.putExtra("title", Transfer_inpsd.this.title);
					MyDialogOne dialog = new MyDialogOne(Transfer_inpsd.this,
							R.style.dialog);
					dialog.setTitleAndInfo("失败提示", "密码错误。。。");
					dialog.Listener(Transfer_inpsd.this,null);
					dialog.show();	
				}
			}
		});

	}
/**
 * 验证密码是否是正确的  返回bool变量
 * @param NUM 帐号
 * @param PSD  密码
 * @return
 */
	private boolean psdIsRight(String NUM, String PSD) {

		JSONObject jsonObj=null;
		try {
			jsonObj = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,
					EOperation.GET_VERIFY_PASSWORD, NUM, PSD);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// List<String> name = EHelper.toList(jsonObj);
		boolean result = Boolean.valueOf(EHelper.toList(jsonObj).get(0));

		return result;
	}
}
