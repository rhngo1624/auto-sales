package app.controllers;

import java.util.ResourceBundle;
import java.net.URL;

import app.utils.ModalUtil;
import app.utils.Resource;
import javafx.fxml.FXML;

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


}
