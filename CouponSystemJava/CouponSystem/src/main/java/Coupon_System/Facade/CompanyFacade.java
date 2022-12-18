/**
 * CompanyFacade includes all the functions that the user(Company) can do in this program. The facade uses the DBDao in order to connect
 * to the DataBase.
 *
 * The CompanyFacade throws SQL and Interrupted exceptions - to be handled later in the testAll.
 *
 * The functions and their uses:
 *    1. Login: The user needs to log in before he can do any functions. Here the program checks if the details he/she entered are correct.
 *    2. getCompanyIDByEmailAndPassword: Not available to the company. The loginManager uses this function.
 *    3. addCoupon: The company can add a coupon to its couponsList and in the database through this function.
 *    4. updateCoupon: The company can update its coupons' details except for the CompanyId. The program would throw a custom exception
 *  (FacadeException).
 *    5. deleteCoupons: The company is able to delete one of its coupons entirely from the database and from the customer who already purchased it.
 *    6. getCompanyCoupons: The company is able to view it's all coupons/ it's coupons sorted by category of their choice
 *    /it's coupons sorted by maxPrice of their choice.
 *    7. getCompanyDetails: The company is able to view it's all details (including the couponsList).
 */

package Coupon_System.Facade;

import Coupon_System.JavaBeans.Category;
import Coupon_System.JavaBeans.Company;
import Coupon_System.JavaBeans.Coupon;
import Coupon_System.JavaBeans.Customer;
import Coupon_System.DesignColors.TextColors;

import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyFacade  extends ClientFacade{
    private int companyID;

    public CompanyFacade() {

    }


    @Override
    public boolean login(String email, String password) throws SQLException, InterruptedException {
        return companiesDAO.isCompanyExists(email,password);
    }


    public void addCoupon(Coupon coupon) throws SQLException, InterruptedException {
        boolean isExist = false;
        Company company = companiesDAO.getOneCompany(companyID);

        if(company.getCouponList()!=null) {
            for (Coupon coupon1 : company.getCouponList()
            ) {
                if (coupon1.getTitle().equals(coupon.getTitle())) {
                    isExist = true;
                    break;
                } }
                if (isExist) {
                    try {
                        throw new FacadeException("This coupon already exists!");
                    } catch (FacadeException e) {
                        System.out.println(TextColors.ANSI_RED +"Exception: " + e.getMessage()+"" +TextColors.ANSI_RESET);
                    }
                }
             else
                {
                company.getCouponList().add(coupon);
                    coupon.setCompanyID(companyID);
                    couponsDAO.addCoupon(coupon);
                }
        }
        else {
            coupon.setCompanyID(companyID);
            couponsDAO.addCoupon(coupon);
            company.getCouponList().add(coupon);
        }

        }


    public void updateCoupon(Coupon coupon) throws SQLException, InterruptedException {
        if(companyID == coupon.getCompanyID())
        {
            couponsDAO.updateCoupon(coupon);
        }
        else
            try {
                throw new FacadeException("You can't update the companyID");
            } catch (FacadeException e) {
                System.out.println(TextColors.ANSI_RED +"Exception: " + e.getMessage()+"" +TextColors.ANSI_RESET);
            }

    }

    public void deleteCoupon(int couponID) throws SQLException, InterruptedException {
        couponsDAO.deleteCouponPurchaseById(couponID);
        couponsDAO.deleteCoupon(couponID);


    }

   public ArrayList<Coupon> getCompanyCoupons() throws SQLException, InterruptedException {
        Company company = companiesDAO.getOneCompany(companyID);
        return company.getCouponList();
   }

  public ArrayList<Coupon> getCompanyCoupons(Category category) throws SQLException, InterruptedException {
      Company company = companiesDAO.getOneCompany(companyID);
      ArrayList<Coupon> companyCategoryCouponList = new ArrayList<Coupon>();
      for (Coupon coupon: company.getCouponList()
      ) { if(coupon.getCategory().equals(category))
          companyCategoryCouponList.add(coupon);
      }
      return companyCategoryCouponList;
  }

    public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws SQLException, InterruptedException {
        ArrayList<Coupon>  couponsTilMaxPrice  = new ArrayList<Coupon>();
        Company company = companiesDAO.getOneCompany(companyID);

        for (Coupon coupon: company.getCouponList()
        ) {
            if(coupon.getPrice() < maxPrice)
            {
                couponsTilMaxPrice.add(coupon);
            }
        }
        return couponsTilMaxPrice;
    }

    public Company getCompanyDetails() throws SQLException, InterruptedException {
       return companiesDAO.getOneCompany(companyID);
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int getCompanyIDByEmailPassword(String email, String password) throws SQLException, InterruptedException {
       int id = companiesDAO.getCompanyIdByEmailPassword(email,password);
        return id;
    }


}
