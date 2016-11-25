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
public class MaintenanceController extends TestDriveController implements Initializable {

    @FXML
    private TextArea notesArea;

    @Override
    protected boolean validate(){
        return super.validate() && !notesArea.getText().isEmpty();
    }

    @Override
    protected void submitAndClose(Appointment a){
        a.setNotes(notesArea.getText());
        new Appointments().insert(a);
        ModalUtil.showMessage("Appointment submitted");
        ((Stage)notesArea.getScene().getWindow()).close();
    }

}
