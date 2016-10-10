package app.controllers;

import java.net.URL;

import app.utils.ResourceUtil;

public class MaintenanceController extends ModalController {

    private final URL UI_PATH = ResourceUtil.getMaintenanceResource();
    private final String TITLE = "Maintenance";

    void initialize(){
        setupAndShow(UI_PATH, TITLE);
    }

}