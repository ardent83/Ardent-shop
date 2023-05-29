package UI;

import controller.PurchaseInvoicesController;
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
import model.user.PurchaseInvoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PurchaseInvoices extends Application {
    public PurchaseInvoices(Buyer buyer) {
        this.items = buyer.getPurchaseHistoryArrayList();
        this.buyer = buyer;
        this.itemArrayList = new ArrayList<>();
        this.itemIntegerHashMap = new HashMap<>();
        this.controller = new PurchaseInvoicesController(buyer);
    }

    private final ArrayList<PurchaseInvoice> items;
    private ArrayList<Item> itemArrayList;
    private HashMap<Item, Integer> itemIntegerHashMap;
    private final PurchaseInvoicesController controller;
    private final Buyer buyer;
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setLeft(vBoxListPurchase(root));
        root.setTop(hBoxTop(stage));


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

    private HBox hBoxTop(Stage stage){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(2));
        hBox.setSpacing(15);
        hBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 171, 179),CornerRadii.EMPTY,Insets.EMPTY)));
        Image imageLogOut = new Image("iconLogOut.png");
        ImageView viewOut = new ImageView(imageLogOut);
        viewOut.setCursor(Cursor.HAND);
        viewOut.setOnMouseClicked(mouseEvent -> {
            new BuyerPanel(buyer).start(new Stage());
            stage.close();
        });
        hBox.getChildren().addAll(viewOut);
        return hBox;
    }
//______________________________________________________________________________________________________________________
    private VBox vBoxListPurchase(BorderPane root){
        ListView<PurchaseInvoice> listView = new ListView<>();
        listView.setMinWidth(300);
        listView.setMinHeight(713);
        listView.setMaxHeight(713);

        listView.setBackground(new Background(new BackgroundFill(Color.rgb(0, 171, 179),CornerRadii.EMPTY,Insets.EMPTY)));

        Pagination pagination = new Pagination();
        pagination.setCurrentPageIndex(0);
        pagination.setPageCount((int) Math.ceil(items.size()/11.0));
        pagination.setMaxPageIndicatorCount(4);
        listView.setItems(FXCollections.observableArrayList(createPage(pagination.getCurrentPageIndex())));

        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            listView.setItems(FXCollections.observableArrayList(createPage(pagination.getCurrentPageIndex())));
        });

        listView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<PurchaseInvoice> call(ListView<PurchaseInvoice> purchaseInvoiceListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(PurchaseInvoice purchaseInvoice, boolean b) {
                        super.updateItem(purchaseInvoice, b);
                        if (purchaseInvoice != null){
                            setText("Amount : " + purchaseInvoice.getAmountPaid() +
                                    "\nDate : " + purchaseInvoice.getDate());
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });

        listView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2 && listView.getSelectionModel().getSelectedItem() != null){
                this.itemIntegerHashMap.clear();
                this.itemIntegerHashMap = new HashMap<>(listView.getSelectionModel().getSelectedItem().getItemIntegerHashMap());
                setItemArrayList(listView.getSelectionModel().getSelectedItem().getItemIntegerHashMap());
                root.setCenter(borderListItem());
            }
        });

        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 171, 179),CornerRadii.EMPTY,Insets.EMPTY)));
        vBox.getChildren().addAll(listView,pagination);

        return vBox;
    }
    private List<PurchaseInvoice> createPage(int pageIndex) {
        int fromIndex = pageIndex * 11;
        int toIndex = Math.min(fromIndex + 11, items.size());
        if (fromIndex < items.size()){
            return (items.subList(fromIndex, toIndex));
        }
        return new ArrayList<>();
    }
