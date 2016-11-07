package app.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import app.ui.SelectedItemPane;
import app.ui.StoreItemPane;
import db.models.Car;
import db.models.StoreItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ItemViewController implements Initializable {
    @FXML
    private BorderPane logoPane;
    @FXML
    private Image logoImage;
    @FXML
    private Image carImage;
    @FXML
    private ImageView carView;
    @FXML
    private ImageView logoView;
    @FXML
    private Label nameLabel;
    @FXML
    private Label fuelEconomy;
    @FXML
    private Label transmission;
    @FXML
    private Label totalSeating;
    @FXML
    private Label doorAmount;
    @FXML
    private Label engine;
    @FXML
    private Label rating;
    private StoreItem item;


    @FXML
    public void initialize(URL location, ResourceBundle rb){

        getLogo();
        getImage();
        getProperties();

    }

    public ItemViewController(){

        StoreItemPane pane = SelectedItemPane.get();
        System.out.println("INIT~");

        if(pane.getItem() != null){
            item = pane.getItem();
            System.out.println("ITEM: " + item);
        }else{
            System.exit(-1);
        }

    }

    private void getLogo(){

        String name = item.getName();
        String basePath = "resources/imgs/logos/";

        System.out.println("ITEM NAME: " + item.getName());

        if(name.contains("Ford")){
            logoImage = new Image(basePath + "ford_logo.png");

        }else if(name.contains("Dodge")){
            logoImage = new Image(basePath + "dodge_logo.png");
        }else if(name.contains("Lexus")){
            logoImage = new Image(basePath + "lexus_logo.png");
        }else if(name.contains("Toyota")){
            logoImage = new Image(basePath + "toyota_logo.png");
        }else{
            logoImage = new Image(basePath + "auto_sales_logo.png");
        }

        logoView.setImage(logoImage);
        logoView.setFitHeight(100);
        logoView.setFitWidth(200);

    }

    private void getImage(){

        if(item.getImageLocation() != null){
            System.out.println("IMG LOC: " + item.getImageLocation());
            carImage = new Image(item.getImageLocation());
            carView.setImage(carImage);

        }

    }

    private void getProperties(){

        Car car = (Car)item;

        nameLabel.setText(car.getName());
        fuelEconomy.setText(car.getFuelEconomy());
        transmission.setText(car.getTransmission());
        totalSeating.setText(String.valueOf(car.getTotalSeating()));
        doorAmount.setText(String.valueOf(car.getDoorAmount()));
        engine.setText(car.getEngineType());
        rating.setText("N/A");

    }



    public void  viewReviews(){

    }

    public void writeReview(){


    }

}