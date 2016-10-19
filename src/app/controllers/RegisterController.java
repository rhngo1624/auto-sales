package app.controllers;

import java.net.URL;

import app.utils.ModalUtil;
import app.utils.Resource;

public class RegisterController {

    private final URL UI_PATH = Resource.REGISTER;
    private final String TITLE = "Register";

    void initialize(){
        ModalUtil.setupAndShow(UI_PATH, TITLE);
    }

}