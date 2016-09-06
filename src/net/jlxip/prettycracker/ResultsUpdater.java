package net.jlxip.prettycracker;

public class ResultsUpdater extends Thread {
	public static Boolean stop = false;
	ResultsWindow window;
	
	public ResultsUpdater(ResultsWindow window) {
		this.window = window;
	}
	
	public void run() {
		while(!ResultsUpdater.stop) {
			window.setTable();
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
