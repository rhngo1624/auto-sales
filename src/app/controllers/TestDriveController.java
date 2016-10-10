package app.controllers;

import java.net.URL;

import app.utils.ResourceUtil;

public class TestDriveController extends ModalController {

    private final URL UI_PATH = ResourceUtil.getTestDriveResource();
    private final String TITLE = "Test Drive";

    void initialize(){
        setupAndShow(UI_PATH, TITLE);
    }

}