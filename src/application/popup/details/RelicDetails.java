package application.popup.details;

import java.util.ArrayList;
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
        stage.setTitle("Figure Detail");
        Image image = new Image(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSufu-xJiSOxynzT2dbbwlAGaP5Gm-TnGM2IA&usqp=CAU");
        ImageView imageView = new ImageView(image);
        borderPane.setLeft(imageView);

        BorderPane.setAlignment(imageView, Pos.CENTER);

        Label name = new Label("Tên di tích " + curSelect.getName());
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
        Label trieuDai = new Label("Triều đại: " + strTrieuDai);
        Label vua = new Label("Vua: " + strKings);
        Label nhanVat = new Label("Nhân vật Lịch sử: " + strFigures);
        VBox contentText = new VBox(10);
        contentText.setPadding(new Insets(20, 20, 20, 20));
        contentText.setAlignment(Pos.CENTER);
        contentText.getChildren().add(name);
        contentText.getChildren().add(location);
        contentText.getChildren().add(type);
        contentText.getChildren().add(rank);
        contentText.getChildren().add(desc);
        contentText.getChildren().add(trieuDai);
        contentText.getChildren().add(vua);
        contentText.getChildren().add(nhanVat);
        borderPane.setCenter(contentText);

        // BorderPane.setAlignment(contentText, Pos.CENTER);

        // moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
        // for (int i = 0; i < curSelect.getTrieuDai().size(); i++) {
        // new DynastyDetails(curSelect.getTrieuDai().get(i));
        // System.out.println("1");
        // }
        // });
        // borderPane.setBottom(moreInfoButton);
        // BorderPane.setAlignment(moreInfoButton, Pos.CENTER);
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
        BorderPane.setAlignment(moreInforContainer, Pos.CENTER);
        Scene scene = new Scene(borderPane, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
