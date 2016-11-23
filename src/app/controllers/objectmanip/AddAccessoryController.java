package app.controllers.objectmanip;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import app.utils.ModalUtil;
import db.models.Accessory;
import db.tables.Accessories;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddAccessoryController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextArea descriptionField;
    @FXML
    private JFXTextField priceField;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton closeButton;
    @FXML
    private JFXButton uploadButton;
    private FileChooser filePicker;
    private boolean imageSet = false;
    private String imgPath;

    @FXML
    public void initialize(URL location, ResourceBundle rb){
        filePicker = new FileChooser();
        descriptionField.setWrapText(true);
        nameField.setStyle("-fx-text-fill: white");
        priceField.setStyle("-fx-text-fill: white");

    }


    public void upload(){
        Stage stage = (Stage) imageView.getScene().getWindow();
        File file = filePicker.showOpenDialog(stage);
        if(file != null){
            imgPath = "file:" + file.getAbsolutePath();

            imageView.setImage(new Image(imgPath));

            imageSet = true;
        }
    }

    public void add(){
        if(validate()){
            Accessory a = new Accessory(nameField.getText(),
                    Double.parseDouble(priceField.getText()), imgPath);

            a.setDescription(descriptionField.getText());

            new Accessories().insert(a);

            ModalUtil.showMessage(a.getName() + " was inserted!");

            close();

        }else{
            ModalUtil.showWarning("Not all fields are filled.");
        }
    }

    private boolean validate(){
        return imageSet && !nameField.getText().isEmpty() && !descriptionField.getText().isEmpty()
                && !priceField.getText().isEmpty();
    }

    public void close(){
        ((Stage)imageView.getScene().getWindow()).close();
    }

}