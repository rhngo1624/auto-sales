package app.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import javax.management.InvalidAttributeValueException;

import app.core.StoreItem;
import app.ui.items.SelectedItemPane;
import app.ui.items.StoreItemPane;
import db.models.Review;
import db.tables.Reviews;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
// TODO: finish implementing logic, make sure to check car and accessory for a reviews field
public class ReviewsController implements Initializable {

    @FXML
    private Accordion accordion;
    private StoreItem item;

    @FXML
    public void initialize(URL location, ResourceBundle rb){
        for(Review r : new Reviews().getModels(item.getID())){

        }
    }

    private void makeView(Review review){
        StoreItemPane selected = SelectedItemPane.get();
        if(selected != null){
            item = selected.getItem();
        }else{
            System.exit(-1);
        }
        String viewName = item.getName() + " " + getRating();
        TextArea reviewText = new TextArea();
        reviewText.setText(review.getContents());
        reviewText.setEditable(false);
        TitledPane view = new TitledPane(viewName, reviewText);
        accordion.getPanes().add(view);

    }

    private String getRating(){

        StringBuilder rating = new StringBuilder();

        for(int i = 0; i < item.getRating(); i++){
            rating.append("*");
        }

        return rating.toString();

    }

}
