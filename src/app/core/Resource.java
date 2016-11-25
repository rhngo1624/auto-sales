package app.core;

import java.net.URL;

public interface Resource {

    String RESOURCE_PATH = "/resources/views/";
    URL LOGIN = System.class.getResource(RESOURCE_PATH + "login.fxml");
    URL MAIN = System.class.getResource(RESOURCE_PATH + "main.fxml");
    URL CUSTOMIZE2 = System.class.getResource(RESOURCE_PATH + "customize.fxml");
    URL CUSTOMIZE1 = System.class.getResource(RESOURCE_PATH + "pick_car.fxml");
    URL TEST_DRIVE = System.class.getResource(RESOURCE_PATH + "testdrive.fxml");
    URL MAINTENANCE = System.class.getResource(RESOURCE_PATH + "maintenance.fxml");
    URL REGISTER = System.class.getResource(RESOURCE_PATH + "register.fxml");
    URL CHECKOUT = System.class.getResource(RESOURCE_PATH + "checkout.fxml");
    URL ITEMVIEW = System.class.getResource(RESOURCE_PATH + "item_view.fxml");
    URL ADMIN = System.class.getResource(RESOURCE_PATH + "admin_panel.fxml");
    URL REVIEWS = System.class.getResource(RESOURCE_PATH + "reviews.fxml");
    URL WRITE_REVIEW = System.class.getResource(RESOURCE_PATH + "write_review.fxml");
    URL FINANCE = System.class.getResource(RESOURCE_PATH + "financial_application.fxml");
    URL ADD_ACCESSORY = System.class.getResource(RESOURCE_PATH + "add_accessory.fxml");
    URL EDIT_ACCESSORY = System.class.getResource(RESOURCE_PATH + "edit_accessory.fxml");
    URL ADD_CAR = System.class.getResource(RESOURCE_PATH + "add_car.fxml");
    URL EDIT_CAR = System.class.getResource(RESOURCE_PATH + "edit_car.fxml");
}
