package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import app.core.StoreItem;
import app.ui.tableview.CartTableView;
import app.utils.ModalUtil;
import app.core.Resource;
import app.utils.Session;
import app.utils.StageUtil;
import db.models.Car;
import db.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 *  Controller Class for Check Out Page
 */
public class CheckoutController implements Initializable {

    private User user;
    private boolean needFinancialApplication = false;

    @FXML
    private VBox mainPane;
    @FXML
    private VBox itemPane;

    /**
     *  Called after FXML file is loaded.
     */
    @FXML
    public void initialize(URL location, ResourceBundle rb){

        user = Session.getInstance().getUser();

        if(user != null){
            System.out.println(user.getUsername());
            CartTableView ctv = new CartTableView();
            itemPane.getChildren().add(ctv);
        }else{
            ModalUtil.showMessage("Not logged in.");

            try{

                StageUtil.getInstance().getMainStage().
                        getScene().setRoot(FXMLLoader.load(Resource.MAIN));

            }catch(IOException e){
                System.out.println(e.getMessage());
                System.exit(-1);
            }

            mainPane.getScene().getWindow().hide();

        }

    }

    public void checkForRequirements(){
        Set<Car> cars = new HashSet<>();
        for(StoreItem item : user.dumpCart()){
            if(item.getClass().getSimpleName().equals("Car")){
                cars.add((Car)item);
            }
        }

        if(!cars.isEmpty()){

        }

    }

    public void checkout(){

    }

}