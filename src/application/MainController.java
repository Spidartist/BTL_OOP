package application;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import objects.dynasty.Dynasty;
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
    void clickMenuItem(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        String lableSelecItem = menuItem.getText();
        searchField.setText(menuItem.getText());
        ReadJson reader = new ReadJson();
        switch (lableSelecItem) {
            case "Vua":
                TableView<King> tableKingView = new TableView<>();
                tableKingView.getColumns().clear();
                String[] kingStr = { "ten", "mieuHieu", "thuyHieu", "nienHieu", "tenHuy", "theThu", "namTriVi" };
                for (int i = 0; i < kingStr.length; i++) {
                    TableColumn<King, String> ColKing = new TableColumn<King, String>(kingStr[i]);
                    ColKing.prefWidthProperty().bind(tableKingView.widthProperty().multiply(0.143));
                    ColKing.setCellValueFactory(new PropertyValueFactory<>(kingStr[i]));
                    tableKingView.getColumns().add(ColKing);
                }
                reader.getKingList().forEach(elm -> tableKingView.getItems().add(elm));
                borderPane.setCenter(tableKingView);
                // Search
                FilteredList<King> filteredData = new FilteredList<>(reader.getKingList(), b -> true);
                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    filteredData.setPredicate(King -> {
                        // If filter text is empty, display all persons.

                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }

                        // Compare first name and last name of every person with filter text.
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (King.getTheThu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; // Filter matches first name.
                        } else if (King.getMieuHieu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; // Filter matches last name.
                        } else if (King.getThuyHieu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; // Filter matches last name.
                        } else if (King.getNienHieu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; // Filter matches last name.
                        } else if (King.getTenHuy().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; // Filter matches last name.
                        } else if (King.getNamTriVi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true; // Filter matches last name.
                        } else if (String.valueOf(King.getTheThu()).indexOf(lowerCaseFilter) != -1)
                            return true;
                        else
                            return false; // Does not match.
                    });
                });
                // SortedList<King> sortedData = new SortedList<>(filteredData);
                // sortedData.comparatorProperty().bind(tableKingView.comparatorProperty());
                tableKingView.setItems(filteredData);
                break;
            case "Nhân Vật Lịch Sử":
                TableView<Figure> tableFigureView = new TableView<>();
                tableFigureView.getColumns().clear();
                String[] figureStr = { "ten", "queQuan", "trieuDai", "namSinh", "namMat", "ghiChu" };
                for (int i = 0; i < figureStr.length; i++) {
                    if (figureStr[i] == "trieuDai"){
                        TableColumn<Figure, String> ColFigure = new TableColumn<Figure, String>(figureStr[i]);
                        ColFigure.prefWidthProperty().bind(tableFigureView.widthProperty().multiply(0.143));
                        ColFigure.setCellValueFactory(new PropertyValueFactory<>(figureStr[i]));
                        tableFigureView.getColumns().add(ColFigure);
                    }
                    else{
                        TableColumn<Figure, String> ColFigure = new TableColumn<Figure, String>(figureStr[i]);
                        ColFigure.prefWidthProperty().bind(tableFigureView.widthProperty().multiply(0.143));
                        ColFigure.setCellValueFactory(new PropertyValueFactory<>(figureStr[i]));
                        tableFigureView.getColumns().add(ColFigure);
                    }
                }
                reader.getFigureList().forEach(elm -> tableFigureView.getItems().add(elm));
                borderPane.setCenter(tableFigureView);
                break;
            case "Sự kiện lịch sử":

                break;
            case "Di tích lịch sử":

                break;
            case "Triều Đại Lịch Sử":
                TableView<Dynasty> tableDynastyView = new TableView<>();
                tableDynastyView.getColumns().clear();
                String[] dynastyStr = { "startYear", "endYear", "kings", "capital", "founder" };
                for (int i = 0; i < dynastyStr.length; i++) {
                    TableColumn<Dynasty, String> colDynasty = new TableColumn<Dynasty, String>(dynastyStr[i]);
                    colDynasty.prefWidthProperty().bind(tableDynastyView.widthProperty().multiply(0.143));
                    colDynasty.setCellValueFactory(new PropertyValueFactory<>(dynastyStr[i]));
                    tableDynastyView.getColumns().add(colDynasty);
                }
                reader.getDinastyList().forEach(elm -> tableDynastyView.getItems().add(elm));
                borderPane.setCenter(tableDynastyView);
                break;
            default:
                break;
        }
    }

    @FXML
    void search(ActionEvent event) {
        ReadJson reader = new ReadJson();
        System.out.println(textField.getText());
        // tableView.setDisable(false);
        // TableColumn column = new TableColumn<>("colunm 1");
        // tableView.getColumns().add(column);
        System.out.println(reader.getKingList().get(0).getMieuHieu());
    }

    @FXML
    void pressEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            search(null);
        }
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
