package webcrawler.test;
import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import objects.dynasty.Dynasty;
import objects.figure.Figure;
import objects.figure.King;
public class Test {
	public static void main(String[] args) {
		String test = "Lạc Long Quân[l2][24]";
		int index = test.indexOf("[");
		while (index != -1) {
			int close = test.indexOf("]");
			String tmp = test.substring(index, close+1);
			test= test.replace(tmp, "");
			index = test.indexOf("[");
		}
		System.out.println(test);
	}
	
}
