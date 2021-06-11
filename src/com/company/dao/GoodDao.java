package com.company.dao;

import com.company.entity.Good;
import com.company.utils.MySqlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author CXQ
 * @param
 */
public class GoodDao {
    public static Good findGoodById(int id){
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
            sql = "select * from goods where gid=\""+id+"\"";
            ResultSet resultSet = stmt.executeQuery(sql);
            resultSet.next();
            Good good=new Good(resultSet.getInt("gid"),resultSet.getString("gname"),resultSet.getDouble("gprice"),resultSet.getInt("gnum"));
            stmt.close();
            conn.close();
            return good;
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
        return null;
    }

    public static void modifyGood(String Gname,double Gprice,int Gnum){
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
            sql = "update goods set gnum="+Gnum+","+"gprice="+Gprice+" where gname=\""+Gname+"\"";
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

    public static void deleteGood(int id){
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
            sql = "delete from goods where gid="+id;
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

    public static void insertGood(Good good){
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
            sql = "insert into goods(gname,gprice,gnum) values (\""+good.getGname()+"\","+good.getGprice()+","+good.getGnum()+")";
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
    public static void getAllGood() {
        System.out.println("以下是本店的所有商品：");
        System.out.println("商品ID\t商品名称\t商品价格\t商品数量");

        List<Good> goodList = new ArrayList<Good>();
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
            sql = "SELECT * FROM goods";
            ResultSet rs = stmt.executeQuery(sql);


            // 展开结果集数据库
            while (rs.next()) {
                goodList.add(new Good(rs.getInt("gid"), rs.getString("gname"), rs.getDouble("gprice"),
                        rs.getInt("gnum")));
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

        for (Good good : goodList) {
            System.out.println(good.getGid() + "\t\t" + good.getGname() + "\t\t" + good.getGprice() + "\t\t" + good.getGnum());
        }
    }
}
