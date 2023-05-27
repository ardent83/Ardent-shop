package UI;

import controller.BuyerController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.user.Admin;
import model.user.Buyer;


public class BuyerPanel extends Application {
    public BuyerPanel(Buyer buyer) {
        this.buyer = buyer;
        this.buyerController = new BuyerController();
    }

    private final Buyer buyer;
    private final Admin admin = Admin.getAdmin();
    private final BuyerController buyerController;
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.rgb(8, 217, 214),CornerRadii.EMPTY, Insets.EMPTY)));

        root.setLeft(vBoxInformation(buyer, stage));
        root.setTop(paneTop(stage));
        root.setRight(paneRight(stage));


        Scene scene = new Scene(root,900,500);
        stage.setScene(scene);
        scene.getStylesheets().add("style.css");
        stage.setTitle("Ardent Shop");
        stage.getIcons().add(new Image("icon.png"));
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
    }

    private VBox vBoxInformation(Buyer buyer,Stage stage){
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20));
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 173, 181), CornerRadii.EMPTY,Insets.EMPTY)));

        Image imageLogo = new Image("logo.png",200,200,true,true);
        ImageView viewLogo = new ImageView(imageLogo);
        viewLogo.setEffect(new DropShadow());

        Text text = new Text(buyer.toString());
        text.setStrokeWidth(1);
        text.setFill(Color.rgb(57, 62, 70));
        text.setStroke(Color.rgb(57, 62, 70));
        text.setTextAlignment(TextAlignment.LEFT);
        text.setId("textSignUp");

        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        Button buttonEdit = new Button("Edit");
        Button buttonIncrease = new Button("Increase AC");
        hBox.getChildren().addAll(buttonIncrease, buttonEdit);
        vBox.getChildren().addAll(viewLogo,text,hBox);

        buttonIncrease.setCursor(Cursor.HAND);
        buttonEdit.setCursor(Cursor.HAND);

        buttonIncrease.setOnMouseClicked(mouseEvent -> {
            new IncreaseAC(buyer).start(new Stage());
            stage.close();
        });
        buttonEdit.setOnMouseClicked(mouseEvent -> {
            new EditInformation(buyer).start(new Stage());
            stage.close();
        });
        return vBox;
    }

    private BorderPane paneTop(Stage stage){
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 66, 90),CornerRadii.EMPTY, Insets.EMPTY)));
        Image image = new Image("iconCart.png");
        ImageView imageView = new ImageView(image);
        Image image1 = new Image("iconCart0.png");
        ImageView imageView1 = new ImageView(image1);
        if (buyer.getCart().size() == 0){
            root.setRight(imageView);
        } else {
            root.setRight(imageView1);
        }
        Image imageLogOut = new Image("iconLogOut.png");
        ImageView imageViewLogOut = new ImageView(imageLogOut);
        root.setLeft(imageViewLogOut);

        imageView.setCursor(Cursor.HAND);
        imageView1.setCursor(Cursor.HAND);
        imageViewLogOut.setCursor(Cursor.HAND);

        imageViewLogOut.setOnMouseClicked(mouseEvent -> {
            new MainPanel().start(new Stage());
            stage.close();
        });

        return root;
    }

    private BorderPane paneRight(Stage stage){
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.rgb(8, 217, 214),CornerRadii.EMPTY, Insets.EMPTY)));

        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(50));

//        history
        Image imageHistory = new Image("history.png");
        ImageView viewHistory = new ImageView(imageHistory);
        Text textHistory = new Text("History");
        textHistory.setId("textBuyer");
        VBox vBoxHistory = new VBox();
        vBoxHistory.setAlignment(Pos.CENTER);
        vBoxHistory.getChildren().addAll(viewHistory,textHistory);

//        discount
        Image imageDiscount = new Image("discount.png");
        ImageView viewDiscount = new ImageView(imageDiscount);
        Image imageDiscount1 = new Image("discount0.png");
        ImageView viewDiscount1 = new ImageView(imageDiscount1);
        Text textDiscount = new Text("Discount Codes");
        textDiscount.setId("textBuyer");
        VBox vBoxDiscount = new VBox();
        vBoxDiscount.setAlignment(Pos.CENTER);
        if (buyer.getDiscountCodes().size() == 0){
            vBoxDiscount.getChildren().addAll(viewDiscount,textDiscount);
        } else {
            vBoxDiscount.getChildren().addAll(viewDiscount1,textDiscount);
        }

//        shop
        Image imageShop = new Image("shop.png");
        ImageView viewShop = new ImageView(imageShop);
        Text textShop = new Text("Shop");
        textShop.setId("textBuyer");
        VBox vBoxShop = new VBox();
        vBoxShop.setAlignment(Pos.CENTER);
        vBoxShop.setOnMouseClicked(mouseEvent -> {
//            new ItemPanelBuyer(admin.getItemArrayList(), buyer).start(new Stage());
//            stage.close();
        });
        vBoxShop.getChildren().addAll(viewShop,textShop);

        vBoxShop.setCursor(Cursor.HAND);
        vBoxDiscount.setCursor(Cursor.HAND);
        vBoxHistory.setCursor(Cursor.HAND);

        hBox.getChildren().addAll(vBoxShop,vBoxDiscount, vBoxHistory);
        root.setTop(hBox);
        return root;
    }
}
