import app.utils.Resource;
import app.utils.StageUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage window;
    private final String TITLE = "AutoSales";


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
        launch(args);
    }

}
