package app.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import app.utils.ModalUtil;
import app.utils.Resource;
import app.utils.Session;
import db.models.User;
import db.tables.Users;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *  Controller Class for Login Page
 */
public class LoginController implements Initializable {

    @FXML
    TextField usernameField;

    @FXML
    TextField passwordField;

    /**
     *  Called after FXML file is loaded.
     */
    @FXML
    public void initialize(URL location, ResourceBundle rb){



    }

    public void validate(){

        Users table = new Users();
        User user;

        try{

            user = table.validate(usernameField.getText(), passwordField.getText());

            if(user != null){

                Session.setUser(user);
                ModalUtil.showMessage("Logged In! Welcome " + user.getUsername() + "!");
                usernameField.getScene().getWindow().hide();

            }else{

                ModalUtil.showWarning("Could not find user.");

            }

        }catch(SQLException e){
            System.out.println(e.getMessage());

        }

    }

}

