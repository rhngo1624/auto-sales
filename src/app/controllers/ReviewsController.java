package app.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import app.core.StoreItem;
import app.ui.items.SelectedItemPane;
import app.ui.items.StoreItemPane;
import app.utils.ModalUtil;
import app.utils.Session;
import db.models.Review;
import db.tables.Reviews;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

// TODO: finish implementing logic, make sure to check car and accessory for a reviews field
public class ReviewsController implements Initializable {

    @FXML
    private VBox mainPane;

    private StoreItem item;

    @FXML
    public void initialize(URL location, ResourceBundle rb){
        StoreItemPane selected = SelectedItemPane.get();
        if(selected != null){
            item = selected.getItem();
            System.out.println("selected != null => " + item);
        }else{
            System.exit(-1);
        }
        Reviews table = new Reviews();
        if(table.getModels(item.getID()).isEmpty()){
            ModalUtil.showMessage("No reviews");
            mainPane.getScene().getWindow().hide();
        }else{

            for(Review r : new Reviews().getModels(item.getID())){
                makeView(r);
            }
        }

    }

    private void makeView(Review review){

        StackPane reviewPane = new StackPane();
        HBox reviewHolder = new HBox();
        VBox infoHolder = new VBox();
        HBox usernameHolder = new HBox();
        HBox ratingHolder = new HBox();
        TextArea reviewArea = new TextArea();

        reviewArea.setText(review.getContents());

        HBox.setMargin(reviewHolder, new Insets(5,5,5,5));

        usernameHolder.getChildren().add(new Label("User: " +
                Session.getInstance().getUser().getUsername()));
        ratingHolder.getChildren().add(new Label("Rating: " + getRating()));
        infoHolder.getChildren().addAll(usernameHolder, ratingHolder);
        reviewHolder.getChildren().addAll(infoHolder, reviewArea);
        reviewPane.getChildren().add(reviewHolder);

        mainPane.getChildren().add(reviewPane);


    }

    private String getRating(){

        StringBuilder rating = new StringBuilder();

        for(int i = 0; i < item.getRating(); i++){
            rating.append("*");
        }

        return rating.toString();

    }

}
