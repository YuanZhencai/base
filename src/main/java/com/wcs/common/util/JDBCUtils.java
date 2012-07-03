package com.wcs.common.util;

import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>Project: BTC</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:hujianguang@wcs-global.com">胡建光</a>
 */
@Singleton
//@TransactionAttribute(value = TransactionAttributeType.NEVER)
@TransactionManagement(TransactionManagementType.BEAN)
public class JDBCUtils {

    @Resource(name = "BTCBASE")
    private DataSource dataSource;

    private Connection conn;

    private Statement stmt;

    private PreparedStatement ps;

    public void initConn(){
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            this.destroyConn();
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void destroyConn(){
        try {
            if (null != conn || !this.conn.isClosed()) {
                conn.commit();
                stmt = null;
                ps = null;
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p></p>
     * @param sql
     * @return
     */
    public PreparedStatement getPreparedStatement(String sql) {

        System.out.println(null == this.dataSource ? "aguang...........................true" : "aguang.....................false");

        //如果sql为空对象，则返回不做后续处理
        if(StringUtils.isBlank(sql)){
            return null;
        }

        try {
            if(null == this.conn || this.conn.isClosed() ){
                this.initConn();
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        try {

            if(null != ps){
                ps.clearParameters();
                ps.clearBatch();
            }

            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return ps;
    }

    public Statement getStatement() {

        System.out.println(null == this.dataSource ? "aguang...........................true" : "aguang.....................false");

        try {
            if(null == this.conn || this.conn.isClosed()){
                this.initConn();
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return stmt;
    }

    /**
     * <p>回滚当前数据</p>
     */
    public void rollData(){
        if(null != this.conn){
            try {
                this.conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
