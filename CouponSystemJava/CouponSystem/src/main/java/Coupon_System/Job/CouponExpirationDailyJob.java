/**
 * CouponExpirationDailyJob is a thread with a daily task of erasing all the coupons that have already expired.
 * The task is done every 24hrs. (with the use of the sleep function)
 * It doesn't stop till the end of program where the program closes it with the flag "quit" where it interrupts the sleep.
 * This thread runs parallel to the whole program.
 */
package Coupon_System.Job;

import Coupon_System.DAO.CouponsDAO;
import Coupon_System.DAO.CouponsDBDAO;
import Coupon_System.JavaBeans.Coupon;
import Coupon_System.DesignColors.TextColors;

import java.sql.SQLException;
import java.util.Date;


public class CouponExpirationDailyJob implements Runnable{
    final static long DAY = 1*1000*60*60*24;
    private CouponsDAO couponsDAO = new CouponsDBDAO();
    private boolean quit = false;

    public CouponExpirationDailyJob() {
    }

    @Override
    public void run() {
        while (!quit) {
            try {
                for (Coupon coupon : couponsDAO.getAllCoupons()) {
                    if (coupon.getEndDate().before(new Date(System.currentTimeMillis()))) {
                        couponsDAO.deleteCouponPurchaseById(coupon.getId());
                        couponsDAO.deleteCoupon(coupon.getId());
                    }
                }
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            }


            try {
                Thread.sleep(DAY);
            } catch (InterruptedException e) {
                System.out.println(TextColors.ANSI_PURPLE + "We are closing the program\nThe JOB has stopped its activity!" +TextColors.ANSI_RESET);
            }

        }
    }


    public void stop(){
        quit=true;
    }
}

