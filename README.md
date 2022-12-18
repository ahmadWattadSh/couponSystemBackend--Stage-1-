# Coupon System Backend Project

In this part of the project we created a program where 

# FOR USER:

## ***Installation instructions:***

In CouponSystem folder, there are two files:

### 1. CouponSystemDataBase (MySQL version) :

Instructions:
* create a new connection in MySQL.
* create a new schema in MySQL with the name "couponsystem".
* Go to 'administration' label from connection. Click Data import/restore
* tick the 'Import from Self-Contained File' box, and import 'CouponSystemDataBase'.
* Make sure to set the 'Default Target Schema' to 'couponsystem' - the schema you created -.
* Start Import.

### 2. CouponSystemJava  (Java version) :

Instructions:
* Open Java IDE.
* File > Open, choose the 'CouponSystemJava' file path.
* Reload Maven Dependencies.
* Make sure to click 'Trust This Project' tab.
* Go to ConnectionPool class and change the DB_NAME, USER_NAME, AND PASSWORD if needed.


*** Usernames, Emails, and Passwords ****

 #### Admin:
email: admin@admin.com
password: admin

  Companies: 
- 	company_name1	company_email1@company.com	password1
-   company_name2	company_email2@company.com	password2
-   company_name3	company_email3@company.com	password3
-   company_name4	company_email4@company.com	password4
-   company_name5	company_email5@company.com	password5
-   company_name6	company_email6@company.com	password6
-   company_name7	company_email7@company.com	password7
-   company_name8	company_email8@company.com	password8
-   company_name9	company_email9@company.com	password9
-   company_name1	company_email10@company.com	password10

  Customers:
-   firstName1	lastName1	customer_email1@customer.com	password1
-   firstName2	lastName2	customer_email2@customer.com	password2
-   firstName3	lastName3	customer_email3@customer.com	password3
-   firstName4	lastName4	customer_email4@customer.com	password4
-   firstName5	lastName5	customer_email5@customer.com	password5
-   firstName6	lastName6	customer_email6@customer.com	password6
-   firstName7	lastName7	customer_email7@customer.com	password7
-   firstName8	lastName8	customer_email8@customer.com	password8
-   firstName9	lastName9	customer_email9@customer.com	password9
-   firstName10	lastName10	customer_email10@customer.com	password10

*** 

The functions that you would be able to use after installation:

#### Admin:
- Display All Companies
- Display One Company
- Create a new company
- Update company
- Delete company 
- Display All Customers
- Display One Customer
- Add a new customer
- Update Customer
- Delete customer

##### Company:
- Display company's details
- Display all the company's coupons 
- Display company's coupons sorted by MaxPrice or Category 
- Create new coupon
- Update coupon
- Delete coupon


#### Customer:
- Display customer's details
- Display all customer's coupons
- Display customer's coupons sorted by MaxPrice or Category
- Purchase coupon

