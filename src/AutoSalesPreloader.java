import com.jfoenix.controls.JFXProgressBar;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class AutoSalesPreloader extends Preloader {

    private Stage preloaderStage;
    private Scene scene;
    private JFXProgressBar progressBar;

    @Override
    public void init() throws Exception{
        Platform.runLater(()->{

            VBox imageHolder = new VBox();

            Image img = new Image("resources/imgs/logos/auto_sales_logo.png");

            ImageView imgv = new ImageView(img);

            final double HEIGHT = img.getHeight();
            final double WIDTH = img.getWidth();

            progressBar = new JFXProgressBar(-1.0f);
            progressBar.setPrefWidth(WIDTH);
            progressBar.setPrefHeight(50);
            progressBar.setStyle("-fx-background-color: white");


            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
                    new KeyFrame(Duration.seconds(2.1), new KeyValue(progressBar.progressProperty(), 1))

            );

            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();

            imgv.fitHeightProperty().setValue(HEIGHT);
            imgv.fitWidthProperty().setValue(WIDTH);

            HBox infoHolder = new HBox();

            infoHolder.setAlignment(Pos.CENTER);

            VBox infoLayout = new VBox();

            Label copyright = new Label("(C) Copyright AutoSales Incorporated 2016");
            copyright.setStyle("-fx-text-fill: white");
            VBox.setMargin(copyright, new Insets(0,0,20,0));

            infoHolder.getChildren().add(infoLayout);

            infoLayout.getChildren().add(copyright);

            HBox.setMargin(infoHolder, new Insets(10,10,10,10));

            imageHolder.setPrefSize(WIDTH, HEIGHT);
            imageHolder.setStyle("-fx-background-color: #2b2e33");
            imageHolder.setEffect(new DropShadow());
            imageHolder.getChildren().add(imgv);
            imageHolder.getChildren().add(infoHolder);
            imageHolder.getChildren().add(progressBar);

            scene = new Scene(imageHolder);
        });
    }

    @Override
    public void start(Stage primaryStage){

        preloaderStage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.show();

    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info){
        if(info instanceof ProgressNotification){
            progressBar.setProgress(((ProgressNotification) info).getProgress());
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info){
        StateChangeNotification.Type type = info.getType();
        switch(type){
            case BEFORE_LOAD:
                break;
            case BEFORE_INIT:
                break;
            case BEFORE_START:
                preloaderStage.hide();
                break;
        }

    }

}