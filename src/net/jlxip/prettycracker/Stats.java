package net.jlxip.prettycracker;

import java.util.ArrayList;

public class Stats {
	public static long last_second = 0;
	public static long this_second = 0;
	public static long last_timestamp = 0;
	
	public static ArrayList<Long> stats = new ArrayList<Long>();
	
	public static void add() {
		if(last_timestamp == 0) {
			last_timestamp = System.currentTimeMillis() / 1000;
		}
		this_second++;
	}
	
	public static void setLastSecond() {
		long this_timestamp = System.currentTimeMillis() / 1000;
		if((this_timestamp-last_timestamp) >= 1) {
			last_second = this_second;
			stats.add(last_second);
			this_second = 0;
			last_timestamp = this_timestamp;
		}
	}
	
	public static long getSpeed() {
		return last_second;
	}
	
	public static long getAverage() {
		long average = 1;
		
		if(stats.size() > 0) {
			for(int i=0;i<stats.size();i++) {
				try {
					average += stats.get(i);
				} catch(Exception e) {
					return 1;
				}
			}
			average /= stats.size();
		}
		
		return average;
	}
}
