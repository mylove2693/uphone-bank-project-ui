package ubank.credit;

import java.io.IOException;

import org.json.JSONObject;

import ubank.base.GeneralActivity;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class OpenCard extends GeneralActivity {
	private Button btnNext;
	private EditText etUserName;
	private EditText etCcNo;
	private EditText etNoVaild;
	private EditText etId;
	private EditText etPhone;
	private EditText etTel;
	private EditText etPwd;
	private Spinner spnrIdType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayout(R.layout.cc_open_card);
		initializeData();// 初始化数据
		btnNext.setOnClickListener(onClick);
	}

	private void initializeData() {
		// TODO 初始化数据

		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("信用卡>");
		setListener(tvClassSecond, this, CreditCardMain.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("开卡");

		((TextView) (findViewById(R.id.cc_tv_openName).findViewById(R.id.blue_Text_View))).setText("*开户名");
		((TextView) (findViewById(R.id.cc_tv_ccNo).findViewById(R.id.blue_Text_View))).setText("*信用卡号");
		((TextView) (findViewById(R.id.cc_tv_noValid).findViewById(R.id.blue_Text_View))).setText("*有效期");
		((TextView) (findViewById(R.id.cc_tv_idType).findViewById(R.id.blue_Text_View))).setText("*证件类型");
		((TextView) (findViewById(R.id.cc_tv_id).findViewById(R.id.blue_Text_View))).setText("*证件号");
		((TextView) (findViewById(R.id.cc_tv_phone).findViewById(R.id.blue_Text_View))).setText("*手机号");
		((TextView) (findViewById(R.id.cc_tv_tel).findViewById(R.id.blue_Text_View))).setText("*固定电话");
		((TextView) (findViewById(R.id.cc_tv_pwd).findViewById(R.id.blue_Text_View))).setText("*账户密码");

		etUserName = (EditText) findViewById(R.id.cc_et_openName).findViewById(R.id.et_user);
		etCcNo = (EditText) findViewById(R.id.cc_et_ccNo).findViewById(R.id.et_user);
		etNoVaild = (EditText) findViewById(R.id.cc_et_noValid).findViewById(R.id.et_user);
		spnrIdType = (Spinner) findViewById(R.id.cc_spnr_idType).findViewById(R.id.Small_Spinner);

		ArrayAdapter<String> sa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				new String[] { "身份证" });
		sa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnrIdType.setAdapter(sa);

		etId = (EditText) findViewById(R.id.cc_et_id).findViewById(R.id.et_user);
		etPhone = (EditText) findViewById(R.id.cc_et_phone).findViewById(R.id.et_user);
		etTel = (EditText) findViewById(R.id.cc_et_tel).findViewById(R.id.et_user);
		etPwd = (EditText) findViewById(R.id.cc_et_pwd).findViewById(R.id.et_psd);

		btnNext = (Button) (findViewById(R.id.cc_btn_next).findViewById(R.id.button));
		btnNext.setText("确认开卡");
	}

	// 按钮监听
	private OnClickListener onClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			boolean flag = false;
			// 获取所需数据
			String userName = etUserName.getText().toString().trim();
			String creditCardNo = etCcNo.getText().toString().trim();
			String availbDate = etNoVaild.getText().toString().trim();
			String idNo = etId.getText().toString().trim();
			String cellPhone = etPhone.getText().toString().trim();
			String tel = etTel.getText().toString().trim();
			String pwd = etPwd.getText().toString().trim();
			if("".equals(creditCardNo) || "".equals(pwd)){
				Toast.makeText(OpenCard.this, "请输入账号和密码！", Toast.LENGTH_SHORT).show();
				return ;
			}
			// 检查网络连接
			if (EHelper.hasInternet(OpenCard.this)) {
				// 连线等待中...
				JSONObject jsonObj = new JSONObject();
				try {
					jsonObj = ConnectWs.connect(OpenCard.this, EAccType.CREDIT_CARD, EOperation.OPEN_CARD,
							userName, creditCardNo, availbDate, idNo, cellPhone, tel, pwd);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Toast.makeText(OpenCard.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
					return;
				}
				// 是否成功?
				flag = EHelper.toBoolean(jsonObj);
			} else {
				Toast.makeText(OpenCard.this, "没有网络", Toast.LENGTH_SHORT).show();
				return;
			}

			// 弹出对话框
			// 设置对话框的布局
			View view = getLayoutInflater().inflate(R.xml.comdialog1, null);
			final Dialog dialog = new Dialog(OpenCard.this, R.style.dialog);
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
//					Intent intent = new Intent(OpenCard.this, CreditCardMain.class);
//					startActivity(intent);
				}
			});
			if (flag) {
				((TextView) view.findViewById(R.id.tv_comdlog_title)).setText("成功提示");
				((TextView) view.findViewById(R.id.tv_comdlog_con1)).setText("开卡成功，此卡在绑定之前，还不能在手机银行里操作");

			} else {
				((TextView) view.findViewById(R.id.tv_comdlog_title)).setText("失败提示");
				((TextView) view.findViewById(R.id.tv_comdlog_con1)).setText("开卡失败，请验证输入的信息是否正确");
			}
		}
	};
}
