package app.controllers;

import java.net.URL;

import app.utils.ModalUtil;
import app.utils.ResourceUtil;

public class LoginController {

    private final URL UI_PATH = ResourceUtil.getLoginResource();
    private final String TITLE = "AutoSales Login";

    void initialize(){
        ModalUtil.setupAndShow(UI_PATH, TITLE);
    }

}

