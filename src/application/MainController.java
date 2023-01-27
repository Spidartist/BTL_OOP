package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

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
    private TableView<?> tableView;

    @FXML
    private TextField textField;

    // Xử lý sự kiện chọn trường để search
    @FXML
    void clickMenuItem(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        searchField.setText(menuItem.getText());
    }

    @FXML
    void search(ActionEvent event) {
        System.out.println(textField.getText());
        tableView.setDisable(false);
        TableColumn column = new TableColumn<>("colunm 1");
        tableView.getColumns().add(column);
    }

    @FXML
    void pressEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            search(null);
        }
    }
}
