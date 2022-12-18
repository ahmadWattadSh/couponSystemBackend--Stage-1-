/**
 * The company is a class that we create objects from in the program
 * The fields of the company are: id, name, email, password, and coupons(arraylist)
 * coupons is the only field that is not used in the dataBase in the SQL.
 */


package Coupon_System.JavaBeans;

import java.util.ArrayList;

public class Company {
    private int id;
    private String name;
    private String email;
    private String password;
    private ArrayList<Coupon> couponList;

    public Company(String name, String email, String password, ArrayList<Coupon> couponList) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.couponList = couponList;
    }

    // Current COnstructorTEST
    public Company(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public Company(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(ArrayList<Coupon> couponList) {
        this.couponList = couponList;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", couponList=" + couponList +
                '}';
    }
}