//    ____________________________________________________________________________________________________________________________________
    private BorderPane borderListItem(){
        BorderPane root = new BorderPane();
        ListView<Item> listView = new ListView<>();
        listView.setMinWidth(300);
        listView.setMinHeight(713);
        listView.setMaxHeight(713);

        listView.setBackground(new Background(new BackgroundFill(Color.rgb(0, 171, 179), CornerRadii.EMPTY, Insets.EMPTY)));

        Pagination pagination = new Pagination();
        pagination.setCurrentPageIndex(0);
        pagination.setPageCount((int) Math.ceil(itemArrayList.size()/7.0));
        pagination.setMaxPageIndicatorCount(4);
        listView.setItems(FXCollections.observableArrayList(createPageItem(pagination.getCurrentPageIndex())));

        pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {;
            listView.setItems(FXCollections.observableArrayList(createPageItem(pagination.getCurrentPageIndex())));
        });

        listView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Item> call(ListView<Item> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Item item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText("Name : " + item.getName() + "\n$" + item.getPrice() + "\nScore : " + item.getAverageScore() + "\nNumber : " + itemIntegerHashMap.get(item));
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });

        listView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2 && listView.getSelectionModel().getSelectedItem() != null){
                root.setCenter(hBoxItem(listView.getSelectionModel().getSelectedItem()));
            }
        });

        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 171, 179),CornerRadii.EMPTY,Insets.EMPTY)));
        vBox.getChildren().addAll(listView,pagination);

        root.setLeft(vBox);
        return root;
    }
    private List<Item> createPageItem(int pageIndex) {
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

    private HBox hBoxItem(Item item){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(30));
        hBox.setSpacing(50);
        hBox.setBackground(new Background(new BackgroundFill(Color.rgb(216, 227, 231),CornerRadii.EMPTY,Insets.EMPTY)));
        DropShadow effect = new DropShadow();
        effect.setColor(Color.rgb(0, 66, 90));
        Text text = new Text((item.toString()+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"
                +"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"));
        text.setId("textItem");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPadding(new Insets(10));
        scrollPane.setId("scroller");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setBackground(new Background(new BackgroundFill(Color.rgb(216, 227, 231),CornerRadii.EMPTY,Insets.EMPTY)));
        VBox vBox = new VBox(text);
        vBox.setPadding(new Insets(20));
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(216, 227, 231),CornerRadii.EMPTY,Insets.EMPTY)));

        scrollPane.setContent(vBox);

        hBox.getChildren().addAll(scrollPane, vBoxStar(item.getIdItem()));

        return hBox;
    }

    private VBox vBoxStar(String idItem){
        DropShadow effect = new DropShadow();
        effect.setColor(Color.rgb(0, 66, 90));
        Button buttonScore = new Button("Give Score");
        Text text1 = new Text("★");
        Text text2 = new Text("★");
        Text text3 = new Text("★");
        Text text4 = new Text("★");
        Text text5 = new Text("★");
        text1.setId("score");
        text2.setId("score");
        text3.setId("score");
        text4.setId("score");
        text5.setId("score");
        text1.setCursor(Cursor.HAND);
        text2.setCursor(Cursor.HAND);
        text3.setCursor(Cursor.HAND);
        text4.setCursor(Cursor.HAND);
        text5.setCursor(Cursor.HAND);
        buttonScore.setCursor(Cursor.HAND);
        text1.setFill(Color.rgb(255, 237, 0));
        text2.setFill(Color.rgb(155, 164, 181));
        text3.setFill(Color.rgb(155, 164, 181));
        text4.setFill(Color.rgb(155, 164, 181));
        text5.setFill(Color.rgb(155, 164, 181));
        

//        ok : rgb(255, 237, 0)
//        default : rgb(155, 164, 181)

        text1.setOnMouseClicked(mouseEvent -> {
            text1.setFill(Color.rgb(255, 237, 0));
            text2.setFill(Color.rgb(155, 164, 181));
            text3.setFill(Color.rgb(155, 164, 181));
            text4.setFill(Color.rgb(155, 164, 181));
            text5.setFill(Color.rgb(155, 164, 181));
        });
        text2.setOnMouseClicked(mouseEvent -> {
            text1.setFill(Color.rgb(255, 237, 0));
            text2.setFill(Color.rgb(255, 237, 0));
            text3.setFill(Color.rgb(155, 164, 181));
            text4.setFill(Color.rgb(155, 164, 181));
            text5.setFill(Color.rgb(155, 164, 181));
        });
        text3.setOnMouseClicked(mouseEvent -> {
            text1.setFill(Color.rgb(255, 237, 0));
            text2.setFill(Color.rgb(255, 237, 0));
            text3.setFill(Color.rgb(255, 237, 0));
            text4.setFill(Color.rgb(155, 164, 181));
            text5.setFill(Color.rgb(155, 164, 181));
        });
        text4.setOnMouseClicked(mouseEvent -> {
            text1.setFill(Color.rgb(255, 237, 0));
            text2.setFill(Color.rgb(255, 237, 0));
            text3.setFill(Color.rgb(255, 237, 0));
            text4.setFill(Color.rgb(255, 237, 0));
            text5.setFill(Color.rgb(155, 164, 181));
        });
        text5.setOnMouseClicked(mouseEvent -> {
            text1.setFill(Color.rgb(255, 237, 0));
            text2.setFill(Color.rgb(255, 237, 0));
            text3.setFill(Color.rgb(255, 237, 0));
            text4.setFill(Color.rgb(255, 237, 0));
            text5.setFill(Color.rgb(255, 237, 0));
        });

        buttonScore.setOnMouseClicked(mouseEvent -> {
            if (text5.getFill().equals(Color.rgb(255, 237, 0))){
                controller.giveScore(idItem,5);
            } else if (text4.getFill().equals(Color.rgb(255, 237, 0))){
                controller.giveScore(idItem,4);
            } else if (text3.getFill().equals(Color.rgb(255, 237, 0))){
                controller.giveScore(idItem,3);
            } else if (text2.getFill().equals(Color.rgb(255, 237, 0))){
                controller.giveScore(idItem,2);
            } else if (text1.getFill().equals(Color.rgb(255, 237, 0))){
                controller.giveScore(idItem,1);
            }
        });

        HBox hBoxStar = new HBox(text1, text2, text3, text4, text5);
        hBoxStar.setSpacing(3);
        hBoxStar.setEffect(effect);
        hBoxStar.setPadding(new Insets(5));

        HBox hBoxButton = new HBox(buttonScore);
        hBoxButton.setAlignment(Pos.CENTER);

        VBox vBoxStar = new VBox(hBoxStar, hBoxButton);
        vBoxStar.setSpacing(1);
        return vBoxStar;
    }
}
