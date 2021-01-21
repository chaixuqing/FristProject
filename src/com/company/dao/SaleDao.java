package com.company.dao;

import com.company.entity.Good;
import com.company.entity.Sale;
import com.company.utils.MySqlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDao {
    public static void modifyPassword(String name,String newPassword){
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(MySqlUtil.JDBC_DRIVER);

            // 打开链接
            conn = DriverManager.getConnection(MySqlUtil.DB_URL, MySqlUtil.USER, MySqlUtil.PASS);

            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "update sales set spassword=\""+newPassword+"\" where sname=\""+name+"\"";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }// 什么都不做
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
    }
    public static List<Sale> getAllSale() {
        List<Sale> saleList = new ArrayList<Sale>();
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(MySqlUtil.JDBC_DRIVER);

            // 打开链接
//            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(MySqlUtil.DB_URL, MySqlUtil.USER, MySqlUtil.PASS);

            // 执行查询
//            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM sales";
            ResultSet rs = stmt.executeQuery(sql);


            // 展开结果集数据库
            while (rs.next()) {
                saleList.add(new Sale(rs.getInt("sid"),rs.getString("sname"),rs.getString("spassword")));
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();
            }// 什么都不做
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        return saleList;
    }
}
