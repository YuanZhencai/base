/**
 * 
 */
package com.wcs.base.camel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * <p>Project: cameServer</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yansong@wcs-global.com">$Author$</a>
 */
public class FileUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	       try  {  
	    	   File   directory =   new   File(".");   
	    	   System.out.println(directory.getCanonicalPath());  
	    	   saveFile(getFile("test.txt"),"2.txt");
	       }catch  (Exception  e)  {  
	           System.out.println("复制单个文件操作出错");  
	           e.printStackTrace();  
	 
	       }  
	}
	
	public static byte[] getFile(String filePathName) {
        byte[]  file = null;
        try {
        	File  oldfile  =  new  File(filePathName);  
	        if  (oldfile.exists())  { 
	     	   System.out.println("********************FileUtil:file get ok********************");
	            InputStream  inStream  =  new  FileInputStream(filePathName);  
	            file  =  new  byte[(int) oldfile.length()];  
	            inStream.read(file);
	            inStream.close();  
	        }else{
	        	System.out.println("********************FileUtil:file not exits********************");
	        }  
        } catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}
	
	public static boolean saveFile(byte[] file,String filePathName) {
        boolean  bol = false;
    	try  {
           if  (file != null)  {  //文件存在时  
               FileOutputStream  fs  =  new  FileOutputStream(filePathName);  
               fs.write(file,  0,  file.length);
               bol = true;
               fs.close();
               System.out.println("********************FileUtil:file save ok********************");
           } else{
        	   System.out.println("********************FileUtil:file save NG********************");
           } 
           
       } catch  (Exception  e)  {  
           e.printStackTrace();  
 
       }  
		return bol;
	}

}
