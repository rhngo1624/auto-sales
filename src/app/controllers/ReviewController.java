package app.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ReviewController implements Initializable {

    @FXML
    private Label typeLabel;
    @FXML
    private Label itemName;
    @FXML
    private Label date;
    @FXML
    private TextField reviewTitleField;
    @FXML
    private TextArea reviewTextArea;
    @FXML
    private CheckBox firstNameShowBox;
    @FXML
    private Button submitButton;

    @FXML
    public void initialize(URL location, ResourceBundle rb){

    }



}
