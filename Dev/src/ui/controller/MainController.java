package ui.controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ui.Main;

public class MainController extends Application {
    @FXML
    private Label label1;

    private Main main;

    @Override
    public void start(Stage _primaryStage) throws Exception {
        //label1.setText("aaa");
    }

    public void setMain(Main _main) {
        this.main = _main;
    }
}
