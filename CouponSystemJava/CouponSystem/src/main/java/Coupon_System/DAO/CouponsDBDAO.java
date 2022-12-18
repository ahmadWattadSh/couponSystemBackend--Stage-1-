/**
 * The CouponsDBDao is with direct connection with the dataBase. SQL commands are handled here.
 * It overrides all the functions in the CouponsDao.
 * It throws SQL and Interrupted Exceptions - to be handled later in the testAll.
 */

package Coupon_System.DAO;

import Coupon_System.ConnectionPool.ConnectionPool;
import Coupon_System.DesignColors.TextColors;
import Coupon_System.JavaBeans.Category;
import Coupon_System.JavaBeans.Coupon;
import Coupon_System.DesignColors.TextColors;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouponsDBDAO implements CouponsDAO{
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    @Override
    public void addCoupon(Coupon coupon) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "insert into coupons (COMPANY_ID,CATEGORY_ID,TITLE,DESCRIPTION,START_DATE,END_DATE,AMOUNT,PRICE,IMAGE ) values ("+coupon.getCompanyID() + "," + (coupon.getCategory().ordinal()+1)+",'" + coupon.getTitle() +"','" + coupon.getDescription() + "','" + coupon.getStartDate() + "','" + coupon.getEndDate() +"'," +coupon.getAmount() + "," + coupon.getPrice() +",'" +coupon.getImage()+"' )";
        PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();

        //ResultSet resultSet = preparedStatement.getGeneratedKeys();
        System.out.println(TextColors.ANSI_BLUE+"Coupon was added successfully!"+TextColors.ANSI_RESET);
        connectionPool.restoreConnection(connection);
    }

    @Override
    public void updateCoupon(Coupon coupon) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "update coupons set category_id=" + (coupon.getCategory().ordinal()+1)+ ", title='"+coupon.getTitle()+"',description='" +coupon.getDescription() +"',start_date='" +coupon.getStartDate() + "',end_date='"+coupon.getEndDate()+"',amount="+coupon.getAmount() +",price="+coupon.getPrice()+",image='"+coupon.getImage()+"' where id="+coupon.getId() ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        //ResultSet resultSet = preparedStatement.getGeneratedKeys();
        System.out.println(TextColors.ANSI_BLUE+"Coupon was updated successfully!"+TextColors.ANSI_RESET);
        connectionPool.restoreConnection(connection);

    }

    @Override
    public void deleteCoupon(int couponID) throws SQLException, InterruptedException {
        Object lock = new Object() ;
        synchronized (lock) {
            Connection connection = connectionPool.getConnection();
            String sql = "delete from coupons where id=" + couponID;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println(TextColors.ANSI_BLUE + "Coupon was deleted successfully!" + TextColors.ANSI_RESET);
            connectionPool.restoreConnection(connection);
        }
        }

    @Override
    public ArrayList<Coupon> getAllCoupons() throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        List<Coupon> resList = new ArrayList<Coupon>();
        String sql = "select * from coupons"; // select * ... will also work here.
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultset = preparedStatement.executeQuery();
        connectionPool.restoreConnection(connection);
        while (resultset.next()) {//[ [id, name, salary], [id, name, salary]... ]
            Coupon coupon = new Coupon();
            int coupon_id = resultset.getInt("ID");
            coupon.setId(coupon_id);
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
            int amount= resultset.getInt("amount");
            coupon.setAmount(amount);
            int price = resultset.getInt("price");
            coupon.setPrice(price);
            String image = resultset.getString("image");
            coupon.setImage(image);
            resList.add(coupon);
        }

        return (ArrayList<Coupon>) resList;
    }

    @Override
    public Coupon getOneCoupon(int couponID) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "select * from coupons where id=" + couponID; // select * ... will also work here.
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultset = preparedStatement.executeQuery();
        connectionPool.restoreConnection(connection);
        Coupon coupon = new Coupon();
        coupon.setId(couponID);
        resultset.next();
        int category_id = resultset.getInt("category_id");
        coupon.setCategory(Category.values()[category_id]);
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
        int amount= resultset.getInt("amount");
        coupon.setAmount(amount);
        int price = resultset.getInt("price");
        coupon.setPrice(price);
        String image = resultset.getString("image");
        coupon.setImage(image);
        return coupon;
    }

    @Override
    public void addCouponPurchase(int customerID, int couponID) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "insert into customers_vs_coupons (customer_id,coupon_id ) values (" + customerID + " ," + couponID+")";
        PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();

        //ResultSet resultSet = preparedStatement.getGeneratedKeys();
        System.out.println(TextColors.ANSI_BLUE+"Coupon has been purchased successfully!"+TextColors.ANSI_RESET);
        connectionPool.restoreConnection(connection);

    }

    @Override
    public void deleteOneCouponPurchase(int customerID, int couponID) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "delete from customers_vs_coupons where customer_id="+customerID +" and coupon_id="+couponID;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        connectionPool.restoreConnection(connection);
    }

    @Override
    public void deleteCouponPurchaseById(int couponID) throws SQLException, InterruptedException {
        Object lock = new Object() ;
        synchronized (lock) {
            Connection connection = connectionPool.getConnection();
            String sql = "delete from customers_vs_coupons where coupon_id=" + couponID;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            connectionPool.restoreConnection(connection);
        }
    }

    @Override
    public void deleteCustomerPurchaseById(int customerID) throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "delete from customers_vs_coupons where customer_id="+customerID;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        connectionPool.restoreConnection(connection);
    }


    public  void loadCategories() throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String category_name;
        for (Category category : Category.values()
             ) {
           category_name =  category.name();
            String sql = "insert into categories (NAME ) values ('" +category_name + "')";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
        connectionPool.restoreConnection(connection);
    }




}
