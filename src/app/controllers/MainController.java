package app.controllers;

import java.util.ResourceBundle;
import java.net.URL;

import app.ui.AccessoryDisplay;
import app.ui.CarDisplay;
import app.utils.ModalUtil;
import app.utils.Resource;
import db.models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

/**
 *  Controller Class for Main Page
 */
public class MainController implements Initializable {

    private final String CHECKOUT_TITLE = "Check Out";
    private final String CUSTOMIZE_TITLE = "Customize"; // TODO: add fxml file and method
    private final String LOGIN_TITLE = "AutoSales Login";
    private final String MAINTENANCE_TITLE = "Maintenance"; // TODO: add fxml file and method
    private final String REGISTER_TITLE = "Register";
    private final String TESTDRIVE_TITLE = "Test Drive"; // TODO: add fxml file and method
    private boolean isCarDisplay = true;
    private static boolean sessionActive = false;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private FlowPane mainBorderPane;

    @FXML
    private Button displayButton;

    @FXML
    public void initialize(URL location, ResourceBundle rb){
        displayCars();

        if(MainController.sessionActive){

            loginButton.setText("Log Out");
            loginButton.setOnAction((e) -> {



            });
            registerButton.setVisible(false);

        }

    }

    /**
     *  Initializes Login Controller when login button is clicked.
     */
    public void login(){

        ModalUtil.setupAndShow(Resource.LOGIN, LOGIN_TITLE);

    }

    /**
     *  Initializes Register Controller when register button is clicked.
     */
    public void register(){

       ModalUtil.setupAndShow(Resource.REGISTER, REGISTER_TITLE);

    }

    /**
     *  Initializes Checkout Controller when checkout button is clicked.
     */
    public void checkout(){

        ModalUtil.setupAndShow(Resource.CHECKOUT, CHECKOUT_TITLE);
    }

    private void displayAccessories(){

        mainBorderPane.getChildren().clear();

        AccessoryDisplay display = new AccessoryDisplay();
        mainBorderPane.getChildren().add(display.getDisplay());

        isCarDisplay = false;

    }

    private void displayCars(){

        mainBorderPane.getChildren().clear();

        CarDisplay display = new CarDisplay();
        mainBorderPane.getChildren().add(display.getDisplay());

        isCarDisplay = true;

    }

    public void switchDisplay(){

        ImageView icon = new ImageView();
        icon.setFitHeight(35);
        icon.setFitWidth(40);

        if(isCarDisplay){
            Image carsImage = new Image("resources/imgs/icons/car_display_icon.png");
            icon.setImage(carsImage);
            displayAccessories();
            displayButton.setGraphic(icon);
        }else{
            Image seatImage = new Image("resources/imgs/icons/safety-seat.png");
            icon.setImage(seatImage);
            displayCars();
            displayButton.setGraphic(icon);
        }

    }

}
