package com.clicktools.model;

import util.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDao {
    private final String col_id = "id";
    private final String col_opt1 = "opt1";
    private final String col_opt2 = "opt2";
    private final String col_opt3 = "opt3";


    public List<Test> selectTest() {
        List<Test> list = null;

        ResultSet rs = null;
        String sqlStr = "Select * from test";
        Connection con = null;
        Statement stmt = null;

        try {
            con = DB.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlStr);

            list = new ArrayList<Test>(rs.getFetchSize());
            while(rs.next()) {
                int id = rs.getInt(col_id);
                String opt1 = rs.getString(col_opt1);
                String opt2 = rs.getString(col_opt2);
                String opt3 = rs.getString(col_opt3);
                Test test = new Test(id, opt1, opt2, opt3);
                if(test != null) {
                    list.add(test);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.getInstance().freeConnection();
            try {
                if(stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
