package app.controllers;

import java.net.URL;

import app.utils.ResourceUtil;

public class LoginController extends ModalController {

    private final URL UI_PATH = ResourceUtil.getLoginResource();
    private final String TITLE = "AutoSales Login";

    void initialize(){
        setupAndShow(UI_PATH, TITLE);
    }

}

