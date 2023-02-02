package application;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.function.Predicate;

import application.popup.PopUpWinDow;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
=======
import java.io.FileNotFoundException;
import java.io.IOException;

import application.popup.PopUpWinDow;
import application.readdata.ReadData;
import javafx.collections.ObservableList;
>>>>>>> f704fee497142ed078ff21c034fa0cae23ec55f8
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
        ObservableList<Festival> listObservablesFestival = new ReadData<Festival>() // Dynasty>()
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
                tableKingView.setItems(listObservablesKing);
                // readerDataKing.forEach(elm -> tableKingView.getItems().add(elm));
                // for (int i = 0; i < readerDataKing.size(); i++) {

                // }
                borderPane.setCenter(tableKingView);
<<<<<<< HEAD
                // Search
                reader.getKingList().remove(0, reader.getKingList().size());
                final ObservableList<King> dataListKing = FXCollections.observableArrayList();
                dataListKing.addAll(reader.getKingList());
                FilteredList<King> filteredDataKing = new FilteredList<>(dataListKing, b -> true);
                // Search text
                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    filteredDataKing.setPredicate(
                            createPredicateKing(newValue));
                });
                tableKingView.setItems(filteredDataKing);
                // SortedKing(filteredData,tableKingView);
=======

>>>>>>> f704fee497142ed078ff21c034fa0cae23ec55f8
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
<<<<<<< HEAD
//                String[] figureStr = { "ten", "queQuan", "trieuDai", "namSinh", "namMat", "ghiChu" };
//                for (int i = 0; i < figureStr.length; i++) {
//                    if (figureStr[i] == "trieuDai") {
//                        TableColumn<Figure, String> ColFigure = new TableColumn<Figure, String>(figureStr[i]);
//                        ColFigure.prefWidthProperty().bind(tableFigureView.widthProperty().multiply(0.143));
//                        ColFigure.setCellValueFactory(new PropertyValueFactory<>( figureStr[i]));
//                        tableFigureView.getColumns().add(ColFigure);
//                    } else {
//                        TableColumn<Figure, String> ColFigure = new TableColumn<Figure, String>(figureStr[i]);
//                        ColFigure.prefWidthProperty().bind(tableFigureView.widthProperty().multiply(0.143));
//                        ColFigure.setCellValueFactory(new PropertyValueFactory<>(figureStr[i]));
//                        tableFigureView.getColumns().add(ColFigure);
//                    }
//                }
//                reader.getFigureList().forEach(elm -> tableFigureView.getItems().add(elm));
                
                TableColumn<Figure, String> tenCol = new TableColumn<Figure,String>("ten");
                tenCol.setCellValueFactory(new PropertyValueFactory<>("ten"));
                TableColumn<Figure, String> queQuanCol = new TableColumn<Figure,String>("Que Quan");
                queQuanCol.setCellValueFactory(new PropertyValueFactory<>("queQuan"));
                TableColumn<Figure, String> trieuDaiCol = new TableColumn<Figure,String>("Trieu Dai");
                trieuDaiCol.setCellValueFactory(cellData -> {
                	ArrayList<Dynasty> dynasty = cellData.getValue().getTrieuDai();
                	String trieuDai = "";
                	for (Dynasty d : dynasty) {
                		trieuDai = trieuDai.concat(d.getName());
                		trieuDai = trieuDai.concat(" ");
                	}
                	return new SimpleStringProperty(trieuDai);
                });
                TableColumn<Figure, String> namSinhCol = new TableColumn<Figure,String>("Nam Sinh");
                namSinhCol.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
                TableColumn<Figure, String> namMatCol = new TableColumn<Figure,String>("Nam Mat");
                namMatCol.setCellValueFactory(new PropertyValueFactory<>("namMat"));
                TableColumn<Figure, String> ghiChuCol = new TableColumn<Figure,String>("Ghi Chu");
                ghiChuCol.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
                ObservableList<Figure> figureList = reader.getFigureList();
                tableFigureView.getColumns().add(tenCol);
                
                tableFigureView.getColumns().add(queQuanCol);
                tableFigureView.getColumns().add(trieuDaiCol);
                tableFigureView.getColumns().add(namSinhCol);
                tableFigureView.getColumns().add(namMatCol);
                tableFigureView.getColumns().add(ghiChuCol);
                for (Figure figure : figureList) {
                	tableFigureView.getItems().add(figure);
                }
