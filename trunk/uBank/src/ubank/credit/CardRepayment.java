package ubank.credit;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ubank.base.GeneralActivity;
import ubank.common.Account_Select;
import ubank.enum_type.EAccType;
import ubank.enum_type.EOperation;
import ubank.helper.EHelper;
import ubank.main.BankMain;
import ubank.main.R;
import ubank.webservice.ConnectWs;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CardRepayment extends GeneralActivity {

	private List<String> mGroupArray;
	private List<List<String>> mChildArray;
	private Button btnNext;
	private EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayout(R.layout.cc_card_repayment);

		initializeData();// 初始化数据

		ExpandableListView ev = (ExpandableListView) findViewById(R.id.expandList);
		ev.setAdapter(new ExpandableAdapter(this));
		ev.setOnChildClickListener(onClickListener);
		btnNext.setOnClickListener(btnOnClick);

	}

	private void initializeData() {
		// TODO 初始化数据
		tvClassFirst.setVisibility(View.VISIBLE);
		tvClassFirst.setText("首页>");
		setListener(tvClassFirst, this, BankMain.class);
		tvClassSecond.setVisibility(View.VISIBLE);
		tvClassSecond.setText("信用卡>");
		setListener(tvClassSecond, this, CreditCardMain.class);
		tvClassThird.setVisibility(View.VISIBLE);
		tvClassThird.setText("信用卡还款");

		mGroupArray = new ArrayList<String>();
		mChildArray = new ArrayList<List<String>>();
		// 接收数据
		JSONObject jsonObj = ConnectWs.connect(this, EAccType.NULL,
				EOperation.GET_BIND_CREDIT_CARD, "");
		List<String> lstStr = EHelper.toList(jsonObj);

		mGroupArray.add(getResources().getString(R.string.cc_isbindcc));// 已绑定信用卡号
		List<String> itemArray = new ArrayList<String>();
		for (String string : lstStr) {
			itemArray.add(string);
		}
		mChildArray.add(itemArray);

		((TextView) findViewById(R.id.cc_tv_otherCC).findViewById(
				R.id.Text_View_18)).setText(R.string.cc_other_cc_repayment);// 其他信用卡还款
		((TextView) findViewById(R.id.cc_tv_cc_num).findViewById(
				R.id.Text_View_18)).setText(R.string.cc_ccNo);// 信用卡号
		btnNext = (Button) findViewById(R.id.cc_btn_next).findViewById(
				R.id.button);
		btnNext.setText(R.string.cc_next);// 下一步
		et = (EditText) findViewById(R.id.cc_et_cc).findViewById(R.id.et_acc);
	}

	// 按钮
	private OnClickListener btnOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(CardRepayment.this, CreditCardInfo.class);
			intent.putExtra("creditcard", et.getText().toString().trim());
			startActivity(intent);
		}

	};

	

	// 字节点的监听器
	private OnChildClickListener onClickListener = new OnChildClickListener() {

		@Override
		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
			// TODO Auto-generated method stub
			TextView tv = (TextView) v;
			Intent intent = new Intent();
			intent.putExtra("creditcard", tv.getText());
			intent.setClass(CardRepayment.this, CreditCardInfo.class);
			startActivity(intent);
			return false;
		}

	};

	// 内部类，Expandable适配器
	public class ExpandableAdapter extends BaseExpandableListAdapter {
		Activity activity;

		public ExpandableAdapter(Activity a) {
			activity = a;
		}

		public Object getChild(int groupPosition, int childPosition) {
			return mChildArray.get(groupPosition).get(childPosition);
		}

		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		public int getChildrenCount(int groupPosition) {
			return mChildArray.get(groupPosition).size();
		}

		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			String string = mChildArray.get(groupPosition).get(childPosition);
			return getGenericView(string);
		}

		public Object getGroup(int groupPosition) {
			return mGroupArray.get(groupPosition);
		}

		public int getGroupCount() {
			return mGroupArray.size();
		}

		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			String string = mGroupArray.get(groupPosition);
			return getGenericView(string);
		}

		public TextView getGenericView(String string) {
			AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT, 64);

			View ll = getLayoutInflater().inflate(R.xml.text_view_18, null);
			TextView textView = (TextView) ll.findViewById(R.id.Text_View_18);
			textView.setLayoutParams(layoutParams);
			textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			textView.setText(string);
			textView.setPadding(50, 0, 0, 0);
			return textView;
		}

		public boolean hasStableIds() {
			return false;
		}

		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}
}
