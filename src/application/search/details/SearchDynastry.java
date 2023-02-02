package application.search.details;

import java.util.function.Predicate;

import objects.dynasty.Dynasty;

public class SearchDynastry {
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
}
