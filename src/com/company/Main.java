package com.company;

import com.company.dao.GSaleDao;
import com.company.dao.GoodDao;
import com.company.dao.SaleDao;
import com.company.entity.Good;
import com.company.entity.Gsale;
import com.company.entity.Sale;
import com.company.utils.MySqlUtil;
import org.ietf.jgss.GSSName;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("欢迎来到超市管理系统！");
        while (true) {
            System.out.println("请选择您的登录身份：");
            System.out.println("1. 顾客");
            System.out.println("2. 管理员");
            System.out.println("3. 退出");
            System.out.println("请输入：");

            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();

            if (choice == 1) {
                System.out.println("您已选择以顾客身份进行登陆！");
                GoodDao.getAllGood();
                while (true) {
                    System.out.print("请输入您要购买的商品ID(输入-1结束购买)：");
                    int id = in.nextInt();
                    if (id != -1) {
                        System.out.print("请输入您要购买的商品的数量：");
                        int num = in.nextInt();
                        if (num <= 0) {
                            System.out.println("购买的商品数量不能为负！");
                            continue;
                        }
                        System.out.println("请选择一个销售员为您结算:");
                        System.out.println("销售员ID\t销售员名称");
                        List<Sale> saleList = SaleDao.getAllSale();
                        for (Sale sale : saleList) {
                            System.out.println(sale.getSid() + "\t\t" + sale.getSname());
                        }
                        int sid = in.nextInt();
                        List<Integer> sidList = new ArrayList<>();
                        for (Sale sale : saleList) {
                            sidList.add(sale.getSid());
                        }
                        if (!sidList.contains(sid)) {
                            System.out.println("您输入的销售员ID不正确！");
                            continue;
                        }
                        // todo
                        GSaleDao.insertGsale(new Gsale(id, sid, num));
                        System.out.println("购买成功！");
                    } else {
                        break;
                    }
                }
            } else if (choice == 2) {
                boolean SecondFlag = true;
                while (SecondFlag) {
                    System.out.print("请输入用户名：");
                    String username = in.next();
                    System.out.print("请输入密码：");
                    String password = in.next();
                    List<Sale> saleList = SaleDao.getAllSale();
                    boolean flag = false;
                    for (Sale sale : saleList) {
                        if (sale.getSname().equals(username) && sale.getSpassword().equals(password)) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag == true) {
                        System.out.println("登陆成功！");
                        while (SecondFlag) {
                            System.out.println("请选择以下功能：");
                            System.out.println("1. 查看所有商品交易记录");
                            System.out.println("2. 查看所有商品信息");
                            System.out.println("3. 添加商品");
                            System.out.println("4. 删除商品");
                            System.out.println("5. 修改商品");
                            System.out.println("6. 查找商品");
                            System.out.println("7. 修改密码");
                            System.out.println("8. 退出");
                            System.out.print("请输入您的选择：");
                            int sale_choice = in.nextInt();
                            switch (sale_choice) {
                                case 1: {
                                    GSaleDao.getAllGsale();
                                    break;
                                }
                                case 2: {
                                    GoodDao.getAllGood();
                                    break;
                                }
                                case 3: {
                                    System.out.print("请输入商品名称：");
                                    String name = in.next();
                                    System.out.print("请输入商品价格：");
                                    double price = in.nextDouble();
                                    System.out.print("请输入商品数量：");
                                    int num = in.nextInt();
                                    GoodDao.insertGood(new Good(0, name, price, num));
                                    System.out.println("插入成功");
                                    break;
                                }
                                case 4: {
                                    System.out.print("请输入商品ID：");
                                    int id = in.nextInt();
                                    GoodDao.deleteGood(id);
                                    System.out.println("删除成功！");
                                    break;
                                }
                                case 5: {
                                    System.out.print("请输入商品名称：");
                                    String name = in.next();
                                    System.out.print("请输入商品价格：");
                                    double price = in.nextDouble();
                                    System.out.print("请输入商品数量：");
                                    int num = in.nextInt();
                                    GoodDao.modifyGood(name, price, num);
                                    break;
                                }
                                case 6: {
                                    System.out.println("请输入商品ID：");
                                    int id=in.nextInt();
                                    System.out.println(GoodDao.findGoodById(id));
                                    break;
                                }
                                case 7: {
                                    System.out.print("请输入您的新密码：");
                                    String NewPassword = in.next();
                                    System.out.print("请再次输入您的密码：");
                                    String NewConfirmPassword = in.next();
                                    if (!NewPassword.equals(NewConfirmPassword)) {
                                        continue;
                                    }
                                    SaleDao.modifyPassword (username,NewPassword);
                                    break;
                                }
                                case 8: {
                                    SecondFlag = false;
                                    break;
                                }
                                default: {
                                    System.out.println("请重新输入您的选择！");
                                }
                            }

                        }

                    } else {
                        System.out.println("用户名或密码错误！");
                        continue;
                    }
                }

            } else if (choice == 3) {
                System.out.println("您已成功退出系统！");
                in.close();
                break;
            } else {
                System.out.println("您的输入有误，请重新输入！");
            }

        }
    }
}
