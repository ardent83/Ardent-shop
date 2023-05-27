package UI;

import controller.EditInformationController;
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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.user.Buyer;


public class EditInformation extends Application {
    public EditInformation(Buyer buyer) {
        this.buyer = buyer;
        this.informationController = new EditInformationController(buyer);
    }

    private final Buyer buyer;
    private final EditInformationController informationController;
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(editStage(stage));
        stage.setMinWidth(900);
        stage.setMinHeight(500);
        scene.getStylesheets().add("style.css");

        stage.setScene(scene);
        stage.setTitle("Edit Information");
        stage.getIcons().add(new Image("icon.png"));
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
    }
    private BorderPane editStage(Stage stage){
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 66, 90), CornerRadii.EMPTY, Insets.EMPTY)));

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 173, 181),CornerRadii.EMPTY, Insets.EMPTY)));
        DropShadow effect = new DropShadow();
        effect.setColor(Color.rgb(0, 66, 90));
        vBox.setPadding(new Insets(50));
        vBox.setSpacing(15);

//        Email
        vBox.getChildren().add(hBoxEmail(stage));

//        Number
        vBox.getChildren().add(hBoxNumber(stage));

//        Password
        vBox.getChildren().add(hBoxPassword(stage));

        Button buttonCansel = new Button("Back");
        buttonCansel.setMinWidth(690);
        buttonCansel.setCursor(Cursor.HAND);
        buttonCansel.setOnMouseClicked(mouseEvent -> {
            new BuyerPanel(buyer).start(new Stage());
            stage.close();
        });
        vBox.getChildren().add(buttonCansel);

        hBox.getChildren().add(vBox);
        root.setCenter(hBox);
        return root;
    }

    private HBox hBoxEmail(Stage stage){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        DropShadow effect = new DropShadow();
        effect.setColor(Color.rgb(0, 66, 90));
        Button buttonStaticEmail = new Button("New Email");
        buttonStaticEmail.setId("buttonStatic");
        buttonStaticEmail.setEffect(effect);

        TextField textEmailField = new TextField();
        textEmailField.setText("smmmpm321@gmail.com");
        textEmailField.setId("fieldEdit");
        textEmailField.setEffect(effect);

        Button buttonEmail = new Button("Submit Email");
        buttonEmail.setId("buttonEdit");
        buttonEmail.setEffect(effect);
        buttonEmail.setCursor(Cursor.HAND);
        buttonEmail.setOnMouseClicked(mouseEvent -> {
            try {
                informationController.editEmail(buyer,textEmailField.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Edit was done successfully.");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });

        hBox.getChildren().addAll(buttonStaticEmail, textEmailField, buttonEmail);
        return hBox;
    }

    private HBox hBoxNumber(Stage stage){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        DropShadow effect = new DropShadow();
        effect.setColor(Color.rgb(0, 66, 90));
        Button buttonStaticNumber = new Button("New Number");
        buttonStaticNumber.setId("buttonStatic");
        buttonStaticNumber.setEffect(effect);

        TextField textNumberField = new TextField();
        textNumberField.setText("09156703445");
        textNumberField.setId("fieldEdit");
        textNumberField.setEffect(effect);

        Button buttonNumber = new Button("Submit Number");
        buttonNumber.setId("buttonEdit");
        buttonNumber.setEffect(effect);
        buttonNumber.setCursor(Cursor.HAND);
        buttonNumber.setOnMouseClicked(mouseEvent -> {
            try {
                informationController.editNumber(buyer,textNumberField.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Edit was done successfully.");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });

        hBox.getChildren().addAll(buttonStaticNumber,textNumberField,buttonNumber);
        return hBox;
    }

    private HBox hBoxPassword(Stage stage){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        DropShadow effect = new DropShadow();
        effect.setColor(Color.rgb(0, 66, 90));
        Button buttonStaticPassword = new Button("New Password");
        buttonStaticPassword.setId("buttonStatic");
        buttonStaticPassword.setEffect(effect);

        TextField textPasswordField = new TextField();
        textPasswordField.setText("83112#Moa");
        textPasswordField.setId("fieldEdit");
        textPasswordField.setEffect(effect);

        Button buttonPassword = new Button("Submit Password");
        buttonPassword.setId("buttonEdit");
        buttonPassword.setEffect(effect);
        buttonPassword.setCursor(Cursor.HAND);
        buttonPassword.setOnMouseClicked(mouseEvent -> {
            try {
                informationController.editPassword(buyer,textPasswordField.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Edit was done successfully.");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });

        hBox.getChildren().addAll(buttonStaticPassword, textPasswordField, buttonPassword);
        return hBox;
    }
}
