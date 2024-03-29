package ubank.base;

import ubank.main.BankMain;
import ubank.main.FinanceAss;
import ubank.main.Login;
import ubank.main.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GeneralFinanceActivity extends Activity implements IGeneralActivity {

	public static final String TAG = "Ubank";
	// 返回键
	protected ImageView btnback;
	// 底部的手机银行图标
	protected ImageView btnbank;
	// 底部的金融助手图标
	protected ImageView btnhelper;

	// 导航栏
	protected TextView tvClassFirst;

	public static boolean isFirst = true;
	public static boolean isHide = false;

	private Intent intent = null;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO 菜单生成
		menu.add(0, 0, 0, R.string.app_exit);// 退出
		menu.add(0, 1, 1, "关于");
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		GeneralActivity.isHide = false;
//		Log.v("Ubank", getClass() + " onResume,hide= " + GeneralActivity.isHide);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
//		if (GeneralActivity.isHide) {
//			Log.v("Ubank", getClass() + " onStop now,hide= "
//					+ GeneralActivity.isHide);
//
//			Intent notifyIntent = new Intent(this, getClass());
//			notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//			/* 创建PendingIntent作为设置递延运行的Activity */
//			PendingIntent appIntent = PendingIntent.getActivity(this, 0,
//					notifyIntent, 0);
//
//			Notification myNoti = new Notification();
//			// 设置如果被点击可以自动删除
//			myNoti.flags = Notification.FLAG_AUTO_CANCEL;
//			/* 设置statusbar显示的icon */
//			myNoti.icon = android.R.drawable.stat_notify_chat;
//			myNoti.tickerText = "你的手机银行正在运行";
//			/* 设置notification发生时同时发出默认声音 */
//			myNoti.defaults = Notification.DEFAULT_SOUND;
//			myNoti.setLatestEventInfo(this, "手机银行", "为了避免信息泄露，请及时完成或退出",
//					appIntent);
//			NotificationManager myNotiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//			myNotiManager.notify(0, myNoti);
//			finish();
//		}
//		GeneralActivity.isHide = true;
//		Log.v("Ubank", getClass() + " onStop count. hide= "
//				+ GeneralActivity.isHide);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO 菜单点击
		switch (item.getItemId()) {
		case 0:
			moveTaskToBack(true);
			break;
		case 1:
			new MyDialogOne(this, R.style.dialog)
					.setTitleAndInfo("关于",
							"手机银行\n客户至上\n版本号v10.\n智翔公司 版权所有\nCopyright 2011\nAll Rights Reserved")
					.setDismissButton().show();

			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	protected TextView tvClassSecond;

	protected TextView tvClassThird;

	protected TextView tvClassFour;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_finance);
		// 获取图片对象
		btnback = (ImageView) findViewById(R.id.paymentabovef).findViewById(R.id.returnToPre);
		btnbank = (ImageView) findViewById(R.id.paymentbelowf).findViewById(R.id.btnMain);
		btnhelper = (ImageView) findViewById(R.id.paymentbelowf).findViewById(R.id.btnHelper);

		// 获取TextView对象
		tvClassFirst = (TextView) findViewById(R.id.paymentabovef).findViewById(R.id.class_first);
		tvClassSecond = (TextView) findViewById(R.id.paymentabovef).findViewById(R.id.class_second);
		tvClassThird = (TextView) findViewById(R.id.paymentabovef).findViewById(R.id.class_third);
		tvClassFour = (TextView) findViewById(R.id.paymentabovef).findViewById(R.id.class_four);

		// 为返回键添加监听
		btnback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		// 设置底部选项卡样式
//		btnbank.setImageResource(R.drawable.main_sjyh);
		btnbank.setImageResource(R.drawable.main_sjyh2);
		// 给底部的手机银行图片添加监听
		btnbank.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (FinanceAss.loginstatus) {
					// 重新载入主页面
					intent = new Intent(GeneralFinanceActivity.this, BankMain.class);
					intent.addFlags(intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					GeneralFinanceActivity.this.startActivity(intent);

				} else {
					// 载入登录按钮
					intent = new Intent(GeneralFinanceActivity.this, Login.class);
					GeneralFinanceActivity.this.startActivity(intent);

				}
			}
		});

		// 设置底部选项卡样式
//		btnhelper.setImageResource(R.drawable.main_jrzs);
		btnhelper.setImageResource(R.drawable.main_jrzs2);
		// 给底部的金融助手图片添加监听
		btnhelper.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				intent = new Intent(GeneralFinanceActivity.this, FinanceAss.class);
				intent.addFlags(intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				GeneralFinanceActivity.this.startActivity(intent);
			}
		});
	}

	// 向Activity中添加新的布局
	@Override
	public void addLayout(int layout) {
		// TODO Auto-generated method stub
		LinearLayout line = (LinearLayout) findViewById(R.id.ListLinearLayoutf);
		View view = getLayoutInflater().inflate(layout, null);
		line.addView(view);
	}

	// 为导航栏的按钮添加监听
	@Override
	public void setListener(TextView tvButton, Activity fromActivity, Class toActivity) {
		// TODO Auto-generated method stub
		tvButton.setOnClickListener(new Listener(fromActivity, toActivity));
	}

	@Override
	public void setListener(ImageView btnButton, Activity fromActivity, Class toActivity) {
		// TODO Auto-generated method stub

	}
}
