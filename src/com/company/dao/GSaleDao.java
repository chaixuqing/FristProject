package com.company.dao;

import com.company.entity.Gsale;
import com.company.entity.Sale;
import com.company.utils.MySqlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GSaleDao {
    public static void getAllGsale(){
        System.out.println("商品ID\t销售员ID\t商品数量");

        List<Gsale> gsaleList=new ArrayList<>();
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
            sql = "select * from gsales";
            ResultSet rs=stmt.executeQuery(sql);
            while (rs.next()){
                gsaleList.add(new Gsale(rs.getInt("gid"),rs.getInt("sid"),rs.getInt("num")));
            }
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
        for (Gsale gsale : gsaleList) {
            System.out.println(gsale.getGid()+"\t\t"+gsale.getSid()+"\t\t"+gsale.getCount());
        }
    }

    public static boolean insertGsale(Gsale gsale) {
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
            sql = "insert into gsales(gid,sid,num) values (" + gsale.getGid() + "," + gsale.getSid() + "," + gsale.getCount() + ")";
            stmt.executeUpdate(sql);
            sql="update goods "+"set gnum=gnum-"+gsale.getCount()+" where gid="+gsale.getGid();
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
        return true;
    }
}
