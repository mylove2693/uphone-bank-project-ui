package ubank.common;
import ubank.main.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Account_Select extends LinearLayout {
	/**
	 * 定义两个Spinner的对象来获取布局中的spinner, 分别是账户类型，账户号
	 */
	public Spinner AccTypSpinner;
	Spinner AccNumSpinner;
	String[] data;
	// public SimpleAdapter AccTypAdapter;
	ArrayAdapter<String> AccTypAdapter;
	ArrayAdapter<String> adapter;
	ArrayAdapter<String> AccNumAdapter;

	public Account_Select(Context context, AttributeSet attrs) {

		super(context, attrs);
		LayoutInflater.from(context).inflate(R.xml.account_select, this,
				true);
		// 获取两个spinner
		TextView d=(TextView)findViewById(R.id.AccTypText).findViewById(R.id.Text_View_18);
		d.setText("请选择账户的类型：");
		AccTypSpinner = (Spinner) findViewById(R.id.spinnerAccTyp)
				.findViewById(R.id.Spinner);
		TextView s =(TextView)findViewById(R.id.AccNumText).findViewById(R.id.Text_View_18);
		s.setText("请选择账户的帐号：");
		AccNumSpinner = (Spinner) findViewById(R.id.spinnerAccNum)
				.findViewById(R.id.Spinner);
		
	}

	public Account_Select(Context context) {
		this(context, null);
	}

	/**
	 * 通过外界给spinner加数据
	 */
	public void AddTypeData(String[] s) {
		// 设置下拉框的标题
		this.AccTypSpinner.setPrompt("请选择账户类型");
		// 为spiiner添加自己有数据的适配器
		this.AccTypSpinner.setAdapter(CreAda(s));

	}

	public void AddNumData(String[] s) {

		// 设置下拉框的标题
		this.AccNumSpinner.setPrompt("请选择账户类型");
		// 为spiiner添加自己有数据的适配器
		this.AccNumSpinner.setAdapter(CreAda(s));

	}

	/**
	 * 获取spinner的值
	 */
	public String getAccTypValue() {

		return this.AccTypSpinner.getSelectedItem().toString();
	}

	public String getAccNumValue() {

		return this.AccNumSpinner.getSelectedItem().toString();
	}

	// 创建一个有数据的适配器
	private ArrayAdapter<String> CreAda(String[] data) {
		/**
		 * 实例化一个适配器，为适配器添加样式 此处采用内部的
		 */
		adapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 向适配器中间加数据
		for (int i = 0; i < data.length; i += 1) {
			adapter.add(data[i]);
		}
		return adapter;
	}
	
}
