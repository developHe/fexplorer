package com.hebin.file.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件路径栈工具
 * @author 何斌
 *
 */
public class FileStackUtil {
	private List<File> mPath;
	public FileStackUtil(){
		mPath = new ArrayList<File>();
		mPath.add(new File(File.separator));
	}
	
	public File pop(){
		if(mPath.size() == 0)
			return null;
		File f = mPath.get(mPath.size() - 1);
		mPath.remove(mPath.size() - 1);
		return f;
	}
	
	public File getTop(){
		if(mPath.size() == 0)
			return null;
		return mPath.get(mPath.size() - 1);
	}
	
	
	public void pushFullPath(File fullFile){
		String path = fullFile.getAbsolutePath();
		String [] paths = path.split(File.separator);
		path ="";
		for(int i=0; i<paths.length; i++){
			if(paths[i].length() == 0)
				continue;
			path += File.separator + paths[i];
			push(new File(path));
		}
	}
	
	public void push(File f){
		mPath.add(f);
	}
	
	public void clear(){
		mPath.clear();
	}
	
	/**
	 * 把文件栈组织成一条完整路径
	 * @return
	 */
	public String combinePath(){
		if(mPath.size() == 0)
			return null;
		if(mPath.size() == 1)
			return File.separator;
		String path = "";
		for(int i=0; i<mPath.size()-1; i++)
			path += mPath.get(i).getName() + File.separator;
		path += mPath.get(mPath.size() - 1).getName();
		return path;
	}
}
