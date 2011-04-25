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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Cost extends GeneralActivity {

	private ArrayAdapter<String> adapter;
	private TextView txt;
	private Button next_btn;
	private String num;
	private EditText edit;
	private Spinner spinner1;
	private Spinner spinner2;
	private String[] firstSpinnerValue;
	private String[] secondSpinnerValue;
	private JSONObject jsonObj;
	private String payId;
	private String operator;// 运营商
	private String denomination;// 面额
	private String editNum;// 输入编辑框的号码
	private String payname;// 要交费的名称
	private String paymoney;// 要交费的金额
	private String payaddress;// 收费方

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
					myDialog("错误提示", "号码不能为空!");
				} else if (num.equals("手机") && !isPhone(editStr)) {
					myDialog("错误提示", "手机号码输入错误!");
				} else if (num.equals("QQ") && !isQQ(editStr)) {
					myDialog("错误提示", "QQ号码输入错误!");
				} else if (num.equals("网易") && !isWY(editStr)) {
					myDialog("错误提示", "网易号码输入错误!");
				} else {
					Intent intent = new Intent();
					String[] name = { "项目名称:", " ", "缴费金额:", "收费方:", "缴费合同号:" };
					String[] value = new String[5];
					if (num.equals("手机")) {
						editNum = edit.getText().toString().trim();
						name[1] = "目标手机号:";
						value[0] = "手机充值";
						value[1] = editNum;
						value[2] = denomination;
						value[3] = operator;
						value[4] = "st110";

						payname = num;// 要交费的名称
						paymoney = denomination;// 要交费的金额
						payaddress = operator;// 收费方
					} else if (num.equals("QQ")) {
						editNum = edit.getText().toString().trim();
						name[1] = "目标QQ号:";
						value[0] = "Q币充值";
						value[1] = editNum;
						value[2] = denomination;
						value[3] = operator;
						value[4] = "st553018";

						payname = num;// 要交费的名称
						paymoney = denomination;// 要交费的金额
						payaddress = operator;// 收费方

					} else if (num.equals("网易")) {
						editNum = edit.getText().toString().trim();
						name[1] = "目标网易号:";
						value[0] = "网易充值";
						value[1] = editNum;
						value[2] = denomination;
						value[3] = operator;
						value[4] = "st3232";

						payname = num;// 要交费的名称
						paymoney = denomination;// 要交费的金额
						payaddress = operator;// 收费方

					} else {
						name[1] = " ";
						value[0] = " ";
						value[1] = " ";
						value[2] = " ";
						value[3] = " ";
						value[4] = " ";
					}
					// 将数据传到密码输入框
					Bundle bubdle = new Bundle();
					bubdle.putString("payname", payname);// 要交费的名称
					bubdle.putString("paymoney", paymoney);// 要交费的金额
					bubdle.putString("payaddress", payaddress);// 收费方
					intent.putExtras(bubdle);
					intent.putExtra("name", name);
					intent.putExtra("value", value);
					intent.setClass(Cost.this, WaitCost.class);
					Cost.this.startActivity(intent);
				}
			}
		});
	}

	// 判断手机号码
	private boolean isPhone(String strPhone) {
		return strPhone.matches("^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$");//国内手机号码
	}

	// 判断QQ号码
	private boolean isQQ(String strQQ) {
		return strQQ.matches("[1-9][0-9]{4,}");//腾讯QQ号从10000开始
	}

	// 判断网易号码
	private boolean isWY(String strWY) {
		return strWY.matches("^[A-Za-z0-9]+$");//匹配由数字和26个英文字母组成的字符串
	}

	// 错误提示框
	private void myDialog(String strTitle, String strInfo) {
		MyDialogOne d1 = new MyDialogOne(Cost.this, R.style.dialog);
		d1.setTitleAndInfo(strTitle, strInfo);
		d1.Listener(Cost.this, null);
		d1.show();
	}

	// 初始化界面
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
		// editNum = edit.getText().toString().trim();
	}

	// 初始化界面数据
	private void ininData() {
		/**
		 * 初始化数据 接收上一个Activity穿过来的 值
		 */
		Intent intent = getIntent();
		Bundle bund = intent.getExtras();
		num = bund.getString("num");
		payId = bund.getString("payId");
		spinner1 = (Spinner) findViewById(R.id.spinner_one).findViewById(
				R.id.Spinner);
		// 从服务器上获得数据
		try {
			jsonObj = ConnectWs.connect(this, EAccType.NULL,
					EOperation.GET_OPERATOR, payId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> list = EHelper.toList(jsonObj);
		firstSpinnerValue = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			firstSpinnerValue[i] = list.get(i);
		}
		// firstSpinnerValue = new String[] { "中国移动", "中国联通", "中国电信" };
		secondSpinnerValue = new String[] { "30", "50", "100" };
		spinner1.setAdapter(getAdapter(firstSpinnerValue));
		// 对spring1进行监听
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner = (Spinner) parent;
				// System.out.println("默认第一项="+spinner.getSelectedItem().toString());
				operator = spinner.getSelectedItem().toString().trim();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		spinner2 = (Spinner) findViewById(R.id.spinner_two).findViewById(
				R.id.Spinner);
		spinner2.setAdapter(getAdapter(secondSpinnerValue));
		// 对spring2进行监听
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner = (Spinner) parent;
				// System.out.println("默认第一项="+spinner.getSelectedItem().toString());
				denomination = spinner.getSelectedItem().toString().trim();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	// 初始化adapter
	private ArrayAdapter<String> getAdapter(String[] str) {
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
