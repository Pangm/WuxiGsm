package com.x2.gsm.client;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PShape;

import com.x2.gsm.client.util.BaseStationTransform;
import com.x2.gsm.client.util.HttpUtil;
import com.x2.gsm.client.util.JSonUtil;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;


public class WuxiGsm extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7202118212240992970L;
	private static final String DATA_DIRECTORY = "./data";
	Location wuxiLocation = new Location(31.38177f, 120.1846f);
	
	private PShape phoneLogo;
	private Image icon;
	private UnfoldingMap map;
	private int initZoomLevel = 10;
	private int loadCnt = 0;
	private ArrayList<Location> locList = null;
	
	public static void main(String[] agrs) {
		PApplet.main(new String[] { "com.x2.gsm.client.WuxiGsm" });
	}

	public void setup() {
		size(1000, 600);
		
		String mbTilesString = sketchPath(DATA_DIRECTORY + "/map/WuxiMap.mbtiles");
		
		map = new UnfoldingMap(this, new MBTilesMapProvider(mbTilesString));
		
		map.zoomToLevel(initZoomLevel);
		map.panTo(wuxiLocation);
		map.setZoomRange(9, 15);
//		map.setPanningRestriction(wuxiLocation, 50);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		frameRate(60);
		loadCnt = (int) (frameRate * 6);
		
		// load logo
		phoneLogo = loadShape(DATA_DIRECTORY + "/phone1.svg");
		Toolkit tk = Toolkit.getDefaultToolkit();
		icon = tk.createImage(DATA_DIRECTORY + "/phone1.png");
//		String uriAPI = "http://219.224.169.45:8080/GsmService/query.action?ak=421081564";
		
//		String uriAPI = "http://219.224.169.45:8080/GsmService/query.action?"
//				+ "ak=421081564"
//				+ "&deviceId=99694252503308235"
//				+ "&startTime=20131111000000"
//				+ "&endTime=20131112000000";
		
//		String jsonString = HttpUtil.sendPost(uriAPI, "");
		String jsonString = dataFromFile();
		
//		System.out.println(jsonString);
		GsmResponse response;
		
		response = JSonUtil.jsonToObject(jsonString, GsmResponse.class);
		System.out.println(response.getStatus());
		BaseStationTransform transform = BaseStationTransform.getInstance();
		
		locList = transform.getLocations(response);
	}
	
	public void draw() {
		background(0);
		this.frame.setTitle("Wuxi GSM");
		this.frame.setIconImage(icon);
		map.draw();
		
		if (loadCnt > 0) {
			fill(255);
			rect(0, 0, width, height);
			shape(phoneLogo, width / 2 - 0.559f * height / 8f, height / 2 - height
					/ 8f, 0.559f * height / 4f, height / 4f);
			loadCnt--;
		} else {
			if (locList != null) {
				System.out.println(locList.size());
				for (Location loc : locList) {
					randomDraw(loc);
				}
			} else {
				System.out.println("Location List is null!");
			}
		}
	}
	
	private void randomDraw(Location loc) {
		ScreenPosition pos = map.getScreenPosition(loc);
		noStroke();
		fill(255, 255, 0, 20);
		ellipse(pos.x+ random(0, 5), pos.y + random(0, 5), 2, 2);
		System.out.println(loc.x + ", " + loc.y);
	}
	
	private String dataFromFile() {
		File file = new File("./data/data/response.json");
		String rs = "";
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(
					new FileInputStream(file), "utf-8")
					);
			try {
				String line = "";
				while((line = br.readLine()) != null) {
					rs += line;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}			
			
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
