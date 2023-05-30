package UI;

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
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.item.Item;
import model.user.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ItemPanel extends Application {
    public ItemPanel(ArrayList<Item> items) {
        this.items = items;
        this.itemController = new ItemController();
    }

    private final Admin admin = Admin.getAdmin();
    private final ArrayList<Item> items;
    private final ItemController itemController;
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.rgb(216, 227, 231),CornerRadii.EMPTY, Insets.EMPTY)));
        root.setLeft(vBoxList(root));
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
        stage.setOnCloseRequest(windowEvent -> {
            new ByeWindow().start(new Stage());
        });
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
            new MainPanel().start(new Stage());
            stage.close();
        });

        Button buttonSort = new Button("Sort");
        HBox hBoxButton = new HBox(buttonSort);
        hBoxButton.setPadding(new Insets(15));
        buttonSort.setCursor(Cursor.HAND);
        buttonSort.setOnMouseClicked(mouseEvent -> {
            this.items.sort(Item::compareTo);
            new ItemPanel(this.items).start(new Stage());
            stage.close();
        });
        hBox.getChildren().addAll(viewOut, hBoxButton);
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

    private HBox hBoxItem(Item item, BorderPane root){
        HBox hBox = new HBox();
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

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPadding(new Insets(10));
        scrollPane.setId("scroller");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setBackground(new Background(new BackgroundFill(Color.rgb(216, 227, 231),CornerRadii.EMPTY,Insets.EMPTY)));
        VBox vBox = new VBox(text);
        vBox.setPadding(new Insets(20));
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(216, 227, 231),CornerRadii.EMPTY,Insets.EMPTY)));

        scrollPane.setContent(vBox);

        hBox.getChildren().addAll(scrollPane, buttonComment);
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
            itemController.postAComment(idItem,textArea.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("The comment was sent successfully");
            alert.showAndWait();
            root.setRight(null);
        });
        vBox.getChildren().addAll(textArea, hBox);
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
}
