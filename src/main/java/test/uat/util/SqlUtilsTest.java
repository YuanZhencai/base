package test.uat.util;


import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.wcs.common.util.SqlUtils;

import test.uat.DBHandler;

import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Project: BTC</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:hujianguang@wcs-global.com">胡建光</a>
 */
public class SqlUtilsTest extends DBHandler {

    /**
     * <p>Description: 判断非法输入是否返回空值</p>
     *
     * @throws Exception
     */
    @Test
    public void testBuildInsertSql() throws Exception {

        Map<String, String> sqlMap = new HashMap<String, String>();

        String sql = SqlUtils.buildInsertSql("PS", sqlMap);
        assert StringUtils.isEmpty(sql);

        sqlMap.put("ID", "1");
        sqlMap.put("DEFUNCT_IND", "N");
        sql = SqlUtils.buildInsertSql("", sqlMap);
        assert StringUtils.isEmpty(sql);

        sql = SqlUtils.buildInsertSql(null, null);
        assert StringUtils.isEmpty(sql);

        sql = SqlUtils.buildInsertSql("PS", sqlMap);
        assert StringUtils.isNotEmpty(sql);

    }

    /**
     * <p>判断返回的sql是否组装正确</p>
     *
     * @throws Exception
     */
    @Test
    public void testSqlInsert() throws Exception {

        Map<String, String> sqlMap = new HashMap<String, String>();
        String id = String.valueOf(new Date().getTime());
        sqlMap.put("ID", id);
        sqlMap.put("SID", "N");
        sqlMap.put("PID", "N");
        String sql = SqlUtils.buildInsertSql("PS", sqlMap);
        assert StringUtils.isNotEmpty(sql);
        super.getStmt().addBatch(sql);
        super.getStmt().executeBatch();
        super.getStmt().clearBatch();

        super.getStmt().execute("select * from ps where id = '" + id + "'");
        ResultSet rs = super.getStmt().getResultSet();
        assert true == rs.next();
    }
}
