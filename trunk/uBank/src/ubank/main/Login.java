package ubank.main;

import java.io.IOException;
import java.util.Map;

import org.json.JSONObject;

import ubank.base.GeneralActivity;
import ubank.base.Lock;
import ubank.base.MyDialogOne;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.webservice.ConnectWs;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

	private String extraCode;
	private String passWord;
	private String InputCode;
	private String logintimes;
	private boolean loginflag = false;

	private ImageView bankmain;
	private ImageView bankhelp;
	private EditText userid;
	private EditText password;
	private EditText extracode;
	private TextView showec;
	public static String userName = "张三";
	public static String userId = "1";
	private Button btn_login;

	private Intent intent = new Intent();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_login);

		// 获取附加码
		loaderData();

		// 显示附加码
		showec = (TextView) findViewById(R.id.loginbox).findViewById(R.id.extraCode);
		showec.setText(extraCode);
		
		//刷新附加码
		showec.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// 获取附加码
				loaderData();
				
				// 显示附加码
				showec.setText(extraCode);
			}
		});

		userid = (EditText) findViewById(R.id.login_box).findViewById(R.id.nameEdit);
		password = (EditText) findViewById(R.id.login_box).findViewById(R.id.passwdEdit);
		extracode = (EditText) findViewById(R.id.login_box).findViewById(R.id.pyramidEdit);

		// 登录按钮
		btn_login = (Button) findViewById(R.id.login_box).findViewById(R.id.btn_userlogin);
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userId = userid.getText().toString();
				passWord = password.getText().toString();
				InputCode = extracode.getText().toString();
				if (InputCode.equals(extraCode)) {

					if (userId.equals("")) {

						// 用Dialog提示用户名为空
						MyDialogOne dialog = new MyDialogOne(Login.this, R.style.dialog);
						dialog.setTitleAndInfo("登录手机银行", "\n\n用户号不能为空！");
						dialog.show();
					} else {

						if (passWord.equals("")) {

							// 用Dialog提示密码为空
							MyDialogOne dialog = new MyDialogOne(Login.this, R.style.dialog);
							dialog.setTitleAndInfo("登录手机银行", "\n\n密码不能为空！");
							dialog.show();
						} else {

							if (EHelper.hasInternet(Login.this)) {
								try {
									// 将用户名和密码发送到服务端进行验证
									JSONObject json = ConnectWs.connect(Login.this, EAccType.NULL,
											EOperation.LOGIN, userId, passWord, InputCode);
									// 将JSON数据转换为boolean型
									loginflag = EHelper.toBoolean(json);

								} catch (IOException e) {
									Toast.makeText(Login.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
									finish();
									e.printStackTrace();
								}

							} else {
								Toast.makeText(Login.this, "没有连接网络", Toast.LENGTH_SHORT).show();
								finish();
							}
						}
						if (loginflag) {
							// 登录成功
							FinanceAss.loginstatus = true;

							if (EHelper.hasInternet(Login.this)) {
								try {
									// 将用户名和密码发送到服务端进行验证
									JSONObject json = ConnectWs.connect(Login.this, EAccType.NULL,
											EOperation.GET_USER_INFO, userId);
									// 将JSON数据转换为MAP型
									Map<String, String> userinfo = EHelper.toMap(json);
									System.out.println(json.toString());
									userName = userinfo.get("userName").toString();
									logintimes = userinfo.get("loginTimes").toString();

								} catch (IOException e) {
									Toast.makeText(Login.this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
									finish();
									e.printStackTrace();
								}

							} else {
								Toast.makeText(Login.this, "没有连接网络", Toast.LENGTH_SHORT).show();
								finish();
							}
							intent.putExtra("logintimes", logintimes);
							intent.setClass(Login.this, BankMain.class);
							Login.this.startActivity(intent);

						} else {
							// 登录失败
							MyDialogOne dialog = new MyDialogOne(Login.this, R.style.dialog);
							dialog.setTitleAndInfo("登录手机银行", "\n登录失败！\n用户名或密码输入错误！");
							dialog.show();
						}
					}
				} else {
					// 用Dialog提示附加码不正确
					MyDialogOne dialog = new MyDialogOne(Login.this, R.style.dialog);
					dialog.setTitleAndInfo("登录手机银行", "\n\n附加码不正确！");
					dialog.show();
				}
			}
		});

		// 设置底部选项卡
		bankmain = (ImageView) findViewById(R.id.mainbelow).findViewById(R.id.btnMain);
		bankmain.setImageResource(R.drawable.main_sjyh);
//		bankmain.setImageResource(R.drawable.main_sjyh2);
		bankmain.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		bankhelp = (ImageView) findViewById(R.id.mainbelow).findViewById(R.id.btnHelper);
		bankhelp.setImageResource(R.drawable.main_jrzs);
//		bankhelp.setImageResource(R.drawable.main_jrzs2);
		bankhelp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				intent = new Intent(Login.this, FinanceAss.class);
				intent.addFlags(intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				Login.this.startActivity(intent);

			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		GeneralActivity.isHide = false;
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

	// 当不再需要时finish该页面
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Login.this.finish();
	}

	// 从服务器读取附加码
	private void loaderData() {
		if (EHelper.hasInternet(this)) {
			try {
				// 从服务器取出所有币种
				JSONObject json = ConnectWs.connect(this, EAccType.NULL, EOperation.GET_EXTRA_CODE, "");
				// 将JSON数据转换为MAP型
				extraCode = EHelper.toStr(json);

			} catch (IOException e) {
				Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
				finish();
				e.printStackTrace();
			}

		} else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO 菜单生成
		menu.add(0, 0, Menu.NONE, "退出");// 退出
		menu.add(0, 1, Menu.NONE, "锁定");
		menu.add(0, 2, Menu.NONE, "关于");
		return super.onCreateOptionsMenu(menu);

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
}