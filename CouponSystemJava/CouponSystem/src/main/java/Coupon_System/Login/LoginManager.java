/**
 * The loginManager is responsible for checking the correctness of the login details of the client.
 * Also, it is responsible for returning ClientFacade object that fits the user's clientType.
 * The loginManager makes use of the functions: getCompanyIdByEmailAndPassword and getCustomerIdByEmailAndPassword
 * in order to get the id of the client by using the email and password in order to assert the client in the dataBase.
 *
 * It throws custom Exceptions when entered wrong details: (LoginException) with proper message to the user.
 */

package Coupon_System.Login;

import Coupon_System.Facade.*;
import Coupon_System.DesignColors.TextColors;
import java.sql.SQLException;


public class LoginManager {
    public static enum ClientType {ADMINISTRATOR,COMPANY,CUSTOMER}


     private static LoginManager instance = new LoginManager();
    private LoginManager(){
    }

    public static LoginManager getInstance(){
        return instance;
    }




    public ClientFacade Login(String email,String password,ClientType clientType) throws SQLException, InterruptedException {
        switch(clientType)
        {
            case COMPANY:
            {
                CompanyFacade companyFacade = new CompanyFacade();
               if( companyFacade.login(email,password)) {
                   int companyID = companyFacade.getCompanyIDByEmailPassword(email, password);
                   companyFacade.setCompanyID(companyID);
                   System.out.println(TextColors.ANSI_YELLOW+"Company has logged-in successfully!"+TextColors.ANSI_RESET);
                   return companyFacade;
               }
               else
               {
                   try {
                       throw new LoginException("Either email or password does not exist for this company");
                   } catch (LoginException e) {
                       System.out.println(TextColors.ANSI_RED +"Exception: " + e.getMessage()+"" +TextColors.ANSI_RESET);
                   }
               }
            }

            case CUSTOMER:
            {
                CustomerFacade customerFacade = new CustomerFacade();
               if( customerFacade.login(email,password))
                {
                    int customerID = customerFacade.getCustomerIDByEmailPassword(email, password);
                    customerFacade.setCustomerID(customerID);
                    System.out.println(TextColors.ANSI_YELLOW+"Customer has logged-in successfully!"+TextColors.ANSI_RESET);
                    return customerFacade;
                }
                else
               {
                   try {
                       throw new LoginException("Either email or password does not exist for this customer");
                   } catch (LoginException e) {
                       System.out.println(TextColors.ANSI_RED +"Exception: " + e.getMessage()+"" +TextColors.ANSI_RESET);
                   }

               }

            }

            case ADMINISTRATOR:
            {
                AdminFacade adminFacade = new AdminFacade();
               if( adminFacade.login(email,password))
               {
                   System.out.println(TextColors.ANSI_YELLOW+"Admin has logged-in successfully!"+TextColors.ANSI_RESET);
                   return adminFacade;
               }
               else {
                    try {
                        throw new LoginException("Either email or password does not exist");
                    } catch (LoginException e) {
                        System.out.println(TextColors.ANSI_RED +"Exception: " + e.getMessage()+"" +TextColors.ANSI_RESET);
                    }

                }
            }

        }
        return null;
    }




}
