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
import app.utils.Session;
import db.models.Review;
import db.tables.Reviews;
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
        setupRadioButtons();

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
        rating1.setUserData(1);
        rating2.setToggleGroup(ratingGroup);
        rating2.setUserData(2);
        rating3.setToggleGroup(ratingGroup);
        rating3.setUserData(3);
        rating4.setToggleGroup(ratingGroup);
        rating4.setUserData(4);
        rating5.setToggleGroup(ratingGroup);
        rating5.setUserData(5);
        rating5.setSelected(true);
    }

    private int getRating(){
        return (int)ratingGroup.selectedToggleProperty().get().getUserData();
    }

    public void submit(){

        if(reviewTextArea.getText().length() < 80){
            ModalUtil.showWarning("Reviews must be at least 80 characters");
        }else{
            Reviews table = new Reviews();
            Review r = new Review();
            if(firstNameShowBox.isSelected()){
                r.setOwner(Session.getInstance().getUser().getUsername());
            }else{
                r.setOwner("Anonymous");
            }
            r.setContents(reviewTextArea.getText());
            r.setRating(getRating());
            r.setTitle(reviewTitleField.getText());
            r.setID(item.getID());

            if(table.insert(r)){
                ModalUtil.showMessage("Review submitted!");
                firstNameShowBox.getScene().getWindow().hide();
            }else{
                ModalUtil.showWarning("There was a problem with submission.");
            }

        }

    }


}
