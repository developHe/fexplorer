package com.hebin.file.util;

import java.util.HashMap;
import java.util.Map;

import com.hebin.file.explorer.R;

/**
 * 文件类型对应表
 */
public class MimeUtil {
	private static final String[][] MIME_MapTable={
		    //{后缀名，自定义类型，    MIME类型}
			//视频
		    {".3gp",  "video",  "video/3gpp"},
		    {".asf",  "video",  "video/x-ms-asf"},
		    {".avi",  "video",  "video/x-msvideo"},
		    {".m4u",  "video",  "video/vnd.mpegurl"},
		    {".m4v",  "video",  "video/x-m4v"},    
		    {".mov",  "video",  "video/quicktime"},
		    {".mp4",  "video",  "video/mp4"},
		    {".mpe",  "video",  "video/mpeg"},
		    {".mpeg", "video",  "video/mpeg"},    
		    {".mpg",  "video",  "video/mpeg"},    
		    {".mpg4", "video",  "video/mp4"}, 
		    {".rmvb", "video",  "video/x-pn-realaudio"},
		    //音频
		    {".m3u",  "audio",  "audio/x-mpegurl"},
		    {".m4a",  "audio",  "audio/mp4a-latm"},
		    {".m4b",  "audio",  "audio/mp4a-latm"},
		    {".m4p",  "audio",  "audio/mp4a-latm"},
		    {".mp2",  "audio",  "audio/x-mpeg"},
		    {".mp3",  "audio",  "audio/x-mpeg"},
		    {".mpga", "audio",  "audio/mpeg"},
		    {".wav",  "audio",  "audio/x-wav"},
		    {".wma",  "audio",  "audio/x-ms-wma"},
		    {".wmv",  "audio",  "audio/x-ms-wmv"},
		    {".ogg",  "audio",  "audio/ogg"},
		    
		    //图片
		    {".bmp",  "image",  "image/bmp"},
		    {".gif",  "image",  "image/gif"},
		    {".jpeg", "image",  "image/jpeg"},
		    {".jpg",  "image",  "image/jpeg"},
		    {".png",  "image",  "image/png"},
		    {".tif",  "image",  "image/tiff"},
		    {".tiff", "image",  "image/tiff"},
		    
		    //文本
		    {".c",     "text",  "text/plain"},
		    {".conf",  "text",  "text/plain"},
		    {".cpp",   "text",  "text/plain"},
		    {".h",     "text",  "text/plain"},
		    {".htm",   "text",  "text/html"},
		    {".html",  "text",  "text/html"},
		    {".log",   "text",  "text/plain"},
		    {".rc",    "text",  "text/plain"},
		    {".sh",    "text",  "text/plain"},
		    {".java",  "text",  "text/plain"},
		    {".prop",  "text",  "text/plain"},
		    {".txt",   "text",  "text/plain"},
		    {".xml",   "text",  "text/plain"},
		    
		    //windows产品
		    {".doc",   "doc",   "application/msword"},
		    {".docx",  "doc",   "application/msword"},
		    {".msg",   "other", "application/vnd.ms-outlook"},
		    {".wps",   "doc",   "application/vnd.ms-works"},
		    {".pps",   "ppt",   "application/vnd.ms-powerpoint"},
		    {".ppt",   "ppt",   "application/vnd.ms-powerpoint"},
		    
		    //压缩包
		    {".gtar",  "zip",  "application/x-gtar"},
		    {".gz",    "zip",  "application/x-gzip"},
		    {".rar",   "zip",  "application/x-rar-compressed"},
		    {".z",     "zip",  "application/x-compress"},
		    {".zip",   "zip",  "application/zip"},
		    {".tar",   "zip",  "application/x-tar"},    
		    {".tgz",   "zip",  "application/x-compressed"},
		    
		    //adobe
		    {".pdf",   "pdf",  "application/pdf"},
		    
		    //安装包
		    {".apk",   "apk",  "application/vnd.android.package-archive"},
		    
		    //其他
		    {".bin",   "other", "application/octet-stream"},
		    {".class", "other", "application/octet-stream"},
		    {".exe",   "other", "application/octet-stream"},
		    {".jar",   "other", "application/java-archive"},
		    {".js",    "other", "application/x-javascript"},
		    {".mpc",   "other", "application/vnd.mpohun.certificate"},
		    {".rtf",   "other", "application/rtf"},
		    {"",       "other", "*/*"}
	};
	
	private final static TypeResourceStruct mResTable[] = {
		new TypeResourceStruct("video", R.drawable.icon_file_video_xml),
		new TypeResourceStruct("audio", R.drawable.icon_file_music_xml),
		new TypeResourceStruct("image", R.drawable.icon_file_pic_xml),
		new TypeResourceStruct("text", R.drawable.icon_file_txt_xml),
		new TypeResourceStruct("doc", R.drawable.icon_file_doc_xml),
		new TypeResourceStruct("ppt", R.drawable.icon_file_ppt_xml),
		new TypeResourceStruct("zip", R.drawable.icon_file_zip_xml),
		new TypeResourceStruct("pdf", R.drawable.icon_file_pdf_xml),
		new TypeResourceStruct("apk", R.drawable.icon_file_apk_xml),
		new TypeResourceStruct("other", R.drawable.icon_file_other_xml)
	};
	static class TypeResourceStruct{
		     String mtype;
		     int    mresId;
		public TypeResourceStruct(String type, int res){
			mtype  = type;
			mresId = res;
		}
	}
	
	private static Map<String , String[]> mMimeHash;
	public static void initMime(){
		mMimeHash = new HashMap<String, String[]>();
		for(int i=0; i<MIME_MapTable.length; i++)
			mMimeHash.put(MIME_MapTable[i][0], MIME_MapTable[i]);
		
	}
	
	public static int getTypeResource(String extName){
		if(mMimeHash == null)
			throw new ExceptionInInitializerError("一定要先调用initMime()进行初始化");
		if(extName==null)
			return -1;
		String[] type = mMimeHash.get(extName.toLowerCase());
		for(int i=0; i<mResTable.length; i++){
			if( mResTable[i].mtype.equals(type[1]) )
				return mResTable[i].mresId;
		}
		return -1;
	}
}
