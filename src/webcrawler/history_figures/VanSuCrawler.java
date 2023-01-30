package webcrawler.history_figures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.figure.Figure;
import objects.figure.King;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VanSuCrawler extends BasicWebScraper implements IScraping {
	private ArrayList<String> links = new ArrayList<String>();

	public ArrayList<String> getLinks() {
		return this.links;
	}

	public VanSuCrawler(String url) {
		setUrl(url);
		connect();
	}

	@Override
	public void scraping() {

	}
}
