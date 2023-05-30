package UI;


import controller.ItemBuyerController;
import controller.ItemController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.item.Category;
import model.item.Item;
import model.user.Admin;
import model.user.Buyer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ItemPanelBuyer extends Application {
    public ItemPanelBuyer(ArrayList<Item> items, Buyer buyer) {
        this.items = items;
        this.itemController = new ItemController();
        this.itemBuyerController = new ItemBuyerController(buyer);
        this.buyer = buyer;
    }

    private final Admin admin = Admin.getAdmin();
    private final ArrayList<Item> items;
    private final Buyer buyer;
    private final ItemController itemController;
    private final ItemBuyerController itemBuyerController;
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.rgb(216, 227, 231),CornerRadii.EMPTY, Insets.EMPTY)));
        root.setLeft(vBoxList(root));
        root.setTop(hBoxTop(stage));

        Scene scene = new Scene(root);
        stage.setMinWidth(1100);
        stage.setMinHeight(600);
        scene.getStylesheets().add("styleItem.css");
        stage.setScene(scene);
        stage.setTitle("Item Panel");
        stage.getIcons().add(new Image("icon.png"));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(windowEvent -> {
            new ByeWindow().start(new Stage());
        });
    }
    private HBox hBoxTop(Stage stage){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(2));
        hBox.setSpacing(20);
        hBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 171, 179),CornerRadii.EMPTY,Insets.EMPTY)));
        Image imageLogOut = new Image("iconLogOut.png");
        ImageView viewOut = new ImageView(imageLogOut);
        viewOut.setCursor(Cursor.HAND);
        viewOut.setOnMouseClicked(mouseEvent -> {
            new BuyerPanel(buyer).start(new Stage());
            stage.close();
        });

        Button buttonSort = new Button("Sort");
        HBox hBoxButton = new HBox(buttonSort);
        hBoxButton.setPadding(new Insets(15));
        buttonSort.setCursor(Cursor.HAND);
        buttonSort.setOnMouseClicked(mouseEvent -> {
            this.items.sort(Item::compareTo);
            new ItemPanelBuyer(this.items,this.buyer).start(new Stage());
            stage.close();
        });

        hBox.getChildren().addAll(viewOut, hBoxButton, filterByCategory(stage),generalFilter(stage));
        return hBox;
    }
    private VBox vBoxList(BorderPane root){
        ListView<Item> listView = new ListView<>();
        listView.setMinWidth(300);
        listView.setMinHeight(713);
        listView.setMaxHeight(713);

        listView.setBackground(new Background(new BackgroundFill(Color.rgb(0, 171, 179),CornerRadii.EMPTY,Insets.EMPTY)));

        Pagination pagination = new Pagination();
        pagination.setCurrentPageIndex(0);
        pagination.setPageCount((int) Math.ceil(items.size()/9.0));
        pagination.setMaxPageIndicatorCount(4);
        listView.setItems(FXCollections.observableArrayList(createPage(pagination.getCurrentPageIndex())));

        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {;
            listView.setItems(FXCollections.observableArrayList(createPage(pagination.getCurrentPageIndex())));
        });


        listView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Item> call(ListView<Item> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Item item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText("Name : " + item.getName() + "\n$" + item.getPrice() + "\nScore : " + item.getAverageScore());
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });

        listView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2 && listView.getSelectionModel().getSelectedItem() != null){
                root.setCenter(hBoxItem(listView.getSelectionModel().getSelectedItem(), root));
            }
        });

        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 171, 179),CornerRadii.EMPTY,Insets.EMPTY)));
        vBox.getChildren().addAll(listView,pagination);

        return vBox;
    }
    private List<Item> createPage(int pageIndex) {
        int fromIndex = pageIndex * 9;
        int toIndex = Math.min(fromIndex + 9, items.size());
        if (fromIndex < items.size()){
            return (items.subList(fromIndex, toIndex));
        }
        return new ArrayList<>();
    }

    private HBox hBoxItem(Item item, BorderPane root){
        HBox hBox = new HBox();
        DropShadow effect = new DropShadow();
        effect.setColor(Color.rgb(0, 66, 90));

        hBox.setPadding(new Insets(30));
        hBox.setSpacing(50);
        hBox.setBackground(new Background(new BackgroundFill(Color.rgb(216, 227, 231),CornerRadii.EMPTY,Insets.EMPTY)));
        Text text = new Text((item.toString()+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"
                +"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"));
        text.setId("textItem");

        Button buttonComment = new Button("Post Comment");

        buttonComment.setAlignment(Pos.CENTER_RIGHT);
        buttonComment.setCursor(Cursor.HAND);
        buttonComment.setOnMouseClicked(mouseEvent -> {
            root.setRight(vBoxComment(root,item.getIdItem()));
        });


        Button buttonMinus = new Button("- ");
        Button buttonPlus = new Button("+");
        buttonMinus.setId("leftButton");
        buttonPlus.setId("rightButton");
        StackPane stackPane = new StackPane();
        Image imageStack = new Image("countCart.png");
        ImageView viewStack = new ImageView(imageStack);

        Text textStack = new Text("1");
        textStack.setId("textItem");
        stackPane.getChildren().addAll(viewStack,textStack);
        buttonMinus.setCursor(Cursor.HAND);
        buttonMinus.setOnMouseClicked(mouseEvent -> {
            if (Integer.parseInt(textStack.getText()) > 1){
                int count = Integer.parseInt(textStack.getText());
                count--;
                textStack.setText(String.valueOf(count));
            }
        });
        buttonPlus.setOnMouseClicked(mouseEvent -> {
            if (Integer.parseInt(textStack.getText()) < item.getAvailableNumber()){
                int count = Integer.parseInt(textStack.getText());
                count++;
                textStack.setText(String.valueOf(count));
            }
        });

        buttonPlus.setCursor(Cursor.HAND);
        stackPane.setEffect(effect);

        Button buttonADD = new Button("Add To Cart");
        buttonADD.setCursor(Cursor.HAND);
        buttonADD.setOnMousePressed(mouseEvent -> {
            itemBuyerController.addItemToCart(buyer, item, Integer.parseInt(textStack.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Adding the item to the cart was successful.");
            alert.showAndWait();
        });
        HBox hBoxButtonAdd = new HBox();
        hBoxButtonAdd.getChildren().addAll(buttonMinus,stackPane, buttonPlus);



        VBox vBoxButton = new VBox();
        vBoxButton.setPadding(new Insets(3));
        vBoxButton.setSpacing(10);
        vBoxButton.getChildren().addAll(buttonComment, hBoxButtonAdd, buttonADD);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPadding(new Insets(10));
        scrollPane.setId("scroller");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setBackground(new Background(new BackgroundFill(Color.rgb(216, 227, 231),CornerRadii.EMPTY,Insets.EMPTY)));
        VBox vBox = new VBox(text);
        vBox.setPadding(new Insets(20));
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(216, 227, 231),CornerRadii.EMPTY,Insets.EMPTY)));

        scrollPane.setContent(vBox);

        hBox.getChildren().addAll(scrollPane, vBoxButton);
        return hBox;
    }
    private VBox vBoxComment(BorderPane root,String idItem){
        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(216, 227, 231),CornerRadii.EMPTY,Insets.EMPTY)));

        vBox.setPadding(new Insets(20));
        vBox.setSpacing(10);
        TextArea textArea = new TextArea();
        textArea.setId("textArea");
        textArea.setEffect(new DropShadow());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        Button buttonCancel = new Button("Cansel");
        Button buttonSubmit = new Button("Submit");
        hBox.getChildren().addAll(buttonCancel, buttonSubmit);
        buttonCancel.setOnMouseClicked(mouseEvent -> {
            root.setRight(null);
        });
        buttonSubmit.setOnMouseClicked(mouseEvent -> {
            itemBuyerController.postAComment(idItem,textArea.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("The comment was sent successfully");
            alert.showAndWait();
            root.setRight(null);
        });
        vBox.getChildren().addAll(textArea, hBox);
        return vBox;
    }
    private HBox filterByCategory(Stage stage){
        HBox hBoxCenter = new HBox();
        hBoxCenter.setSpacing(10);
        hBoxCenter.setAlignment(Pos.CENTER);
        hBoxCenter.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
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
        Image digital = new Image("12.png",50,50,true,true);
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
            new ItemPanelBuyer(itemController.filterCategory(Category.DIGITAL), buyer).start(new Stage());
            stage.close();
        });

        Text text = new Text("Digital");
        text.setFont(Font.font("Bell MT",14));
        vBox.getChildren().addAll(digitalView,text);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private VBox foodView(Stage stage){
        VBox vBox = new VBox();
        Image food = new Image("14.png",50,50,true,true);
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
            new ItemPanelBuyer(itemController.filterCategory(Category.FOOD), buyer).start(new Stage());
            stage.close();
        });
        Text text = new Text("Food");
        text.setFont(Font.font("Bell MT",14));
        vBox.getChildren().addAll(foodView,text);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private VBox stationaryView(Stage stage){
        VBox vBox = new VBox();
        Image stationary = new Image("13.png",50,50,true,true);
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
            new ItemPanelBuyer(itemController.filterCategory(Category.STATIONERY), buyer).start(new Stage());
            stage.close();
        });

        Text text = new Text("Stationary");
        text.setFont(Font.font("Bell MT",14));
        vBox.getChildren().addAll(stationaryView,text);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private VBox vehiclesView(Stage stage){
        VBox vBox = new VBox();
        Image vehicles = new Image("15.png",50,50,true,true);
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
            new ItemPanelBuyer(itemController.filterCategory(Category.VEHICLES), buyer).start(new Stage());
            stage.close();
        });

        Text text = new Text("Vehicles");
        text.setFont(Font.font("Bell MT",14));
        vBox.getChildren().addAll(vehiclesView,text);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }
    private BorderPane generalFilter(Stage stage){
        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(Color.rgb(0, 173, 181),CornerRadii.EMPTY,Insets.EMPTY)));
        borderPane.setPadding(new Insets(2));
        Text text = new Text("Filter By ...");
        text.setFont(Font.font("Bell MT",18));
        borderPane.setTop(text);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(50);
        Text price = new Text("Price");
        Text customerReview = new Text("Customer Review");
        Text availability = new Text("Availability");
        price.setFont(Font.font("Bell MT",16));
        customerReview.setFont(Font.font("Bell MT",16));
        availability.setFont(Font.font("Bell MT",16));

        price.setCursor(Cursor.HAND);
        price.setOnMouseClicked(mouseEvent -> {
            borderPane.setLeft(vBoxPrice(borderPane, stage));
        });

        availability.setCursor(Cursor.HAND);
        availability.setOnMouseClicked(mouseEvent -> {
            new ItemPanelBuyer(itemController.filterAvailableItem(admin.getItemArrayList()),buyer).start(new Stage());
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
        vBox.setPadding(new Insets(10));

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
                new ItemPanelBuyer(itemController.filterPrice(Double.parseDouble(textMinField.getText()),Double.parseDouble(textMaxField.getText()),admin.getItemArrayList()), buyer).start(new Stage());
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
                    new ItemPanelBuyer(itemController.filterPrice(Double.parseDouble(textMinField.getText()),Double.parseDouble(textMaxField.getText()),admin.getItemArrayList()), buyer).start(new Stage());
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
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(5);
        Image image4 = new Image("4star.png",80,80,true,true);
        ImageView view4 = new ImageView(image4);
        Image image3 = new Image("3star.png",80,80,true,true);
        ImageView view3 = new ImageView(image3);
        Image image2 = new Image("2star.png",80,80,true,true);
        ImageView view2 = new ImageView(image2);
        Image image1 = new Image("1star.png",80,80,true,true);
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
            new ItemPanelBuyer(itemController.filterScore(4,5,admin.getItemArrayList()), buyer).start(new Stage());
            stage.close();
        });
        view3.setOnMouseClicked(mouseEvent -> {
            new ItemPanelBuyer(itemController.filterScore(3,5,admin.getItemArrayList()), buyer).start(new Stage());
            stage.close();
        });
        view2.setOnMouseClicked(mouseEvent -> {
            new ItemPanelBuyer(itemController.filterScore(2,5,admin.getItemArrayList()), buyer).start(new Stage());
            stage.close();
        });
        view1.setOnMouseClicked(mouseEvent -> {
            new ItemPanelBuyer(itemController.filterScore(1,5,admin.getItemArrayList()), buyer).start(new Stage());
            stage.close();
        });

        vBox.getChildren().addAll(view4,view3,view2,view1,buttonCancel);
        return vBox;
    }
}
