package com.hebin.file.explorer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hebin.file.util.MimeUtil;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileAdapter extends BaseAdapter {
	private List<File> mFiles;
	private Context    mContext;
	public FileAdapter(Context context){
		if(context == null)
			throw new IllegalArgumentException("param cann't be null!");
		mContext = context;
		mFiles   = new ArrayList<File>();
	}
	
	public void fillData(List<File> files){
		if(files == null)
			return;
		mFiles.clear();
		mFiles.addAll(files);
		
	}
	
	public void addData(int index, File f){
		if(f == null)
			return;
		if(index == -1 || index > mFiles.size())
			mFiles.add(f);
		else
			mFiles.add(index, f);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mFiles.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mFiles.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(contentView == null)
			contentView = View.inflate(mContext, R.layout.layout_file_list_item, null);
		File file = (File)getItem(position);
		TextView tx = (TextView)contentView.findViewById(R.id.fileitem_filename_text);
		tx.setText(file.getName());
		
		tx = (TextView)contentView.findViewById(R.id.fileitem_lastdate_text);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		tx.setText(sdf.format(file.lastModified()));
		
		tx = (TextView)contentView.findViewById(R.id.fileitem_filesize_text);
		if(file.isDirectory())
			tx.setVisibility(View.INVISIBLE);
		else
			tx.setText(file.length() + "B");
		ImageView iv = (ImageView)contentView.findViewById(R.id.fileitem_type_image);
		int last = file.getName().lastIndexOf(".");
		int id = 0;
		if(last < 0)
			id = MimeUtil.getTypeResource("");
		else
			id = MimeUtil.getTypeResource(file.getName().substring(last));
		iv.setBackgroundResource(id);
		
		return contentView;
	}

}
