package app.utils;

import javax.lang.model.type.NullType;
import javax.management.InvalidAttributeValueException;

import javafx.stage.Stage;

public class StageUtil {

    private static Stage mainStage;

    public Stage getMainStage() throws NullPointerException {
        if(mainStage == null){
            throw new NullPointerException();
        }else{
            return mainStage;
        }
    }

    public void setMainStage(Stage stage) throws InvalidAttributeValueException{
        if(mainStage != null){
            throw new InvalidAttributeValueException();
        }else{
            mainStage = stage;
        }
    }

}
