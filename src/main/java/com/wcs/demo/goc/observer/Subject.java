/** * Subject.java 
 * Created on 2014年5月9日 下午4:45:41 
 */

package com.wcs.demo.goc.observer;


/** 
* <p>Project: btcbase</p> 
* <p>Title: Subject.java</p> 
* <p>Description: </p> 
* <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a> 
*/
public interface Subject {
	
	/* 增加观察者 */
	public void add(Observer observer);

	/* 删除观察者 */
	public void del(Observer observer);

	/* 通知所有的观察者 */
	public void notifyObservers();

	/* 自身的操作 */
	public void operation();

}
