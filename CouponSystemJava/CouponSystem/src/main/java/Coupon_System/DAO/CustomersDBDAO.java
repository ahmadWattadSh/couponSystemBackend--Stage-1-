/**
 * The CustomersDBDao is with direct connection with the dataBase. SQL commands are handled here.
 * It overrides all the functions in the CustomersDao.
 * It throws SQL and Interrupted Exceptions - to be handled later in the testAll.
 *
 * Note: CouponForOneCustomer is not in the Dao because we use it for getOneCustomer function as help.
 */

package Coupon_System.DAO;

import Coupon_System.ConnectionPool.ConnectionPool;
import Coupon_System.JavaBeans.Category;
import Coupon_System.JavaBeans.Coupon;
import Coupon_System.JavaBeans.Customer;
import Coupon_System.DesignColors.TextColors;


import java.sql.*;
import java.util.ArrayList;

public class CustomersDBDAO implements CustomersDAO{
    private ConnectionPool connectionPool = ConnectionPool.getInstance();



    @Override
    public boolean isCustomerExists(String email, String password) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "select * from customers where email='" +email + "'" + " and password=" + "'" + password + "'" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultset = preparedStatement.executeQuery();
        connectionPool.restoreConnection(connection);
        if(resultset.next()) {
            return true;
        }
        else {
            return  false ;
        }
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "insert into customers ( first_Name, last_Name, email, password) values ('" + customer.getFirstName() + "','" + customer.getLastName() + "', '" + customer.getEmail()+"', '" + customer.getPassword() + "')";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        System.out.println(TextColors.ANSI_BLUE +"Customer was added successfully!"+TextColors.ANSI_RESET);
        connectionPool.restoreConnection(connection);
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "update customers set first_Name='" + customer.getFirstName() + "', last_Name='" + customer.getLastName() + "', email='" + customer.getEmail() + "', password='" + customer.getPassword() + "' where id=" + customer.getId();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        System.out.println(TextColors.ANSI_BLUE +"Customer was updated successfully!" +TextColors.ANSI_RESET);
        connectionPool.restoreConnection(connection);
    }

    @Override
    public void deleteCustomer(int customerID) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "delete from customers where id=" + customerID;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        System.out.println(TextColors.ANSI_BLUE +"Customer was deleted successfully!" + TextColors.ANSI_RESET);
        connectionPool.restoreConnection(connection);
    }

    @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        ArrayList<Customer> resList = new ArrayList<Customer>();
        String sql = "select id, first_Name, last_Name, email, password from customers"; // select * ... will also work here.
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultset = preparedStatement.executeQuery();
        connectionPool.restoreConnection(connection);
        while (resultset.next()) {//[ [id, name, salary], [id, name, salary]... ]
            Customer emp = new Customer();
            int id = resultset.getInt("id");
            emp.setId(id);
            String firstName = resultset.getString("first_Name");
            emp.setFirstName(firstName);
            String lastName = resultset.getString("last_Name");
            emp.setLastName(lastName);
            String email = resultset.getString("email");
            emp.setEmail(email);
            String password = resultset.getString("password");
            emp.setPassword(password);
            resList.add(emp);
        }
        return resList;
    }

    @Override
    public Customer getOneCustomer(int customerID) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "select id, first_Name, last_Name, email, password from customers where id=" + customerID; // select * ... will also work here.
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultset = preparedStatement.executeQuery();
        connectionPool.restoreConnection(connection);
        Customer customer = new Customer();
        customer.setId(customerID);
        resultset.next();
            String firstName = resultset.getString("first_Name");
            customer.setFirstName(firstName);
            String lastName = resultset.getString("last_Name");
            customer.setLastName(lastName);
            String email = resultset.getString("email");
            customer.setEmail(email);
            String password = resultset.getString("password");
            customer.setPassword(password);

       customer.setCouponList(couponsForOneCustomer(customerID));
        return customer;
    }


    public ArrayList<Coupon> couponsForOneCustomer(int customerID) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT  * from customers_vs_coupons t1 join coupons t2 ON t1.COUPON_ID= t2.ID where customer_id=" + customerID;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultset = preparedStatement.executeQuery();
        connectionPool.restoreConnection(connection);
        ArrayList<Coupon> couponList = new ArrayList<Coupon>();
        while(resultset.next()) {
            Coupon coupon = new Coupon();
            int id = resultset.getInt("id");
            coupon.setId(id);
            int category_id = resultset.getInt("category_id");
            coupon.setCategory(Category.values()[category_id-1]);
            int company_id = resultset.getInt("company_id");
            coupon.setCompanyID(company_id);
            String title = resultset.getString("title");
            coupon.setTitle(title);
            String description = resultset.getString("description");
            coupon.setDescription(description);
            String start_date = resultset.getString("start_date");
            coupon.setStartDate(Date.valueOf(start_date));
            String end_date = resultset.getString("end_date");
            coupon.setEndDate(Date.valueOf(end_date));
            int price = resultset.getInt("price");
            coupon.setPrice(price);
            String image = resultset.getString("image");
            coupon.setImage(image);
            couponList.add(coupon);
        }


        return couponList;
    }
        @Override
    public boolean isCustomerExistByEmail(String email) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "select * from customers where email='" + email + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultset = preparedStatement.executeQuery();
        connectionPool.restoreConnection(connection);
        if(resultset.next()) {
            return true;
        }
        else {
            return  false ;
        }
    }

    @Override
public int getCustomerIdByEmailPassword(String email, String password) throws SQLException, InterruptedException {
    Connection connection = connectionPool.getConnection();
    String sql = " select * from customers where EMAIL='" + email + "' and PASSWORD='" + password + "'";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet resultset = preparedStatement.executeQuery();
    connectionPool.restoreConnection(connection);
    resultset.next();
    int customer_id = resultset.getInt("ID");
    return customer_id;
}

}

