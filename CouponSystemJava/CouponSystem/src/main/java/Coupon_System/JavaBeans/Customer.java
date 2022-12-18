/**
 * The Customer is a class that we create objects from in the program
 * The fields of the Customer are: id, firstName, lastName, email, password, and coupons(arraylist)
 * coupons is the only field that is not used in the dataBase in the SQL.
 */
package Coupon_System.JavaBeans;

import Coupon_System.JavaBeans.Coupon;

import java.util.ArrayList;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ArrayList<Coupon> couponList;

    public Customer(String firstName, String lastName, String email, String password, ArrayList<Coupon> couponList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.couponList = couponList;

    }

    public Customer(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Customer(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", couponList=" + couponList +
                '}';
    }
}
