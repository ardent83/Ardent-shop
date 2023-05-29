package UI;

import controller.SignUpController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Optional;

public class SignUP extends Application {
    public SignUP() {
        this.signUpController  = new SignUpController();
    }

    private final SignUpController signUpController;
    @Override
    public void start(Stage stage) {
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
        stage.setTitle("Sign Up");
        stage.getIcons().add(new Image("icon.png"));
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
    }
    private VBox inputVbox(Stage stage){
        VBox vBox = new VBox();
        DropShadow effect = new DropShadow();
        effect.setColor(Color.rgb(0, 66, 90));
        vBox.setPadding(new Insets(100));
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 173, 181),CornerRadii.EMPTY, Insets.EMPTY)));

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
        textEmailField.setText("smmmpm321@gmail.com");
        textEmailField.setId("SignUp");
        textEmailField.setEffect(effect);
        hBoxEmail.getChildren().addAll(stackEmail, textEmailField);

//        Number
        HBox hBoxNumber = new HBox();
        StackPane stackNumber = new StackPane();
        stackNumber.setAlignment(Pos.CENTER);
        Text textNumber = new Text("Number");
        textNumber.setId("textSignUp");
        textNumber.setFill(Color.rgb(255,255,255));
        ImageView imageViewNumber = new ImageView(image);
        imageViewNumber.setEffect(effect);
        stackNumber.getChildren().addAll(imageViewNumber, textNumber);

        TextField textNumberField = new TextField();
        textNumberField.setText("09156703445");
        textNumberField.setId("SignUp");
        textNumberField.setEffect(effect);
        hBoxNumber.getChildren().addAll(stackNumber,textNumberField);

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
        textPasswordField.setText("83112#Moha");
        textPasswordField.setId("SignUp");
        textPasswordField.setEffect(effect);
        hBoxPassword.getChildren().addAll(stackPassword,textPasswordField);

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

        buttonSubmit.setOnMouseClicked(mouseEvent -> {
            try {
                signUpController.register(textEmailField.getText(),textNumberField.getText(),textPasswordField.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Request sent successfully!");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    new MainPanel().start(new Stage());
                    stage.close();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });
        hBoxButton.getChildren().addAll(buttonCancel, buttonSubmit);
        hBoxButton.setAlignment(Pos.CENTER);
        hBoxButton.setSpacing(50);
        vBox.getChildren().addAll(hBoxEmail, hBoxNumber, hBoxPassword, hBoxButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        return vBox;
    }
}
