/** * LuceneUtil.java 
* Created on 2012-2-21 下午3:16:20 
*/

package com.wcs.base.util;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

import com.wcs.lucene.demo.FileSearchConstant;

/** 
* <p>Project: btcbase</p> 
* <p>Title: LuceneUtil.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2012-2020.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/

public class LuceneUtil {
    private static LuceneUtil luceneUtil = null;
    private File fileIndex = new File(FileSearchConstant.FILE_INDEX);   // 索引存放的目录
    private File fileDir = new File(FileSearchConstant.FILE_DIR);       // 需要建立索引的文件目录
    
    public static LuceneUtil getInstance() {
        if (luceneUtil == null) {
            luceneUtil = new LuceneUtil();
        }
        
        return luceneUtil;
    }

    /**
     * 创建索引
     * IndexWriter 是用来操作（增、删、改）索引库的
     * @throws IOException 
     * @throws LockObtainFailedException 
     * @throws CorruptIndexException 
     */
    public void createIndex() throws CorruptIndexException, LockObtainFailedException, IOException {
        boolean isCreate = true;
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_35, new SmartChineseAnalyzer(Version.LUCENE_35)); // 建立索引的配置类
        setModel(isCreate, indexWriterConfig);  // 设置我们的解析器是新建还是追加更新
        
        // 创建索引库
        IndexWriter indexWriter = new IndexWriter(FSDirectory.open(fileIndex), indexWriterConfig);
        writerFileToIndex(fileDir, indexWriter);
        indexWriter.forceMerge(1);
        indexWriter.close();
    }
    
    /**
     * 搜索
     * @param searchStr
     * @throws IOException
     * @throws ParseException
     */
    public void search(String searchStr) throws IOException, ParseException {
       
        Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_35);
        
        // 1，把要搜索的文本解析为 Query
        QueryParser queryParser = new QueryParser(Version.LUCENE_35, FileSearchConstant.CONTENTS, analyzer);
        Query query = queryParser.parse(searchStr);
        
        // 2、进行查询
        IndexReader indexReader = IndexReader.open(FSDirectory.open(new File(FileSearchConstant.FILE_INDEX)));
        IndexSearcher searcher = new IndexSearcher(indexReader);
        TopDocs topDocs = searcher.search(query, 10000);
        System.out.println("总共有【" + topDocs.totalHits + "】条匹配结果");
        
        // 3，打印结果
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            int docSn = scoreDoc.doc;                                       // 文档内部编号
            Document doc = searcher.doc(docSn);                             // 根据编号取出相应的文档
            String contents = doc.get(FileSearchConstant.CONTENTS);
            String path = doc.get(FileSearchConstant.PATH);
            
            // 打印出文档信息
            System.out.println(contents);
            System.out.println(path);
       }
    }

    /**
     * 读取文件写到索引库
     * @param fileDir
     * @param fileIndex
     * @throws CorruptIndexException
     * @throws LockObtainFailedException
     * @throws IOException
     */
    private void writerFileToIndex(File fileDir, IndexWriter indexWriter) throws CorruptIndexException, IOException {
        if (fileDir.canRead()) {
            if (fileDir.isDirectory()) {
                String[] listFiles = fileDir.list();
                for (String file : listFiles) {
                    writerFileToIndex(new File(fileDir, file), indexWriter);
                }
            } else {
                File2DocumentUtils file2DocumentUtils = File2DocumentUtils.getInstance();
                Document doc = file2DocumentUtils.file2Document(fileDir);
                if (indexWriter.getConfig().getOpenMode() == OpenMode.CREATE) {
                    indexWriter.addDocument(doc);
                } else if (indexWriter.getConfig().getOpenMode() == OpenMode.CREATE_OR_APPEND) {
                    indexWriter.updateDocument(new Term(FileSearchConstant.PATH, fileDir.getPath()), doc);
                }
            }
        }
    }
    
    /**
     * 设置解析器新建还是追加更新
     * @param isCreate
     * @param indexWriterConfig
     */
    private void setModel(boolean isCreate, IndexWriterConfig indexWriterConfig) {
        if (isCreate) {
            indexWriterConfig.setOpenMode(OpenMode.CREATE);
        } else {
            indexWriterConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
        }
    }
}
