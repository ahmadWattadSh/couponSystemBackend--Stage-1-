/**
 * The clientFacade is the Factory of all the possible clients that are able to log in.
 * The login Function  is abstract in order for any class inheriting the ClientFace can have the ability to log in.
 *
 * It throws SQL and Interrupted Exceptions - to be handled later in the testAll
 */

package Coupon_System.Facade;


import Coupon_System.DAO.*;

import java.sql.SQLException;

public abstract class ClientFacade {
    protected CompaniesDAO companiesDAO= new CompaniesDBDAO();
    protected CustomersDAO customersDAO = new CustomersDBDAO();
    protected CouponsDAO couponsDAO = new CouponsDBDAO();

    public abstract boolean login(String email, String password) throws SQLException, InterruptedException;
}
