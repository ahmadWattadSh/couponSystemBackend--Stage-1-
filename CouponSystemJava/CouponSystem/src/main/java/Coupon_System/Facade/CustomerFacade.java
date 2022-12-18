/**
 * CustomerFacade includes all the functions that the user(Customer) can do in this program. The facade uses the DBDao in order to connect
 * to the DataBase.
 *
 * The CompanyFacade throws SQL and Interrupted exceptions - to be handled later in the testAll.
 *
 * The functions and their uses:
 *    1. Login: The user needs to log in before he can do any functions. Here the program checks if the details he/she entered are correct.
 *    2. getCustomerIDByEmailAndPassword: Not available to the company. The loginManager uses this function.
 *    3. Purchase Coupon: The customer is able to purchase a coupon of his choice. Yet, he can't purchase the same coupon more than once,
 * he can't purchase a coupon that the company run out of ,and he can't purchase it if it is already expired. if he does, The program
 * will throw a custom exception (FacadeException) attached with proper message.
 *    4. getCustomerCoupons: The customer is able to view all of his coupons, or he can view them sorted by Category of their choice ,
 * or he can view them sorted by maxPrice of their choice.
 *    5. getCustomerDetails: The customer is able to view all of his details. (couponsList included).
 *    6. getAllCoupons: The customer is able to view all the coupons, so he can choose a coupon to purchase.
 *
 */

package Coupon_System.Facade;

import Coupon_System.JavaBeans.Category;
import Coupon_System.JavaBeans.Coupon;
import Coupon_System.JavaBeans.Customer;
import Coupon_System.DesignColors.TextColors;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CustomerFacade extends ClientFacade {
    private int customerID;


    public CustomerFacade() {
    }

    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        return customersDAO.isCustomerExists(email, password);
    }

    public void purchaseCoupon(Coupon coupon) throws SQLException, InterruptedException {

        Customer customer = customersDAO.getOneCustomer(customerID);
try {
    if (coupon.getAmount() > 0) {
        if (!customer.getCouponList().contains(coupon)) {
            if (coupon.getEndDate().after((Calendar.getInstance()).getTime())) {
                couponsDAO.addCouponPurchase(customerID, coupon.getId());
                coupon.setAmount(coupon.getAmount() - 1);
                couponsDAO.updateCoupon(coupon);
                customer.getCouponList().add(coupon);
            } else throw new FacadeException("This coupon has expired!");
        } else throw new FacadeException("We have run out of this coupon!");

    } else {
        throw new FacadeException("You have already purchased this coupon!");
    }
} catch(FacadeException e)
{
    System.out.println(TextColors.ANSI_RED +"Exception: " + e.getMessage()+"" +TextColors.ANSI_RESET);
}

}

    public ArrayList<Coupon> getCustomerCoupons() throws SQLException, InterruptedException {
       Customer customer = customersDAO.getOneCustomer(customerID);
        return customer.getCouponList();
    }

    public ArrayList<Coupon> getCustomerCoupons(Category category) throws SQLException, InterruptedException {
        Customer customer = customersDAO.getOneCustomer(customerID);
        ArrayList<Coupon> customerCategoryCouponList = new ArrayList<Coupon>();
        for (Coupon coupon: customer.getCouponList()
             ) { if(coupon.getCategory().equals(category))
                 customerCategoryCouponList.add(coupon);
        }
        return customerCategoryCouponList;
    }

    public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws SQLException, InterruptedException {
      ArrayList<Coupon>  couponsTilMaxPrice  = new ArrayList<Coupon>();
        Customer customer = customersDAO.getOneCustomer(customerID);

        for (Coupon coupon: customer.getCouponList()
             ) {
          if(coupon.getPrice() < maxPrice)
          {
              couponsTilMaxPrice.add(coupon);
          }
        }
        return couponsTilMaxPrice;
    }

    public Customer getCustomerDetails() throws SQLException, InterruptedException {
        return customersDAO.getOneCustomer(customerID);
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getCustomerIDByEmailPassword(String email, String password) throws SQLException, InterruptedException {
      int customerID=  customersDAO.getCustomerIdByEmailPassword(email,password);
      return customerID;
    }


}
