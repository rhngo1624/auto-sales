package app.controllers;

import java.net.URL;

import app.utils.ModalUtil;
import app.utils.ResourceUtil;

public class CustomizeController {

    private final URL UI_PATH = ResourceUtil.getCustomizeResource();
    private final String TITLE = "Customize";

    void initialize(){

        ModalUtil.setupAndShow(UI_PATH, TITLE);

    }

}
