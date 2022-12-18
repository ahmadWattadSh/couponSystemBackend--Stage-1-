/**
 * The companyDBDao is with direct connection with the dataBase. SQL commands are handled here.
 * It overrides all the functions in the companiesDao.
 * It throws SQL and Interrupted Exceptions - to be handled later in the testAll.
 *
 *  * Note: CouponForOneCompany is not in the Dao because we use it for getOneCompany function as help.
 */
package Coupon_System.DAO;

import Coupon_System.ConnectionPool.ConnectionPool;
import Coupon_System.JavaBeans.Category;
import Coupon_System.JavaBeans.Company;
import Coupon_System.JavaBeans.Coupon;
import Coupon_System.DesignColors.TextColors;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CompaniesDBDAO implements CompaniesDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();


    @Override
    public boolean isCompanyExists(String email, String password) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = " select * from companies where EMAIL='" + email + "' and PASSWORD='" + password + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultset = preparedStatement.executeQuery();
        connectionPool.restoreConnection(connection);
        if (resultset.next())
            return true;
        return false;
    }

    @Override
    public void addCompany(Company company) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "insert into companies (NAME,EMAIL,PASSWORD ) values ('" + company.getName() + "' ,'" + company.getEmail() + "','" + company.getPassword() + "')";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();

        //ResultSet resultSet = preparedStatement.getGeneratedKeys();
        System.out.println( TextColors.ANSI_BLUE+"" + company.getName() + " Added Successfully!" + TextColors.ANSI_RESET);
        connectionPool.restoreConnection(connection);

    }


    @Override
    public void updateCompany(Company company) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "update companies set email='" + company.getEmail() + "', password='" + company.getPassword() + "' where id= "+company.getId();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();

        //ResultSet resultSet = preparedStatement.getGeneratedKeys();
        System.out.println( TextColors.ANSI_BLUE+ "" +company.getName() + " Updated Successfully!"+TextColors.ANSI_RESET);
        connectionPool.restoreConnection(connection);

    }

    @Override
    public void deleteCompany(int companyID) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "delete from companies where id=" + companyID;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        System.out.println( TextColors.ANSI_BLUE+ "Company " +companyID +" was deleted successfully!"+TextColors.ANSI_RESET );
        connectionPool.restoreConnection(connection);
    }

    @Override
    public ArrayList<Company> getAllCompanies() throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        List<Company> resList = new ArrayList<Company>();
        String sql = "select * from companies"; // select * ... will also work here.
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultset = preparedStatement.executeQuery();
        connectionPool.restoreConnection(connection);
        while (resultset.next()) {//[ [id, name, salary], [id, name, salary]... ]
            Company company = new Company();
            int id = resultset.getInt("id");
            company.setId(id);
            String name = resultset.getString("name");
            company.setName(name);
            String email = resultset.getString("email");
            company.setEmail(email);
            String password = resultset.getString("password");
            company.setPassword(password);
            resList.add(company);
        }
        return (ArrayList<Company>) resList;
    }

    @Override
    public Company getOneCompany(int companyID) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "select * from companies where id=" + companyID; // select * ... will also work here.
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultset = preparedStatement.executeQuery();
        connectionPool.restoreConnection(connection);
        Company company = new Company();
        company.setId(companyID);
        resultset.next();
        String name = resultset.getString("name");
        company.setName(name);
        String password = resultset.getString("password");
        company.setPassword(password);
        String email = resultset.getString("email");
        company.setEmail(email);
company.setCouponList(CouponsForOneCompany(companyID));
        return company;
    }
    @Override
    public boolean isToAddCompany(String email, String name) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = " select * from companies where EMAIL='" + email + "' or NAME='" + name + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultset = preparedStatement.executeQuery();
        connectionPool.restoreConnection(connection);
        if (resultset.next())
            return true;
        return false;
    }


    public ArrayList<Coupon> CouponsForOneCompany(int companyID) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "select * from coupons where company_id=" + companyID; // select * ... will also work here.
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
            int amount = resultset.getInt("amount");
            coupon.setAmount(amount);
            int price = resultset.getInt("price");
            coupon.setPrice(price);
            String image = resultset.getString("image");
            coupon.setImage(image);
            couponList.add(coupon);
        }
        return couponList;
    }

    @Override
    public int getCompanyIdByEmailPassword(String email, String password) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = " select * from companies where EMAIL='" + email + "' and PASSWORD='" + password + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultset = preparedStatement.executeQuery();
        connectionPool.restoreConnection(connection);
        resultset.next();
            int company_id = resultset.getInt("ID");
            return company_id;
    }
}
