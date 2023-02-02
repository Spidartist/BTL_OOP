package application.search.details;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;

public class Sorted<X> {
	Sorted(FilteredList<X> filteredData , TableView<X> table) {
    	SortedList<X> sortedData = new SortedList<>(filteredData);
    	sortedData.comparatorProperty().bind(table.comparatorProperty());
    	table.setItems(filteredData);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> f704fee497142ed078ff21c034fa0cae23ec55f8
