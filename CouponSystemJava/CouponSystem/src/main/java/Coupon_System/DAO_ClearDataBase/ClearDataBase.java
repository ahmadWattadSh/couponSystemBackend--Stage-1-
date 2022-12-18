/**
 * ClearDataBase - is used to clear all the data in tables: coupons,companies,customer_vs_coupons,categories,customers.
 * This class is used in testAll class so that there is no need to clear all the old data by hand.
 * if not used, it is not possible to repeat running the testAll class without clearing the database by hand.
 * The class holds two functions :
 * clearDataBase - which handles clearing the data.
 * clearAutoIncrement - which handles resetting the auto-increment to 1.
 */
package Coupon_System.DAO_ClearDataBase;

import Coupon_System.ConnectionPool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClearDataBase {
   private ConnectionPool connectionPool = ConnectionPool.getInstance();


    public  void clearDataBase() throws SQLException, InterruptedException {
        Connection connection = connectionPool.getConnection();
        String sql = "DELETE FROM customers_vs_coupons WHERE (CUSTOMER_ID > 0);";
        PreparedStatement  preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
         sql = "DELETE FROM coupons WHERE (ID > 0)   ";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        sql = "DELETE FROM companies WHERE (ID > 0)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        sql = "DELETE FROM customers WHERE (ID > 0);";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        sql = "DELETE FROM categories WHERE (ID > 0);";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
         connectionPool.restoreConnection(connection);
        }

        public void clearAutoIncrement() throws SQLException, InterruptedException {
        String sql =  "ALTER TABLE companies AUTO_INCREMENT = 1";
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            sql =  "ALTER TABLE companies AUTO_INCREMENT = 1";
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            sql =  "ALTER TABLE customers AUTO_INCREMENT = 1";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
             sql =  "ALTER TABLE coupons AUTO_INCREMENT = 1";
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            sql =  "ALTER TABLE categories AUTO_INCREMENT = 1";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            connectionPool.restoreConnection(connection);
        }


    }

