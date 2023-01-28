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
                String[] kingStr = { "ten", "mieuHieu", "thuyHieu", "nienHieu", "tenHuy", "theThu", "namTriVi" };
                for (int i = 0; i < kingStr.length; i++) {
                    TableColumn<King, String> ColKing = new TableColumn<King, String>(kingStr[i]);
                    ColKing.prefWidthProperty().bind(table.widthProperty().multiply(0.143));
                    ColKing.setCellValueFactory(new PropertyValueFactory<>(kingStr[i]));
                    table.getColumns().add(ColKing);
                }

                reader.getKingList().forEach(elm -> table.getItems().add(elm));
                break;
            case "Nhân Vật Lịch Sử":
                // TableView<King> table = new TableView<>();
                table.getColumns().clear();
                String[] figureStr = { "ten", "queQuan", "danToc", "namNhapNgu", "namSinh", "namMat", "ghiChu" };
                for (int i = 0; i < figureStr.length; i++) {
                    TableColumn<King, String> ColFigure = new TableColumn<King, String>(figureStr[i]);
                    ColFigure.prefWidthProperty().bind(table.widthProperty().multiply(0.143));
                    ColFigure.setCellValueFactory(new PropertyValueFactory<>(figureStr[i]));
                    table.getColumns().add(ColFigure);
                }

                // table.getColumns().addAll(ColFigure8, ColFigure2, ColFigure3, ColFigure4,
                // ColFigure5, ColFigure6,
                // ColFigure7);
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
