package UI;

import controller.IncreaseACController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.user.Buyer;

import java.util.Optional;

public class IncreaseAC extends Application {
    public IncreaseAC(Buyer buyer) {
        this.increaseACController = new IncreaseACController();
        this.buyer = buyer;
    }

    private final IncreaseACController increaseACController;
    private final Buyer buyer;
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(increaseAC(stage));
        stage.setMinWidth(900);
        stage.setMinHeight(500);
        scene.getStylesheets().add("style.css");

        stage.setScene(scene);
        stage.setTitle("Increase account credit");
        stage.getIcons().add(new Image("icon.png"));
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
    }

    private BorderPane increaseAC(Stage stage){
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 66, 90),CornerRadii.EMPTY, Insets.EMPTY)));
        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 173, 181), CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setPadding(new Insets(50));
        vBox.setSpacing(15);
        vBox.setAlignment(Pos.CENTER);
        DropShadow effect = new DropShadow();
        effect.setColor(Color.rgb(0, 66, 90));

//        Number
        HBox hBoxNumber = new HBox();
        hBoxNumber.setAlignment(Pos.CENTER);
        Button buttonStaticNumber = new Button("Number");
        buttonStaticNumber.setId("buttonStatic");
        buttonStaticNumber.setEffect(effect);

        TextField textNumberField = new TextField();
        textNumberField.setText("6037701513131141");
        textNumberField.setId("SignUp");
        textNumberField.setEffect(effect);

        hBoxNumber.getChildren().addAll(buttonStaticNumber,textNumberField);

//        CVV2
        HBox hBoxCVV2 = new HBox();
        hBoxCVV2.setAlignment(Pos.CENTER);
        Button buttonStaticCVV2 = new Button("CVV2");
        buttonStaticCVV2.setId("buttonStatic");
        buttonStaticCVV2.setEffect(effect);

        TextField textCVV2Field = new TextField();
        textCVV2Field.setText("9865");
        textCVV2Field.setId("increaseACField");
        textCVV2Field.setEffect(effect);
        hBoxCVV2.getChildren().addAll(buttonStaticCVV2, textCVV2Field);

//        Password
        HBox hBoxPassword = new HBox();
        hBoxPassword.setAlignment(Pos.CENTER);
        Button buttonStaticPassword = new Button("Password");
        buttonStaticPassword.setId("buttonStatic");
        buttonStaticPassword.setEffect(effect);

        TextField textPasswordField = new TextField();
        textPasswordField.setText("2569");
        textPasswordField.setId("increaseACField");
        textPasswordField.setEffect(effect);
        hBoxPassword.getChildren().addAll(buttonStaticPassword,textPasswordField);

//        CVV2 & Password
        HBox hBoxCVV2Password = new HBox();
        hBoxCVV2Password.setAlignment(Pos.CENTER);
        hBoxCVV2Password.setSpacing(6);
        hBoxCVV2Password.getChildren().addAll(hBoxCVV2,hBoxPassword);

//        amount
        HBox hBoxAmount = new HBox();
        hBoxAmount.setAlignment(Pos.CENTER);
        Button buttonStaticAmount = new Button("Amount");
        buttonStaticAmount.setId("buttonStatic");
        buttonStaticAmount.setEffect(effect);

        TextField textAmountField = new TextField();
        textAmountField.setText("2500000");
        textAmountField.setId("SignUp");
        textAmountField.setEffect(effect);
        hBoxAmount.getChildren().addAll(buttonStaticAmount,textAmountField);

//        button
        HBox hBoxButton = new HBox();
        Button buttonBack = new Button("  Back  ");
        Button buttonSubmit = new Button("   Submit   ");
        hBoxButton.setSpacing(15);
        hBoxButton.setAlignment(Pos.CENTER);
        hBoxButton.getChildren().addAll(buttonBack,buttonSubmit);

        buttonBack.setCursor(Cursor.HAND);
        buttonSubmit.setCursor(Cursor.HAND);

        buttonBack.setOnMouseClicked(mouseEvent -> {
            new BuyerPanel(buyer).start(new Stage());
            stage.close();
        });
        buttonSubmit.setOnMouseClicked(mouseEvent -> {
            try {
                increaseACController.increaseCredit(buyer,Double.parseDouble(textAmountField.getText()),textNumberField.getText(),textPasswordField.getText(),textCVV2Field.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Account credit request has been successfully sent.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    new BuyerPanel(buyer).start(new Stage());
                    stage.close();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });

        vBox.getChildren().addAll(hBoxNumber,hBoxCVV2Password,hBoxAmount,hBoxButton);
        HBox hBox = new HBox(vBox);
        hBox.setAlignment(Pos.CENTER);
        root.setCenter(hBox);
        return root;
    }
}
