package app.controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import app.core.Resource;
import app.core.StoreItem;
import app.ui.items.SelectedItemPane;
import app.utils.ModalUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


public class WriteReviewController implements Initializable {

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
    private RadioButton rating1;
    @FXML
    private RadioButton rating2;
    @FXML
    private RadioButton rating3;
    @FXML
    private RadioButton rating4;
    @FXML
    private RadioButton rating5;
    @FXML
    private Button submitButton;
    private ToggleGroup ratingGroup;
    private StoreItem item;
    private Date timestamp;

    @FXML
    public void initialize(URL location, ResourceBundle rb){
        itemName.setText(item.getName());
        date.setText(getFormattedDate());

    }

    public WriteReviewController(){
        if(SelectedItemPane.get() != null){
            this.item = SelectedItemPane.get().getItem();
        }
        timestamp = new Date();
        ratingGroup = new ToggleGroup();

    }

    private String getFormattedDate(){
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        return df.format(timestamp);
    }

    private void setupRadioButtons(){
        rating1.setToggleGroup(ratingGroup);
        rating2.setToggleGroup(ratingGroup);
        rating3.setToggleGroup(ratingGroup);
        rating4.setToggleGroup(ratingGroup);
    }

    private int getRating(){

    }

    public void submit(){


    }


}
