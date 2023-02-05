package application;

import java.io.IOException;

import application.popup.PopUpWinDow;
import application.readdata.ReadData;
import application.search.Search;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import objects.dynasty.Dynasty;
import objects.festival.Festival;
import objects.figure.Figure;
import objects.figure.King;

public class MainController {

    @FXML
    private MenuItem menuItem1;

    @FXML
    private MenuItem menuItem2;

    @FXML
    private MenuItem menuItem3;

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuButton searchField;

    @FXML
    private TextField textField;

    // Xử lý sự kiện chọn trường để search
    @FXML
    void clickMenuItem(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        String lableSelecItem = menuItem.getText();
        PopUpWinDow newPopUp = new PopUpWinDow();
        searchField.setText(menuItem.getText());

        ObservableList<Figure> listObservablesFigure = new ReadData<Figure>()
                .FromJsonToArray("src/data/figureUpdate.json", Figure.class);
        ObservableList<King> listObservablesKing = new ReadData<King>()
                .FromJsonToArray("src/data/king.json", King.class);
        ObservableList<Dynasty> listObservablesDynasty = new ReadData<Dynasty>()
                .FromJsonToArray("src/data/dynastys.json", Dynasty.class);
        ObservableList<Festival> listObservablesFestival = new ReadData<Festival>()
                .FromJsonToArray("src/data/festival.json", Festival.class);

        switch (lableSelecItem) {
            case "Vua":
                TableView<King> tableKingView = new TableView<King>();
                tableKingView.getColumns().clear();
                // xu ly xu kien click row pop up window
                tableKingView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (e.getClickCount() > 1) {
                        King demo = tableKingView.getSelectionModel().getSelectedItem();
                        System.out.println(demo.getTen());
                        newPopUp.getPopUpWindow(demo);
                    }
                });

                String[] kingStr = { "mieuHieu", "thuyHieu", "nienHieu", "tenHuy", "theThu", "namTriVi" };
                for (int i = 0; i < kingStr.length; i++) {
                    TableColumn<King, String> ColKing = new TableColumn<King, String>(kingStr[i]);
                    ColKing.prefWidthProperty().bind(tableKingView.widthProperty().multiply(0.143));
                    ColKing.setCellValueFactory(new PropertyValueFactory<King, String>(kingStr[i]));
                    tableKingView.getColumns().add(ColKing);
                }

                // System.out.println(reader.getKingList());
                // for (King elm : readerDataKing) {
                // System.out.println(elm.getTen());
                // King newKing = new King(elm.getTen());
                // }
                Search<King> searchKing = new Search<King>();
                tableKingView.setItems(searchKing.demoSearch(listObservablesKing, textField, King.class));
                borderPane.setCenter(tableKingView);
                break;
            case "Nhân Vật Lịch Sử":
                TableView<Figure> tableFigureView = new TableView<>();
                tableFigureView.getColumns().clear();
                tableFigureView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (e.getClickCount() > 1) {
                        Figure demo = tableFigureView.getSelectionModel().getSelectedItem();
                        System.out.println(demo.getTen());
                        newPopUp.getPopUpWindow(demo);
                    }
                });
                String[] figureStr = { "ten", "queQuan", "namSinh", "namMat" };
                for (int i = 0; i < figureStr.length; i++) {
                    if (figureStr[i] == "trieuDai") {
                        TableColumn<Figure, String> ColFigure = new TableColumn<Figure, String>(figureStr[i]);
                        ColFigure.prefWidthProperty().bind(tableFigureView.widthProperty().multiply(0.25));
                        ColFigure.setCellValueFactory(new PropertyValueFactory<>((String) figureStr[i]));
                        tableFigureView.getColumns().add(ColFigure);
                    } else {
                        TableColumn<Figure, String> ColFigure = new TableColumn<Figure, String>(figureStr[i]);
                        ColFigure.prefWidthProperty().bind(tableFigureView.widthProperty().multiply(0.25));
                        ColFigure.setCellValueFactory(new PropertyValueFactory<>(figureStr[i]));
                        tableFigureView.getColumns().add(ColFigure);
                    }
                }
                // readerDataFigure.forEach(elm -> tableFigureView.getItems().add(elm));
                Search<Figure> searchFigure = new Search<Figure>();
                tableFigureView.setItems(searchFigure.demoSearch(listObservablesFigure, textField, Figure.class));
                System.out.println("fig");
                borderPane.setCenter(tableFigureView);
                break;
            case "Sự kiện lịch sử":

                break;
            case "Di tích lịch sử":

                break;
            case "Triều Đại Lịch Sử":
                TableView<Dynasty> tableDynastyView = new TableView<>();
                tableDynastyView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (e.getClickCount() > 1) {
                        Dynasty demo = tableDynastyView.getSelectionModel().getSelectedItem();
                        System.out.println(demo.getFounder());
                        newPopUp.getPopUpWindow(demo);
                    }
                });
                tableDynastyView.getColumns().clear();
                String[] dynastyStr = { "name", "startYear", "endYear", "capital", "founder" };
                for (int i = 0; i < dynastyStr.length; i++) {
                    TableColumn<Dynasty, String> colDynasty = new TableColumn<Dynasty, String>(dynastyStr[i]);
                    colDynasty.prefWidthProperty().bind(tableDynastyView.widthProperty().multiply(0.2));
                    colDynasty.setCellValueFactory(new PropertyValueFactory<>(dynastyStr[i]));
                    tableDynastyView.getColumns().add(colDynasty);
                }
                // readerDataDynasty.forEach(elm -> tableDynastyView.getItems().add(elm));
                Search<Dynasty> searchDynasty = new Search<Dynasty>();
                tableDynastyView.setItems(searchDynasty.demoSearch(listObservablesDynasty, textField, Dynasty.class));
                borderPane.setCenter(tableDynastyView);
                break;
            default:
                break;
        }
    }

    @FXML
    void search(ActionEvent event) {
        // ReadJson reader = new ReadJson();
        System.out.println(textField.getText());
    }

    @FXML
    void pressEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            search(null);
        } else {

        }
    }
}
