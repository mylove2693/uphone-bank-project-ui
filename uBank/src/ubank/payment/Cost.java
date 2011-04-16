package ubank.payment;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import ubank.base.GeneralActivity;
import ubank.base.MyDialogOne;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Cost extends GeneralActivity {

	private ArrayAdapter<String> adapter;
	private TextView txt;
	private Button next_btn;
	private String num;
	private EditText edit;
	private Spinner spinner;
	private String[] firstSpinnerValue;
	private String[] secondSpinnerValue;
	private JSONObject jsonObj;
	private String payId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 界面初始化和数据初始化
		inint();
		// 下一步按钮
		next_btn = (Button) findViewById(R.id.ok_btn).findViewById(R.id.button);
		next_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String editStr = edit.getText().toString().trim();
				if (editStr.length() == 0) {// 判断文本框中是否输入为空
					// 显示错误提示对话框
					MyDialogOne d1 = new MyDialogOne(Cost.this, R.style.dialog);
					d1.setTitleAndInfo("错误提示", "号码不能为空!");
					d1.Listener(Cost.this, null);
					d1.show();
				} else {
					Intent intent = new Intent();
					String[] name = { "项目名称:", " ", "缴费金额", "收费方:", "缴费合同号:" };
					String[] value = new String[5];
					if (num.equals("手机")) {
						name[1] = "目标手机号:";
						value[0] = "手机充值";
						value[1] = "110";
						value[2] = "10";
						value[3] = "中国移动";
						value[4] = "st110";
					} else if (num.equals("QQ")) {
						name[1] = "目标QQ号:";
						value[0] = "手机充值";
						value[1] = "553018332";
						value[2] = "20";
						value[3] = "腾讯QQ";
						value[4] = "st553018";

					} else if (num.equals("网易")) {
						name[1] = "目标网易号:";
						value[0] = "网易充值";
						value[1] = "120";
						value[2] = "20";
						value[3] = "网易";
						value[4] = "st3232";

					} else {
						name[1] = " ";
						value[0] = " ";
						value[1] = " ";
						value[2] = " ";
						value[3] = " ";
						value[4] = " ";
					}
					intent.putExtra("name", name);
					intent.putExtra("value", value);
					intent.setClass(Cost.this, WaitCost.class);
					Cost.this.startActivity(intent);
				}
			}
		});
	}
//初始化界面
	private void inint() {
		addLayout(R.layout.input_num);
		tvClassFirst.setVisibility(View.VISIBLE);
		// 监听
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("自助缴费>");
		// 监听
		setListener(tvClassSecond, this, AllPaymentSer.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("便捷服务");
		// 设置上面的字
		txt = (TextView) findViewById(R.id.txt_one).findViewById(
				R.id.Text_View_20);
		txt.setText("请选择运营商:");
		txt = (TextView) findViewById(R.id.txt_two).findViewById(
				R.id.Text_View_20);
		txt.setText("请选择充值面额:");
		txt = (TextView) findViewById(R.id.txt_three).findViewById(
				R.id.Text_View_20);
		// 初始化数据
		ininData();
		txt.setText("请输入目标" + num + "号");
		// 号码输入框
		edit = (EditText) findViewById(R.id.edit).findViewById(R.id.et_amt);
	}

	//初始化界面数据
	private void ininData() {
		/**
		 * 初始化数据 接收上一个Activity穿过来的 值
		 */
		Intent intent = getIntent();
		Bundle bund = intent.getExtras();
		num=bund.getString("num");
		payId=bund.getString("payId");
		spinner = (Spinner) findViewById(R.id.spinner_one).findViewById(R.id.Spinner);
		//从服务器上获得数据
		try {
			jsonObj = ConnectWs.connect(this, EAccType.NULL,
					EOperation.GET_OPERATOR, payId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> list=EHelper.toList(jsonObj);
		firstSpinnerValue = new String[list.size()];
		for(int i=0;i<list.size();i++){
			firstSpinnerValue[i]=list.get(i);
		}
		//firstSpinnerValue = new String[] { "中国移动", "中国联通", "中国电信" };
		secondSpinnerValue = new String[] { "30元", "50元", "100元" };
		spinner.setAdapter(getAdapter(firstSpinnerValue));
		spinner = (Spinner) findViewById(R.id.spinner_two).findViewById(R.id.Spinner);
		spinner.setAdapter(getAdapter(secondSpinnerValue));
	}
	//初始化adapter
	private ArrayAdapter<String> getAdapter(String[] str){
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		for (int i = 0; i < str.length; i++) {
			adapter.add(str[i]);
		}
		return adapter;
	}
}
