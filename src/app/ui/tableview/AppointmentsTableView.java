package app.ui.tableview;

import app.utils.Session;
import db.models.Appointment;
import db.models.Car;
import db.tables.Appointments;
import db.tables.Cars;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AppointmentsTableView extends TableView<Appointment> {

    public AppointmentsTableView(){

        setupColumns();
        setItems(new Appointments().getAllRows());

    }

    private void setupColumns(){

        TableColumn<Appointment, Integer> ID = new TableColumn<>("ID");
        TableColumn<Appointment, String> Date = new TableColumn<>("Date");
        TableColumn<Appointment, String> Time = new TableColumn<>("Time");
        TableColumn<Appointment, String> User = new TableColumn<>("User");

        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Time.setCellValueFactory(new PropertyValueFactory<>("time"));
        User.setCellValueFactory((cellDataFeatures) -> {
            if(cellDataFeatures.getValue() != null){
                return new SimpleStringProperty(Session.getInstance().getUser().getUsername());
            }else{
                return new SimpleStringProperty("N/A");
            }
        });

        ID.prefWidthProperty().bind(this.widthProperty().divide(6));
        Date.prefWidthProperty().bind(this.widthProperty().divide(4));
        Time.prefWidthProperty().bind(this.widthProperty().divide(4));
        User.prefWidthProperty().bind(this.widthProperty().divide(4));

        getColumns().add(0, ID);
        getColumns().add(1, Date);
        getColumns().add(2, Time);
        getColumns().add(3, User);
    }

    @Override
    public void refresh(){
        setItems(new Appointments().getAllRows());
    }
}
