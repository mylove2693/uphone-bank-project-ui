package ubank.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Login extends Activity {

	public static String userId = "1";
	private Button btn_login;
	private Intent intent;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_login);

		// 登录按钮
		btn_login = (Button) findViewById(R.id.login_box).findViewById(
				R.id.btn_userlogin);
		btn_login.setOnClickListener(new LoginClickListener());

	}

	class LoginClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			intent = new Intent(Login.this, BankMain.class);
			Login.this.startActivity(intent);
		}

	}
}