package com.wcs.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.FieldInfo.IndexOptions;

import com.wcs.lucene.demo.FileSearchConstant;

public class File2DocumentUtils {
    private static File2DocumentUtils file2DocumentUtils = null;

    public static File2DocumentUtils getInstance() {
        if (file2DocumentUtils == null) {
            file2DocumentUtils = new File2DocumentUtils();
        }

        return file2DocumentUtils;
    }

    /**
     * 文件: File转换成Document
     * @param fileIndex
     * @return
     */
    public Document file2Document(File file) {
        Document doc = new Document();
        Field pathField = new Field(FileSearchConstant.PATH, file.getPath(), Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS);
        pathField.setIndexOptions(IndexOptions.DOCS_ONLY);
        doc.add(pathField);

        Field contentField = new Field(FileSearchConstant.CONTENTS, readFileContent(file), Field.Store.YES, Field.Index.ANALYZED);
        contentField.setIndexOptions(IndexOptions.DOCS_ONLY);
        doc.add(contentField);

        return doc;
    }

    /**
     * 读取文件内容
     * @param file
     * @return
     */
    private String readFileContent(File file) {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return stringBuffer.toString();
    }
}
