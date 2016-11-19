package app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import app.core.StoreItem;
import app.ui.display.AccessoryDisplay;
import app.ui.display.CarDisplay;
import app.ui.display.SearchDisplay;
import app.utils.ModalUtil;
import app.core.Resource;
import app.utils.Session;
import app.utils.StageUtil;
import db.models.Transaction;
import db.tables.Transactions;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Session session = Session.getInstance();

    @FXML
    private Button adminButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private FlowPane mainBorderPane;

    @FXML
    private Button displayButton;

    @FXML
    private TextField searchField;

    @FXML
    private ToolBar toolbar;

    private SearchDisplay searchDisplay;

    @FXML
    public void initialize(URL location, ResourceBundle rb){

        displayCars();

        toolbar.getItems().remove(2);

        searchField.textProperty().addListener((ob, ov, nv) -> {

            search();

        });

        if(session.isActive()){

            loginButton.setText("Log Out");
            loginButton.setOnAction((e) -> {

                session.end();
                try{

                    ModalUtil.showMessage("Logged Out!");
                    StageUtil.getInstance().getMainStage().
                            getScene().setRoot(FXMLLoader.load(Resource.MAIN));
                }catch(IOException ex){
                    System.out.println(ex.getMessage());
                    System.exit(-1);
                }

            });

            if(session.getUser().isAdmin()){
                toolbar.getItems().add(2, adminButton);
            }

            registerButton.setVisible(false);

        }

    }

    public void administration(){

        ModalUtil.setupAndShow(Resource.ADMIN, "Administration", true);

    }

    /**
     *  Initializes Login Controller when login button is clicked.
     */
    public void login(){

        ModalUtil.setupAndShow(Resource.LOGIN, LOGIN_TITLE, false);

    }

    /**
     *  Initializes Register Controller when register button is clicked.
     */
    public void register(){

       ModalUtil.setupAndShow(Resource.REGISTER, REGISTER_TITLE, false);

    }

    /**
     *  Initializes Checkout Controller when checkout button is clicked.
     */
    public void checkout(){

        ModalUtil.setupAndShow(Resource.CHECKOUT, CHECKOUT_TITLE, false);
    }

    private void displayAccessories(){

        ExecutorService executor = Executors.newFixedThreadPool(1);

        mainBorderPane.getChildren().clear();

        Callable<Group> carDisplay = () -> {
            AccessoryDisplay display = new AccessoryDisplay();
            display.createElements();
            return display.getDisplay();
        };

        Future<Group> results = executor.submit(carDisplay);

        try{
            mainBorderPane.getChildren().add(results.get());
            isCarDisplay = false;
        }catch(ExecutionException ee){
            System.out.println("Execution exception");
        }catch(InterruptedException ie){
            System.out.println("Interrupted exception");
        }finally{
            executor.shutdown();
        }

    }

    private void displayCars(){

        ExecutorService executor = Executors.newFixedThreadPool(1);

        mainBorderPane.getChildren().clear();

        Callable<Group> carDisplay = () -> {
            CarDisplay display = new CarDisplay();
            display.createElements();
            return display.getDisplay();
        };

        Future<Group> results = executor.submit(carDisplay);

        try{
            mainBorderPane.getChildren().add(results.get());
            isCarDisplay = true;
        }catch(ExecutionException ee){
            System.out.println("Execution exception");
        }catch(InterruptedException ie){
            System.out.println("Interrupted exception");
        }finally {
            executor.shutdown();
        }
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

    private void search(){

            ExecutorService executor = Executors.newFixedThreadPool(1);

            if(!searchField.getText().isEmpty()){

                mainBorderPane.getChildren().clear();

                Callable<Group> searchTask = () -> {
                        SearchDisplay display = new SearchDisplay();
                        display.setSearch(searchField.getText());
                        display.createElements();
                        return display.getDisplay();

                };

                Future<Group> results = executor.submit(searchTask);

                try{
                    mainBorderPane.getChildren().add(results.get());
                }catch(ExecutionException ee){
                    System.out.println("Execution exception");
                }catch(InterruptedException ie){
                    System.out.println("Interrupted exception");
                }finally {
                    executor.shutdown();
                }

            }else{

                if(isCarDisplay){

                    displayCars();

                }else{

                    displayAccessories();
                }

            }

    }


}
