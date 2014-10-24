package com.hebin.file.explorer;

import java.io.File;

import com.hebin.file.util.FileStackUtil;
import com.hebin.file.util.SortUtil;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener{
	private AutoCompleteTextView mUriText;
	private Button 		         mBtnBack;
	private FileStackUtil        mFileStack;
	private ListView			 mFileListView;
	private SortUtil			 mSortTool;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		if(savedInstanceState == null){
			mFileStack = new FileStackUtil();
			mSortTool  = new SortUtil();
			initData();
		}
	}
	
	private void initViews(){
		mUriText = (AutoCompleteTextView) findViewById(R.id.main_toolbar_edit_uri);
		mBtnBack = (Button) findViewById(R.id.main_toolbar_imgbtn_back);
		mBtnBack.setOnClickListener(this);
		
		mFileListView = (ListView) findViewById(R.id.main_list_files);
	}
	
	/**
	 * 初始化显示内容
	 */
	private void initData(){
		String sdPath = Environment.getExternalStorageDirectory().getPath();
		mFileStack.pushFullPath(new File(sdPath));
		mUriText.setText(sdPath);
		
		String[] files = new File(sdPath).list();
		if(files == null)
			return;
		mSortTool.sortStringArray(files);
		ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files);
		mFileListView.setAdapter(adap);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyUp(keyCode, event);
	}


}
