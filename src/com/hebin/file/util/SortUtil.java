package com.hebin.file.util;

import java.util.Arrays;
import java.util.Comparator;

public class SortUtil {
	private SortComparator mComp;
	public SortUtil(){
		mComp = new SortComparator();
	}
	
	public void sortStringArray(String[] array){
		Arrays.sort(array, mComp);
	}

	class SortComparator implements Comparator<String>{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			if(arg0 == null && arg1 == null)
				return 0;
			
			if(arg0 == null)
				return -1;
			if(arg1 == null)
				return 1;
			return arg0.compareToIgnoreCase(arg1);
//			return arg0.compareTo(arg1);
		}
		
	}
}
