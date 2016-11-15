package db.models;

import java.util.ArrayList;

import app.core.Cart;
import app.core.SQLModel;
import app.core.StoreItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User implements SQLModel {

    private int ID;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String phone;
    private int birthDay;
    private int birthMonth;
    private int birthYear;
    private boolean isAdmin = false;
    private ArrayList<Transaction> transactions;
    private Cart cart;

    public User(String username){
        cart = new Cart();
        setUsername(username);

    }

    public Cart getCart(){
        return cart;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public String getUsername(){
        return username;
    }

    public void setID(int id){
        ID = id;
    }

    public int getID(){
        return ID;
    }

    public void setAdminPriveleges(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin(){
        return isAdmin;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
