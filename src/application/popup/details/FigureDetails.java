package application.popup.details;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import objects.dynasty.Dynasty;
import objects.figure.Figure;
import objects.figure.King;

public class FigureDetails {

    public FigureDetails(Figure curSelect, ObservableList<Dynasty> listDynasty, ObservableList<King> listKing) {
        BorderPane borderPane = new BorderPane();
        Stage stage = new Stage();
        stage.setTitle("Figure Detail");
        Image imagebackground = new Image(
                "https://media.discordapp.net/attachments/755083836169257062/1071699179488944128/image.png?width=1190&height=670");
        BackgroundImage backgroundImage = new BackgroundImage(imagebackground, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        borderPane.setBackground(new Background(backgroundImage));

        Image image = new Image(
                "https://media.ohay.tv/v1/upload/content/2017-05/01/1-03e8c239c32be5d42c2bd5fa63243545-ohaytv.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(233);
        imageView.setFitHeight(145);
        imageView.getStyleClass().add("border-style");

        Label ten = new Label("Họ và Tên: " + curSelect.getTen());
        Label queQuan = new Label("Quê Quán: " + curSelect.getQueQuan());
        Label namSinh = new Label("Năm sinh: " + curSelect.getNamSinh());
        Label namMat = new Label("Năm mất: " + curSelect.getNamMat());
        String strTrieuDai = "";
        ArrayList<Dynasty> newDynasty = new ArrayList<Dynasty>();
        for (int i = 0; i < curSelect.getTrieuDai().size(); i++) {

            strTrieuDai += curSelect.getTrieuDai().get(i).getName() + ",";
            for (int j = 0; j < listDynasty.size(); j++) {

                if (curSelect.getTrieuDai().get(i).getName().toLowerCase()
                        .indexOf(listDynasty.get(j).getName().toLowerCase()) != -1) {
                    newDynasty.add(listDynasty.get(j));
                    System.out.println(listDynasty.get(j).getFounder().getTen());
                }
            }

        }
        curSelect.setTrieuDai(newDynasty);
        // System.out.println(newDynasty.get(0).getFounder().getTen());
        Label trieuDai = new Label("Triều đại: " + strTrieuDai);
        Label ghiChu = new Label("Ghi chú: " + curSelect.getGhiChu());
        ghiChu.setWrapText(true);

        ten.getStyleClass().add("text-color");
        queQuan.getStyleClass().add("text-color");
        namSinh.getStyleClass().add("text-color");
        namMat.getStyleClass().add("text-color");
        trieuDai.getStyleClass().add("text-color");
        ghiChu.getStyleClass().add("text-color");

        VBox vbox = new VBox();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(20, 20, 20, 20));
        hbox.setSpacing(25);
        HBox picturebox = new HBox();
        // picturebox.setSpacing(20);
        picturebox.getChildren().add(imageView);
        picturebox.setStyle(
                "-fx-border-color: white; -fx-border-width: 3px; -fx-effect : dropshadow(one-pass-box,white, 5, 5, 0, 0);");

        VBox labelbox = new VBox();
        // labelbox.setSpacing(20);
        labelbox.getChildren().addAll(ten, queQuan, namSinh, namMat, trieuDai, ghiChu);

        hbox.getChildren().addAll(picturebox, labelbox);

        VBox contentText = new VBox();
        contentText.getChildren().addAll(ghiChu);
        contentText.setPadding(new Insets(10, 50, 10, 50));

        vbox.getChildren().addAll(hbox, contentText);
        borderPane.setCenter(vbox);
        picturebox.setAlignment(Pos.CENTER_LEFT);
        labelbox.setAlignment(Pos.BASELINE_LEFT);
        hbox.setAlignment(Pos.CENTER);
        contentText.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);

        HBox moreInforContainer = new HBox();

        for (int i = 0; i < curSelect.getTrieuDai().size(); i++) {
            final int index = i;
            Button moreInfoButton = new Button("More Info " + curSelect.getTrieuDai().get(index).getName());
            moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                new DynastyDetails(curSelect.getTrieuDai().get(index), listKing);
            });
            moreInforContainer.getChildren().add(moreInfoButton);
        }

        borderPane.setBottom(moreInforContainer);
        BorderPane.setAlignment(moreInforContainer, Pos.CENTER);
        Scene scene = new Scene(borderPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}