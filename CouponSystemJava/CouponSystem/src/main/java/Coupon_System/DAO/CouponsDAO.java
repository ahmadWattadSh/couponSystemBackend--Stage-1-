/**
 *   The CouponsDao holds the functions that will be used by the Facade. These functions form a direct connection to the dataBase
 *   through the CouponsDBDao.
 *   It throws SQL and Interrupted exceptions
 *
 *   ABOUT THE FUNCTIONS AND THEIR USES:
 *   addCoupon - The function adds a coupon object to the dateBase.
 *   updateCoupon -  This function updates the new details that you changed to the current coupon object in the DataBase.
 *   deleteCoupon -  This function deletes coupon object from the dataBase (couponsList and customer vs coupons) by its id.
 *   getAllCoupons - This function returns an array of all the coupons that are found in the DataBase.
 *   getOneCoupon -  This function returns coupon object with all details relying on dataBase.
 *   addCouponPurchase - This function adds a coupon to the (customers vs coupons) list in the dataBase attaching it to the desired customer.
 *   deleteCouponPurchase - This function deletes a coupon from the (customers vs coupons) list in the dataBase by its customer and coupon ids.
 *   deleteCouponPurchaseByCustomerID - This function deletes a coupon from the (customers vs coupons) list in the dataBase by its customer's id.
 *   deleteCouponPurchaseByCouponID - This function deletes a coupon from the (customers vs coupons) list in the dataBase by its coupon's id.
 *
 * Notes: 1. getOneCoupon is not used. we found no use for it
 *        2. deleteOneCouponPurchase - similar functions are used but not this function.
 */

package Coupon_System.DAO;

import Coupon_System.JavaBeans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CouponsDAO {
    void addCoupon(Coupon coupon) throws SQLException, InterruptedException;
    void updateCoupon(Coupon coupon) throws SQLException, InterruptedException;
    void deleteCoupon(int couponID) throws SQLException, InterruptedException;
    ArrayList<Coupon> getAllCoupons() throws SQLException, InterruptedException;
    Coupon getOneCoupon(int couponID) throws SQLException, InterruptedException;
    void addCouponPurchase(int customerID, int couponID) throws SQLException, InterruptedException;

    void deleteOneCouponPurchase(int customerID, int couponID) throws SQLException, InterruptedException;

    void deleteCouponPurchaseById(int couponID) throws SQLException, InterruptedException;


    void deleteCustomerPurchaseById(int customerID) throws SQLException, InterruptedException;
}
