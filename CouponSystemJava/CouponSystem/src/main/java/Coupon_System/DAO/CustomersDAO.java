package Coupon_System.DAO;

import Coupon_System.JavaBeans.Customer;
/**
 * The CustomersDao holds the functions that will be used by the Facade. These functions form a direct connection to the dataBase
 * through the CustomersDBDao.
 * It throws SQL and Interrupted exceptions
 *
 * ABOUT THE FUNCTIONS AND THEIR USES:
 * isCustomerExist - this function checks if the customer actually exists in the database.(Using email and password)
 * isCustomerExistByEmail - this function checks if the customer actually exists in the database.(Using email)
 * getCustomerIdByEmailAndPassword - The login uses this function. This function returns the customerId by using email and password.
 * addCustomer - This function adds a new customer object to the dataBase.
 * updateCustomer - This function updates the new details that you changed to the current customer object in the DataBase.
 * getAllCustomers - This function returns an array of all the customers that are found in the DataBase. (without couponsList)
 * getOneCustomer  - This function returns a specific customer object by its id. (with couponsList)
 */
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomersDAO {
    boolean isCustomerExists(String email, String password) throws SQLException, InterruptedException;
    void addCustomer(Customer customer) throws SQLException, InterruptedException;
    void updateCustomer(Customer customer) throws SQLException, InterruptedException;
    void deleteCustomer(int customerID) throws SQLException, InterruptedException;
    ArrayList<Customer> getAllCustomers() throws SQLException, InterruptedException;
    Customer getOneCustomer(int customerID) throws SQLException, InterruptedException;
    boolean isCustomerExistByEmail(String email) throws SQLException, InterruptedException;
    int getCustomerIdByEmailPassword(String email, String password) throws SQLException, InterruptedException;
}
