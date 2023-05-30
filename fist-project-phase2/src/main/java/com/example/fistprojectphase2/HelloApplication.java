package com.example.fistprojectphase2;

import UI.MainPanel;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new MainPanel().start(stage);
    }
    public static void main(String[] args) throws InterruptedException {
        launch();
    }
}