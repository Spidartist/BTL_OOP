package application;

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
    private ScrollPane pane;

    @FXML
    private MenuButton searchField;

    @FXML
    private TableView<King> table;

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

                // TableView<King> table = new TableView<>();
                table.getColumns().clear();

                // TableColumn<King, String> Col1 = new TableColumn<King, String>("paperURL");
                // Col1.prefWidthProperty().bind(table.widthProperty().multiply(0.125));
                // Col1.setCellValueFactory(new PropertyValueFactory<>("paperURL"));
                TableColumn<King, String> ColKing2 = new TableColumn<King, String>("mieuHieu");
                ColKing2.prefWidthProperty().bind(table.widthProperty().multiply(0.143));
                ColKing2.setCellValueFactory(new PropertyValueFactory<>("mieuHieu"));

                TableColumn<King, String> ColKing3 = new TableColumn<King, String>("thuyHieu");
                ColKing3.prefWidthProperty().bind(table.widthProperty().multiply(0.142));
                ColKing3.setCellValueFactory(new PropertyValueFactory<>("thuyHieu"));

                TableColumn<King, String> ColKing4 = new TableColumn<King, String>("nienHieu");
                ColKing4.prefWidthProperty().bind(table.widthProperty().multiply(0.142));
                ColKing4.setCellValueFactory(new PropertyValueFactory<>("nienHieu"));

                TableColumn<King, String> ColKing5 = new TableColumn<King, String>("tenHuy");
                ColKing5.prefWidthProperty().bind(table.widthProperty().multiply(0.142));
                ColKing5.setCellValueFactory(new PropertyValueFactory<>("tenHuy"));

                TableColumn<King, String> ColKing6 = new TableColumn<King, String>("theThu");
                ColKing6.prefWidthProperty().bind(table.widthProperty().multiply(0.142));
                ColKing6.setCellValueFactory(new PropertyValueFactory<>("theThu"));

                TableColumn<King, String> ColKing7 = new TableColumn<King, String>("namTriVi");
                ColKing7.prefWidthProperty().bind(table.widthProperty().multiply(0.142));
                ColKing7.setCellValueFactory(new PropertyValueFactory<>("namTriVi"));

                TableColumn<King, String> ColKing8 = new TableColumn<King, String>("ten");
                ColKing8.prefWidthProperty().bind(table.widthProperty().multiply(0.142));
                ColKing8.setCellValueFactory(new PropertyValueFactory<>("ten"));

                table.getColumns().addAll(ColKing8, ColKing2, ColKing3, ColKing4, ColKing5, ColKing6, ColKing7);
                reader.getKingList().forEach(elm -> table.getItems().add(elm));
                break;
            case "Nhân Vật Lịch Sử":
                // TableView<King> table = new TableView<>();
                table.getColumns().clear();
                // TableColumn<King, String> Col1 = new TableColumn<King, String>("paperURL");
                // Col1.prefWidthProperty().bind(table.widthProperty().multiply(0.125));
                // Col1.setCellValueFactory(new PropertyValueFactory<>("paperURL"));
                double wid = 1 / 7;
                TableColumn<Figure, String> ColFigure2 = new TableColumn<Figure, String>("mieuHieu");
                ColFigure2.prefWidthProperty().bind(table.widthProperty().multiply(0.143));
                ColFigure2.setCellValueFactory(new PropertyValueFactory<>("mieuHieu"));

                TableColumn<Figure, String> ColFigure3 = new TableColumn<Figure, String>("thuyHieu");
                ColFigure3.prefWidthProperty().bind(table.widthProperty().multiply(0.142));
                ColFigure3.setCellValueFactory(new PropertyValueFactory<>("thuyHieu"));

                TableColumn<Figure, String> ColFigure4 = new TableColumn<Figure, String>("nienHieu");
                ColFigure4.prefWidthProperty().bind(table.widthProperty().multiply(0.142));
                ColFigure4.setCellValueFactory(new PropertyValueFactory<>("nienHieu"));

                TableColumn<Figure, String> ColFigure5 = new TableColumn<Figure, String>("tenHuy");
                ColFigure5.prefWidthProperty().bind(table.widthProperty().multiply(0.142));
                ColFigure5.setCellValueFactory(new PropertyValueFactory<>("tenHuy"));

                TableColumn<Figure, String> ColFigure6 = new TableColumn<Figure, String>("theThu");
                ColFigure6.prefWidthProperty().bind(table.widthProperty().multiply(0.142));
                ColFigure6.setCellValueFactory(new PropertyValueFactory<>("theThu"));

                TableColumn<Figure, String> ColFigure7 = new TableColumn<Figure, String>("namTriVi");
                ColFigure7.prefWidthProperty().bind(table.widthProperty().multiply(0.142));
                ColFigure7.setCellValueFactory(new PropertyValueFactory<>("namTriVi"));

                TableColumn<Figure, String> ColFigure8 = new TableColumn<Figure, String>("ten");
                ColFigure8.prefWidthProperty().bind(table.widthProperty().multiply(0.142));
                ColFigure8.setCellValueFactory(new PropertyValueFactory<>("ten"));

                table.getColumns().addAll(ColFigure8, ColFigure2, ColFigure3, ColFigure4, ColFigure5, ColFigure6,
                        ColFigure7);
                reader.getKingList().forEach(elm -> table.getItems().add(elm));
                break;
            case "Sự kiện lịch sử":

                break;
            case "Di tích lịch sử":

                break;
            // case "Nhân vật lịch sử":

            // break;

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
