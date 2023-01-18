package jSoupWebCrawler.history_figures;

import org.jsoup.Jsoup;

import java.awt.desktop.ScreenSleepEvent;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jSoupWebCrawler.parent.BasicWebScraper;

public class FindFigure {
	public static void main(String[] args) {
		AnhHungLucLuong anhHung = new AnhHungLucLuong();
		anhHung.scraping();
	}
}
