package application.search.details;

import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import objects.figure.King;

public class SearchKing {
	private boolean searchFindsKing(King king, String newValue) {
    	String lowerCaseFilter = newValue.toLowerCase();
		if ((king.getTheThu().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
		   (king.getMieuHieu().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
		   (king.getThuyHieu().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
		   (king.getMieuHieu().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
		   (king.getTenHuy().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
		   (king.getNamTriVi().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
		   (king.getTheThu().toLowerCase().indexOf(lowerCaseFilter) != -1 )) {
			return true ;
		}
		return false;
	}
    private Predicate<King> createPredicateKing(String newValue){
        return King -> {
            if (newValue == null || newValue.isEmpty())// If filter text is empty, display all persons.
            	return true;
            return searchFindsKing(King, newValue);
        };
    }
//    Show data King after search and sort
//    void SortedKing(FilteredList<King> filteredData , TableView<King> table) {
//    	SortedList<King> sortedData = new SortedList<>(filteredData);
//    	sortedData.comparatorProperty().bind(table.comparatorProperty());
//    	table.setItems(filteredData);
//    }
}
