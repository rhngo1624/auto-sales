package app.utils;

import com.sun.org.apache.regexp.internal.RE;

import java.net.URL;
import javafx.stage.Stage;

public class ResourceUtil {

    private final static String RESOURCE_PATH = "/resources/views/";


    public static URL getLoginResource(){

        return System.class.getResource(RESOURCE_PATH + "login.fxml");

    }

    public static URL getMainResource(){

        return System.class.getResource(RESOURCE_PATH + "main.fxml");

    }

    public static URL getCustomizeResource(){

        return System.class.getResource(RESOURCE_PATH + "customize.fxml");

    }

    public static URL getTestDriveResource(){

        return System.class.getResource(RESOURCE_PATH + "testdrive.fxml");

    }

    public static URL getMaintenanceResource(){

        return System.class.getResource(RESOURCE_PATH + "maintenance.fxml");

    }

    public static URL getAccessoriesResource(){

        return System.class.getResource(RESOURCE_PATH + "accessories.fxml");

    }

    public static URL getRegisterResource(){

        return System.class.getResource(RESOURCE_PATH + "register.fxml");

    }



}
