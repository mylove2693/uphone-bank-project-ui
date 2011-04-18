package ubank.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import ubank.base.GeneralFinanceActivity;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.enum_type.ERateType;
import ubank.helper.EHelper;
import ubank.webservice.ConnectWs;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * 浦楠
 * 贷款利率界面
 * @author punan
 * 
 */

public class LoanRates extends GeneralFinanceActivity {
	private List<String> data = new ArrayList<String>();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addLayout(R.layout.main_loan);
        
        //设置导航栏和监听
        tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("金融助手>");
		setListener(tvClassFirst, this, FinanceAss.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("贷款利率");
        
        //从服务器读取存款利率
        loaderData();
        //显示数据
        showDataToView();
    }
    
    //从服务器读取存款利率
    private void loaderData(){
		if (EHelper.hasInternet(this)) {
		try {
			//从服务器取出贷款利率
			JSONObject json = ConnectWs.connect(this, EAccType.NULL, EOperation.GET_RATE,ERateType.getRateTypeName(ERateType.LENDING_RATE));
			//将JSON数据转换为MAP型
			Map<String, String> name = EHelper.toMap(json);
			//将贷款利率放到List中
			for (int i = 0; i < name.size(); i++) {
				data.add(name.get(String.valueOf(i)).toString());
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
    
    //显示数据
    private void showDataToView(){		 
		 
		 List<TextView> ratesText = new ArrayList<TextView>();
		 
		 ratesText.add((TextView)findViewById(R.id.loan_one_1));
		 ratesText.add((TextView)findViewById(R.id.loan_one_2));
		 ratesText.add((TextView)findViewById(R.id.loan_two_1));
		 ratesText.add((TextView)findViewById(R.id.loan_two_2));
		 
		 for(int i = 0; i < ratesText.size(); i ++)
		 {
			ratesText.get(i).setText(data.get(i));
		 }
	 }
}