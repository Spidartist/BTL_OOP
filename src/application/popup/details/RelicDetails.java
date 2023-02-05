package application.popup.details;

import java.util.LinkedList;

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
import objects.relic.Relic;

public class RelicDetails {
    public RelicDetails(Relic curSelect, ObservableList<Figure> listFigures, ObservableList<King> listKings,
            ObservableList<Dynasty> listDynasty) {
        BorderPane borderPane = new BorderPane();
        Stage stage = new Stage();
        stage.setTitle("Relic Detail");

        Image imagebackground = new Image(
                "https://media.discordapp.net/attachments/755083836169257062/1071699179488944128/image.png?width=1190&height=670");
        BackgroundImage backgroundImage = new BackgroundImage(imagebackground, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        borderPane.setBackground(new Background(backgroundImage));

        Image image = new Image(
                "https://mcdn.coolmate.me/uploads/January2022/di-tic-lich-su-viet-nam.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(233);
        imageView.setFitHeight(145);
        Label name = new Label("Tên di tích: " + curSelect.getName());
        Label location = new Label("Địa điểm: " + curSelect.getLocation());
        Label type = new Label("Kiểu di tích: " + curSelect.getType());
        Label rank = new Label("Di tích cấp: " + curSelect.getRank());
        Label desc = new Label("Thông tin thêm: " + curSelect.getDesc());
        desc.setWrapText(true);
        String strTrieuDai = "";
        String strKings = "";
        String strFigures = "";
        LinkedList<Dynasty> newDynasty = new LinkedList<Dynasty>();
        LinkedList<King> newKings = new LinkedList<King>();
        LinkedList<Figure> newFigures = new LinkedList<Figure>();
        for (int i = 0; i < curSelect.getDynastys().size(); i++) {

            strTrieuDai += curSelect.getDynastys().get(i).getName() + ",";
            for (int j = 0; j < listDynasty.size(); j++) {

                if (curSelect.getDynastys().get(i).getName().toLowerCase()
                        .indexOf(listDynasty.get(j).getName().toLowerCase()) != -1) {
                    newDynasty.add(listDynasty.get(j));
                    System.out.println(listDynasty.get(j).getFounder().getTen());
                }
            }
        }
        for (int i = 0; i < curSelect.getKings().size(); i++) {

            strKings += curSelect.getKings().get(i).getTen() + ",";
            for (int j = 0; j < listKings.size(); j++) {

                if (curSelect.getKings().get(i).getTen().toLowerCase()
                        .indexOf(listKings.get(j).getTen().toLowerCase()) != -1) {
                    newKings.add(listKings.get(j));

                }
            }
        }
        for (int i = 0; i < curSelect.getFigures().size(); i++) {

            strFigures += curSelect.getFigures().get(i).getTen() + ",";
            for (int j = 0; j < listFigures.size(); j++) {

                if (curSelect.getFigures().get(i).getTen().toLowerCase()
                        .indexOf(listFigures.get(j).getTen().toLowerCase()) != -1) {
                    newFigures.add(listFigures.get(j));

                }
            }
        }
        curSelect.setDynastys(newDynasty);
        curSelect.setKings(newKings);
        curSelect.setFigures(newFigures);
        Label trieuDai = new Label(
                "Triều đại: " + (strTrieuDai == "" ? "Chưa rõ" : strTrieuDai));
        Label vua = new Label("Vua: " + (strKings == "" ? "Không có" : strKings));
        Label nhanVat = new Label(
                "Nhân vật Lịch sử: " + (strFigures == "" ? "Không có" : strFigures));

        name.getStyleClass().add("text-color");
        location.getStyleClass().add("text-color");
        type.getStyleClass().add("text-color");
        rank.getStyleClass().add("text-color");
        desc.getStyleClass().add("text-color");
        trieuDai.getStyleClass().add("text-color");
        vua.getStyleClass().add("text-color");
        nhanVat.getStyleClass().add("text-color");

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
        labelbox.getChildren().addAll(name, location, type, rank, trieuDai, vua, nhanVat);

        hbox.getChildren().addAll(picturebox, labelbox);

        VBox contentText = new VBox();
        contentText.getChildren().addAll(desc);
        contentText.setPadding(new Insets(10, 50, 10, 50));

        vbox.getChildren().addAll(hbox, contentText);
        borderPane.setCenter(vbox);
        picturebox.setAlignment(Pos.CENTER_LEFT);
        labelbox.setAlignment(Pos.BASELINE_LEFT);
        hbox.setAlignment(Pos.CENTER);
        contentText.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        HBox moreInforContainer = new HBox();

        for (int i = 0; i < curSelect.getDynastys().size(); i++) {
            final int index = i;
            Button moreInfoButton = new Button("More Info " + curSelect.getDynastys().get(index).getName());
            moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                new DynastyDetails(curSelect.getDynastys().get(index), listKings);
            });
            moreInforContainer.getChildren().add(moreInfoButton);
        }

        for (int i = 0; i < curSelect.getKings().size(); i++) {
            final int index = i;
            Button moreInfoButton = new Button("More Info " + curSelect.getKings().get(index).getTen());
            moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                new KingDetails(curSelect.getKings().get(index));
            });
            moreInforContainer.getChildren().add(moreInfoButton);
        }

        for (int i = 0; i < curSelect.getFigures().size(); i++) {
            final int index = i;
            Button moreInfoButton = new Button("More Info " + curSelect.getFigures().get(index).getTen());
            moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                new FigureDetails(curSelect.getFigures().get(index), listDynasty, listKings);
            });
            moreInforContainer.getChildren().add(moreInfoButton);
        }
        borderPane.setBottom(moreInforContainer);
        Scene scene = new Scene(borderPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
