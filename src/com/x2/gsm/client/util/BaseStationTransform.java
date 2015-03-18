package com.x2.gsm.client.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.x2.gsm.client.GsmResponse;
import com.x2.gsm.dao.PhoneRecordDAO;

import de.fhpotsdam.unfolding.geo.Location;

public class BaseStationTransform {
	
	private static BaseStationTransform transform = null;
	private Map<String, Location> map = new HashMap<String, Location>();
	
	private BaseStationTransform() {
		File file = new File("./data/data/base_station_GPS.txt");
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(
					new FileInputStream(file), "utf-8")
					);
			try {
				String line = br.readLine();
				String[] rs = null;
				Location loc = null;
				while((line = br.readLine()) != null) {
					rs = line.split("\\|");
					loc = new Location(Float.parseFloat(rs[4]),
							Float.parseFloat(rs[5]));
					map.put(rs[0] + rs[1], loc);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static BaseStationTransform getInstance() {
		if (transform == null) {
			transform = new BaseStationTransform();
		}
		return transform;
	}
	
	public ArrayList<Location> getLocations(GsmResponse r) {
		ArrayList<Location> list = new ArrayList<Location>();
		
		for(PhoneRecordDAO dao : r.getList()) {
			System.out.println(dao.getTime());
			Location loc = map.get(dao.getAreaID() + dao.getCellID());
			if (loc != null) {
				list.add(loc);
			}
		}
		
		return list;
	}

}
