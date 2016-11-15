package app.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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
        selectedDate = dates.getSelectedItem.getValue();
        setupTimeBox();
        setupCarBox();
    }
    
    public void setupTimeBox() {
        Dates date = new Dates();
        ObservableList<Time> timeList = table.getTimes(selectedDate);
        times.setItems(timeList);
    }
    
    public void setupCarBox() {
        Cars car = new Cars();
        ObservableList<SQLModel> carList = table.getCars(selectedCar);
        cars.setItem(carList);
    }
    
    public void submitClicked() {
        ObservableList<SQLModel> = FXCollections.observableArrayList();
        Date date = DatePicker.getSelectedItem().getValue();
        Time time = times.getSelectedItem().getItem();
        Car car = cars.getSelectedItem().getItem();
        String maintenanceNotes = String.format("%s", notes.getText());
    }
    
}
