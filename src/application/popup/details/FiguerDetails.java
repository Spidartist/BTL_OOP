package application.popup.details;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import objects.figure.Figure;

public class FiguerDetails {

    public FiguerDetails(Figure curSelect) {
        BorderPane borderPane = new BorderPane();
        Stage stage = new Stage();
        stage.setTitle("Figure Detail");
        Image image = new Image(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSufu-xJiSOxynzT2dbbwlAGaP5Gm-TnGM2IA&usqp=CAU");
        ImageView imageView = new ImageView(image);
        borderPane.setLeft(imageView);

        BorderPane.setAlignment(imageView, Pos.CENTER);

        Label ten = new Label("Họ và Tên:" + curSelect.getTen());
        Label queQuan = new Label("Quê Quán:" + curSelect.getQueQuan());
        Label namSinh = new Label("Năm sinh:" + curSelect.getNamSinh());
        Label namMat = new Label("Năm mất:" + curSelect.getNamMat());
        String strTrieuDai = "";
        for (int i = 0; i < curSelect.getTrieuDai().size(); i++) {
            strTrieuDai += curSelect.getTrieuDai().get(i).getName() + ",";
        }
        Label trieuDai = new Label("Triều đại: " + strTrieuDai);
        Label ghiChu = new Label("Ghi chú:" + curSelect.getGhiChu());
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

        Scene scene = new Scene(borderPane, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

}
