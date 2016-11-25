package app.controllers;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import app.core.SQLModel;
import app.utils.ModalUtil;
import app.utils.Session;
import db.models.Appointment;
import db.models.Car;
import db.tables.Appointments;
import db.tables.Cars;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 *  Controller Class for Test Drive Page
 *
 *  Edited by RN 11 / 11 / 16.
 */
public class TestDriveController implements Initializable {

    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> availableTimesBox;
    @FXML
    private ComboBox<Car> carsBox;
    protected int type;

    public TestDriveController(){
        if(this.getClass().getSimpleName().equals("MaintenanceController")){
            type = Appointment.MAINTENANCE_T;
        }else{
            type = Appointment.TESTDRIVE_T;
        }
    }
    
    /**
     *  Called after FXML file is loaded.
     */
    @FXML
    public void initialize(URL location, ResourceBundle rb){

        setupCarBox();
        datePicker.setValue(LocalDate.now());
        datePicker.setOnAction((e) -> {
            setupTimeBox(type);
        });

        setupTimeBox(type);

    }
    
    public void setupTimeBox(int appointment_t) {

        availableTimesBox.getItems().clear();

        ArrayList<Appointment> appointments;

        if(appointment_t == Appointment.MAINTENANCE_T){
           appointments = new Appointments().getAllMaintenanceAppointments();
        }else{
            appointments = new Appointments().getAllTestDriveAppointments();
        }


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

    protected boolean validate(){
        return !availableTimesBox.getSelectionModel().isEmpty() &&
                (carsBox.getSelectionModel().getSelectedItem() != null);
    }

    
    public void submitClicked() {

        if(validate()) {
            Appointment a = new Appointment(Appointment.MAINTENANCE_T,
                    carsBox.getSelectionModel().getSelectedItem().getID(),
                    Session.getInstance().getUser().getID());

            a.setDate(datePicker.getValue().toString());
            a.setTime(availableTimesBox.getSelectionModel().getSelectedItem());
            a.setAppointmentType(type);
            submitAndClose(a);
        }

    }

    protected void submitAndClose(Appointment a){

        new Appointments().insert(a);
        ModalUtil.showMessage("Appointment submitted!");
        ((Stage)carsBox.getScene().getWindow()).close();

    }


}
