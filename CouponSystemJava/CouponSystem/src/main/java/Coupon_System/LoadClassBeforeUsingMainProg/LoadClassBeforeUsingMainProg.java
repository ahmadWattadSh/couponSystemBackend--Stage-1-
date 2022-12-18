/**
 * This class is to be run before using the main to load the basic data and avoid
 * Exception: Cannot add or update a child row: a foreign key constraint fails.
 * This is a copy of testAll without delete, update and unnecessary console messages.
 * These details will be depicted in a separate notepad for the user to review.
 */
package Coupon_System.LoadClassBeforeUsingMainProg;

import Coupon_System.DAO.CouponsDBDAO;
import Coupon_System.DesignColors.TextColors;
import Coupon_System.Facade.AdminFacade;
import Coupon_System.Facade.CompanyFacade;
import Coupon_System.JavaBeans.Category;
import Coupon_System.JavaBeans.Company;
import Coupon_System.JavaBeans.Coupon;
import Coupon_System.JavaBeans.Customer;
import Coupon_System.Login.LoginManager;
import Coupon_System.DesignColors.TextColors;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.SQLException;

public class LoadClassBeforeUsingMainProg {
    @Autowired
    private static LoginManager loginManager = LoginManager.getInstance() ;

    public static void loadData() throws SQLException, InterruptedException {

     //   try {
            CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
            couponsDBDAO.loadCategories();

            //ADMINS
            System.out.println(TextColors.ANSI_CYAN +"****  Entries  ****" + TextColors.ANSI_RESET);
            String admin_email = "admin@admin.com";
            String admin_password = "admin";

            LoginManager.ClientType admin_clientType = LoginManager.ClientType.ADMINISTRATOR;


            AdminFacade adminFacade = (AdminFacade) loginManager.Login(admin_email, admin_password, admin_clientType) ;

//            AdminFacade adminFacade = (AdminFacade) loginManager.Login(admin_email, admin_password, admin_clientType);
/*
            System.out.println(TextColors.ANSI_CYAN +"****  LOADING COMPANIES  ****"+ TextColors.ANSI_RESET);
            for (int i = 0; i < 10; i++) {
                Company company = new Company("company_name" + (i + 1), "company_email" + (i + 1) + "@company.com", "password" + (i + 1));
                adminFacade.addCompany(company);
            }



            System.out.println(TextColors.ANSI_CYAN +"**** LOADING CUSTOMERS *****"+ TextColors.ANSI_RESET);
            for (int i = 1; i <= 10; i++) {
                Customer customer = new Customer("firstName" + i, "lastName" + i, "customer_email" + i + "@customer.com", "password" + i);
                adminFacade.addCustomer(customer);
            }

            CompanyFacade companyFacade1 = (CompanyFacade) loginManager.Login("company_email1@company.com", "password1", LoginManager.ClientType.COMPANY);
            CompanyFacade companyFacade2 = (CompanyFacade) loginManager.Login("company_email2@company.com", "password2", LoginManager.ClientType.COMPANY);


            System.out.println(TextColors.ANSI_CYAN +"**** LOADING COUPONS *****"+ TextColors.ANSI_RESET);
            companyFacade1.addCoupon(new Coupon(Category.FOOD, "ProteinChocolate", "All-In", Date.valueOf("2015-3-29"), Date.valueOf("2070-7-25"), 2, 70, "image1"));
            companyFacade1.addCoupon(new Coupon(Category.TOYS, "Bikachu", "Cartoon-Toys",Date.valueOf("2023-2-2"), Date.valueOf("2023-2-2"), 2, 115, "image2"));
            companyFacade1.addCoupon(new Coupon(Category.ELECTRICITY, "FAN", "ELECTRA", Date.valueOf("2023-2-2"),Date.valueOf("2023-2-2"), 1, 250, "image3"));
            companyFacade2.addCoupon(new Coupon(Category.FURNITURE, "SOFA", "ComfortableSofa", Date.valueOf("2023-2-2"), Date.valueOf("2023-2-2"), 2, 1000, "imageSOFA"));
            companyFacade2.addCoupon(new Coupon(Category.TOYS, "Cards", "Card Games", Date.valueOf("2023-2-2"), Date.valueOf("2023-2-2"), 2, 20, "imageCard"));
            companyFacade2.addCoupon(new Coupon(Category.ELECTRICITY, "TV", "MAC", Date.valueOf("2023-2-2"), Date.valueOf("2023-2-2"), 1, 1250, "imageElectra"));

            System.out.println(TextColors.ANSI_WHITE_BACKGROUND + "Finished Loading all Data successfully"+ TextColors.ANSI_RESET);





        }
        catch
        (Exception e)
        {

            System.out.println(TextColors.ANSI_RED + "Exception: there is either problem in sql functions or threads functionality!" + TextColors.ANSI_RESET);
        }
 */
    }

}
