package ubank.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import ubank.base.GeneralActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.webservice.ConnectWs;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ExchangeCalc extends GeneralActivity{
	private EditText InputAmt;
	private Spinner SpSource;
	private Spinner SpDestinations;
	private Button Calc;
	
	private List<String> MTpye = new ArrayList<String>();
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addLayout(R.layout.main_exchange);
        
        //设置导航栏和监听
        tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("金融助手>");
		setListener(tvClassFirst, this, FinanceAss.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("汇率计算");
        
		//从服务器读取所有币种
		loaderData();
        
		
		
		
		
    }
    
    //从服务器读取所有币种
    private void loaderData(){
		if (EHelper.hasInternet(this)) {
		try {
			//从服务器取出所有币种
			JSONObject json = ConnectWs.connect(this, EAccType.NULL, EOperation.GET_MONEY_TYPE,"");
			//将JSON数据转换为MAP型
			MTpye = EHelper.toList(json);
			for (int i = 0; i < MTpye.size(); i++) {
				System.out.println(MTpye.get(i));
			}
			
		} catch (IOException e) {
			Toast.makeText(this, "对不起，服务器未连接", Toast.LENGTH_SHORT).show();
			finish();
			e.printStackTrace();
		}
		
		}else {
			Toast.makeText(this, "没有连接网络", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
    
}
