package UI;

import controller.CartController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.item.Item;
import model.user.Buyer;

import java.util.*;

public class Cart extends Application {
    public Cart(Buyer buyer) {
        cartController = new CartController();
        items = buyer.getCart();
        setItemArrayList(items);
        this.buyer = buyer;
    }
    private final CartController cartController;
    private final HashMap<Item,Integer> items;
    private ArrayList<Item> itemArrayList;
    private final Buyer buyer;
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setLeft(vBoxList(root, stage));
        root.setTop(paneTop(stage));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("styleItem.css");
        stage.setScene(scene);
        stage.setTitle("Item Panel");
        stage.getIcons().add(new Image("icon.png"));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();

    }
    private BorderPane paneTop(Stage stage) {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0, 66, 90), CornerRadii.EMPTY, Insets.EMPTY)));
        DropShadow effect = new DropShadow();
        effect.setColor(Color.rgb(216, 227, 231));

        Image imageLogOut = new Image("iconLogOut.png");
        ImageView imageViewLogOut = new ImageView(imageLogOut);
        root.setLeft(imageViewLogOut);

        HBox hBoxAmount = new HBox();
        hBoxAmount.setPadding(new Insets(20));
        hBoxAmount.setEffect(effect);
        double amount = 0;
        for (Item item : itemArrayList){
            amount += (item.getPrice() * items.get(item));
        }
        Text textAmount = new Text(("Amount : " + amount));
        textAmount.setId("textItem");
        textAmount.setFill(Color.web("efefef"));
        hBoxAmount.getChildren().addAll(textAmount);
        root.setRight(hBoxAmount);

        imageViewLogOut.setCursor(Cursor.HAND);
        imageViewLogOut.setOnMouseClicked(mouseEvent -> {
            new BuyerPanel(buyer).start(new Stage());
            stage.close();
        });

        return root;
    }

    private VBox vBoxList(BorderPane root, Stage stage){
        ListView<Item> listView = new ListView<>();
        listView.setMinWidth(300);
        listView.setMinHeight(713);
        listView.setMaxHeight(713);

        listView.setBackground(new Background(new BackgroundFill(Color.rgb(0, 171, 179), CornerRadii.EMPTY, Insets.EMPTY)));

        Pagination pagination = new Pagination();
        pagination.setCurrentPageIndex(0);
        pagination.setPageCount((int) Math.ceil(itemArrayList.size()/7.0));
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
                            setText("Name : " + item.getName() + "\n$" + item.getPrice() + "\nScore : " + item.getAverageScore() + "\nNumber : " + items.get(item));
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });

        listView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2 && listView.getSelectionModel().getSelectedItem() != null){
                root.setCenter(hBoxItem(listView.getSelectionModel().getSelectedItem(), root,stage));
            }
        });

        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 171, 179),CornerRadii.EMPTY,Insets.EMPTY)));
        vBox.getChildren().addAll(listView,pagination);

        return vBox;
    }
    private HBox hBoxItem(Item item, BorderPane root, Stage stage){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(30));
        hBox.setSpacing(50);
        hBox.setBackground(new Background(new BackgroundFill(Color.rgb(216, 227, 231),CornerRadii.EMPTY,Insets.EMPTY)));
        DropShadow effect = new DropShadow();
        effect.setColor(Color.rgb(0, 66, 90));
        Text text = new Text((item.toString()+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"
                +"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"));
        text.setId("textItem");
        Button buttonComment = new Button("Buy");
        buttonComment.setAlignment(Pos.CENTER_RIGHT);
        buttonComment.setCursor(Cursor.HAND);
        buttonComment.setOnMouseClicked(mouseEvent -> {
            root.setRight(vBoxBuy(stage));
        });

        Button buttonMinus = new Button("- ");
        Button buttonPlus = new Button("+");
        buttonMinus.setId("leftButton");
        buttonPlus.setId("rightButton");
        StackPane stackPane = new StackPane();
        Image imageStack = new Image("countCart.png");
        ImageView viewStack = new ImageView(imageStack);

        Text textStack = new Text(String.valueOf(items.get(item)));
        textStack.setId("textItem");
        stackPane.getChildren().addAll(viewStack,textStack);
        buttonMinus.setOnMouseClicked(mouseEvent -> {
            if (Integer.parseInt(textStack.getText()) > 0){
                int count = Integer.parseInt(textStack.getText());
                count--;
                textStack.setText(String.valueOf(count));
                items.replace(item,count);
            }
        });
        buttonPlus.setOnMouseClicked(mouseEvent -> {
            if (Integer.parseInt(textStack.getText()) < item.getAvailableNumber()){
                int count = Integer.parseInt(textStack.getText());
                count++;
                textStack.setText(String.valueOf(count));
                items.replace(item,count);
            }
        });
        buttonMinus.setCursor(Cursor.HAND);
        buttonPlus.setCursor(Cursor.HAND);
        stackPane.setEffect(effect);

        HBox hBoxButton = new HBox(buttonMinus,stackPane,buttonPlus);
        Button buttonFix = new Button("Fix");
        buttonFix.setOnMousePressed(mouseEvent -> {
            items.replace(item,Integer.parseInt(textStack.getText()));
            if (Integer.parseInt(textStack.getText()) == 0){
                items.remove(item);
            }
            new Cart(buyer).start(new Stage());
            stage.close();
        });
        VBox vBoxButton = new VBox(buttonComment,hBoxButton,buttonFix);
        vBoxButton.setSpacing(10);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPadding(new Insets(10));
        scrollPane.setId("scroller");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setBackground(new Background(new BackgroundFill(Color.rgb(216, 227, 231),CornerRadii.EMPTY,Insets.EMPTY)));
        VBox vBox = new VBox(text);
        vBox.setPadding(new Insets(20));
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(216, 227, 231),CornerRadii.EMPTY,Insets.EMPTY)));

        scrollPane.setContent(vBox);
        hBox.getChildren().addAll(scrollPane,vBoxButton);
        return hBox;
    }
    private VBox vBoxBuy(Stage stage){
        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(216, 227, 231),CornerRadii.EMPTY,Insets.EMPTY)));
        vBox.setPadding(new Insets(40));
        vBox.setSpacing(10);
        DropShadow effect = new DropShadow();
        effect.setColor(Color.rgb(0, 66, 90));

        Text textDiscount = new Text("Discount Codes");
        textDiscount.setId("textItem");

        TextField textField = new TextField();
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();

        textField.setId("FieldDiscount");
        textField1.setId("FieldDiscount");
        textField2.setId("FieldDiscount");
        textField.setEffect(effect);
        textField1.setEffect(effect);
        textField2.setEffect(effect);

        Button buttonPlus = new Button("+");
        Button buttonBuy = new Button("Buy");

        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(10));
        hBox.getChildren().addAll(textDiscount,buttonPlus);
        vBox.getChildren().addAll(hBox,buttonBuy);
        int[] count = new int[1];
        buttonPlus.setOnMouseClicked(mouseEvent -> {
            if (count[0] == 0){
                vBox.getChildren().clear();
                hBox.getChildren().clear();
                hBox.getChildren().addAll(textField, buttonPlus);
                vBox.getChildren().addAll(textDiscount,hBox,buttonBuy);
                count[0] += 1;
            } else if (count[0] == 1){
                vBox.getChildren().clear();
                hBox.getChildren().clear();
                hBox.getChildren().addAll(textField1, buttonPlus);
                vBox.getChildren().addAll(textDiscount, textField, hBox, buttonBuy);
                count[0] += 1;
            } else if (count[0] == 2){
                vBox.getChildren().clear();
                vBox.getChildren().addAll(textDiscount, textField, textField1, textField2, buttonBuy);
                count[0] += 1;
            }
        });
        buttonBuy.setOnMouseClicked(mouseEvent -> {
            ArrayList<String> arrayListDiscountCode = new ArrayList<>();
            if (!textField.getText().equals("")){
                arrayListDiscountCode.add(textField.getText());
            }
            if (!textField1.getText().equals("")){
                arrayListDiscountCode.add(textField1.getText());
            }
            if (!textField2.getText().equals("")){
                arrayListDiscountCode.add(textField2.getText());
            }
            try {
                if (arrayListDiscountCode.size() != 0){
                    Text text = new Text("Amount with discount : " + cartController.getAmount(buyer, arrayListDiscountCode));
                    text.setId("textItem");
                    vBox.getChildren().remove(buttonBuy);
                    vBox.getChildren().addAll(text,buttonBuy);
                }
                buttonBuy.setText("Finalized");
                buttonBuy.setOnMouseClicked(mouseEvent1 -> {
                    try {
                        cartController.finalizeCart(buyer,arrayListDiscountCode,cartController.getAmount(buyer, arrayListDiscountCode));
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("The purchase was made successfully!");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            new BuyerPanel(buyer).start(new Stage());
                            stage.close();
                        }
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                });
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }

        });

        return vBox;
    }
    private List<Item> createPage(int pageIndex) {
        int fromIndex = pageIndex * 7;
        int toIndex = Math.min(fromIndex + 7, itemArrayList.size());
        if (fromIndex < itemArrayList.size()){
            return (itemArrayList.subList(fromIndex, toIndex));
        }
        return new ArrayList<>();
    }
    private void setItemArrayList(HashMap<Item,Integer> items){
        Set<Item> entrySet = items.keySet();
        this.itemArrayList = new ArrayList<>(entrySet);
    }
}
