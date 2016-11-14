package app.controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import app.core.Resource;
import app.core.StoreItem;
import app.utils.ModalUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


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
    private Button submitButton;
    private StoreItem item;
    private Date timestamp;

    @FXML
    public void initialize(URL location, ResourceBundle rb){
        itemName.setText(item.getName());
        date.setText(getFormattedDate());

    }

    public WriteReviewController(StoreItem item){
        this.item = item;
        timestamp = new Date();
        ModalUtil.setupAndShow(Resource.WRITE_REVIEW, item.getName());
    }

    private String getFormattedDate(){
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return df.format(timestamp);
    }

    public void submit(){


    }


}
