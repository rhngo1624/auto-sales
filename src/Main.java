import com.sun.javafx.application.LauncherImpl;

import java.sql.Date;

import app.core.Resource;
import app.utils.StageUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage window;
    private final String TITLE = "AutoSales";

    @Override
    public void init() throws Exception {
        for(int i = 0; i < 50000; i++){
            int progress = (100*i)/50000;
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        StageUtil su = StageUtil.getInstance();
        su.setMainStage(window);

        Parent root = new FXMLLoader(Resource.MAIN).load();
        setup(root);

    }

    private void setup(Parent root){

        window.setTitle(TITLE);
        window.setScene(new Scene(root));
        window.setResizable(false);
        window.show();

    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, AutoSalesPreloader.class, args);
    }


}
