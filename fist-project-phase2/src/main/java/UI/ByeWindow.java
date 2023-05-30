package UI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Timer;
import java.util.TimerTask;

public class ByeWindow extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setCenter(vBoxLogo());
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 171, 179),new CornerRadii(30),Insets.EMPTY)));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("styleItem.css");

        stage.setScene(scene);
        stage.setMinWidth(300);
        stage.setMinHeight(400);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(stage::close);
            }
        }, 5000);
    }
    private VBox vBoxLogo(){
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20));
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 171, 179),new CornerRadii(30),Insets.EMPTY)));

        DropShadow effect = new DropShadow();
        effect.setColor(Color.rgb(0, 66, 90));
        Image image = new Image("logo.png",400,400,true,true);

        ImageView imageView = new ImageView(image);
        imageView.setEffect(effect);

        Text text = new Text("Thank you for considering our online store!" +
                                "\n        We hope to serve you in the future.");
        text.setId("textItem");

        vBox.getChildren().addAll(imageView, text);

        return vBox;
    }
}
