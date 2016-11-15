package app.controllers;

import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;

import app.core.SQLModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 *  Controller Class for Test Drive Page
 *
 *  Edited by RN 11 / 11 / 16.
 */
public class TestDriveController implements Initializable {

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
    
    /**
     *  Called after FXML file is loaded.
     */
    @FXML
    public void initialize(URL location, ResourceBundle rb){
        //  selectedDate = dates.getSelectedItem().getValue();
        /* I read some documentation on DatePicker.  I think it returns a chosen date as a LocalDate object.  So:
         * {
         * Label label;
         * LocalDate date;
         * 
         * label.setText(date.toString());
         * }
         * selectedDate might have to change to LocalDate instead of Date.
         */
        
       // setupTimeBox();
        //setupCarBox();

    }
    
    /*public void setupTimeBox() {
        Dates date = new Dates();
        ObservableList<Time> timeList = table.getTimes(selectedDate);
        times.setItems(timeList);
    }
    
    public void setupCarBox() {
        Cars car = new Cars();
        ObservableList<SQLModel> carList = table.getCars(selectedCar);
        cars.setItems(carList);
        
        /* Or maybe this would help.
         * Cars table = new Cars();
         *
         * for (Car car : carList) {
         *     carNames.add(car.getName());
         * }
         *
    }
    
    public void submitClicked() {
        ObservableList<SQLModel> = FXCollections.observableArrayList();
        Date date = DatePicker.getSelectedItem().getItem(); // or getValue instead of getItem.
        Time time = times.getSelectedItem().getItem();
        Cars car = cars.getSelectedItem().getItem();
        
        try () {
            table.getAllRows();
        } catch (SQLException ex) {
            ModelUtility.showWarning(); // 
        }
    }
    */

}
