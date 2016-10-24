package app.controllers;

import java.sql.SQLException;
import java.util.ResourceBundle;
import java.net.URL;

import app.utils.CarPane;
import app.utils.ModalUtil;
import app.utils.Resource;
import app.utils.StageUtil;
import db.models.SQLModel;
import db.tables.Cars;
import db.models.Car;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    @FXML
    private GridPane mainGrid;

    @FXML
    public void initialize(URL location, ResourceBundle rb){
       ModalUtil.setupAndShow();
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

    private void makeCarGrid(){

        Cars table = new Cars();
        ObservableList<SQLModel> rows = null;

        try{

            rows = table.getAllRows();

        }catch(SQLException e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        for(SQLModel model : rows){

            CarPane carView = new CarPane(((Car)model));

            mainGrid.add(carView, 0, 0);

        }


    }


}
