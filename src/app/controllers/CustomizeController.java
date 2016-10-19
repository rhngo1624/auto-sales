package app.controllers;

import java.net.URL;

import app.utils.ModalUtil;
import app.utils.Resource;

public class CustomizeController {

    private final URL UI_PATH = Resource.CUSTOMIZE;
    private final String TITLE = "Customize";

    void initialize(){

        ModalUtil.setupAndShow(UI_PATH, TITLE);

    }

}
