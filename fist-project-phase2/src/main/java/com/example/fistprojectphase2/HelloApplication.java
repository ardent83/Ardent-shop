package com.example.fistprojectphase2;

import UI.MainPanel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.AdminPanel;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
//        new AdminPanel().adminMenu();
        new MainPanel().start(stage);
    }
    public static void main(String[] args) {
        launch();
    }
}