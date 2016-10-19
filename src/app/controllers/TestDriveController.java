package app.controllers;

import java.net.URL;

import app.utils.ModalUtil;
import app.utils.Resource;

public class TestDriveController {

    private final URL UI_PATH = Resource.TEST_DRIVE;
    private final String TITLE = "Test Drive";

    void initialize(){

        ModalUtil.setupAndShow(UI_PATH, TITLE);

    }



}