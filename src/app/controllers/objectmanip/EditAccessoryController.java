package app.controllers.objectmanip;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import app.controllers.AdministrationController;
import app.utils.ModalUtil;
import db.models.Accessory;
import db.tables.Accessories;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Created by RayDeveloper on 11/20/16.
 */
public class EditAccessoryController implements Initializable {

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

        Accessory a = new Accessories().get(AdministrationController.getSelected());

        nameField.setText(a.getName());
        descriptionField.setText(a.getDescription());
        priceField.setText(String.valueOf(a.getPrice()));
        imgPath = a.getImageLocation();
        imageView.setImage(new Image(imgPath));

    }

    public void update(){

        String name = nameField.getText();
        Double price = Double.parseDouble(priceField.getText());
        String description = descriptionField.getText();

        if(!name.isEmpty() && price > 0){

            Accessory a = new Accessory(name, price, imgPath);
            a.setID(AdministrationController.getSelected());
            if(!description.isEmpty()) a.setDescription(description);
            new Accessories().update(a);
            ModalUtil.showMessage(a.getName() + " was updated!");
            ((Stage)descriptionField.getScene().getWindow()).close();
        }

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


    public void close(){
        ((Stage)imageView.getScene().getWindow()).close();
    }
}
