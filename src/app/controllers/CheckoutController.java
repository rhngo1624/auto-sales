package app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.ui.CartItem;
import app.utils.ModalUtil;
import app.core.Resource;
import app.utils.Session;
import app.utils.StageUtil;
import app.core.StoreItem;
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
    private ListView<CartItem> itemView;
    /**
     *  Called after FXML file is loaded.
     */
    @FXML
    public void initialize(URL location, ResourceBundle rb){

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

    public void loadItems(){

        ObservableList<StoreItem> items = user.dumpCart();
        ObservableList<CartItem> cartItems = FXCollections.observableArrayList();

        for(StoreItem item : items){
            cartItems.add(new CartItem(item, this));
        }

        itemView.setItems(cartItems);

    }

}