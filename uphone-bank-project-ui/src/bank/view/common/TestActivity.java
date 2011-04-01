package bank.view.common;

import portfolio_control.Account_Select;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class TestActivity extends Activity{
	//分别定义了账户类型的adapter的和
	
	ArrayAdapter<String> AccTypAdapter, AccNumAdapter = null;
	Spinner AccTypSpinner;
//	SimpleAdapter AccTypAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
//		Button n1=(Button)this.findViewById(R.id.btnbu).findViewById(R.id.button);
//		Button n2=(Button)this.findViewById(R.id.nn).findViewById(R.id.button);
//		n1.setText("上");
//		n2.setText("下");
//		n1.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				System.out.println("上");
//			}
//		});
//		
//		n2.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				System.out.println("下");
//			}
//		});
	}
//		Account_Select my_acc_select = new Account_Select(this);
//		my_acc_select.AddTypeData(this, s);
//		my_acc_select.AddTypeData(this,s);
////		addData();
//	}
//	private void addData(){
//		AccTypSpinner = (Spinner) findViewById(R.id.spinnerAccTyp);
//		AccTypAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
//		AccTypAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		for (int i = 0; i < s.length; i += 1) {
//			AccTypAdapter.add(s[i]);
//		}
//		AccTypSpinner.setPrompt("请选择账户类型");
//		AccTypSpinner.setAdapter(AccTypAdapter);	
//	}
}
