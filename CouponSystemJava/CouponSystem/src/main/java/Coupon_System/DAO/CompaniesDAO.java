/**
 * The companyDao holds the functions that will be used by the Facade. These functions form a direct connection to the dataBase
 * through the companyDBDao.
 * It throws SQL and Interrupted exceptions
 *
 * ABOUT THE FUNCTIONS AND THEIR USES:
 * isCompanyExist - this function checks if the company actually exists in the database.
 * getCompanyIdByEmailAndPassword - the function returns a company object by the email and password
 * isToAddCompanyExist - the function checks if the company that you want to add exists in the dataBase.
 * addCompany - This function adds a company object (details) to dataBase.
 * updateCompany - This function updates the new details that you changed to the current company object in the DataBase.
 * deleteCompany - This function deletes the company object and its coupons from the dataBase using the CompanyId.
 * getAllCompanies - This function returns an array of all companies without its couponsList.
 * getOneCompany - This function returns company object with all details (including couponsList) relying on dataBase.
 */

package Coupon_System.DAO;

import Coupon_System.JavaBeans.Company;
import Coupon_System.JavaBeans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;


public interface CompaniesDAO {
   boolean isCompanyExists(String email,String password) throws SQLException, InterruptedException;
   void addCompany(Company company) throws SQLException, InterruptedException;
   void updateCompany(Company company) throws SQLException, InterruptedException;
   void deleteCompany(int companyID) throws SQLException, InterruptedException;
   ArrayList<Company> getAllCompanies() throws SQLException, InterruptedException;
   Company getOneCompany(int companyID) throws SQLException, InterruptedException;

   boolean isToAddCompany(String email, String name) throws SQLException, InterruptedException;

   int getCompanyIdByEmailPassword(String email, String password) throws SQLException, InterruptedException;
}
