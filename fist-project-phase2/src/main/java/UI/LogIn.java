package UI;

import controller.LogInController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import view.AdminPanel;

public class LogIn extends Application {
    public LogIn() {
        this.logInController = new LogInController();
    }

    private final LogInController logInController;
    @Override
    public void start(Stage stage){
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 66, 90),CornerRadii.EMPTY, Insets.EMPTY)));
        HBox hBox = new HBox();
        hBox.getChildren().add(inputVbox(stage));
        hBox.setAlignment(Pos.CENTER);
        root.setCenter(hBox);
        Scene scene = new Scene(root);
        stage.setMinWidth(900);
        stage.setMinHeight(500);
        scene.getStylesheets().add("style.css");

        stage.setScene(scene);
        stage.setTitle("Log In");
        stage.getIcons().add(new Image("icon.png"));
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();

    }

    private VBox inputVbox(Stage stage) {
        VBox vBox = new VBox();
        DropShadow effect = new DropShadow();
        effect.setColor(Color.rgb(0, 66, 90));
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 173, 181),CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setPadding(new Insets(100));


//        Email
        HBox hBoxEmail = new HBox();
        StackPane stackEmail = new StackPane();
        stackEmail.setAlignment(Pos.CENTER);
        Text textEmail = new Text("Email");
        textEmail.setFill(Color.rgb(255,255,255));
        textEmail.setTextAlignment(TextAlignment.CENTER);
        textEmail.setId("textSignUp");
        Image image = new Image("signUp.png");
        ImageView imageViewEmail = new ImageView(image);
        imageViewEmail.setEffect(effect);
        stackEmail.getChildren().addAll(imageViewEmail,textEmail);

        TextField textEmailField = new TextField();
        textEmailField.setId("SignUp");
        textEmailField.setEffect(effect);

        hBoxEmail.getChildren().addAll(stackEmail, textEmailField);

//        Password
        HBox hBoxPassword = new HBox();
        StackPane stackPassword = new StackPane();
        Text textPassword = new Text("Password");
        textPassword.setId("textSignUp");
        textPassword.setFill(Color.rgb(255,255,255));
        ImageView imageViewPassword = new ImageView(image);
        imageViewPassword.setEffect(effect);
        stackPassword.getChildren().addAll(imageViewPassword,textPassword);

        TextField textPasswordField = new TextField();
        textPasswordField.setId("SignUp");
        textPasswordField.setEffect(effect);
        hBoxPassword.getChildren().addAll(stackPassword,textPasswordField);

        textEmailField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER){
                textPasswordField.requestFocus();
                keyEvent.consume();
            }
        });

//        Button
        HBox hBoxButton = new HBox();
        Button buttonSubmit = new Button("Submit");
        Button buttonCancel = new Button("Cansel");

        buttonCancel.setCursor(Cursor.HAND);
        buttonSubmit.setCursor(Cursor.HAND);

        buttonCancel.setOnMouseClicked(mouseEvent -> {
            new MainPanel().start(new Stage());
            stage.close();
        });

        textPasswordField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER){
                buttonSubmit.requestFocus();
                keyEvent.consume();
            }
        });
        buttonSubmit.setOnMouseClicked(mouseEvent -> {
            if (textEmailField.getText().equals("admin") && textPasswordField.getText().equals("admin")){
                stage.close();
                new AdminPanel().adminMenu();
            } else {
                try {
                    new BuyerPanel(logInController.logIn(textEmailField.getText(), textPasswordField.getText())).start(new Stage());
                    stage.close();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
        });
        buttonSubmit.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER){
                if (textEmailField.getText().equals("admin") && textPasswordField.getText().equals("admin")){
                    stage.close();
                    new AdminPanel().adminMenu();
                } else {
                    try {
                        new BuyerPanel(logInController.logIn(textEmailField.getText(), textPasswordField.getText())).start(new Stage());
                        stage.close();
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                }
            }
        });
        hBoxButton.getChildren().addAll(buttonCancel, buttonSubmit);
        hBoxButton.setAlignment(Pos.CENTER);
        hBoxButton.setSpacing(50);
        vBox.getChildren().addAll(hBoxEmail, hBoxPassword, hBoxButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        return vBox;
    }
}

