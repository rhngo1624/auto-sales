package app.controllers;

import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;

import app.core.SQLModel;
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
    private DatePicker dates;
    @FXML
    private ComboBox<Time> times;
    @FXML
    private ComboBox<SQLModel> cars;
    @FXML
    private Date selectedDate;
    @FXML
    private SQLModel selectedCar;
    @FXML
    private TextArea notes;
    
    /**
     *  Called after FXML file is loaded.
     */
    @FXML
    public void initialize(URL location, ResourceBundle rb){
        //selectedDate = dates.getChronology().dateNow();
        //setupTimeBox();
        //setupCarBox();
    }
    /*
    
    public void setupTimeBox() {
        Dates date = new Dates();
        ObservableList<Time> timeList = table.getTimes(selectedDate);
        times.setItems(timeList);
    }
    
    public void setupCarBox() {
        Cars car = new Cars();
        ObservableList<SQLModel> carList = car.getCars(selectedCar);
        cars.setItem(carList);
    }
    
    public void submitClicked() {
        ObservableList<SQLModel> = FXCollections.observableArrayList();
        Date date = DatePicker.getSelectedItem().getValue();
        Time time = times.getSelectedItem().getItem();
        Car car = cars.getSelectedItem().getItem();
        String maintenanceNotes = String.format("%s", notes.getText());
    }*/
    
}
