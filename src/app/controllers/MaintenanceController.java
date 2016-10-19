package app.controllers;

import java.net.URL;

import app.utils.ModalUtil;
import app.utils.Resource;
import app.utils.Resource;

public class MaintenanceController {

    private final URL UI_PATH = Resource.MAINTENANCE;
    private final String TITLE = "Maintenance";

    void initialize(){
        ModalUtil.setupAndShow(UI_PATH, TITLE);
    }

}