/** * FileCache.java 
 * Created on 2014年5月5日 下午4:15:03 
 */

package com.wcs.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.util.JSFUtils;


/** 
* <p>Project: btcbase</p> 
* <p>Title: FileCacheService.java</p> 
* <p>Description: </p> 
* <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a> 
*/
@Stateless
public class FileCacheService implements CacheInterface {
	private static Logger logger = LoggerFactory.getLogger(FileCacheService.class);

	private static final String CACHE_FOLDER = JSFUtils.getRealPath() + "\\faces\\cache\\";

	@Override
	public void putCache(String key, Cache obj) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream cacheFileOs = new FileOutputStream(CACHE_FOLDER + key);
			ObjectOutputStream os = new ObjectOutputStream(cacheFileOs);
			os.writeObject(obj);
			os.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public Cache getCache(String key) {
		// TODO Auto-generated method stub
		Cache cache;
		ObjectInputStream ois = null;
		try {
			FileInputStream cacheFileIs = new FileInputStream(CACHE_FOLDER + key);// ("foo.ser");
			ois = new ObjectInputStream(cacheFileIs);
			cache = (Cache) ois.readObject();
			return cache;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

	@Override
	public void removeCache(String key) {
		File file = new File(CACHE_FOLDER + key);
		if (file != null && file.isFile()) {
			file.delete();
		}
	}

	@Override
	public void clear() {
		File cacheFolder = new File(CACHE_FOLDER);
		if (cacheFolder.isDirectory()) {
			File[] files = cacheFolder.listFiles();
			for (File file : files) {
				if (file.isFile()) {
					file.delete();
				}
			}
		}

	}

	@Override
	public void bulkRemove(String prefix) {
		File cacheFolder = new File(CACHE_FOLDER);
		if (cacheFolder.isDirectory()) {
			File[] files = cacheFolder.listFiles();
			for (File file : files) {
				if (file.isFile() && file.getName().startsWith(prefix)) {
					file.delete();
				}
			}
		}
	}

	public static void main(String[] args) {
		FileCacheService service = new FileCacheService();
//		Cache obj = new Cache();
//		obj.setValue("liqing1_10201_10202");
//		service.putCache("liqing1_10201_10202", obj );
//		Cache cache = service.getCache("liqing1_10201_10202");
//		System.out.println("[cache]" + cache.getValue());
		service.removeCache("liqing1_10201_10202");
	}
}
