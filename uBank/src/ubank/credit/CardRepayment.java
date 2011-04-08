package ubank.credit;

import java.util.ArrayList;
import java.util.List;

import ubank.base.GeneralActivity;
import ubank.main.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CardRepayment extends GeneralActivity {

	private List<String> mGroupArray;
	private List<List<String>> mChildArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addLayout(R.layout.expandablelistview);

		ExpandableListView ev = (ExpandableListView) findViewById(R.id.expandList);

		mGroupArray = new ArrayList<String>();
		mChildArray = new ArrayList<List<String>>();
		// 模拟接收到数据
		String[] strings = new String[] { "11", "22", "33" };

		mGroupArray.add("已绑定信用卡还款");
		List<String> itemArray = new ArrayList<String>();
		for (String string : strings) {
			itemArray.add(string);
		}
		mChildArray.add(itemArray);

		ev.setAdapter(new ExpandableAdapter(this));
	}

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
