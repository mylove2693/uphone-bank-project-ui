package ubank.base;

import ubank.main.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 一个按钮的对话框
 * 
 * @author Administrator
 * 
 */
public class MyDialogOne extends Dialog implements OnClickListener {
	TextView title_tv;
	TextView inmation;
	Button btn;
	private Activity fromActivity;
	private Class<Activity> toActivity;
	private Intent intent = null;

	public MyDialogOne(Context context, int theme) {
		super(context, theme);
		setContentView(R.xml.comdialog1);
		title_tv = (TextView) findViewById(R.id.tv_comdlog_title);
		inmation = (TextView) findViewById(R.id.tv_comdlog_con1);
		btn = (Button) findViewById(R.id.btn_comdlog_ok);
		btn.setOnClickListener(this);
		// TODO Auto-generated constructor stub
	}

	// 设置跳转的方法  无传值的
	public void Listener(Activity fromActivity, Class toActivity) {
		this.fromActivity = fromActivity;
		this.toActivity = toActivity;
	}
	//传递Intent的方法
	public void Listener(Intent inIntent,Activity fromActivity) {
		this.fromActivity = fromActivity;
		this.intent=inIntent;
	}

	// 设置提示标题和提示信息

	public void setTitleAndInfo(String title, String info) {
		this.title_tv.setText(title);
		inmation.setText(info);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub


		if(intent!=null){
			fromActivity.startActivity(intent);
			this.dismiss();
		}
		if (toActivity != null) {
			System.out.println("--------");
			intent = new Intent();
			intent.setClass(fromActivity, toActivity);
			fromActivity.startActivity(intent);
			this.dismiss();
		} else {
			this.dismiss();
		}
	}

}
