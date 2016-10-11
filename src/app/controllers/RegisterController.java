package app.controllers;

import java.net.URL;

import app.utils.ModalUtil;
import app.utils.ResourceUtil;

public class RegisterController {

    private final URL UI_PATH = ResourceUtil.getRegisterResource();
    private final String TITLE = "Register";

    void initialize(){
        ModalUtil.setupAndShow(UI_PATH, TITLE);
    }

}