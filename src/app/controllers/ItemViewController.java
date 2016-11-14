package app.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import app.core.Resource;
import app.ui.items.SelectedItemPane;
import app.ui.items.StoreItemPane;
import app.utils.ModalUtil;
import app.utils.Session;
import db.models.Accessory;
import db.models.Car;
import app.core.StoreItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ItemViewController implements Initializable {

    @FXML
    private VBox propertiesPane;
    @FXML
    private Image logoImage;
    @FXML
    private Image itemImage;
    @FXML
    private ImageView itemView;
    @FXML
    private ImageView logoView;
    @FXML
    private Label nameLabel;
    @FXML
    private Label fuelEconomyLabel;
    @FXML
    private Label fuelEconomy;
    @FXML
    private Label transmissionLabel;
    @FXML
    private Label transmission;
    @FXML
    private Label totalSeatingLabel;
    @FXML
    private Label totalSeating;
    @FXML
    private Label doorAmountLabel;
    @FXML
    private Label doorAmount;
    @FXML
    private Label engineLabel;
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

        itemView.setOnMouseEntered((e) ->{
            itemView.setOpacity(1.0);
        });

        itemView.setOnMouseExited((e) -> {
            itemView.setOpacity(0.8);
        });

        if(Session.getInstance().isActive()){

            itemView.setOnMouseClicked((e) -> {

                Session.getInstance().getUser().addCartItem(item);
                ModalUtil.showMessage(item.getName() + " was added!");

            });

        }

    }

    public ItemViewController(){

        StoreItemPane pane = SelectedItemPane.get();

        if(pane.getItem() != null){
            item = pane.getItem();
        }else{
            System.exit(-1);
        }

    }

    private void getLogo(){

        String name = item.getName();
        String basePath = "resources/imgs/logos/";

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
            itemImage = new Image(item.getImageLocation());
            itemView.setImage(itemImage);

        }

    }

    private void getProperties(){

        if(item.getClass().getSimpleName().equals("Accessory")){

            Accessory acc = (Accessory)item;

            nameLabel.setText(acc.getName());
            fuelEconomyLabel.setText("Description: ");
            fuelEconomy.setText(acc.getDescription());
            propertiesPane.getChildren().remove(1, 5);
            rating.setText("N/A");

        }else{

            Car car = (Car)item;

            nameLabel.setText(car.getName());
            fuelEconomy.setText(car.getFuelEconomy());
            transmission.setText(car.getTransmission());
            totalSeating.setText(String.valueOf(car.getTotalSeating()));
            doorAmount.setText(String.valueOf(car.getDoorAmount()));
            engine.setText(car.getEngineType());
            rating.setText("N/A");

        }

    }



    public void  viewReviews(){
        ModalUtil.setupAndShow(Resource.REVIEWS, "Reviews");
    }

    public void writeReview(){
        if(Session.getInstance().isActive()){
            ModalUtil.setupAndShow(Resource.WRITE_REVIEW, "Write Review");
        }else{
            ModalUtil.showMessage("Only logged in users can write reviews");
        }

    }

}
