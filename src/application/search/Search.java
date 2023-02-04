package application.search;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;

public class Search<T> {
    public ObservableList<T> demo(ObservableList<T> data, TextField textField) {
        FilteredList<T> filteredData = new FilteredList<>(data, b -> true);
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(obj -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                return false;

            });
        });
        return filteredData;
    }
}
