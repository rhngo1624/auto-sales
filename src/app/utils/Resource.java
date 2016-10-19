package app.utils;

import java.net.URL;

public interface Resource {

    String RESOURCE_PATH = "/resources/views/";
    URL LOGIN = System.class.getResource(RESOURCE_PATH + "login.fxml");
    URL MAIN = System.class.getResource(RESOURCE_PATH + "main.fxml");
    URL CUSTOMIZE = System.class.getResource(RESOURCE_PATH + "customize.fxml");
    URL TEST_DRIVE = System.class.getResource(RESOURCE_PATH + "testdrive.fxml");
    URL MAINTENANCE = System.class.getResource(RESOURCE_PATH + "maintenance.fxml");
    URL REGISTER = System.class.getResource(RESOURCE_PATH + "register.fxml");
    URL CHECKOUT = System.class.getResource(RESOURCE_PATH + "checkout.fxml");

}
