/** * IndexSearchBeanTest.java 
* Created on 2012-2-21 上午10:54:20 
*/

package com.wcs.Lucene.demo;

import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.store.LockObtainFailedException;
import org.junit.Test;

import com.wcs.base.util.LuceneUtil;

/** 
* <p>Project: btcbase</p> 
* <p>Title: IndexSearchBeanTest.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2012-2020.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/

public class IndexSearchBeanTest {
    /**
     * 测试创建索引
     * @throws CorruptIndexException
     * @throws LockObtainFailedException
     * @throws IOException
     */
    @Test
    public void testCreateIndex() throws CorruptIndexException, LockObtainFailedException, IOException {
        long startTime = System.currentTimeMillis();
        LuceneUtil luceneUtil = LuceneUtil.getInstance();
        luceneUtil.createIndex();
        long endTime = System.currentTimeMillis();

        System.out.println("cost: " + (endTime - startTime) + " seconds");
    }
    
    /**
     * 搜索
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void testSearch() throws IOException, ParseException {
        String searchStr = "总统";
        LuceneUtil luceneUtil = LuceneUtil.getInstance();
        luceneUtil.search(searchStr);
    }
}
