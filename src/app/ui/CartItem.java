package app.ui;


import app.controllers.CheckoutController;
import app.utils.Session;
import db.models.StoreItem;
import db.models.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class CartItem extends HBox {

    private StoreItem item;
    private CheckoutController control;
    private int type;

    public CartItem(StoreItem item, CheckoutController control){
        this.item = item;
        this.control = control;
        if(item.getClass().getName().equals("Car")){
            type = 0;
        }else{
            type = 1;
        }
        setup();
    }

    private void setup(){

        Button deleteButton = new Button("X");
        deleteButton.setPadding(Insets.EMPTY);
        deleteButton.setOnAction(new ButtonListener());
        deleteButton.setPrefSize(20,15);
        deleteButton.setStyle("-fx-background-color: #3c4656; " +
                "-fx-text-fill: white; -fx-font-size: 8pt");

        Label name = new Label(item.getName());
        name.setAlignment(Pos.CENTER);
        name.setStyle("-fx-text-fill: white");

        Label price = new Label(item.getDollarAmount());
        price.setStyle("-fx-text-fill: white");

        Region spacing = new Region();
        spacing.setPrefSize(325, 0);

        getChildren().addAll(deleteButton, name, spacing,  price);

        setSpacing(10);

    }

    public String getType(){
        if(type == 0){
            return "Car";
        }else{
            return "Accessory";
        }
    }


    private class ButtonListener implements EventHandler<ActionEvent> {

        public void handle(ActionEvent e){

            if(Session.getInstance().isActive()){
                User u = Session.getInstance().getUser();
                u.delCartItem(item);
                control.loadItems();

            }else{
                System.out.println("Session not active.");
            }

        }


    }

}
