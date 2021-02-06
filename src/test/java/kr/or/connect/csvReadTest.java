package kr.or.connect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class csvReadTest {
	private static String locationFilePath = "C:/Users/이유환/Desktop/졸업작품 데이터/감염병 데이터/성매개감염병.csv";
	private static List<String[]> locationList = new ArrayList<String[]>();
	
	public static void loadFile(String locationFilePath) {
		String line = null;
		File locationFile = new File(locationFilePath);
		try {
			BufferedReader in = new BufferedReader(new FileReader(locationFile));
			while((line = in.readLine()) != null) {
				String [] arr = line.split(",");
				locationList.add(arr);
			}
		}catch(Exception e) {
			e.printStackTrace();	
		}
	}
	
	public static void main(String[] args) {
		loadFile(locationFilePath);
		
		for(int i = 0; i < locationList.size(); i++) {
			String[] tmp = locationList.get(i);
		    for (int j = 0; j < tmp.length; j++) {
		        System.out.print(tmp[j] + " ");
		    }
		    System.out.println();
		}
	}
	
}
