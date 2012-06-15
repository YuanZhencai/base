package com.wcs.common.util;

import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: aguang
 * Date: 12-6-14
 * Time: 上午11:18
 * To change this template use File | Settings | File Templates.
 */
public final class SqlUtils {

    public static String buildInsertSql(String tableName, Map<String, String> sqlMap) {

        if (StringUtils.isEmpty(tableName) || sqlMap.isEmpty()) {
            return null;
        }

        StringBuilder sqlBuff = new StringBuilder("INSERT INTO ");

        sqlBuff.append(tableName);

        sqlBuff.append(" (");

        Set<String> fieldSet = sqlMap.keySet();

        int length = fieldSet.size();

        int i = 0;
        for (String field : fieldSet) {

            sqlBuff.append(field);

            //去掉结尾的逗号
            sqlBuff.append(length != ++i ? "," :"");
        }

        sqlBuff.append(") VALUES (");

        i = 0;
        for (String field : fieldSet) {
            sqlBuff.append("'");
            sqlBuff.append(sqlMap.get(field));
            sqlBuff.append("'");

            //去掉结尾的逗号
            sqlBuff.append(length != ++i ? "," :"");
        }

        sqlBuff.append(")");

        return sqlBuff.toString();

    }

}
