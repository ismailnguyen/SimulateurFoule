package ui.controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import ui.Main;
import ui.model.Ground;
import ui.model.SpecialPoint;
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
        Ground ground = MapUtil.mapMaker(ConfigUtil.getConfiguration("ground"));

        for(int i=0; i<ground.getLength(); i++)
            for(int j=0; j<ground.getWidth(); j++)
                groundGridPane.add(new Label(((ground.getSquare(new SpecialPoint(i, j))).getType()).toString()), i, j);

    }

    public void setMain(Main _main) {
        this.main = _main;
    }
}
