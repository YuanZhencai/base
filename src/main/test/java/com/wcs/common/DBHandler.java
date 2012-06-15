package com.wcs.common;

import org.junit.After;
import org.junit.Before;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * <p>Project: BTC</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:hujianguang@wcs-global.com">胡建光</a>
 */
public abstract class DBHandler {

    private static final String DRIVER = "com.ibm.db2.jcc.DB2Driver";
    private static final String URL = "jdbc:db2://10.227.254.220:50000/BTC";

    private static final String USER = "root";

    private static final String PASS = "123";

    private Connection conn;

    private Statement stmt;

    @Before
    public void setUp() throws Exception {
        Class.forName(DRIVER);
        conn = DriverManager.getConnection(URL, USER,PASS);
        stmt = conn.createStatement();
    }

    @After
    public void tearDown() throws Exception {
        stmt = null;
        if (null == conn || !conn.isClosed()) {
            conn.close();
        }
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }
}
