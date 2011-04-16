package ubank.credit;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import ubank.base.GeneralActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.R;
import ubank.webservice.ConnectWs;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class DestroyCard extends GeneralActivity {

	private Button btnNext;
	private EditText etUserName;
	private Spinner spnrIdType;
	private EditText etId;
	private EditText etPhone;
	private EditText etPwd;
	private Spinner spnrCcNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayout(R.layout.cc_destory_card);
		initializeData();// 初始化数据

		btnNext.setOnClickListener(onClick);
	}

	private void initializeData() {
		((TextView) (findViewById(R.id.cc_tv_openName)
				.findViewById(R.id.blue_Text_View))).setText("开户名");
		((TextView) (findViewById(R.id.cc_tv_ccNo)
				.findViewById(R.id.blue_Text_View))).setText("信用卡号");
		((TextView) (findViewById(R.id.cc_tv_idType)
				.findViewById(R.id.blue_Text_View))).setText("证件类型");
		((TextView) (findViewById(R.id.cc_tv_id)
				.findViewById(R.id.blue_Text_View))).setText("证件号");
		((TextView) (findViewById(R.id.cc_tv_phone)
				.findViewById(R.id.blue_Text_View))).setText("手机号");
		((TextView) (findViewById(R.id.cc_tv_pwd)
				.findViewById(R.id.blue_Text_View))).setText("账户密码");
		// 检查网络
		if (EHelper.hasInternet(this)) {

			// 连接服务器...
			JSONObject jsonObj = new JSONObject();
			try {
				jsonObj = ConnectWs.connect(DestroyCard.this, EAccType.NULL,
						EOperation.GET_BIND_CREDIT_CARD, "");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			}
			List<String> lstCc = EHelper.toList(jsonObj);
			spnrCcNo = (Spinner) findViewById(R.id.cc_spnr_ccNo).findViewById(
					R.id.Small_Spinner);

			ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, lstCc);
			adapterType
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// 将adapter 添加到spinner中
			spnrCcNo.setAdapter(adapterType);
		} else {
			Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();
			finish();
		}
		btnNext = (Button) (findViewById(R.id.cc_btn_next)
				.findViewById(R.id.button));
		btnNext.setText("确认销卡");

		etUserName = (EditText) findViewById(R.id.cc_et_openName).findViewById(
				R.id.et_user);

		spnrIdType = (Spinner) findViewById(R.id.cc_spnr_idType).findViewById(
				R.id.et_user);
		etId = (EditText) findViewById(R.id.cc_et_id)
				.findViewById(R.id.et_user);
		etPhone = (EditText) findViewById(R.id.cc_et_phone).findViewById(
				R.id.et_user);
		etPwd = (EditText) findViewById(R.id.cc_et_pwd).findViewById(
				R.id.et_psd);

		btnNext = (Button) (findViewById(R.id.cc_btn_next)
				.findViewById(R.id.button));
		btnNext.setText("确认销卡");
	}

	// 按钮监听
	private OnClickListener onClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			boolean flag = false;
			// 获取所需数据
			String userName = etUserName.getText().toString().trim();
			String ccNo = spnrCcNo.getSelectedItem().toString().trim();
			String idNo = etId.getText().toString().trim();
			String cellPhone = etPhone.getText().toString().trim();
			String pwd = etPwd.getText().toString().trim();
			// 检查网络连接
			if (EHelper.hasInternet(DestroyCard.this)) {
				// 连接服务器...
				JSONObject jsonObj = new JSONObject();
				try {
					jsonObj = ConnectWs.connect(DestroyCard.this,
							EAccType.CREDIT_CARD, EOperation.DESTROY_CARD,
							userName, ccNo, idNo, cellPhone, pwd);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Toast.makeText(DestroyCard.this, "对不起，服务器未连接",
							Toast.LENGTH_SHORT).show();
					e.printStackTrace();
					return;
				}
				flag = EHelper.toBoolean(jsonObj);
			} else {
				Toast.makeText(DestroyCard.this, "没有网络", Toast.LENGTH_SHORT)
						.show();
				return;
			}

			// 弹出对话框
			// 设置对话框的布局
			View view = getLayoutInflater().inflate(R.xml.comdialog1, null);
			final Dialog dialog = new Dialog(DestroyCard.this, R.style.dialog);
			dialog.show();
			// 设置具体对话框布局的宽和高
			LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.MATCH_PARENT);
			// 将设置好的布局View加到对话框中
			dialog.addContentView(view, params);
			Button Ok_btn = (Button) view.findViewById(R.id.btn_comdlog_ok);
			Ok_btn.setText("确定");
			Ok_btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(DestroyCard.this,
							CreditCardMain.class);
					startActivity(intent);
					dialog.dismiss();
				}
			});
			if (flag) {
				((TextView) view.findViewById(R.id.tv_comdlog_title))
						.setText("成功提示");
				((TextView) view.findViewById(R.id.tv_comdlog_con1))
						.setText("销卡成功");

			} else {
				((TextView) view.findViewById(R.id.tv_comdlog_title))
						.setText("失败提示");
				((TextView) view.findViewById(R.id.tv_comdlog_con1))
						.setText("销卡失败，请验证输入的信息是否正确");

			}

		}
	};
}
