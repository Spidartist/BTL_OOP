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
        Image image = new Image(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSufu-xJiSOxynzT2dbbwlAGaP5Gm-TnGM2IA&usqp=CAU");
        ImageView imageView = new ImageView(image);
        borderPane.setLeft(imageView);

        BorderPane.setAlignment(imageView, Pos.CENTER);

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
        System.out.println(newDynasty.get(0).getFounder().getTen());
        Label trieuDai = new Label("Triều đại: " + strTrieuDai);
        Label ghiChu = new Label("Ghi chú: " + curSelect.getGhiChu());
        ghiChu.setWrapText(true);
        VBox contentText = new VBox(10);
        contentText.setPadding(new Insets(20, 20, 20, 20));
        contentText.setAlignment(Pos.CENTER);
        contentText.getChildren().add(ten);
        contentText.getChildren().add(queQuan);
        contentText.getChildren().add(namSinh);
        contentText.getChildren().add(namMat);
        contentText.getChildren().add(trieuDai);
        contentText.getChildren().add(ghiChu);
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
        stage.setScene(scene);
        stage.show();
    }

}
