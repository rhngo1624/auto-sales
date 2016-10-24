package app.controllers;

import java.sql.SQLException;
import java.util.ResourceBundle;
import java.net.URL;

import app.utils.ModalUtil;
import app.utils.Resource;
import db.models.SQLModel;
import db.tables.Cars;
import db.models.Car;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *  Controller Class for Main Page
 */
public class MainController {

    private final String CHECKOUT_TITLE = "Check Out";
    private final String CUSTOMIZE_TITLE = "Customize"; // TODO: add fxml file and method
    private final String LOGIN_TITLE = "AutoSales Login";
    private final String MAINTENANCE_TITLE = "Maintenance"; // TODO: add fxml file and method
    private final String REGISTER_TITLE = "Register";
    private final String TESTDRIVE_TITLE = "Test Drive"; // TODO: add fxml file and method

    /**
     * Controller initialized by JVM once all document properties have been loaded
     * It is not possible to reference FXML objects until this method is called.
     *
     * WARNING: Referencing FXML objects in constructor of controller results in
     *          NULL POINTER EXCEPTION.
     *
     * @param Location - location of fxml file
     * @param rb - bundle containing object properties
     *
     */
    @FXML
    public void initialize(URL Location, ResourceBundle rb){

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

    private VBox makeCarGrid(Car car){

        VBox grid = new VBox();
        Label name = new Label(car.getMake() + " " + car.getModel() + " " + car.getYear());
        Label price = new Label("$" + car.getPrice());
        ImageView carImage = new ImageView(new Image(car.getImageLocation()));
        Button viewButton = new Button("View");

        grid.getChildren().add(name);
        grid.getChildren().add(carImage);
        grid.getChildren().add(price);
        grid.getChildren().add(viewButton);

    }


}
