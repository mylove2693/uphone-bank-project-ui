package ubank.base;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;


public interface IGeneralActivity {
	//向Activity中添加新的布局
	public void addLayout(int layout);
	
	//为导航栏的按钮添加监听
	@SuppressWarnings("unchecked")
	public void setListener(TextView tvButton,Activity fromActivity,Class toActivity);
	
	//为底部的图标按钮添加监听
	@SuppressWarnings("unchecked")
	public void setListener(ImageView btnButton,Activity fromActivity,Class toActivity);
	
}
