package portfolio_control;

import bank.view.common.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Account_Select extends LinearLayout{

	/**
	 * 定义两个Spinner的对象来获取布局中的spinner,
	 * 分别是账户类型，账户号
	*/
	public Spinner AccTypSpinner;
	Spinner AccNumSpinner;
	String[] data;
//	public SimpleAdapter AccTypAdapter;
	ArrayAdapter<String> AccTypAdapter;
	ArrayAdapter<String> AccNumAdapter;

	public Account_Select(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.account_select,this,true);	
//		AccTypSpinner.setOnItemSelectedListener(new selectItemListener());
		
		String[] s={"1","2","3","4"};
		AddTypeData(context,s);
		
		// TODO Auto-generated constructor stub
	}

	public Account_Select(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}

//	//自身内部加数据
//	public void AddSpinnerData(Context con){
//		//获取两个spinner
//		AccTypSpinner = (Spinner) findViewById(R.id.spinnerAccTyp);
//		AccNumSpinner=(Spinner) findViewById(R.id.spinnerAccNum);
//       /*实例化一个适配器，为适配器添加样式
//		此处采用内部的*/
//		AccTypAdapter = new ArrayAdapter<String>(con,android.R.layout.simple_spinner_item);
//		AccTypAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		//向适配器中间加数据
//		for (int i = 0; i < 7; i += 1) {
////			int s=i;		
//			AccTypAdapter.add(String.valueOf(i));
//		}
//       //设置下拉框的标题
//		AccTypSpinner.setPrompt("请选择账户类型");
//	  //为spiiner添加自己有数据的适配器
//		AccTypSpinner.setAdapter(AccTypAdapter);
//	}
	/**
	 * 通过外界传递数据加数据
	 */
	public void AddTypeData(Context con,String[] data1){
		this.data=data1;
		//获取两个spinner
		AccTypSpinner = (Spinner) findViewById(R.id.spinnerAccTyp);
		AccNumSpinner=(Spinner) findViewById(R.id.spinnerAccNum);
       /*实例化一个适配器，为适配器添加样式
		此处采用内部的*/
		AccTypAdapter = new ArrayAdapter<String>(con,android.R.layout.simple_spinner_item);
		AccTypAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//向适配器中间加数据
		for (int i = 0; i < data.length; i += 1) {		
			AccTypAdapter.add(data[i]);
//			System.out.println(data[i]);
		}
       //设置下拉框的标题
		this.AccTypSpinner.setPrompt("请选择账户类型");
	  //为spiiner添加自己有数据的适配器
		this.AccTypSpinner.setAdapter(AccTypAdapter);
		System.out.println("--------=========---------");
	}
	
	/**
	 * spinner的监听
	 */
//	class selectItemListener implements OnItemSelectedListener{
//
//		@Override
//		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
//				long arg3) {
//		
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void onNothingSelected(AdapterView<?> arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//		
//	}
}
