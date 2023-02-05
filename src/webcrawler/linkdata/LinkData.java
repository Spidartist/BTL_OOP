package webcrawler.linkdata;

import java.util.List;

import objects.figure.Figure;
import objects.figure.King;

public abstract class LinkData {
	protected List<Figure> listObservablesFigure;
	protected List<King> listObservablesKing;
	
	public abstract void genLink(String tenNguoiTho);

}
