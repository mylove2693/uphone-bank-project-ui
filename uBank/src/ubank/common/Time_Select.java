package ubank.common;

import java.util.Calendar;

import ubank.base.Listener;
import ubank.main.R;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Time_Select extends LinearLayout {
	OnDateSetListener onDateSetListener1, onDateSetListener2;
	String StartTime = null, EndTime;
	Button start, end, btn = null;
	Activity fromActivity = null;
	@SuppressWarnings("unchecked")
	Class toActivity = null;
	Intent intent = new Intent();
	  
	// 通过一个类来获取当前的时间的年月日
	final Calendar calendar = Calendar.getInstance();
	final int year = calendar.get(Calendar.YEAR);
	final int month = calendar.get(Calendar.MONTH);
	final int day = calendar.get(Calendar.DAY_OF_MONTH);
	Context con;

	public Time_Select(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.con = context;
		LayoutInflater.from(context).inflate(R.xml.time_select, this, true);
		
		
		//提示栏
		TextView information_textview=(TextView) findViewById(R.id.information_text).findViewById(
				R.id.Text_View_18);
		information_textview.setText("请选择你要查询的时间区间：");
		
		TextView from_textview = (TextView) findViewById(R.id.from_text).findViewById(
				R.id.Text_View_18);
		from_textview.setText("从：");

		
		start = (Button) findViewById(R.id.Start_Time);
		start.setBackgroundResource(R.drawable.selection);
		start.setText("开始时间");

		TextView to_textview = (TextView) findViewById(R.id.to_text).findViewById(
				R.id.Text_View_18);
		to_textview.setText("到：");

		end = (Button) findViewById(R.id.End_Time);
		end.setBackgroundResource(R.drawable.selection);
		end.setText("结束时间");
		// 时间按钮监听事件
		/**
		 * 第一个 开始时间
		 * */
		onDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				monthOfYear += 1;
				StartTime = year + "-" + monthOfYear + "-" + dayOfMonth;
				start.setText(StartTime);
				intent.putExtra("start_time", StartTime);
			}
		};
		/**
		 * 第二个 结束时间
		 * */
		onDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				monthOfYear += 1;
				EndTime = year + "-" + monthOfYear + "-" + dayOfMonth;
				end.setText(EndTime);
				intent.putExtra("end_time", EndTime);
			}
		};

		// 弹出时间对话框
		/* 开始时间的监听 */
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("start time-------");
				// TODO Auto-generated method stub
				DatePickerDialog datePickerDialog = new DatePickerDialog(con,
						onDateSetListener1, year, month, day);
				datePickerDialog.show();
			}
		});
		/* 结束时间的监听 */
		end.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.out.println("End time-------");
				// TODO Auto-generated method stub
				DatePickerDialog datePickerDialog = new DatePickerDialog(con,
						onDateSetListener2, year, month, day);
				datePickerDialog.show();

			}
		});
		// 查询按钮上的监听
		btn = (Button) findViewById(R.id.query).findViewById(R.id.button);
		btn.setText("查询");

	}
	
	@SuppressWarnings("unchecked")
	public void setButtonListener(Activity fromActivity,Class toActivity){
		this.fromActivity = fromActivity;
		this.toActivity = toActivity;
		btn.setOnClickListener(new Listener(intent,fromActivity,toActivity));
	}
	
	//设置传递的参数
	public void setParams(String[] name,String[] value){
		for(int i = 0;i < name.length;i++){
			intent.putExtra(name[i], value[i]);
		}
	}
	
}
