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

        switch (lableSelecItem) {
            case "Nhân vật lịch sử":
                ReadJson reader = new ReadJson();
                // TableView<King> table = new TableView<>();
                table.getColumns().clear();
                TableColumn<King, String> Col1 = new TableColumn<King, String>("paperURL");
                Col1.prefWidthProperty().bind(table.widthProperty().multiply(0.125));
                Col1.setCellValueFactory(new PropertyValueFactory<>("paperURL"));

                TableColumn<King, String> Col2 = new TableColumn<King, String>("mieuHieu");
                Col2.prefWidthProperty().bind(table.widthProperty().multiply(0.125));
                Col2.setCellValueFactory(new PropertyValueFactory<>("mieuHieu"));

                TableColumn<King, String> Col3 = new TableColumn<King, String>("thuyHieu");
                Col3.prefWidthProperty().bind(table.widthProperty().multiply(0.125));
                Col3.setCellValueFactory(new PropertyValueFactory<>("thuyHieu"));

                TableColumn<King, String> Col4 = new TableColumn<King, String>("nienHieu");
                Col4.prefWidthProperty().bind(table.widthProperty().multiply(0.125));
                Col4.setCellValueFactory(new PropertyValueFactory<>("nienHieu"));

                TableColumn<King, String> Col5 = new TableColumn<King, String>("tenHuy");
                Col5.prefWidthProperty().bind(table.widthProperty().multiply(0.125));
                Col5.setCellValueFactory(new PropertyValueFactory<>("tenHuy"));

                TableColumn<King, String> Col6 = new TableColumn<King, String>("theThu");
                Col6.prefWidthProperty().bind(table.widthProperty().multiply(0.125));
                Col6.setCellValueFactory(new PropertyValueFactory<>("theThu"));

                TableColumn<King, String> Col7 = new TableColumn<King, String>("namTriVi");
                Col7.prefWidthProperty().bind(table.widthProperty().multiply(0.125));
                Col7.setCellValueFactory(new PropertyValueFactory<>("namTriVi"));

                TableColumn<King, String> Col8 = new TableColumn<King, String>("ten");
                Col8.prefWidthProperty().bind(table.widthProperty().multiply(0.125));
                Col8.setCellValueFactory(new PropertyValueFactory<>("ten"));

                table.getColumns().addAll(Col1, Col2, Col3, Col4, Col5, Col6, Col7, Col8);
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
