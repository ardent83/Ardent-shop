package UI;

import controller.ItemController;
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
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.item.Category;
import model.item.Item;
import model.item.food.Food;
import model.user.Admin;
import model.user.Buyer;
import model.user.Discount;
import model.user.PurchaseInvoice;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class MainPanel extends Application {
    public MainPanel() {
        this.itemController = new ItemController();
    }

    private final ItemController itemController;
    private final Admin admin = Admin.getAdmin();
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setTop(borderPaneTop(stage));
        root.setCenter(itemPanelMain(stage));
        root.setBottom(bottomFilter(stage));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("style.css");

        stage.setScene(scene);
        stage.setMinWidth(1100);
        stage.setMinHeight(650);
        stage.setTitle("Ardent Shop");
        stage.getIcons().add(new Image("icon.png"));
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(windowEvent -> {
            new ByeWindow().start(new Stage());
        });
    }

    private BorderPane borderPaneTop(Stage stage) {
        BorderPane borderPane = new BorderPane();
        Button buttonLogin = new Button("Log in");
        Button buttonSignUp = new Button("Sign Up");

        buttonLogin.setCursor(Cursor.HAND);
        buttonSignUp.setCursor(Cursor.HAND);

        buttonSignUp.setOnMouseClicked(mouseEvent -> {
            new SignUP().start(new Stage());
            stage.close();
        });
        buttonLogin.setOnMouseClicked(mouseEvent -> {
            new LogIn().start(new Stage());
            stage.close();
        });
        HBox hBox = new HBox(buttonSignUp,buttonLogin);
        hBox.setSpacing(10);
        borderPane.setRight(hBox);
        borderPane.setBackground(new Background(new BackgroundFill(Color.rgb(0, 173, 181),CornerRadii.EMPTY,Insets.EMPTY)));
        borderPane.setPadding(new Insets(12));
        borderPane.setLeft(paneSearch(stage));

        return borderPane;
    }
    private HBox paneSearch(Stage stage){
        HBox hBox = new HBox();

        TextField textField = new TextField();
        textField.setId("searchField");
        textField.setPromptText("Search");

        StackPane stackPane = new StackPane();

        Image image = new Image("search0.png");
        ImageView imageView = new ImageView(image);

        Image image1 = new Image("search1.png");
        ImageView imageView1 = new ImageView(image1);

        stackPane.getChildren().addAll(imageView,imageView1);
        hBox.getChildren().addAll(textField,stackPane);

        stackPane.setCursor(Cursor.HAND);

        stackPane.setOnMouseClicked(mouseEvent -> {
            new ItemPanel(itemController.searchItem(textField.getText())).start(new Stage());
            stage.close();
        });
        return hBox;
    }

    private HBox itemPanelMain(Stage stage){
        HBox hBoxCenter = new HBox();
        hBoxCenter.setSpacing(20);
        hBoxCenter.setAlignment(Pos.CENTER);
        hBoxCenter.setBackground(new Background(new BackgroundFill(Color.rgb(214, 214, 214),CornerRadii.EMPTY,Insets.EMPTY)));

//        digital
        hBoxCenter.getChildren().add(digitalView(stage));

//        food
        hBoxCenter.getChildren().add(foodView(stage));

//        stationary
        hBoxCenter.getChildren().add(stationaryView(stage));

//        vehicles
        hBoxCenter.getChildren().add(vehiclesView(stage));

        return hBoxCenter;
    }

    private VBox digitalView(Stage stage){
        VBox vBox = new VBox();
        Image digital = new Image("12.png",250,250,true,true);
        ImageView digitalView = new ImageView(digital);
        DropShadow shadowDigital = new DropShadow();
        shadowDigital.setColor(Color.rgb(238, 238, 238));
        digitalView.setEffect(shadowDigital);
        vBox.setCursor(Cursor.HAND);
        digitalView.setOnMouseEntered(mouseEvent -> {
            shadowDigital.setColor(Color.rgb(57, 62, 70));
        });
        digitalView.setOnMouseExited(mouseEvent -> {
            shadowDigital.setColor(Color.rgb(238, 238, 238));
        });
        digitalView.setOnMouseClicked(mouseEvent -> {
            shadowDigital.setColor(Color.rgb(0, 173, 181));
            new ItemPanel(itemController.filterCategory(Category.DIGITAL)).start(new Stage());
            stage.close();
        });

        Text text = new Text("Digital");
        text.setFont(Font.font("Bell MT",32));
        vBox.getChildren().addAll(digitalView,text);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private VBox foodView(Stage stage){
        VBox vBox = new VBox();
        Image food = new Image("14.png",250,250,true,true);
        ImageView foodView = new ImageView(food);
        DropShadow shadowFood = new DropShadow();
        shadowFood.setColor(Color.rgb(238, 238, 238));
        foodView.setEffect(shadowFood);
        vBox.setCursor(Cursor.HAND);
        foodView.setOnMouseEntered(mouseEvent -> {
            shadowFood.setColor(Color.rgb(57, 62, 70));
        });
        foodView.setOnMouseExited(mouseEvent -> {
            shadowFood.setColor(Color.rgb(238, 238, 238));
        });
        foodView.setOnMouseClicked(mouseEvent -> {
            shadowFood.setColor(Color.rgb(0, 173, 181));
            new ItemPanel(itemController.filterCategory(Category.FOOD)).start(new Stage());
            stage.close();
        });
        Text text = new Text("Food");
        text.setFont(Font.font("Bell MT",32));
        vBox.getChildren().addAll(foodView,text);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private VBox stationaryView(Stage stage){
        VBox vBox = new VBox();
        Image stationary = new Image("13.png",250,250,true,true);
        ImageView stationaryView = new ImageView(stationary);
        DropShadow shadowStationary = new DropShadow();
        shadowStationary.setColor(Color.rgb(238, 238, 238));
        stationaryView.setEffect(shadowStationary);
        vBox.setCursor(Cursor.HAND);
        stationaryView.setOnMouseEntered(mouseEvent -> {
            shadowStationary.setColor(Color.rgb(57, 62, 70));
        });
        stationaryView.setOnMouseExited(mouseEvent -> {
            shadowStationary.setColor(Color.rgb(238, 238, 238));
        });
        stationaryView.setOnMouseClicked(mouseEvent -> {
            shadowStationary.setColor(Color.rgb(0, 173, 181));
            new ItemPanel(itemController.filterCategory(Category.STATIONERY)).start(new Stage());
            stage.close();
        });

        Text text = new Text("Stationary");
        text.setFont(Font.font("Bell MT",32));
        vBox.getChildren().addAll(stationaryView,text);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private VBox vehiclesView(Stage stage){
        VBox vBox = new VBox();
        Image vehicles = new Image("15.png",250,250,true,true);
        ImageView vehiclesView = new ImageView(vehicles);
        DropShadow shadowVehicles = new DropShadow();
        shadowVehicles.setColor(Color.rgb(238, 238, 238));
        vehiclesView.setEffect(shadowVehicles);
        vBox.setCursor(Cursor.HAND);
        vehiclesView.setOnMouseEntered(mouseEvent -> {
            shadowVehicles.setColor(Color.rgb(57, 62, 70));
        });
        vehiclesView.setOnMouseExited(mouseEvent -> {
            shadowVehicles.setColor(Color.rgb(238, 238, 238));
        });
        vehiclesView.setOnMouseClicked(mouseEvent -> {
            shadowVehicles.setColor(Color.rgb(0, 173, 181));
            new ItemPanel(itemController.filterCategory(Category.VEHICLES)).start(new Stage());
            stage.close();
        });

        Text text = new Text("Vehicles");
        text.setFont(Font.font("Bell MT",32));
        vBox.getChildren().addAll(vehiclesView,text);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private BorderPane bottomFilter(Stage stage){
        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(Color.rgb(0, 173, 181),CornerRadii.EMPTY,Insets.EMPTY)));
        borderPane.setPadding(new Insets(30));
        Text text = new Text("Filter By ...");
        text.setFont(Font.font("Bell MT",32));
        borderPane.setTop(text);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(50);
        hBox.setPadding(new Insets(0));
        Text price = new Text("Price");
        Text customerReview = new Text("Customer Review");
        Text availability = new Text("Availability");
        price.setFont(Font.font("Bell MT",32));
        customerReview.setFont(Font.font("Bell MT",32));
        availability.setFont(Font.font("Bell MT",32));

        price.setCursor(Cursor.HAND);
        price.setOnMouseClicked(mouseEvent -> {
            borderPane.setLeft(vBoxPrice(borderPane, stage));
        });

        availability.setCursor(Cursor.HAND);
        availability.setOnMouseClicked(mouseEvent -> {
            new ItemPanel(itemController.filterAvailableItem(admin.getItemArrayList())).start(new Stage());
            stage.close();
        });

        customerReview.setCursor(Cursor.HAND);
        customerReview.setOnMouseClicked(mouseEvent -> {
            borderPane.setRight(vBoxCustomerReview(borderPane,stage));
        });

        hBox.getChildren().addAll(price,availability,customerReview);
        borderPane.setCenter(hBox);
        return borderPane;
    }
    VBox vBoxPrice(BorderPane borderPane,Stage stage){
        VBox vBox = new VBox();
        DropShadow effect = new DropShadow();
        effect.setColor(Color.rgb(0, 66, 90));

        HBox hBoxMin = new HBox();
        hBoxMin.setAlignment(Pos.CENTER);
        Button buttonStaticMin = new Button("Min");
        buttonStaticMin.setId("buttonStatic");
        buttonStaticMin.setEffect(effect);

        TextField textMinField = new TextField();
        textMinField.setId("SignUp");
        textMinField.setEffect(effect);
        hBoxMin.getChildren().addAll(buttonStaticMin, textMinField);

        HBox hBoxMax = new HBox();
        hBoxMax.setAlignment(Pos.CENTER);
        Button buttonStaticMax = new Button("Max");
        buttonStaticMax.setId("buttonStatic");
        buttonStaticMax.setEffect(effect);

        TextField textMaxField = new TextField();
        textMaxField.setId("SignUp");
        textMaxField.setEffect(effect);
        hBoxMax.getChildren().addAll(buttonStaticMax,textMaxField);
        vBox.getChildren().addAll(hBoxMin,hBoxMax);
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10,10,10,100));

        HBox hBoxButton = new HBox();
        hBoxButton.setAlignment(Pos.CENTER);
        hBoxButton.setSpacing(5);
        hBoxButton.setPadding(new Insets(5));
        Button buttonCancel = new Button("Cancel");
        Button buttonSubmit = new Button("Submit");
        buttonCancel.setCursor(Cursor.HAND);
        buttonSubmit.setCursor(Cursor.HAND);
        buttonCancel.setOnMouseClicked(mouseEvent -> {
            borderPane.setLeft(null);
        });
        buttonSubmit.setOnMouseClicked(mouseEvent -> {
            try{
                new ItemPanel(itemController.filterPrice(Double.parseDouble(textMinField.getText()),Double.parseDouble(textMaxField.getText()),admin.getItemArrayList())).start(new Stage());
                stage.close();
            }
            catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });
        buttonSubmit.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER){
                try{
                    new ItemPanel(itemController.filterPrice(Double.parseDouble(textMinField.getText()),Double.parseDouble(textMaxField.getText()),admin.getItemArrayList())).start(new Stage());
                    stage.close();
                }
                catch (Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
        });
        hBoxButton.getChildren().addAll(buttonCancel, buttonSubmit);
        vBox.getChildren().add(hBoxButton);

        return vBox;
    }
    private VBox vBoxCustomerReview(BorderPane borderPane,Stage stage){
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0,150,0,0));
        vBox.setSpacing(5);
        Image image4 = new Image("4star.png",120,30,true,true);
        ImageView view4 = new ImageView(image4);
        Image image3 = new Image("3star.png",120,30,true,true);
        ImageView view3 = new ImageView(image3);
        Image image2 = new Image("2star.png",120,30,true,true);
        ImageView view2 = new ImageView(image2);
        Image image1 = new Image("1star.png",120,30,true,true);
        ImageView view1 = new ImageView(image1);
        Button buttonCancel = new Button("Cancel");
        buttonCancel.setCursor(Cursor.HAND);
        buttonCancel.setOnMouseClicked(mouseEvent -> {
            borderPane.setRight(null);
        });
        view4.setCursor(Cursor.HAND);
        view3.setCursor(Cursor.HAND);
        view2.setCursor(Cursor.HAND);
        view1.setCursor(Cursor.HAND);

        view4.setOnMouseClicked(mouseEvent -> {
            new ItemPanel(itemController.filterScore(4,5,admin.getItemArrayList())).start(new Stage());
            stage.close();
        });
        view3.setOnMouseClicked(mouseEvent -> {
            new ItemPanel(itemController.filterScore(3,5,admin.getItemArrayList())).start(new Stage());
            stage.close();
        });
        view2.setOnMouseClicked(mouseEvent -> {
            new ItemPanel(itemController.filterScore(2,5,admin.getItemArrayList())).start(new Stage());
            stage.close();
        });
        view1.setOnMouseClicked(mouseEvent -> {
            new ItemPanel(itemController.filterScore(1,5,admin.getItemArrayList())).start(new Stage());
            stage.close();
        });

        vBox.getChildren().addAll(view4,view3,view2,view1,buttonCancel);
        return vBox;
    }
}
