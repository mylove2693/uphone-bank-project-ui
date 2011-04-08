package ubank.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ubank.main.BankMain;
import ubank.main.FinanceAss;
import ubank.main.R;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class GeneralListActivity extends ListActivity implements IGeneralActivity{
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
	
	//向ListView中添加数据的适配器
	private SimpleAdapter adapter = null;
	
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);
        //获取图片对象
        btnback = (ImageView)findViewById(R.id.returnToPre);
        btnbank = (ImageView)findViewById(R.id.btnMain);
        btnhelper = (ImageView)findViewById(R.id.btnHelper);
        
        //获取TextView对象
        tvClassFirst = (TextView)findViewById(R.id.class_first);
        tvClassSecond = (TextView)findViewById(R.id.class_second);
        tvClassThird = (TextView)findViewById(R.id.class_third);
        tvClassFour = (TextView)findViewById(R.id.class_four);
        
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
    	LinearLayout line = (LinearLayout)findViewById(R.id.ListLinearLayout1);
    	View view=getLayoutInflater().inflate(layout, null);
    	line.addView(view);
    }
    
    //在ListView下面添加新的布局
    public void addLayoutBlow(int layout){
    	LinearLayout line = (LinearLayout)findViewById(R.id.ListLinearLayout2);
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
    
    //图片--文字--图片 的适配器
    public SimpleAdapter createImg_Text_ImgAdapter(String[] value){
		ArrayList<HashMap<String, Object>> mainlist = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> paylist1 = null;
		for (int i = 0; i < value.length; i++) {
			paylist1 = new HashMap<String, Object>();
			paylist1.put("listimg1", R.drawable.trans_main);
			paylist1.put("payment_list", value[i]);
			paylist1.put("listimg2", R.drawable.righticon);
			mainlist.add(paylist1);
		}

		adapter = new SimpleAdapter(this, mainlist,
				R.xml.img_text_img, new String[]{ "listimg1", "payment_list",
				"listimg2"}, new int[] { R.id.before_img,
						R.id.data_text, R.id.after_img });

		return adapter;
	}
    
    //文字--文字--图片 的适配器
    public SimpleAdapter createText_Text_Img(String[] name,String[] value){
    	ArrayList<HashMap<String, Object>> mainlist = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < value.length; i++) {
			HashMap<String, Object> paylist1 = new HashMap<String, Object>();
			paylist1.put("text1",name[i]);
			paylist1.put("text2", value[i]);
			paylist1.put("Rimg", R.drawable.righticon);
			mainlist.add(paylist1);
		}
		adapter = new SimpleAdapter(this, mainlist,
				R.xml.text_text_img, new String[] { "text1", "text2",
						"Rimg" }, new int[] { R.id.data_text1,
						R.id.data_text2, R.id.Right_img });
		return adapter;
    }
    
  //文字--文字--部分有图片 的适配器
    public SimpleAdapter createText_Text_Img(String[] name,String[] value,List<Integer> index){
    	ArrayList<HashMap<String, Object>> mainlist = new ArrayList<HashMap<String, Object>>();
    	HashMap<String, Object> paylist1 = null;
		for (int i = 0; i < value.length; i++) {
			paylist1 = new HashMap<String, Object>();
			paylist1.put("text1",name[i]);
			paylist1.put("text2", value[i]);
			if (index.contains(Integer.valueOf(i))) {
				paylist1.put("Rimg", R.drawable.righticon);
			}else{
				paylist1.put("Rimg", null);
			}
			mainlist.add(paylist1);
		}
		adapter = new SimpleAdapter(this, mainlist,
				R.xml.text_text_img, new String[] { "text1", "text2",
						"Rimg" }, new int[] { R.id.data_text1,
						R.id.data_text2, R.id.Right_img });
		return adapter;
    }
	
    //文字--文字 的适配器
    public SimpleAdapter createText_Text(String[] name,String[] value){
    	ArrayList<HashMap<String, Object>> mainlist = new ArrayList<HashMap<String, Object>>();
    	HashMap<String, Object> paylist1 = null;
    	for (int i = 0; i < value.length; i++) {
			paylist1 = new HashMap<String, Object>();
			paylist1.put("text1",name[i]);
			paylist1.put("text2", value[i]);
			mainlist.add(paylist1);
		}
		adapter = new SimpleAdapter(this, mainlist,
				R.xml.bluetext_text, new String[] { "text1", "text2"}, new int[] { R.id.blue_data_txt1,
						R.id.data_txt2});
		return adapter;
    }
    
    //文字--图片 的适配器
    public SimpleAdapter createText_Img(String[] value){
    	ArrayList<HashMap<String, Object>> mainlist = new ArrayList<HashMap<String, Object>>();
    	HashMap<String, Object> paylist1 = null;
    	for (int i = 0; i < value.length; i++) {
			paylist1 = new HashMap<String, Object>();
			paylist1.put("text1",value[i]);
			paylist1.put("img2", value[i]);
			mainlist.add(paylist1);
		}
		adapter = new SimpleAdapter(this, mainlist,
				R.xml.text_img, new String[] { "text1", "img2"}, new int[] { R.id.left_txt,
						R.id.only_img});
		return adapter;
    }
}
