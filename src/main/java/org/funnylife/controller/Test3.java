package org.funnylife.controller;

import java.sql.*;

/**
 * Created by cheng on 2017/7/1.
 */
public class Test3 {
    public static void main(String[] args) throws Exception {
//        show();
        select();
    }

    public static void show() throws Exception {
        String url = "jdbc:mysql://localhost:3306?user=root&password=root";
        Connection con = DriverManager.getConnection(url);
        Statement stmt = con.createStatement();
        String query = "show databases";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            System.out.println(rs.getString(2));
        }
        stmt.close();
        con.close();
    }
    public static void select() throws Exception {
        String url = "jdbc:mysql://localhost:3306/deren?user=root&password=root";
        Connection con = DriverManager.getConnection(url);
        Statement stmt = con.createStatement();
        String query = "select * from t_article limit 0, 10";
        ResultSet rs = stmt.executeQuery(query);
        boolean isFirst = true;
        int count = 0 ;
        while (rs.next()) {
            if(isFirst) {
                ResultSetMetaData metaData = rs.getMetaData();
                metaData.getColumnName(1);
                metaData.getColumnClassName(1);
                metaData.getColumnType(1);
                metaData.getColumnDisplaySize(1);
                count = metaData.getColumnCount();
                for (int i = 1; i <= metaData.getColumnCount(); i++)
                    System.out.println(
                            metaData.getColumnName(i) + "\t" +
                                    metaData.getColumnTypeName(i) + "\t" +
                                    metaData.getColumnDisplaySize(i)
                    );
                isFirst = false;
            }
            for(int i = 0; i<count; i++) {
                System.out.print(rs.getString(i+1) + "\t");
            }
            System.out.println();
//            break;
        }
        stmt.close();
        con.close();
    }
}