=======
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
                tableFigureView.setItems(listObservablesFigure);
>>>>>>> f704fee497142ed078ff21c034fa0cae23ec55f8
                borderPane.setCenter(tableFigureView);
                // Code thêm đoạn search
                reader.getFigureList().remove(0, reader.getFigureList().size());
                final ObservableList<Figure> dataListFigure = FXCollections.observableArrayList();
                dataListFigure.addAll(reader.getFigureList());
                FilteredList<Figure> filteredDataFigure = new FilteredList<>(dataListFigure, b -> true);
                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    filteredDataFigure.setPredicate(
                            createPredicateFigure(newValue));
                });
                tableFigureView.setItems(filteredDataFigure);
                break;
            case "Sự kiện lịch sử":

                break;
            case "Di tích lịch sử":

                break;
            case "Triều Đại Lịch Sử":
                TableView<Dynasty> tableDynastyView = new TableView<>();
                tableDynastyView.getColumns().clear();
                tableDynastyView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (e.getClickCount() > 1) {
                        Dynasty demo = tableDynastyView.getSelectionModel().getSelectedItem();
                        System.out.println(demo.getFounder());
                        newPopUp.getPopUpWindow(demo);
                    }
                });
<<<<<<< HEAD
                // tableDynastyView.getColumns().clear();
                String[] dynastyStr = { "startYear", "endYear", "kings", "capital", "founder" };
=======
                tableDynastyView.getColumns().clear();
                String[] dynastyStr = { "name", "startYear", "endYear", "capital", "founder" };
