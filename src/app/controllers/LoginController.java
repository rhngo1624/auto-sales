package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import app.utils.ModalUtil;
import app.core.Resource;
import app.utils.Session;
import app.utils.StageUtil;
import db.models.User;
import db.tables.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *  Controller Class for Login Page
 */
public class LoginController implements Initializable {

    @FXML
    TextField usernameField;

    @FXML
    TextField passwordField;

    private Session session = Session.getInstance();

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

                session.setUser(user);
                ModalUtil.showMessage("Logged In! Welcome " + user.getUsername() + "!");
                try{
                    StageUtil.getInstance().getMainStage().
                            getScene().setRoot(FXMLLoader.load(Resource.MAIN));
                    usernameField.getScene().getWindow().hide();
                }catch(IOException ex){
                    System.out.println(ex.getMessage());
                    System.exit(-1);
                }

            }else{

                ModalUtil.showWarning("Could not find user.");

            }

        }catch(SQLException e){
            System.out.println(e.getMessage());

        }

    }

}

