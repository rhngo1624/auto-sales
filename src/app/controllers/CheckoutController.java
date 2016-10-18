package app.controllers;

import java.net.URL;

import app.utils.ModalUtil;
import app.utils.ResourceUtil;

public class CheckoutController {

    private final URL UI_PATH = ResourceUtil.getCheckoutResource();
    private final String TITLE = "Check Out";

    void initialize(){

        ModalUtil.setupAndShow(UI_PATH, TITLE);

    }

}