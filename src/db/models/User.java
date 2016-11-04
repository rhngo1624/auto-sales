package db.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import db.tables.SQLTable;
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
    private boolean isAdmin = false;
    private ArrayList<Transaction> transactions;
    private ArrayList<StoreItem> cart;

    public User(String username){
        cart = new ArrayList<>();
        setUsername(username);

    }

    public void addCartItem(StoreItem item){

        cart.add(item);

    }

    public void delCartItem(StoreItem item){

        cart.remove(item);

    }

    public ObservableList<StoreItem> dumpCart(){
        ObservableList<StoreItem> items = FXCollections.observableArrayList();
        items.addAll(cart);
        return items;
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
    
}
