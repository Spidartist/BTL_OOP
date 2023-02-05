package webcrawler.test;
import java.util.ArrayList;

import objects.dynasty.Dynasty;
public class Test {
	public static void main(String[] args) {
		Dynasty d1 = new Dynasty("Thời tiền sử");
		ArrayList<Dynasty> list = new ArrayList<Dynasty>();
		Dynasty d2 = new Dynasty("đầu","3100 TCN","Thời tiền sử");
		Dynasty d4 = new Dynasty("204 TCN","111 TCN","Nhà Triệu");
		list.add(d4);
		list.add(d2);
		
		for (Dynasty d : list) {
			if (d.getName().contains(d1.getName())) {
				d1 = d;
			}
		}
	}
	
}
