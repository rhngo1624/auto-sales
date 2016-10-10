package app.controllers;

import java.net.URL;

import app.utils.ResourceUtil;

public class CheckoutController extends ModalController {

    private final URL UI_PATH = ResourceUtil.getCustomizeResource();
    private final String TITLE = "Check Out";

    void initialize(){
        setupAndShow(UI_PATH, TITLE);
    }

}