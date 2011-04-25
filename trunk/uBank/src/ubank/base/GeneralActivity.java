package ubank.base;

import ubank.main.BankMain;
import ubank.main.FinanceAss;
import ubank.main.R;
import android.app.Activity;
import android.app.ActivityManager;
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
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GeneralActivity extends Activity implements IGeneralActivity {
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO 菜单生成
		menu.add(0, 0, Menu.NONE, "退出");// 退出
//		menu.add(0, 1, Menu.NONE, "锁定");
		menu.add(0, 2, Menu.NONE, "关于");
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
	public void onAttachedToWindow() {
		// TODO 为了实现获取HOME键
		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
		super.onAttachedToWindow();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_HOME:// 如果是Home键
			Intent notifyIntent = new Intent(Intent.ACTION_MAIN);
			notifyIntent.addCategory(Intent.CATEGORY_LAUNCHER);
			notifyIntent.setClass(this, getClass());
			notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

			/* 创建PendingIntent作为设置递延运行的Activity */
			PendingIntent appIntent = PendingIntent.getActivity(this, 0, notifyIntent, 0);

			Notification myNoti = new Notification();
			// 设置如果被点击可以自动删除
			myNoti.flags = Notification.FLAG_AUTO_CANCEL;
			/* 设置statusbar显示的icon */
			myNoti.icon = android.R.drawable.stat_notify_chat;
			myNoti.tickerText = "你的手机银行正在运行";
			/* 设置notification发生时同时发出默认声音 */
			myNoti.defaults = Notification.DEFAULT_SOUND;
			myNoti.setLatestEventInfo(this, "手机银行", "为了避免信息泄露，请及时完成或退出", appIntent);
			NotificationManager myNotiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			myNotiManager.notify(0, myNoti);

			// 锁定
			Intent intent = new Intent(this, Lock.class);
			startActivity(intent);
			moveTaskToBack(true);
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
//		if (GeneralActivity.isHide) {
//			Log.v("Ubank", getClass() + " onStop now,hide= " + GeneralActivity.isHide);
//
//			Intent notifyIntent = new Intent(Intent.ACTION_MAIN);
//			notifyIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//			notifyIntent.setClass(this, getClass());
//			notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//
//			/* 创建PendingIntent作为设置递延运行的Activity */
//			PendingIntent appIntent = PendingIntent.getActivity(this, 0, notifyIntent, 0);
//
//			Notification myNoti = new Notification();
//			// 设置如果被点击可以自动删除
//			myNoti.flags = Notification.FLAG_AUTO_CANCEL;
//			/* 设置statusbar显示的icon */
//			myNoti.icon = android.R.drawable.stat_notify_chat;
//			myNoti.tickerText = "你的手机银行正在运行";
//			/* 设置notification发生时同时发出默认声音 */
//			myNoti.defaults = Notification.DEFAULT_SOUND;
//			myNoti.setLatestEventInfo(this, "手机银行", "为了避免信息泄露，请及时完成或退出", appIntent);
//			NotificationManager myNotiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//			myNotiManager.notify(0, myNoti);
//
//			// 锁定
//			Intent intent = new Intent(this, Lock.class);
//			startActivity(intent);
//		}
//		GeneralActivity.isHide = true;
//		Log.v("Ubank", getClass() + " onStop count. hide= " + GeneralActivity.isHide);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO 菜单点击
		switch (item.getItemId()) {
		case 0:
			Intent startMain = new Intent(Intent.ACTION_MAIN);
			startMain.addCategory(Intent.CATEGORY_HOME);
			startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(startMain);
			System.exit(0);
			// moveTaskToBack(true);
			break;
		case 1:
			Intent intent = new Intent(this, Lock.class);
			startActivity(intent);
//			moveTaskToBack(true);
			break;
		case 2:
			new MyDialogOne(this, R.style.dialog)
					.setTitleAndInfo("关于",
							"手机银行\n客户至上\n版本号v10.\n智翔公司android小组 版权所有\nCopyright 2011\nAll Rights Reserved")
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
		setContentView(R.layout.main);
		// 获取图片对象
		btnback = (ImageView) findViewById(R.id.paymentabove).findViewById(R.id.returnToPre);
		btnbank = (ImageView) findViewById(R.id.paymentbelow).findViewById(R.id.btnMain);
		btnhelper = (ImageView) findViewById(R.id.paymentbelow).findViewById(R.id.btnHelper);

		// 获取TextView对象
		tvClassFirst = (TextView) findViewById(R.id.paymentabove).findViewById(R.id.class_first);
		tvClassSecond = (TextView) findViewById(R.id.paymentabove).findViewById(R.id.class_second);
		tvClassThird = (TextView) findViewById(R.id.paymentabove).findViewById(R.id.class_third);
		tvClassFour = (TextView) findViewById(R.id.paymentabove).findViewById(R.id.class_four);

		// 为返回键添加监听
		btnback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		// 给底部的手机银行图片添加监听
		setListener(btnbank, this, BankMain.class);

		// 给底部的金融助手图片添加监听
		setListener(btnhelper, this, FinanceAss.class);
	}

	// 向Activity中添加新的布局
	public void addLayout(int layout) {
		LinearLayout line = (LinearLayout) findViewById(R.id.ListLinearLayout);
		View view = getLayoutInflater().inflate(layout, null);
		line.addView(view);
	}

	// 为导航栏的按钮添加监听
	@SuppressWarnings("unchecked")
	public void setListener(TextView tvButton, Activity fromActivity, Class toActivity) {
		tvButton.setOnClickListener(new Listener(fromActivity, toActivity));
	}

	// 为底部的图标按钮添加监听
	@SuppressWarnings("unchecked")
	public void setListener(ImageView btnButton, Activity fromActivity, Class toActivity) {
		btnButton.setOnClickListener(new Listener(fromActivity, toActivity));
	}

}