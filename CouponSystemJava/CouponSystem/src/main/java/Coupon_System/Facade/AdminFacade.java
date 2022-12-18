/**
 * AdminFacade includes all the functions that the user(admin) can do in this program. The facade uses the DBDao in order to connect
 * to the DataBase.
 *
 * The AdminFacade throws SQL and Interrupted exceptions - to be handled later in the testAll.
 *
 * The functions and their uses:
 *    1. Login: The user needs to log in before he can do any functions. Here the program checks if the details he/she entered are correct.
 *    2. addCompany: The admin can add a new company to the dataBase. However, he can't add an already existing company. if he does,
 * The program will throw a custom exception (FacadeException).
 *    3. updateCompany: The admin is able to update the company details except for its name. The program would throw a
 * custom exception (FacadeException).
 *    4. deleteCompany: The admin can delete a company, and automatically it will delete its coupons entirely from the dataBase.
 *    5. getAllCompanies: The admin is able to see all the companies details except for the couponsList with this function.
 *    6. getOneCompany: The admin is able to see all the details (including the couponsList) of one company through its id.
 *    7. addCustomer: The admin can add a new customer to the dataBase. However, he can't add a customer with an email similar to
 * another from the dataBase.If he does,The program will throw a custom exception (FacadeException).
 *    8. update Customer: The admin is able to update all the Customer's details.
 *    9. deleteCustomer: The admin is able to delete a customer and his coupons from the dataBase.
 *    10. getAllCustomers: The admin is able to see all the customers' details except for the couponsList with this function.
 *    11. getOneCustomer: The admin is able to see all the details (including the couponsList) of one customer through the id.
 */

package Coupon_System.Facade;

import Coupon_System.DAO.CompaniesDBDAO;
import Coupon_System.JavaBeans.Company;
import Coupon_System.JavaBeans.Coupon;
import Coupon_System.JavaBeans.Customer;
import Coupon_System.DesignColors.TextColors;


import java.sql.SQLException;
import java.util.ArrayList;

public class AdminFacade extends ClientFacade {
  private final String email = "admin@admin.com";
  private final String password = "admin";


    @Override
    public boolean login(String email, String password) {
        if(this.email.equals(email) && this.password.equals(password))
            return true;
        return false;
    }

    public AdminFacade(){
    }

    public void addCompany (Company company) throws SQLException, InterruptedException {
       if(!companiesDAO.isToAddCompany(company.getEmail(),company.getName()))
        companiesDAO.addCompany(company);
       else
           try {
               throw new FacadeException("Company already exists!\nCannot be added!");
           } catch (FacadeException e) {
               System.out.println(TextColors.ANSI_RED +"Exception: " + e.getMessage()+"" +TextColors.ANSI_RESET);
           }
    }


    public void updateCompany(Company company) throws SQLException, InterruptedException {
        Company old_company = getOneCompany(company.getId());
        if(old_company.getName().equals(company.getName()))
        companiesDAO.updateCompany(company);
        else
            try {
                throw new FacadeException("Cannot update with different name!");
            } catch (FacadeException e) {
                System.out.println(TextColors.ANSI_RED +"Exception: " + e.getMessage()+"" +TextColors.ANSI_RESET);
            }
    }

    public void deleteCompany(int companyID) throws SQLException, InterruptedException {
       ArrayList<Coupon> couponsList = couponsDAO.getAllCoupons();

        for (Coupon coupon: couponsList
             ) { if(coupon.getCompanyID()==companyID){
                 couponsDAO.deleteCouponPurchaseById(coupon.getId());
                 couponsDAO.deleteCoupon(coupon.getId());
        }

        }
        companiesDAO.deleteCompany(companyID);
    }

   public ArrayList<Company> getAllCompanies() throws SQLException, InterruptedException {
       return companiesDAO.getAllCompanies();
 }

    public Company getOneCompany(int companyID) throws SQLException, InterruptedException {
        return companiesDAO.getOneCompany(companyID);
    }

    public void addCustomer(Customer customer) throws SQLException, InterruptedException {
        if (!customersDAO.isCustomerExistByEmail(customer.getEmail()))
            customersDAO.addCustomer(customer);
        else {
            try {
                throw new FacadeException("Customer already exists!");
            } catch (FacadeException e) {
                System.out.println(TextColors.ANSI_RED +"Exception: " + e.getMessage()+"" +TextColors.ANSI_RESET);
            }


        }
    }

    public void updateCustomer(Customer customer) throws SQLException, InterruptedException {
        customersDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int customerID) throws SQLException, InterruptedException {
        couponsDAO.deleteCustomerPurchaseById(customerID);
        customersDAO.deleteCustomer(customerID);
    }

    public ArrayList<Customer> getAllCustomers() throws SQLException, InterruptedException {
        return customersDAO.getAllCustomers();
    }

    public Customer getOneCustomer(int customerID) throws SQLException, InterruptedException {
      return  customersDAO.getOneCustomer(customerID);
    }

}
