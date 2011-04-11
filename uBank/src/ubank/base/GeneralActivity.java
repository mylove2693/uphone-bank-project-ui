package ubank.base;

import ubank.main.BankMain;
import ubank.main.FinanceAss;
import ubank.main.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GeneralActivity extends Activity implements IGeneralActivity{
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //获取图片对象
        btnback = (ImageView)findViewById(R.id.paymentabove).findViewById(R.id.returnToPre);
        btnbank = (ImageView)findViewById(R.id.paymentbelow).findViewById(R.id.btnMain);
        btnhelper = (ImageView)findViewById(R.id.paymentbelow).findViewById(R.id.btnHelper);
        
        //获取TextView对象
        tvClassFirst = (TextView)findViewById(R.id.paymentabove).findViewById(R.id.class_first);
        tvClassSecond = (TextView)findViewById(R.id.paymentabove).findViewById(R.id.class_second);
        tvClassThird = (TextView)findViewById(R.id.paymentabove).findViewById(R.id.class_third);
        tvClassFour = (TextView)findViewById(R.id.paymentabove).findViewById(R.id.class_four);
        
        //为返回键添加监听
        btnback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
        
        //给底部的手机银行图片添加监听
        setListener(btnbank, this, BankMain.class);
        
        //给底部的金融助手图片添加监听
        setListener(btnhelper, this, FinanceAss.class);
    }
    
    //向Activity中添加新的布局
    public void addLayout(int layout){
    	LinearLayout line = (LinearLayout)findViewById(R.id.ListLinearLayout);
    	View view=getLayoutInflater().inflate(layout, null);
    	line.addView(view);
    }
    
  //为导航栏的按钮添加监听
    @SuppressWarnings("unchecked")
	public void setListener(TextView tvButton,Activity fromActivity,Class toActivity){
		tvButton.setOnClickListener(new Listener(fromActivity , toActivity));
	}
	
	//为底部的图标按钮添加监听
    @SuppressWarnings("unchecked")
	public void setListener(ImageView btnButton,Activity fromActivity,Class toActivity){
		btnButton.setOnClickListener(new Listener(fromActivity, toActivity));
	}
    
}