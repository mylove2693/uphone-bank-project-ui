package ubank.base;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class Listener implements OnClickListener{
	private Activity fromActivity;
	private Class<Activity> toActivity;
	
	public Listener(Activity fromActivity,Class<Activity> toActivity) {
		this.fromActivity = fromActivity;
		this.toActivity = toActivity;
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setClass(fromActivity, toActivity);
		fromActivity.startActivity(intent);
	}
	
}
