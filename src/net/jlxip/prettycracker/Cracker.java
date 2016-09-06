package net.jlxip.prettycracker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

public class Cracker extends Thread {
	String charset;
	int min;
	int max;
	Boolean stop;
	JLabel state;
	String prefix;
	String suffix;
	int threadn;
	int threads;
	String algorithm;
	ArrayList<String> hashes;
	
	int lgs;	// Locally Generated Strings
	int stc;	// Supposed To Crack
	
	public Cracker(String charset, int min, int max, JLabel state, String prefix, String suffix, int threadn, int threads, String algorithm, ArrayList<String> hashes) {
		this.charset = charset;
		this.min = min;
		this.max = max;
		stop = false;
		this.state = state;
		this.prefix = prefix;
		this.suffix = suffix;
		this.threadn = threadn;
		this.threads = threads;
		this.algorithm = algorithm;
		this.hashes = hashes;
		
		lgs = 0;
		stc = threadn;
	}
	
	public void crack(String algorithm, String str) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		String formatString = "";
		switch(algorithm) {
			case "MD5":
				formatString = "%032x";
				break;
			case "SHA-1":
				formatString = "%040x";
				break;
			case "SHA-256":
				formatString = "%064x";
				break;
		}
		
		md.update(str.getBytes());
		byte[] digest = md.digest();
		String hash = String.format(formatString, new java.math.BigInteger(1, digest));
		if(hashes.size() > 0) {
			for(int i=0;i<hashes.size();i++) {
				if(hash.equals(hashes.get(i))) {
					List<String> toAdd = new ArrayList<String>();
					toAdd.add(String.valueOf(i+1));
					toAdd.add(hash);
					toAdd.add(algorithm);
					toAdd.add(str);
					ResultsWindow.results.add(toAdd);
					hashes.remove(hash);
				}
			}
		} else {
			MainWindow.stop(threadn, "Finished.");
			stop = true;
		}
	}
	
	public void generate(String str, int pos, int length) {		// Source (bottom answer): http://goo.gl/gLQEQO
		if(!stop) {
			if(length==0) {
				if(lgs==stc) {
					String finalstr = prefix + str + suffix;
					
					if(threadn==0) Stats.setLastSecond();	// For avoiding saturation of the function! Which means wrong stats
					state.setText("State: Cracking. Trying: "+finalstr+" @ "+Stats.getSpeed()+" hashes/sec. --- Average: "+Stats.getAverage()+" hashes/sec.");
					
					if(algorithm.equals("UNKNOWN")) {
						for(int i=0;i<3;i++) {
							switch(i) {
								case 0:
									crack("MD5", finalstr);
									break;
								case 1:
									crack("SHA-1", finalstr);
									break;
								case 2:
									crack("SHA-256", finalstr);
									break;
							}
						}
					} else {
						crack(algorithm, finalstr);
					}
					
					Stats.add();
					stc += threads;
				}
				
				lgs++;
			} else {
				if(pos != 0) {
					pos = 0;
				}
				
				for(int i=pos;i<charset.length();i++) {
					generate(str + charset.charAt(i), i, length - 1);
				}
			}
		}
	}
	
	public void run() {
		int fixed_min = min;
		int fixed_max = max;
		if(!prefix.equals("")) {
			fixed_min -= prefix.length();
			fixed_max -= prefix.length();
		}
		if(!suffix.equals("")) {
			fixed_min -= suffix.length();
			fixed_max -= suffix.length();
		}
		
		for(int i=fixed_min;i<=fixed_max;i++) {
			if(stop) {
				break;
			}
			
			generate("", 0, i);
		}
		
		MainWindow.stop(threadn, "Finished.");
	}
	
	
}
