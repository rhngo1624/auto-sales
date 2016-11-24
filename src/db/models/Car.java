package db.models;


import java.util.ArrayList;

import app.core.SQLModel;
import app.core.StoreItem;

public class Car extends StoreItem implements SQLModel {

    private String make;
    private String model;
    private int year;
    private String fuelEconomy;
    private String transmission;
    private int totalSeating;
    private int doorAmount;
    private String engineType;
    private int rating;
    private ArrayList<Review> reviews;

    public Car(String make, String model, int year){
        setMake(make);
        setModel(model);
        setYear(year);
        setName(this.make + " " + this.model + " " + this.year);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFuelEconomy() {
        return fuelEconomy;
    }

    public void setFuelEconomy(String fuelEconomy) {
        this.fuelEconomy = fuelEconomy;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getTotalSeating() {
        return totalSeating;
    }

    public void setTotalSeating(int totalSeating) {
        this.totalSeating = totalSeating;
    }

    public int getDoorAmount() {
        return doorAmount;
    }

    public void setDoorAmount(int doorAmount) {
        this.doorAmount = doorAmount;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString(){
        return getName();
    }

}
