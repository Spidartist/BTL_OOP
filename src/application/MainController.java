package application;

import java.io.IOException;

import application.popup.details.DynastyDetails;
import application.popup.details.FestivalDetails;
import application.popup.details.FigureDetails;
import application.popup.details.KingDetails;
import application.popup.details.RelicDetails;
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
import objects.relic.Relic;

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

        searchField.setText(menuItem.getText());

        ObservableList<Figure> listObservablesFigure = new ReadData<Figure>()
                .FromJsonToArray("src/data/figureUpdate.json", Figure.class);
        ObservableList<King> listObservablesKing = new ReadData<King>()
                .FromJsonToArray("src/data/king.json", King.class);
        ObservableList<Dynasty> listObservablesDynasty = new ReadData<Dynasty>()
                .FromJsonToArray("src/data/dynasty.json", Dynasty.class);
        ObservableList<Festival> listObservablesFestival = new ReadData<Festival>()
                .FromJsonToArray("src/data/festival.json", Festival.class);
        ObservableList<Relic> listObservablesRelic = new ReadData<Relic>()
                .FromJsonToArray("src/data/relic.json", Relic.class);

        switch (lableSelecItem) {
            case "Vua":
                TableView<King> tableKingView = new TableView<King>();
                tableKingView.getColumns().clear();
                // xu ly xu kien click row pop up window
                tableKingView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (e.getClickCount() > 1) {
                        King demo = tableKingView.getSelectionModel().getSelectedItem();
                        System.out.println(demo.getTen());
                        new KingDetails(demo);
                    }
                });
                String[] nameColKing = { "Tên Vua", "Năm trị vì", "Miếu hiệu", "Thụy hiệu", "Niên hiệu" };
                String[] kingStr = { "ten", "namTriVi", "mieuHieu", "thuyHieu", "nienHieu" };
                for (int i = 0; i < kingStr.length; i++) {
                    TableColumn<King, String> ColKing = new TableColumn<King, String>(nameColKing[i]);
                    ColKing.prefWidthProperty().bind(tableKingView.widthProperty().multiply(0.2));
                    ColKing.setCellValueFactory(new PropertyValueFactory<King, String>(kingStr[i]));
                    tableKingView.getColumns().add(ColKing);
                }

                // System.out.println(reader.getKingList());
                // for (King elm : readerDataKing) {
                // System.out.println(elm.getTen());
                // King newKing = new King(elm.getTen());
                // }
                Search<King> searchKing = new Search<King>();
                tableKingView.setItems(searchKing.searchList(listObservablesKing, textField, King.class));
                borderPane.setCenter(tableKingView);
                break;
            case "Nhân Vật Lịch Sử":
                TableView<Figure> tableFigureView = new TableView<>();
                tableFigureView.getColumns().clear();
                tableFigureView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (e.getClickCount() > 1) {
                        Figure demo = tableFigureView.getSelectionModel().getSelectedItem();

                        new FigureDetails(demo, listObservablesDynasty, listObservablesKing);
                    }
                });
                String[] nameColFigure = { "Tên Nhân Vật", "Quê quán", "Năm sinh", "Năm mất" };
                String[] figureStr = { "ten", "queQuan", "namSinh", "namMat" };
                for (int i = 0; i < figureStr.length; i++) {
                    TableColumn<Figure, String> ColFigure = new TableColumn<Figure, String>(nameColFigure[i]);
                    ColFigure.prefWidthProperty().bind(tableFigureView.widthProperty().multiply(0.25));
                    ColFigure.setCellValueFactory(new PropertyValueFactory<>(figureStr[i]));
                    tableFigureView.getColumns().add(ColFigure);
                }
                // readerDataFigure.forEach(elm -> tableFigureView.getItems().add(elm));
                Search<Figure> searchFigure = new Search<Figure>();
                tableFigureView.setItems(searchFigure.searchList(listObservablesFigure, textField, Figure.class));
                borderPane.setCenter(tableFigureView);
                break;
            case "Sự kiện lịch sử":

                break;
            case "Di tích lịch sử":
                TableView<Relic> tableRelicView = new TableView<>();
                tableRelicView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (e.getClickCount() > 1) {
                        Relic demo = tableRelicView.getSelectionModel().getSelectedItem();

                        new RelicDetails(demo, listObservablesFigure, listObservablesKing, listObservablesDynasty);
                    }
                });
                tableRelicView.getColumns().clear();
                String[] nameColRelic = { "Tên Di tích", "Địa điểm", "Thể loại", "Xếp hạng" };
                String[] RelicStr = { "name", "location", "type", "rank" };
                for (int i = 0; i < RelicStr.length; i++) {
                    TableColumn<Relic, String> colRelic = new TableColumn<Relic, String>(nameColRelic[i]);
                    colRelic.prefWidthProperty().bind(tableRelicView.widthProperty().multiply(0.25));
                    colRelic.setCellValueFactory(new PropertyValueFactory<>(RelicStr[i]));
                    tableRelicView.getColumns().add(colRelic);
                }
                // readerDataRelic.forEach(elm -> tableRelicView.getItems().add(elm));
                Search<Relic> searchRelic = new Search<Relic>();
                tableRelicView.setItems(searchRelic.searchList(listObservablesRelic, textField, Relic.class));
                borderPane.setCenter(tableRelicView);
                break;
            case "Lễ Hội Văn Hóa":
                TableView<Festival> tableFestivalView = new TableView<>();
                tableFestivalView.getColumns().clear();
                tableFestivalView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (e.getClickCount() > 1) {
                        Festival demo = tableFestivalView.getSelectionModel().getSelectedItem();

                        new FestivalDetails(demo, listObservablesFigure, listObservablesDynasty, listObservablesKing);
                    }
                });
                String[] nameColFestival = { "Tên Lễ hội", "Thời gian", "Địa điểm" };
                String[] festivalStr = { "tenLeHoi", "thoigian", "diaDiem" };
                for (int i = 0; i < festivalStr.length; i++) {

                    TableColumn<Festival, String> ColFestival = new TableColumn<Festival, String>(nameColFestival[i]);
                    ColFestival.prefWidthProperty().bind(tableFestivalView.widthProperty().multiply(0.33));
                    ColFestival.setCellValueFactory(new PropertyValueFactory<>(festivalStr[i]));
                    tableFestivalView.getColumns().add(ColFestival);

                }
                Search<Festival> searchFestival = new Search<Festival>();
                tableFestivalView
                        .setItems(searchFestival.searchList(listObservablesFestival, textField, Festival.class));
                borderPane.setCenter(tableFestivalView);
                break;
            case "Triều Đại Lịch Sử":
                TableView<Dynasty> tableDynastyView = new TableView<>();
                tableDynastyView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (e.getClickCount() > 1) {
                        Dynasty demo = tableDynastyView.getSelectionModel().getSelectedItem();
                        System.out.println(demo.getFounder());
                        new DynastyDetails(demo, listObservablesKing);
                    }
                });
                tableDynastyView.getColumns().clear();
                String[] nameColDynasty = { "Tên Triều đại", "Năm bất đầu", "Năm kết thúc", "Kinh đô" };
                String[] dynastyStr = { "name", "startYear", "endYear", "capital" };
                for (int i = 0; i < dynastyStr.length; i++) {
                    TableColumn<Dynasty, String> colDynasty = new TableColumn<Dynasty, String>(nameColDynasty[i]);
                    colDynasty.prefWidthProperty().bind(tableDynastyView.widthProperty().multiply(0.25));
                    colDynasty.setCellValueFactory(new PropertyValueFactory<>(dynastyStr[i]));
                    tableDynastyView.getColumns().add(colDynasty);
                }
                // readerDataDynasty.forEach(elm -> tableDynastyView.getItems().add(elm));
                Search<Dynasty> searchDynasty = new Search<Dynasty>();
                tableDynastyView.setItems(searchDynasty.searchList(listObservablesDynasty, textField, Dynasty.class));
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
