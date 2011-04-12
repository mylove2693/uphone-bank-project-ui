package ubank.account_manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ubank.base.GeneralActivity;
import ubank.main.BankMain;
import ubank.main.R;

public class AddAccount extends GeneralActivity{
	// 导航栏三级标题
	private TextView txt=null;
	private Button next_btn =null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 获得传过来数据
		Intent intent = getIntent();
//		title = intent.getStringExtra("title");

		tvClassFirst.setVisibility(View.VISIBLE);
		//首页监听
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("账户管理>");
		 //账户管理的监听
        setListener(tvClassSecond, this, ManagerHome.class);
		
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("添加账户");
		//布局文件
		addLayout(R.layout.add_acc);
		
		txt=(TextView)findViewById(R.id.above_txt).findViewById(R.id.Text_View_16_Gray);
        txt.setText("用户号323132332");
        txt = (TextView) findViewById(R.id.txt_acc_type).findViewById(R.id.Text_View_18);
        txt.setText("请选择账户类型:");
        txt = (TextView) findViewById(R.id.txt1).findViewById(R.id.Text_View_18);
		txt.setText("请输入账户号:");
		txt = (TextView) findViewById(R.id.txt2).findViewById(R.id.Text_View_18);
		txt.setText("请设置账户别名:");
		txt = (TextView) findViewById(R.id.psd_txt).findViewById(R.id.Text_View_18);
		txt.setText("请输入账户密码:");
		next_btn= (Button) findViewById(R.id.next_btn).findViewById(R.id.button);
		next_btn.setText("确认添加");
	}
}
