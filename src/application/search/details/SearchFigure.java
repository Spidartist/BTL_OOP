package application.search.details;

import java.util.function.Predicate;

import objects.figure.Figure;

public class SearchFigure {
	private boolean searchFindsFigure(Figure figure, String newValue) {
    	String lowerCaseFilter = newValue.toLowerCase();
		if ((figure.getTen().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
		   (figure.getQueQuan().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
//		   (figure.getTrieuDai().contains(newValue)) ||
		   (figure.getNamSinh().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
		   (figure.getNamMat().toLowerCase().indexOf(lowerCaseFilter) != -1) ||
		   (figure.getGhiChu().toLowerCase().indexOf(lowerCaseFilter) != -1) ) {
			return true ;
		}
		return false;
	}
    private Predicate<Figure> createPredicateFigure(String newValue){
        return Figure -> {
            if (newValue == null || newValue.isEmpty())// If filter text is empty, display all persons.
            	return true;
            return searchFindsFigure(Figure, newValue);
        };
    }
}
