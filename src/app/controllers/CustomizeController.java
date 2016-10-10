package app.controllers;

import java.net.URL;

import app.utils.ResourceUtil;

public class CustomizeController extends ModalController {

    private final URL UI_PATH = ResourceUtil.getCustomizeResource();
    private final String TITLE = "Customize";

    void initialize(){
        setupAndShow(UI_PATH, TITLE);
    }

}
