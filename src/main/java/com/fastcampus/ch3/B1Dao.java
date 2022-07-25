package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Repository
public class B1Dao {
    @Autowired
    DataSource ds;

    public int insert(int key, int value)  throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            //conn = ds.getConnection();
            conn = DataSourceUtils.getConnection(ds);
            pstmt = conn.prepareStatement("insert into b1 values(?,?)");
            pstmt.setInt(1, key);
            pstmt.setInt(2, value);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
//            close(conn, pstmt);
            close(pstmt);
            DataSourceUtils.releaseConnection(conn, ds); // 무조건 닫는게 아니라 tx매니저의 판단 하에 닫는다.
        }
    }

    private void close(AutoCloseable... acs) {
        for(AutoCloseable ac :acs)
            try { if(ac!=null) ac.close(); } catch(Exception e) { e.printStackTrace(); }
    }

}
