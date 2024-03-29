package ubank.base;

import ubank.main.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 两个按钮的对话框
 * 
 * @author Administrator
 * 
 */
public class MyDialogTwo extends Dialog implements OnClickListener {
	TextView title_tv;
	TextView inmation;
	Button btn_ok;
	Button btn_cancel;
	private Activity fromActivity;
	private Class<Activity> toActivity;
	private Intent intent = null;
	private EditText editpwd;
	private TextView tvpwd;

	public MyDialogTwo(Context context, int theme) {
		super(context, theme);
		setContentView(R.xml.comdialog2);
		title_tv = (TextView) findViewById(R.id.tv_comdlog_title);
		inmation = (TextView) findViewById(R.id.tv_comdlog_con1);
		btn_ok = (Button) findViewById(R.id.btn_comdlog_ok);
		btn_ok.setText("确认");
		btn_cancel = (Button) findViewById(R.id.btn_comdlog_cancel);
		btn_cancel.setText("取消");
		btn_ok.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
		tvpwd = (TextView) findViewById(R.id.act_tv_pwd);
		editpwd = (EditText) findViewById(R.id.act_et_pwd);
		// TODO Auto-generated constructor stub
	}

	public String getPwd() {
		Log.v("Ubank", editpwd.getText().toString().trim());
		return editpwd.getText().toString().trim();
	}

	public MyDialogTwo setPwdVisibility() {
		tvpwd.setVisibility(View.VISIBLE);
		editpwd.setVisibility(View.VISIBLE);
		return this;
	}

	// 设置跳转的方法 无传值的
	public void Listener(Activity fromActivity, Class toActivity) {
		this.fromActivity = fromActivity;
		this.toActivity = toActivity;
	}

	// 传递Intent的方法
	public void Listener(Intent inIntent, Activity fromActivity) {
		this.fromActivity = fromActivity;
		this.intent = inIntent;
	}

	// 设置按钮的文本
	public void setOkAndCancleText(String ok, String cancle) {
		btn_ok.setText(ok);
		btn_cancel.setText(cancle);
	}

	public MyDialogTwo setTitleAndInfo(String title, String info) {
		this.title_tv.setText(title);
		inmation.setText(info);
		return this;
	}

	public MyDialogTwo setOkToService(android.view.View.OnClickListener l) {
		btn_ok.setOnClickListener(l);
		return this;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub


		if (v.equals(btn_ok)) {
			if (intent != null) {
			this.dismiss();
			fromActivity.startActivity(intent);
		}
			
			if (toActivity != null) {
				System.out.println("--------");
				intent = new Intent();
				intent.setClass(fromActivity, toActivity);
				this.dismiss();
				fromActivity.startActivity(intent);

			} else {
				this.dismiss();
			}
		}
		if (v.equals(btn_cancel)) {
			System.out.println("+++++++");
			this.dismiss();
		}
	}

}
