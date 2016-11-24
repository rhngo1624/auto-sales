package app.controllers;

import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;

import app.core.SQLModel;
import db.models.Appointment;
import db.models.Car;
import db.tables.Cars;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

/**
 *  Controller Class for Maintenance Page.
 *
 *  Edited by RN 10 / 15 / 16.
 *
 */
public class MaintenanceController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> availableTimesBox;
    @FXML
    private ComboBox<Car> carsBox;
    @FXML
    private TextArea notesArea;

    /**
     *  Called after FXML file is loaded.
     */
    @FXML
    public void initialize(URL location, ResourceBundle rb){
        setupTimeBox();
        setupCarBox();
    }

    public void setupTimeBox() {
        for(String time : Appointment.times){
            availableTimesBox.getItems().add(time);
        }
    }
    
    public void setupCarBox() {
        for(Car car : new Cars().getAllRows()){
            carsBox.getItems().add(car);
        }
    }
    
    public void submitClicked() {

    }

}
