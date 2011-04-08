package ubank.base;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class Listener implements OnClickListener{
	private Activity fromActivity;
	private Class<Activity> toActivity;
	private Intent intent = null;
	
	public Listener(Activity fromActivity,Class<Activity> toActivity) {
		this.fromActivity = fromActivity;
		this.toActivity = toActivity;
	}
	
	public Listener(Intent intent,Activity fromActivity,Class<Activity> toActivity) {
		this.fromActivity = fromActivity;
		this.toActivity = toActivity;
		this.intent = intent;
	}
	
	@Override
	public void onClick(View v) {
		if (intent == null) {
			intent = new Intent();
			intent.setClass(fromActivity, toActivity);
			fromActivity.startActivity(intent);
		}else{
			intent.setClass(fromActivity, toActivity);
			fromActivity.startActivity(intent);
		}
	}
	
}
