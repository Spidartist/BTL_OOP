package application.popup.details;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import objects.figure.King;

public class KingDetails {
    public KingDetails(King curSelect) {
        BorderPane borderPane = new BorderPane();
        Stage stage = new Stage();
        stage.setTitle("Figure Detail");
        Image image = new Image(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSufu-xJiSOxynzT2dbbwlAGaP5Gm-TnGM2IA&usqp=CAU");
        ImageView imageView = new ImageView(image);
        borderPane.setLeft(imageView);

        BorderPane.setAlignment(imageView, Pos.CENTER);
        Label ten = new Label("Tên Vua: " + curSelect.getTen());
        Label namTriVi = new Label("Năm trị vì: " + curSelect.getNamTriVi());
        Label theThu = new Label("Tên thứ: " + curSelect.getTheThu());
        Label tenHuy = new Label("Tên húy: " + curSelect.getTenHuy());
        Label nienHieu = new Label("Niên hiệu: " + curSelect.getNienHieu());
        Label thuyHieu = new Label("Thụy hiệu: " + curSelect.getThuyHieu());
        Label mieuHieu = new Label("Miếu hiệu: " + curSelect.getMieuHieu());
        Label paperURL = new Label("Link bài báo tìm hiểu thêm: " + curSelect.getPaperURL());
        VBox contentText = new VBox(10);
        contentText.setPadding(new Insets(20, 20, 20, 20));
        contentText.setAlignment(Pos.CENTER);
        contentText.getChildren().add(ten);
        contentText.getChildren().add(namTriVi);
        contentText.getChildren().add(theThu);
        contentText.getChildren().add(tenHuy);
        contentText.getChildren().add(nienHieu);
        contentText.getChildren().add(thuyHieu);
        contentText.getChildren().add(mieuHieu);
        contentText.getChildren().add(paperURL);
        borderPane.setCenter(contentText);
        Scene scene = new Scene(borderPane, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
