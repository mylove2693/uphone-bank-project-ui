package ubank.base;

import ubank.main.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class Lock extends Activity {

	private EditText etPwd;
	private Button btnOk;
	private Button btnExit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

		setContentView(R.layout.lock);
		etPwd = (EditText) findViewById(R.id.lock_et_pwd);
		btnOk = (Button) findViewById(R.id.lock_btn_ok);
		btnOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String password = "123";
				String input = etPwd.getText().toString().trim();
				// TODO Auto-generated method stub
				if (input.equals("123")) {
					finish();
				} else {
					Animation shake = AnimationUtils.loadAnimation(Lock.this, R.anim.shake);
					etPwd.startAnimation(shake);
				}
			}
		});
		btnExit = (Button) findViewById(R.id.lock_btn_exit);
		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent startMain = new Intent(Intent.ACTION_MAIN);
				startMain.addCategory(Intent.CATEGORY_HOME);
				startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(startMain);
				System.exit(0);
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
