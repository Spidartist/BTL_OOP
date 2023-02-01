package application.popup;

import application.popup.details.DynastyDetails;
import application.popup.details.FiguerDetails;
import application.popup.details.KingDetails;
import objects.dynasty.Dynasty;
import objects.figure.Figure;
import objects.figure.King;

public class PopUpWinDow {
    public void getPopUpWindow(Object obj) {
        if (obj.getClass() == King.class) {
            King curSelect = (King) obj;
            new KingDetails(curSelect);
        } else if (obj.getClass() == Figure.class) {
            Figure curSelect = (Figure) obj;
            new FiguerDetails(curSelect);
        } else if (obj.getClass() == Dynasty.class) {
            Dynasty curSelect = (Dynasty) obj;
            new DynastyDetails(curSelect);
        } else {

        }
    }
}
