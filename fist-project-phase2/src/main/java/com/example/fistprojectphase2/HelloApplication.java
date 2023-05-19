package com.example.fistprojectphase2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        Button buttonSignUp = new Button("Sign Up");
        Button buttonLogIn = new Button("Log In");
        Button buttonItemPanel = new Button("Item Panel");
        VBox vBox = new VBox(buttonSignUp,buttonLogIn,buttonItemPanel);
        vBox.setSpacing(10);
        root.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Main Panel");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}