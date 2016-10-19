package app.controllers;

import java.net.URL;

import app.utils.ModalUtil;
import app.utils.Resource;

public class LoginController {

    private final URL UI_PATH = Resource.LOGIN;
    private final String TITLE = "AutoSales Login";

    void initialize(){

        ModalUtil.setupAndShow(UI_PATH, TITLE);

    }

}

