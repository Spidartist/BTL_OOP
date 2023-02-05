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
import javafx.scene.layout.BorderPane;
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
        stage.setTitle("Figure Detail");
        Image image = new Image(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSufu-xJiSOxynzT2dbbwlAGaP5Gm-TnGM2IA&usqp=CAU");
        ImageView imageView = new ImageView(image);
        borderPane.setLeft(imageView);

        BorderPane.setAlignment(imageView, Pos.CENTER);
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
        VBox contentText = new VBox(10);
        contentText.setPadding(new Insets(20, 20, 20, 20));
        contentText.setAlignment(Pos.CENTER);
        contentText.getChildren().add(tenLeHoi);
        contentText.getChildren().add(thoigian);
        contentText.getChildren().add(diaDiem);
        contentText.getChildren().add(noiDung);
        contentText.getChildren().add(figure);

        borderPane.setCenter(contentText);
        if (curSelect.getFigure().getTen() != null) {
            Button moreInfoButton = new Button("More Info " + curSelect.getFigure().getTen());
            moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                new FigureDetails(curSelect.getFigure(), listDynasty, listKing);
            });
            borderPane.setBottom(moreInfoButton);
        }

        Scene scene = new Scene(borderPane, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
