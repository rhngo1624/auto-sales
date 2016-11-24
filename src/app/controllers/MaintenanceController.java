package app.controllers;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import app.core.SQLModel;
import app.utils.ModalUtil;
import app.utils.Session;
import db.models.Appointment;
import db.models.Car;
import db.tables.Appointments;
import db.tables.Cars;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

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
        setupCarBox();
        datePicker.setValue(LocalDate.now());
        datePicker.setOnAction((e) -> {
            setupTimeBox();
        });

        setupTimeBox();

    }

    public void setupTimeBox() {

        availableTimesBox.getItems().clear();

        ArrayList<Appointment> appointments = new Appointments().getAllMaintenanceAppointments();

        for(String time : Appointment.times){
            availableTimesBox.getItems().add(time);
        }

        for(Appointment appt : appointments){

            String date = appt.getDate();

            if(date.equals(datePicker.getValue().toString())){

                availableTimesBox.getItems().remove(appt.getTime());

            }

        }

    }
    
    public void setupCarBox() {
        for(Car car : new Cars().getAllRows()){
            carsBox.getItems().add(car);
        }
    }
    
    public void submitClicked() {
        boolean fieldsEmpty = availableTimesBox.getSelectionModel().isEmpty() ||
                carsBox.getSelectionModel().isEmpty();

        if(!fieldsEmpty){
            Appointment a = new Appointment(Appointment.MAINTENANCE_T,
                    carsBox.getSelectionModel().getSelectedItem().getID(),
                    Session.getInstance().getUser().getID() );

            a.setDate(datePicker.getValue().toString());
            a.setTime(availableTimesBox.getSelectionModel().getSelectedItem());

            new Appointments().insert(a);
            ModalUtil.showMessage("Appointment submitted!");
            ((Stage)carsBox.getScene().getWindow()).close();
        }


    }

}