>>>>>>> f704fee497142ed078ff21c034fa0cae23ec55f8
                for (int i = 0; i < dynastyStr.length; i++) {
                    TableColumn<Dynasty, String> colDynasty = new TableColumn<Dynasty, String>(dynastyStr[i]);
                    colDynasty.prefWidthProperty().bind(tableDynastyView.widthProperty().multiply(0.2));
                    colDynasty.setCellValueFactory(new PropertyValueFactory<>(dynastyStr[i]));
                    tableDynastyView.getColumns().add(colDynasty);
                }
                // readerDataDynasty.forEach(elm -> tableDynastyView.getItems().add(elm));
                tableDynastyView.setItems(listObservablesDynasty);
                borderPane.setCenter(tableDynastyView);

                reader.getDinastyList().remove(0, reader.getDinastyList().size());
                final ObservableList<Dynasty> dataListDynasty = FXCollections.observableArrayList();
                dataListDynasty.addAll(reader.getDinastyList());
                FilteredList<Dynasty> filteredDataDynasty = new FilteredList<>(dataListDynasty, b -> true);
                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    filteredDataDynasty.setPredicate(
                            createPredicateDynastry(newValue));
                });
                tableDynastyView.setItems(filteredDataDynasty);
            default:
                break;
        }
    }

    @FXML
    void search(ActionEvent event) {
        // ReadJson reader = new ReadJson();
        System.out.println(textField.getText());
        // tableView.setDisable(false);
        // TableColumn column = new TableColumn<>("colunm 1");
        // tableView.getColumns().add(column);
        // System.out.println(reader.getKingList().get(0).getMieuHieu());
    }

    @FXML
    void pressEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            search(null);
        }
    }

    private boolean searchFindsKing(King king, String newValue) {
        String lowerCaseFilter = newValue.toLowerCase();
        if ((king.getTheThu().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
                (king.getMieuHieu().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
                (king.getThuyHieu().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
                (king.getMieuHieu().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
                (king.getTenHuy().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
                (king.getNamTriVi().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
                (king.getTheThu().toLowerCase().indexOf(lowerCaseFilter) != -1)) {
            return true;
        }
        return false;
    }

    private Predicate<King> createPredicateKing(String newValue) {
        return King -> {
            if (newValue == null || newValue.isEmpty())// If filter text is empty, display all persons.
                return true;
            return searchFindsKing(King, newValue);
        };
    }

    // @SuppressWarnings("unlikely-arg-type")
    private boolean searchFindsFigure(Figure figure, String newValue) {
        String lowerCaseFilter = newValue.toLowerCase();
        if ((figure.getTen().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
                (figure.getQueQuan().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
                // (figure.getTrieuDai().indexOf(lowerCaseFilter) != -1) ||
                // (figure.getNamSinh().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
                // (figure.getNamMat().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
                (figure.getGhiChu().toLowerCase().indexOf(lowerCaseFilter) != -1)) {
            return true;
        }
        return false;
    }

    private Predicate<Figure> createPredicateFigure(String newValue) {
        return Figure -> {
            if (newValue == null || newValue.isEmpty())// If filter text is empty, display all persons.
                return true;
            return searchFindsFigure(Figure, newValue);
        };
    }

    private boolean searchFindsDynasty(Dynasty dynasty, String newValue) {
        String lowerCaseFilter = newValue.toLowerCase();
        if ((dynasty.getStartYear().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
                (dynasty.getEndYear().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
                (dynasty.getCapital().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
                (dynasty.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
                (dynasty.getFounder().getTen().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
                (dynasty.getKings().contains(newValue))) {
            return true;
        }
        return false;
    }

    private Predicate<Dynasty> createPredicateDynastry(String newValue) {
        return Dynasty -> {
            if (newValue == null || newValue.isEmpty())// If filter text is empty, display all persons.
                return true;
            return searchFindsDynasty(Dynasty, newValue);
        };
    }

    // Show data King after search and sort
    void SortedKing(FilteredList<King> filteredData, TableView<King> table) {
        SortedList<King> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(filteredData);
    }
}
// package application;

// import java.net.URL;
// import java.util.ResourceBundle;

// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.collections.transformation.FilteredList;
// import javafx.collections.transformation.SortedList;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.Initializable;
// import javafx.scene.control.MenuButton;
// import javafx.scene.control.MenuItem;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.control.TextField;
// import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.input.KeyCode;
// import javafx.scene.input.KeyEvent;
// import objects.figure.HistoricalFigure;
// import objects.figure.King;

// public class MainController implements Initializable {

// @FXML
// private MenuItem menuItem1;

// @FXML
// private MenuItem menuItem2;

// @FXML
// private MenuItem menuItem3;

// @FXML
// private MenuButton searchField;

// @FXML
// private TextField textField;

// @FXML
// private TableView<King> tableview;
// @FXML
// private TableColumn<King, String> ten;
// @FXML
// private TableColumn<King, String> namSinh;
// @FXML
// private TableColumn<King, String> namMat;

// private ObservableList<King> dataList = FXCollections.observableArrayList();

// public void initialize(URL url, ResourceBundle rb) {
// ReadJson reader = new ReadJson();
// reader.readData();
// ten.setCellValueFactory(new PropertyValueFactory<>("ten"));
// namSinh.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
// namMat.setCellValueFactory(new PropertyValueFactory<>("namMat"));

// dataList.addAll(reader.getKingList());
// // Wrap the ObservableList in a FilteredList (initially display all data).
// FilteredList<King> filteredData = new FilteredList<>(dataList);

// // 2. Set the filter Predicate whenever the filter changes.
// textField.textProperty().addListener((observable, oldValue, newValue) -> {
// filteredData.setPredicate(King -> {
// // If filter text is empty, display all persons.

// if (newValue == null || newValue.isEmpty()) {
// return true;
// }

// // Compare first name and last name of every person with filter text.
// String lowerCaseFilter = newValue.toLowerCase();

// if (King.getTen().toLowerCase().indexOf(lowerCaseFilter) != -1) {
// return true; // Filter matches first name.
// } else if (King.getNamSinh().toLowerCase().indexOf(lowerCaseFilter) != -1) {
// return true; // Filter matches last name.
// } else if (String.valueOf(King.getNamMat()).indexOf(lowerCaseFilter) != -1)
// return true;
// else
// return false; // Does not match.
// });
// });

// // 3. Wrap the FilteredList in a SortedList.
// ObservableList<King> sortedData = new SortedList<>(filteredData);

// // // 4. Bind the SortedList comparator to the TableView comparator.
// // // Otherwise, sorting the TableView would have no effect.
// // sortedData.comparatorProperty().bind(tableview.comparatorProperty());

// // // 5. Add sorted (and filtered) data to the table.
// tableview.setItems(sortedData);

// }

// // Xử lý sự kiện chọn trường để search
// @FXML
// void clickMenuItem(ActionEvent event) {
// MenuItem menuItem = (MenuItem) event.getSource();
// searchField.setText(menuItem.getText());
// }

// @FXML
// void search(ActionEvent event) {
// System.out.println(textField.getText());
// }

// @FXML
// void pressEnter(KeyEvent event) {
// if (event.getCode().equals(KeyCode.ENTER)) {
// search(null);
// }
// }
// }
