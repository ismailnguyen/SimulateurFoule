package ui.controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ui.Main;
import ui.model.Ground;
import utils.ConfigUtil;
import utils.MapUtil;

public class MainController extends Application {
    @FXML
    private Label label1;

    @FXML
    private GridPane groundGridPane;

    private Main main;

    @Override
    public void start(Stage _primaryStage) throws Exception {
        //label1.setText("aaa");

        Ground ground = MapUtil.mapMaker(ConfigUtil.getConfiguration("ground"));
        for(int width=0; width<ground.getWidth(); width++)
            for(int length=0; length<ground.getLength(); length++)
                groundGridPane.add(new Label(), width, length);
    }

    public void setMain(Main _main) {
        this.main = _main;
    }
}
