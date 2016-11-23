package app.ui.tableview;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;

import java.util.Iterator;

import db.models.FinancialApplication;
import app.utils.ModalUtil;
import db.models.Car;
import db.models.User;
import db.tables.Cars;
import db.tables.Finances;
import db.tables.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FinancialApplicationView extends TableView<FinancialApplication> {

    public FinancialApplicationView(){

        setUpColumns();
        getIncompleteApps();

    }

    private void setUpColumns(){

        TableColumn<FinancialApplication, String> User = new TableColumn<>("User");
        User.setStyle("-fx-alignment: CENTER-LEFT");
        TableColumn<FinancialApplication, String> Car = new TableColumn<>("Car");
        Car.setStyle("-fx-alignment: CENTER");
        TableColumn<FinancialApplication, String> Completed = new TableColumn<>("Completed");

        User.setCellValueFactory(new PropertyValueFactory<>("userName"));
        Car.setCellValueFactory(new PropertyValueFactory<>("carName"));
        Completed.setCellValueFactory(new PropertyValueFactory<>("completed"));

        User.prefWidthProperty().bind(this.widthProperty().divide(3));
        Car.prefWidthProperty().bind(this.widthProperty().divide(3));
        Completed.prefWidthProperty().bind(this.widthProperty().divide(4));

        getColumns().add(0, User);
        getColumns().add(1, Car);
        getColumns().add(2, Completed);
    }

    public void showApp(FinancialApplication app, TableView ref){

        User user = new Users().get(app.getUserID());
        Car car = new Cars().get(app.getCarID());

        VBox info = new VBox();
        VBox.setMargin(info, new Insets(10,10,10,10));
        info.setStyle("-fx-background-color: #1b222d");
        info.setSpacing(10);
        Label userName = new Label("User: " + user.getUsername());
        userName.setStyle("-fx-text-fill: white");
        Label carName = new Label("Car: " + car.getName());
        carName.setStyle("-fx-text-fill: white");
        Label numDependents = new Label("Dependents: " + app.getNumberOfDependents());
        numDependents.setStyle("-fx-text-fill: white");
        Label residentialStatus = new Label("Residential Status: " + app.getResidentialStatus());
        residentialStatus.setStyle("-fx-text-fill: white");
        Label monthlyPayment = new Label("Monthly Home Payment: $" + app.getMonthlyHomePayment()+"0");
        monthlyPayment.setStyle("-fx-text-fill: white");
        Label landLordName = new Label("LandLord: " + app.getLandLordName());
        landLordName.setStyle("-fx-text-fill: white");
        Label previousAddress = new Label("Previous Address: " + app.getPreviousAddress());
        previousAddress.setStyle("-fx-text-fill: white");
        Label currentEmployerName = new Label("Current Employer: " + app.getCurrentEmployerName());
        currentEmployerName.setStyle("-fx-text-fill: white");
        Label currentEmployerAddress = new Label("Employer Address: " + app.getCurrentEmployerAddress());
        currentEmployerAddress.setStyle("-fx-text-fill: white");
        Label grossMonthlySalary = new Label("Gross Monthly Salary: $" + app.getGrossMonthlySalary()+"0");
        grossMonthlySalary.setStyle("-fx-text-fill: white");
        Label workPhone = new Label("Work Phone: " + app.getWorkPhone());
        workPhone.setStyle("-fx-text-fill: white");
        Label jobTitle = new Label("Job Title: " + app.getJobTitle());
        jobTitle.setStyle("-fx-text-fill: white");
        Label otherIncome = new Label("Other Income Amount: $" + app.getOtherMonthlyGrossIncome()+"0");
        otherIncome.setStyle("-fx-text-fill: white");
        Label otherIncomeSource = new Label("Other Income Source: " + app.getOtherIncomeSource());
        otherIncomeSource.setStyle("-fx-text-fill: white");
        Label ref1 = new Label("Reference 1: " + app.getRef1());
        ref1.setStyle("-fx-text-fill: white");
        Label ref2 = new Label("Reference 2: " + app.getRef2());
        ref2.setStyle("-fx-text-fill: white");
        Label ref1Phone = new Label("Reference 1 Phone: " + app.getRef1Phone());
        ref1Phone.setStyle("-fx-text-fill: white");
        Label ref2Phone = new Label("Reference 2 Phone: " + app.getRef2Phone());
        ref2Phone.setStyle("-fx-text-fill: white");
        Label driversLicenseNo = new Label("Drivers License #: " + app.getDriversLicenseNo());
        driversLicenseNo.setStyle("-fx-text-fill: white");
        Label ssn = new Label("SSN: " + app.getSSN());
        ssn.setStyle("-fx-text-fill: white");

        JFXButton completed = new JFXButton("Accept");
        completed.setAlignment(Pos.CENTER);
        completed.setOnAction((e) -> {
            app.setCompleted(true);
            new Finances().update(app);
            ModalUtil.showMessage("Application Review Complete!");
            completed.getScene().getWindow().hide();
            ((FinancialApplicationView)ref).getIncompleteApps();
        });


        info.getChildren().addAll(userName, carName, numDependents, residentialStatus, monthlyPayment,
                landLordName, previousAddress, currentEmployerName, currentEmployerAddress, grossMonthlySalary,
                workPhone, jobTitle, otherIncome, otherIncomeSource, ref1, ref2, ref1Phone, ref2Phone,
                driversLicenseNo, ssn, completed);

        VBox.setMargin(completed, new Insets(5,5,5,80));
        Stage view = new Stage();
        Scene scene = new Scene(info);
        view.initModality(Modality.APPLICATION_MODAL);
        view.setScene(scene);
        view.show();

    }

    private void getIncompleteApps() {
        ObservableList<FinancialApplication> apps = FXCollections.observableArrayList();
        apps.setAll(new Finances().getModels());

        Iterator it = apps.iterator();
        while (it.hasNext()) {

            FinancialApplication app = (FinancialApplication) it.next();

            app.setCarName(new Cars().get(app.getCarID()).getName());
            app.setUserName(new Users().get(app.getUserID()).getUsername());

            if (app.isCompleted()) {
                it.remove();
            }
        }

        setItems(apps);

    }

    @Override
    public void refresh(){
        setItems(new Finances().getAllRows());
    }

}
