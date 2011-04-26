package ubank.transfer;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import ubank.base.GeneralActivity;
import ubank.base.GeneralListActivity;
import ubank.base.MyDialogOne;
import ubank.base.MyDialogTwo;
import ubank.common.Account_Select;
import ubank.credit.SelectRepaymentAcc;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.payment.AllPaymentSer;
import ubank.payment.ElseAcc;
import ubank.payment.InputPsw;
import ubank.payment.SelectAcc;
import ubank.webservice.ConnectWs;

/**
 * 杨勇 转账 首选和其他账户 的选择
 * 
 * @author Administrator
 * 
 */
public class TransferAccSelect extends GeneralListActivity {
	private String[] value = { "首选账户", "其他账户" };
	// 提示文本框
	private TextView txt = null;
	// 导航栏标题
	private String title = null;
	// 账户类型和账户号
	private String acc_type, acc_num;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();// 加载无需访问后台的数据
	}

	/**
	 * 加载无需访问后台的数据
	 */
	private void init() {
		Intent up_intent = getIntent();
		// 获得传过来的标题
		title = up_intent.getStringExtra("title");

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
		addLayout(R.layout.above_list_txt);
		// 获取提示信息文本框 并设定值
		txt = (TextView) findViewById(R.id.above_list_txt).findViewById(
				R.id.Text_View_16_Gray);
		txt.setText("请选择账户：");

		// 向页面上加入选项
		this.setListAdapter(createText_Img(value));

	}

	protected void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);
		final MyDialogTwo dialogTwo ;
		if (id == 0) {
			// 首选账户
			/**
			 * 向服务器发请求获取首选账户的帐号类型和其帐号 有专门的首选账户表
			 */
			JSONObject jsonObj = null;
			try {
				jsonObj = ConnectWs.connect(this, EAccType.NULL,
						EOperation.GET_PRE_ACC, "1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			}

			String str = EHelper.toStr(jsonObj);
			if (str == null) {
				acc_type = "活期存储卡：";
				acc_num = "100000";
			} else {
				acc_type = str.split("#")[3];
				acc_num = str.split("#")[1];
			}
			/**
			 * 将服务器上取得的值传给下一个Activity
			 */

			if(!isTrue(acc_num)){
				// 如果没有激活
				dialogTwo = new MyDialogTwo(TransferAccSelect.this, R.style.dialog).setTitleAndInfo("提示信息",
						"此账户第一次使用，是否激活").setPwdVisibility();
				dialogTwo.setOkToService(new OnClickListener() {

					@Override
					public void onClick(View v) {
						String pwd = dialogTwo.getPwd();
						try {//激活这个账户
							JSONObject jsonObj = ConnectWs.connect(TransferAccSelect.this,
									EAccType.CURRENT_DEPOSIT, EOperation.SET_ACC_ACTIVE, acc_num, pwd);
							boolean flag = jsonObj.getBoolean("result");
							dialogTwo.dismiss();
							if (flag) {
								// 如果激活成功
								Toast.makeText(TransferAccSelect.this, "激活成功", Toast.LENGTH_SHORT).show();
							} else {
								Toast.makeText(TransferAccSelect.this, "激活不成功，请检查密码", Toast.LENGTH_SHORT).show();
								return;
							}
						} catch (IOException e) {
							Toast.makeText(TransferAccSelect.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
							e.printStackTrace();
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				});
					Intent elseAcc_intent = new Intent();
			elseAcc_intent.putExtra("acc_type", acc_type);
			elseAcc_intent.putExtra("acc_num", acc_num);
			elseAcc_intent.putExtra("title", TransferAccSelect.this.title);
			elseAcc_intent.setClass(TransferAccSelect.this,
					Transfer_inpsd.class);
			dialogTwo.Listener(elseAcc_intent, TransferAccSelect.this);
			dialogTwo.show();
			}else
			{
				Intent elseAcc_intent = new Intent();
				elseAcc_intent.putExtra("acc_type", acc_type);
				elseAcc_intent.putExtra("acc_num", acc_num);
				elseAcc_intent.putExtra("title", TransferAccSelect.this.title);
				elseAcc_intent.setClass(TransferAccSelect.this,
						Transfer_inpsd.class);
				TransferAccSelect.this.startActivity(elseAcc_intent);
			}
		} else if (id == 1) {
			// 其他账户
			Intent elseAcc_intent = new Intent();
			elseAcc_intent.putExtra("title", this.title);
			elseAcc_intent.setClass(TransferAccSelect.this,
					Transfer_OtherAccSelect.class);
			TransferAccSelect.this.startActivity(elseAcc_intent);
		}
	}
	
	
	
	
	/**
	 * 账户激活的判断
	 */
	private boolean isTrue(String actnum) {
		JSONObject js = null;
		boolean isActive;
		try {
			js = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,
					EOperation.ACC_IS_ACTIVE, actnum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
			finish();
			e.printStackTrace();
		}
//		System.out.println("js"+js.toString());
		isActive=EHelper.toBoolean(js);
//		System.out.println("isActive"+isActive);
		return isActive;
	}
}
