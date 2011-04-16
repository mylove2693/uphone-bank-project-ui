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
import ubank.base.GeneralActivity;
import ubank.base.GeneralListActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;

/**
 * 转账之前的详细信息显示
 */
public class Transfer_information extends GeneralActivity {
	// 各项名字数组
	private String[] s = null;// 提示栏的数组
	// 对应的数据数组
	private String[] data = null;// 具体对应的数据数组
	private String title = null;// 导航栏标题
	private String acc_num = null;// 帐号
	private String acc_type = null;// 帐号类型
	private String acc_balance = null;// 余额

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent up_intent = getIntent();
		/**
		 * 获得传过来的导航栏标题 帐号 帐号类型 账户余额
		 */
		title = up_intent.getStringExtra("title");
		acc_num = up_intent.getStringExtra("acc_num");
		acc_type = up_intent.getStringExtra("acc_type");
		System.out.println(acc_num + acc_type + "12345678");
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
		
		TextView txt_acc=(TextView)findViewById(R.id.acc_txt1);
		TextView txt_accType=(TextView)findViewById(R.id.acc_txt2);
		TextView txt_accBalance=(TextView)findViewById(R.id.acc_txt3);
		
		
		
		

		/**
		 * 根据帐号来查询其详细信息 主要有 帐号 类型 余额
		 */

		JSONObject jsonObj=null;
		try {
			jsonObj = ConnectWs.connect(this, EAccType.CURRENT_DEPOSIT,
					EOperation.GET_ACC_INFO, acc_num);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> name = EHelper.toMap(jsonObj);
		acc_balance = name.get("余额");
		// name.get(0);
		// System.out.println( name.get("余额"));

		// 提示数组
		// s = new String[] { "  账户：", "  类型：", "  余额：" };
		// 详细信息数组
		data = new String[] { acc_num, acc_type, acc_balance };
		
		
		
		
		
		// 为具体的问题添加数据
		// this.setListAdapter(createText_Text(s, data));
		txt_acc.setText(data[0]);
		txt_accType.setText(data[1]);
		txt_accBalance.setText(data[2]);
		// 添加布局

		/**
		 * 设置按钮的文本和监听
		 */
		Button Re_election = (Button) findViewById(R.id.Re_election_acc)
				.findViewById(R.id.button);
		Re_election.setTextSize(18);
		Re_election.setText("重选账户");

		Button Continue_transfer = (Button) findViewById(R.id.Continue_transfer)
				.findViewById(R.id.button);
		Continue_transfer.setTextSize(18);
		Continue_transfer.setText("继续转账");

		// 为重选账户加监听
		Re_election.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent payment_intent = new Intent();
				/**
				 * 将服务器上取得的值传给下一个Activity
				 */
				payment_intent.putExtra("title",
						Transfer_information.this.title);
				payment_intent.setClass(Transfer_information.this,
						TransferAccSelect.class);
				Transfer_information.this.startActivity(payment_intent);

			}
		});
		// 为继续转账加监听
		Continue_transfer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent payment_intent = new Intent();
				/**
				 * 将服务器上取得的值传给下一个Activity
				 */
				payment_intent.putExtra("title",
						Transfer_information.this.title);// 放入标题
				payment_intent.putExtra("acc_num", acc_num);// 放入帐号
				payment_intent.putExtra("acc_balance", acc_balance);// 放入余额 便于比较
				payment_intent.setClass(Transfer_information.this,
						TransferPhToSignedAcc.class);
				Transfer_information.this.startActivity(payment_intent);

			}
		});

	}

}
