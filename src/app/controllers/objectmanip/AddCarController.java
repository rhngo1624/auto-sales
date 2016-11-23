package app.controllers.objectmanip;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import app.utils.ModalUtil;
import db.models.Car;
import db.tables.Cars;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddCarController implements Initializable {

    @FXML
    private JFXButton uploadButton;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton closeButton;
    @FXML
    private JFXTextField makeField;
    @FXML
    private JFXTextField modelField;
    @FXML
    private JFXTextField priceField;
    @FXML
    private JFXComboBox<String> engineTypeBox;
    @FXML
    private JFXComboBox<Integer> yearBox;
    @FXML
    private JFXComboBox<String> fuelEconomyBox;
    @FXML
    private JFXComboBox<String> transmissionBox;
    @FXML
    private JFXComboBox<Integer> totalSeatsBox;
    @FXML
    private JFXComboBox<Integer> doorAmountBox;
    @FXML
    private ImageView imageView;
    private FileChooser filePicker;
    private boolean imageSet = false;
    private String imgPath;


    public void initialize(URL location, ResourceBundle rb){

        filePicker = new FileChooser();
        makeField.setStyle("-fx-text-fill: white;");
        modelField.setStyle("-fx-text-fill: white;");
        priceField.setStyle("-fx-text-fill: white;");

        fillEngineTypeBox();
        fillYearBox();
        fillFuelEconomyBox();
        fillTransmissionBox();
        fillTotalSeatsBox();
        fillDoorAmountBox();

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

            Car c = new Car(makeField.getText(), modelField.getText(), yearBox.getValue());
            c.setPrice(Double.parseDouble(priceField.getText()));
            c.setEngineType(engineTypeBox.getSelectionModel().getSelectedItem());
            c.setFuelEconomy(fuelEconomyBox.getSelectionModel().getSelectedItem());
            c.setTransmission(transmissionBox.getSelectionModel().getSelectedItem());
            c.setTotalSeating(totalSeatsBox.getSelectionModel().getSelectedItem());
            c.setDoorAmount(doorAmountBox.getSelectionModel().getSelectedItem());
            c.setImageLocation(imgPath);

            new Cars().insert(c);

            ModalUtil.showMessage(c.getName() + " was inserted!");
            close();

        }else{
            ModalUtil.showWarning("Not all fields are filled.");
        }

    }




    private boolean validate(){
        return !makeField.getText().isEmpty() && !modelField.getText().isEmpty() &&
                !priceField.getText().isEmpty() && !engineTypeBox.getSelectionModel().isEmpty() &&
                !yearBox.getSelectionModel().isEmpty() && !fuelEconomyBox.getSelectionModel()
                .isEmpty() && !transmissionBox.getSelectionModel().isEmpty() &&
                !totalSeatsBox.getSelectionModel().isEmpty() &&
                !doorAmountBox.getSelectionModel().isEmpty() && imageSet;
    }

    public void close(){
        ((Stage)uploadButton.getScene().getWindow()).close();
    }

    private void fillEngineTypeBox(){
        engineTypeBox.getItems().addAll("4 cyl.", "6 cyl.", "8 cyl.", "12 cyl.");
    }

    private void fillYearBox(){
        for(int year = 1900; year < 2018; year++){
            yearBox.getItems().addAll(year);
        }
    }

    private void fillFuelEconomyBox(){
        for(int city = 15; city < 40; city++){
            for(int highway = city; highway < 40; highway++){
                fuelEconomyBox.getItems().add(city + " City / " + highway + " Highway");
            }
        }
    }

    private void fillTransmissionBox(){
        transmissionBox.getItems().addAll("Automatic", "Manual");
    }

    private void fillTotalSeatsBox(){
        for(int seats = 0; seats < 10; seats++){
            totalSeatsBox.getItems().add(seats);
        }
    }

    private void fillDoorAmountBox(){
        for(int doors = 0; doors < 10; doors++){
            doorAmountBox.getItems().add(doors);
        }
    }

}
