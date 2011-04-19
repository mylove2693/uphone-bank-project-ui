package ubank.transfer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import ubank.base.GeneralActivity;
import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;

/**
 * 杨勇
 *  转账之前的详细信息
 *  显示 帐号 帐号类型 余额
 */
public class Transfer_information extends GeneralActivity {
	// 各项名字数组
	private String[] s = null;// 提示栏的数组
	private String[] data = null;// 具体对应的数据数组
	private String title = null;// 导航栏标题
	private String acc_num = null;// 帐号
	private String acc_type = null;// 帐号类型
	private String acc_balance = null;// 余额

	Button Re_election, Continue_transfer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();// 加载不需要从后台填充数据的控件
		initData();// 加载从后台得来的数据
		BtnClick on_click=new BtnClick();
		
		//为重选账户和继续转账按钮加监听
		Re_election.setOnClickListener(on_click);
		Continue_transfer.setOnClickListener(on_click);
	}

	/**
	 * 加载界面上的信息和监听
	 */
	private void init() {
		Intent up_intent = getIntent();
		/**
		 * 获得传过来的导航栏标题 帐号 帐号类型 账户余额
		 */
		title = up_intent.getStringExtra("title");
		acc_num = up_intent.getStringExtra("acc_num");
		acc_type = up_intent.getStringExtra("acc_type");
		// System.out.println(acc_num + acc_type + "12345678");
		/**
		 *设置导航栏和监听的添加
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
		/**
		 * 添加布局文件
		 */
		addLayout(R.layout.transfer_information);
		
		
		
		/**
		 * 设置按钮的文本和监听
		 */
		Re_election = (Button) findViewById(R.id.Re_election_acc).findViewById(
				R.id.button);
		Re_election.setTextSize(18);
		Re_election.setText("重选账户");

		Continue_transfer = (Button) findViewById(R.id.Continue_transfer)
				.findViewById(R.id.button);
		Continue_transfer.setTextSize(18);
		Continue_transfer.setText("继续转账");

	}

	/**
	 * 从后台得到的数据 加到相应的控件上
	 */
	private void initData() {

		/**
		 * 根据帐号来查询其详细信息 主要有 帐号 类型 余额
		 */

		JSONObject jsonObj = null;
		try {
			jsonObj = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,
					EOperation.GET_ACC_INFO, acc_num);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
			finish();
			e.printStackTrace();
		}
		// 通过后台来获取余额
		Map<String, String> name = EHelper.toMap(jsonObj);
		acc_balance = name.get("余额");

		// 提示数组
		// s = new String[] { "  账户：", "  类型：", "  余额：" };
		// 详细信息数组
		data = new String[] { acc_num, acc_type, acc_balance };

		// 获取到三个文本框 来方便填充数据
		TextView txt_acc = (TextView) findViewById(R.id.acc_txt1);
		TextView txt_accType = (TextView) findViewById(R.id.acc_txt2);
		TextView txt_accBalance = (TextView) findViewById(R.id.acc_txt3);
		// 为具体的问题添加数据
		txt_acc.setText(data[0]);
		txt_accType.setText(data[1]);
		txt_accBalance.setText(data[2]);

	}

	
	
	/**
	 * 按钮的监听类
	 */
	
	
	class BtnClick implements OnClickListener {

		@Override
		public void onClick(View v) {

			// TODO Auto-generated method stub
			Intent next_intent = new Intent();
			// 重选账户加监听
			if (v.equals(Re_election)) {
				next_intent.putExtra("title", Transfer_information.this.title);
				next_intent.setClass(Transfer_information.this,
						TransferAccSelect.class);

			}
			// 为继续转账加监听
			if (v.equals(Continue_transfer)) {
				next_intent.putExtra("title", Transfer_information.this.title);// 放入标题
				next_intent.putExtra("acc_num", acc_num);// 放入帐号
				next_intent.putExtra("acc_balance", acc_balance);// 放入余额 便于比较
				next_intent.setClass(Transfer_information.this,
						TransferPhToSignedAcc.class);
			}
			Transfer_information.this.startActivity(next_intent);
		}

	}
}
