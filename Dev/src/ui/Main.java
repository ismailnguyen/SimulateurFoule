package ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import ui.controller.MainController;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Simulateur de foule");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/root.fxml"));
        Parent root = loader.load();

        MainController controller = loader.getController();
        controller.setMain(this);
        controller.start(primaryStage);

        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
