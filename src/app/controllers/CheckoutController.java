package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import app.utils.ModalUtil;
import app.utils.Resource;
import app.utils.Session;
import app.utils.StageUtil;
import db.models.StoreItem;
import db.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
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
    private ListView<String> itemView;
    /**
     *  Called after FXML file is loaded.
     */
    @FXML
    public void initialize(URL location, ResourceBundle rb){
        System.out.println("Cart LOADED");

        user = Session.getInstance().getUser();

        if(user != null){
            System.out.println(user.getUsername());
            loadItems();
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

    private void loadItems(){

        ObservableList<StoreItem> items = user.dumpCart();
        ObservableList<String> itemNames = FXCollections.observableArrayList();

        for(StoreItem item : items){
            itemNames.add(item.getName());
        }

        itemView.setItems(itemNames);

    }

}