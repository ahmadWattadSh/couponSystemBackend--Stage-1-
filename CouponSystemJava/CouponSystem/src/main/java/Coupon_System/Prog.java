/**
 * This program was created by Wattad Ahmad and Wattad Oday.
 * This class contains three functions:
 * Clear() - the purpose of this function to delete all data
 * TestAll() - tests all the program functions and asserts the availability to use this program safely.
 * LoadData() - Please use this function if you desire to customize and use this program.
 */
package Coupon_System;

import Coupon_System.ConnectionPool.ConnectionPool;
import Coupon_System.DAO.CompaniesDBDAO;
import Coupon_System.DAO_ClearDataBase.ClearDataBase;
import Coupon_System.DesignColors.TextColors;
import Coupon_System.JavaBeans.Coupon;
import Coupon_System.LoadClassBeforeUsingMainProg.LoadClassBeforeUsingMainProg;
import Coupon_System.Test.TestAll;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;


public class Prog {



    public static void main(String[] args) throws SQLException, InterruptedException {

        Clear();
    TestAll.testAll();
    //LoadClassBeforeUsingMainProg.loadData(); // delete the comment to use it
     //   Coupon coupon = (Coupon) ctx.getBean("bean1");
       // System.out.println(coupon);
    }



    static void Clear()  {
        ClearDataBase c = new ClearDataBase();
        try {
        c.clearDataBase();
        c.clearAutoIncrement();
        } catch (Exception e) {
            System.out.println(TextColors.ANSI_RED + "Exception: there is either problem in sql functions or threads functionality!" + TextColors.ANSI_RESET);
        }
    }



}
