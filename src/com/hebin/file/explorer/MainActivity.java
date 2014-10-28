package com.hebin.file.explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.hebin.file.util.FileStackUtil;
import com.hebin.file.util.MimeUtil;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener, OnItemClickListener{
	private AutoCompleteTextView mUriText;
	private Button 		         mBtnBack;
	private FileStackUtil        mFileStack;
	private ListView			 mFileListView;
	private SortUtil			 mSortTool;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MimeUtil.initMime();

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
		mFileListView.setOnItemClickListener(this);
	}
	
	/**
	 * 初始化显示内容
	 */
	private void initData(){
		String sdPath = Environment.getExternalStorageDirectory().getPath();
		mFileStack.pushFullPath(new File(sdPath));
		mUriText.setText(sdPath);
		File ff = new File(sdPath);
		updateFileViews(ff);
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
		if(keyCode == KeyEvent.KEYCODE_BACK){
			File file = mFileStack.getTop();
			String name = file.getAbsolutePath();
			if(name.equals(File.separator))
				return super.onKeyUp(keyCode, event);
			mFileStack.pop();
			file = mFileStack.getTop();
			mUriText.setText(file.getAbsolutePath());
			updateFileViews(file);
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	private void updateFileViews(File f){
		String[] files = f.list(new FilenameFilter());
		if(files == null)
			files = new String[0];
		mSortTool.sortStringArray(files);
		FileAdapter fileAdap = new FileAdapter(this);
		List<File> listF = new ArrayList<File>();
		for(int i=0; i<files.length; i++)
			listF.add(new File(f.getAbsolutePath() + "/" + files[i]));
		fileAdap.fillData(listF);
//		ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files);
		mFileListView.setAdapter(fileAdap);
		mUriText.setText(f.getAbsolutePath());
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		String fname = ((File) mFileListView.getAdapter().getItem(arg2)).getName();
		String path  = mUriText.getText().toString();
		File file = new File(path+"/"+fname);
		if(file.isDirectory()){
			updateFileViews(file);
			mFileStack.push(file);
		}
	}

	class FilenameFilter implements java.io.FilenameFilter{

		@Override
		public boolean accept(File arg0, String arg1) {
			// TODO Auto-generated method stub
			return true;
		}
		
	}

}
