package com.hebin.file.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * �ļ�·��ջ����
 * @author �α�
 *
 */
public class FileStackUtil {
	private List<File> mPath;
	public FileStackUtil(){
		mPath = new ArrayList<File>();
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
	 * ���ļ�ջ��֯��һ������·��
	 * @return
	 */
	public String combinePath(){
		if(mPath.size() == 0)
			return null;
		String path = "";
		for(int i=0; i<mPath.size(); i++)
			path += "/" + mPath.get(i).getName();
		return path;
	}
}
