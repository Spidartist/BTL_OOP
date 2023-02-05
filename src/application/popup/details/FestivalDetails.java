package application.popup.details;

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
import objects.festival.Festival;
import objects.figure.Figure;
import objects.figure.King;

public class FestivalDetails {
    public FestivalDetails(Festival curSelect, ObservableList<Figure> listFigure, ObservableList<Dynasty> listDynasty,
            ObservableList<King> listKing) {
        BorderPane borderPane = new BorderPane();
        Stage stage = new Stage();
        stage.setTitle("Festival Detail");

        Image imagebackground = new Image(
                "https://media.discordapp.net/attachments/755083836169257062/1071699179488944128/image.png?width=1190&height=670");
        BackgroundImage backgroundImage = new BackgroundImage(imagebackground, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        borderPane.setBackground(new Background(backgroundImage));

        Image image = new Image(
                "https://lib.agu.edu.vn/images/2020/2.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(233);
        imageView.setFitHeight(145);
        imageView.getStyleClass().add("border-style");

        Label tenLeHoi = new Label("Tên Lễ hội: " + curSelect.getTenLeHoi());
        Label thoigian = new Label("Thời gian: " + curSelect.getThoigian());
        Label diaDiem = new Label("Địa điểm: " + curSelect.getDiaDiem());
        Label noiDung = new Label("Nội dung: " + curSelect.getNoiDung());
        noiDung.setWrapText(true);
        String strFigure = "";
        Figure newFigure = new Figure();

        strFigure = curSelect.getFigure().getTen();
        System.out.println(strFigure);
        for (int j = 0; j < listFigure.size(); j++) {

            if (curSelect.getFigure().getTen().toLowerCase()
                    .indexOf(listFigure.get(j).getTen().toLowerCase()) != -1) {
                newFigure = listFigure.get(j);
            }
        }

        curSelect.setFigure(newFigure);
        Label figure = new Label(
                "Nhân vật liên quan: " + (curSelect.getFigure().getTen() == null ? "Không có" : strFigure));
        figure.setWrapText(true);

        tenLeHoi.getStyleClass().add("text-color");
        thoigian.getStyleClass().add("text-color");
        diaDiem.getStyleClass().add("text-color");
        noiDung.getStyleClass().add("text-color");
        figure.getStyleClass().add("text-color");

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
        labelbox.getChildren().addAll(tenLeHoi, thoigian, diaDiem, figure);

        hbox.getChildren().addAll(picturebox, labelbox);

        VBox contentText = new VBox();
        contentText.getChildren().addAll(noiDung);
        contentText.setPadding(new Insets(10, 50, 10, 50));

        vbox.getChildren().addAll(hbox, contentText);
        borderPane.setCenter(vbox);
        picturebox.setAlignment(Pos.CENTER_LEFT);
        labelbox.setAlignment(Pos.BASELINE_LEFT);
        hbox.setAlignment(Pos.CENTER);
        contentText.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);

        if (curSelect.getFigure().getTen() != null) {
            Button moreInfoButton = new Button("More Info " + curSelect.getFigure().getTen());
            moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                new FigureDetails(curSelect.getFigure(), listDynasty, listKing);
            });
            borderPane.setBottom(moreInfoButton);
        }

        Scene scene = new Scene(borderPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
