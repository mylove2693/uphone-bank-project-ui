package ubank.base;

import ubank.main.R;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GeneralListActivity extends ListActivity implements IGeneralActivity{
	//返回键
	protected ImageView btnback;
	//底部的手机银行图标
	protected ImageView btnbank;
	//底部的金融助手图标
	protected ImageView btnhelper;
	
	//导航栏
	protected TextView tvClassFirst;
	
	protected TextView tvClassSecond;
	
	protected TextView tvClassThird;
	
	protected TextView tvClassFour;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //获取图片对象
        btnback = (ImageView)findViewById(R.id.returnToPre);
        btnbank = (ImageView)findViewById(R.id.btnMain);
        btnhelper = (ImageView)findViewById(R.id.btnHelper);
        
        //获取TextView对象
        tvClassFirst = (TextView)findViewById(R.id.class_first);
        tvClassSecond = (TextView)findViewById(R.id.class_second);
        tvClassThird = (TextView)findViewById(R.id.class_third);
        tvClassFour = (TextView)findViewById(R.id.class_four);
    }
    
    //向Activity中添加新的布局
    public void addLayout(int layout){
    	LinearLayout line = (LinearLayout)findViewById(R.id.ListLinearLayout);
    	View view=getLayoutInflater().inflate(R.layout.test, null);
    	line.addView(view);
    }
    
    //为导航栏的按钮添加监听
	public void setListener(TextView tvButton,Activity fromActivity,Class<Activity> toActivity){
		tvButton.setOnClickListener(new Listener(fromActivity , toActivity));
	}
	
	//为底部的图标按钮添加监听
	public void setListener(ImageView btnButton,Activity fromActivity,Class<Activity> toActivity){
		btnButton.setOnClickListener(new Listener(fromActivity, toActivity));
	}
	
}
