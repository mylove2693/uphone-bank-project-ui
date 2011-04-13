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
		title_tv.setText("fdf");
		inmation = (TextView) findViewById(R.id.tv_comdlog_con1);
		btn = (Button) findViewById(R.id.btn_comdlog_ok);
		btn.setOnClickListener(this);
		// TODO Auto-generated constructor stub
	}

	public void Listener(Activity fromActivity, Class toActivity) {
		this.fromActivity = fromActivity;
		this.toActivity = toActivity;
	}

	public void setTitleAndInfo(String title,String info) {
		this.title_tv.setText(title);
		inmation.setText(info);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		// this.dismiss();
		// Intent intent=new Intent();
		if(toActivity!=null){
		System.out.println("--------");
		intent = new Intent();
		intent.setClass(fromActivity, toActivity);
		fromActivity.startActivity(intent);
		this.dismiss();
		}else{
		this.dismiss();
		}
	}

}
