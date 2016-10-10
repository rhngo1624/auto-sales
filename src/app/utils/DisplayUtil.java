package app.utils;

import java.net.URL;
import app.controllers.MainController;

public class DisplayUtil {

    private final static String RESOURCE_PATH = "/resources/views/";

    public static URL getLoginResource(){

        return System.class.getResource(RESOURCE_PATH + "login.fxml");

    }

    public static URL getMainResource(){

        return System.class.getResource(RESOURCE_PATH + "main.fxml");

    }
}
