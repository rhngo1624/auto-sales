package app.controllers;

import java.net.URL;

import app.utils.ModalUtil;
import app.utils.ResourceUtil;

public class TestDriveController {

    private final URL UI_PATH = ResourceUtil.getTestDriveResource();
    private final String TITLE = "Test Drive";

    void initialize(){

        ModalUtil.setupAndShow(UI_PATH, TITLE);

    }



}