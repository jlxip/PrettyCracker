package net.jlxip.prettycracker;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 3376727316474695546L;
	
	public static ArrayList<Cracker> crackers = null;
	public static JLabel state;
	public static JButton crack;
	public static ResultsWindow results = null;
	
	public static int WIDTH = 0;
	public static int HEIGHT = 0;
	public static int window_x = 0;
	public static int window_y = 0;

	public MainWindow() {
		super("PrettyCracker");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		
		JPanel algorithm = new JPanel();
		algorithm.setLayout(null);
		TitledBorder tbalgorithm = BorderFactory.createTitledBorder("Algorithm");
		algorithm.setBorder(tbalgorithm);
		
		ButtonGroup group = new ButtonGroup();
		
		JRadioButton md5 = new JRadioButton("MD5");
		Dimension Dmd5 = md5.getPreferredSize();
		int Pmd5_x = 8;
		int Pmd5_y = 24;
		Point Pmd5 = new Point(Pmd5_x, Pmd5_y);
		Rectangle Rmd5 = new Rectangle(Pmd5, Dmd5);
		md5.setBounds(Rmd5);
		md5.setSelected(true);
		algorithm.add(md5);
		group.add(md5);
		
		JRadioButton sha1 = new JRadioButton("SHA-1");
		Dimension Dsha1 = sha1.getPreferredSize();
		int Psha1_x = md5.getBounds().x + md5.getBounds().width + 8;
		int Psha1_y = md5.getBounds().y;
		Point Psha1 = new Point(Psha1_x, Psha1_y);
		Rectangle Rsha1 = new Rectangle(Psha1, Dsha1);
		sha1.setBounds(Rsha1);
		algorithm.add(sha1);
		group.add(sha1);
		
		JRadioButton sha256 = new JRadioButton("SHA-256");
		Dimension Dsha256 = sha256.getPreferredSize();
		int Psha256_x = sha1.getBounds().x + sha1.getBounds().width + 8;
		int Psha256_y = sha1.getBounds().y;
		Point Psha256 = new Point(Psha256_x, Psha256_y);
		Rectangle Rsha256 = new Rectangle(Psha256, Dsha256);
		sha256.setBounds(Rsha256);
		algorithm.add(sha256);
		group.add(sha256);
		
		JRadioButton unknown = new JRadioButton("Unknown");
		Dimension Dunknown = unknown.getPreferredSize();
		int Punknown_x = sha256.getBounds().x + sha256.getBounds().width + 8;
		int Punknown_y = sha256.getBounds().y;
		Point Punknown = new Point(Punknown_x, Punknown_y);
		Rectangle Runknown = new Rectangle(Punknown, Dunknown);
		unknown.setBounds(Runknown);
		algorithm.add(unknown);
		group.add(unknown);
		
		
		Component algorithm_largest_component = unknown;
		Component algorithm_last_component = unknown;
		int Dalgorithm_width = algorithm_largest_component.getBounds().x + algorithm_largest_component.getBounds().width + 8;
		int Dalgorithm_height = algorithm_last_component.getBounds().y + algorithm_last_component.getBounds().height + 8;
		Dimension Dalgorithm = new Dimension(Dalgorithm_width, Dalgorithm_height);
		Point Palgorithm = new Point(8, 8);
		Rectangle Ralgorithm = new Rectangle(Palgorithm, Dalgorithm);
		algorithm.setBounds(Ralgorithm);
		add(algorithm);
		
		
		JPanel charset = new JPanel();
		charset.setLayout(null);
		TitledBorder tbcharset = BorderFactory.createTitledBorder("Charset");
		charset.setBorder(tbcharset);
		
		JCheckBox az = new JCheckBox("a-z");
		Dimension Daz = az.getPreferredSize();
		int Paz_x = 8;
		int Paz_y = 24;
		Point Paz = new Point(Paz_x, Paz_y);
		Rectangle Raz = new Rectangle(Paz, Daz);
		az.setBounds(Raz);
		az.setSelected(true);
		charset.add(az);
		
		JCheckBox AZ = new JCheckBox("A-Z");
		Dimension DAZ = AZ.getPreferredSize();
		int PAZ_x = az.getBounds().x + az.getBounds().width + 8;
		int PAZ_y = az.getBounds().y;
		Point PAZ = new Point(PAZ_x, PAZ_y);
		Rectangle RAZ = new Rectangle(PAZ, DAZ);
		AZ.setBounds(RAZ);
		charset.add(AZ);
		
		JCheckBox numbers = new JCheckBox("0-9");
		Dimension Dnumbers = numbers.getPreferredSize();
		int Pnumbers_x = AZ.getBounds().x + AZ.getBounds().width + 8;
		int Pnumbers_y = AZ.getBounds().y;
		Point Pnumbers = new Point(Pnumbers_x, Pnumbers_y);
		Rectangle Rnumbers = new Rectangle(Pnumbers, Dnumbers);
		numbers.setBounds(Rnumbers);
		numbers.setSelected(true);
		charset.add(numbers);
		
		JLabel lblmin = new JLabel("Min.:");
		Dimension Dlblmin = lblmin.getPreferredSize();
		int Plblmin_x = numbers.getBounds().x + numbers.getBounds().width + 8;
		int Plblmin_y = numbers.getBounds().y+3;
		Point Plblmin = new Point(Plblmin_x, Plblmin_y);
		Rectangle Rlblmin = new Rectangle(Plblmin, Dlblmin);
		lblmin.setBounds(Rlblmin);
		charset.add(lblmin);
		
		JTextField min = new JTextField("3");
		Dimension Dmin = new Dimension(32, 24);
		int Pmin_x = lblmin.getBounds().x + lblmin.getBounds().width + 8;
		int Pmin_y = lblmin.getBounds().y - 2;
		Point Pmin = new Point(Pmin_x, Pmin_y);
		Rectangle Rmin = new Rectangle(Pmin, Dmin);
		min.setBounds(Rmin);
		charset.add(min);
		
		JLabel lblmax = new JLabel("Max.:");
		Dimension Dlblmax = lblmax.getPreferredSize();
		int Plblmax_x = min.getBounds().x + min.getBounds().width + 8;
		int Plblmax_y = lblmin.getBounds().y;
		Point Plblmax = new Point(Plblmax_x, Plblmax_y);
		Rectangle Rlblmax = new Rectangle(Plblmax, Dlblmax);
		lblmax.setBounds(Rlblmax);
		charset.add(lblmax);
		
		JTextField max = new JTextField("6");
		Dimension Dmax = new Dimension(32, 24);
		int Pmax_x = lblmax.getBounds().x + lblmax.getBounds().width + 8;
		int Pmax_y = lblmax.getBounds().y - 2;
		Point Pmax = new Point(Pmax_x, Pmax_y);
		Rectangle Rmax = new Rectangle(Pmax, Dmax);
		max.setBounds(Rmax);
		charset.add(max);
		
		Component charset_largest_component = max;
		Component charset_last_component = numbers;
		int Dcharset_width = charset_largest_component.getBounds().x + charset_largest_component.getBounds().width + 8;
		int Dcharset_height = charset_last_component.getBounds().y + charset_last_component.getBounds().height + 8;
		Dimension Dcharset = new Dimension(Dcharset_width, Dcharset_height);
		int Pcharset_x = 8;
		int Pcharset_y = algorithm.getBounds().y + algorithm.getBounds().height + 8;
		Point Pcharset = new Point(Pcharset_x, Pcharset_y);
		Rectangle Rcharset = new Rectangle(Pcharset, Dcharset);
		charset.setBounds(Rcharset);
		add(charset);
		
		
		JPanel pattern = new JPanel();
		pattern.setLayout(null);
		TitledBorder tbpattern = BorderFactory.createTitledBorder("Pattern");
		pattern.setBorder(tbpattern);
		
		JCheckBox cbprefix = new JCheckBox("Prefix:");
		Dimension Dcbprefix = cbprefix.getPreferredSize();
		int Pcbprefix_x = 8;
		int Pcbprefix_y = 24;
		Point Pcbprefix = new Point(Pcbprefix_x, Pcbprefix_y);
		Rectangle Rcbprefix = new Rectangle(Pcbprefix, Dcbprefix);
		cbprefix.setBounds(Rcbprefix);
		pattern.add(cbprefix);
		
		JTextField prefix = new JTextField();
		Dimension Dprefix = new Dimension(128, 24);
		int Pprefix_x = cbprefix.getBounds().x + cbprefix.getBounds().width + 4;
		int Pprefix_y = cbprefix.getBounds().y - 2;
		Point Pprefix = new Point(Pprefix_x, Pprefix_y);
		Rectangle Rprefix = new Rectangle(Pprefix, Dprefix);
		prefix.setBounds(Rprefix);
		prefix.setEnabled(false);
		pattern.add(prefix);
		
		cbprefix.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				prefix.setEnabled(cbprefix.isSelected());
			}
		});
		
		JCheckBox cbsuffix = new JCheckBox("Suffix:");
		Dimension Dcbsuffix = cbsuffix.getPreferredSize();
		int Pcbsuffix_x = 8;
		int Pcbsuffix_y = cbprefix.getBounds().y + cbprefix.getBounds().height + 8;
		Point Pcbsuffix = new Point(Pcbsuffix_x, Pcbsuffix_y);
		Rectangle Rcbsuffix = new Rectangle(Pcbsuffix, Dcbsuffix);
		cbsuffix.setBounds(Rcbsuffix);
		pattern.add(cbsuffix);
		
		JTextField suffix = new JTextField();
		Dimension Dsuffix = new Dimension(128, 24);
		int Psuffix_x = cbsuffix.getBounds().x + cbsuffix.getBounds().width + 4;
		int Psuffix_y = cbsuffix.getBounds().y - 2;
		Point Psuffix = new Point(Psuffix_x, Psuffix_y);
		Rectangle Rsuffix = new Rectangle(Psuffix, Dsuffix);
		suffix.setBounds(Rsuffix);
		suffix.setEnabled(false);
		pattern.add(suffix);
		
		cbsuffix.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				suffix.setEnabled(cbsuffix.isSelected());
			}
		});
		
		Component pattern_largest_component = prefix;
		Component pattern_last_component = suffix;
		int Dpattern_width = pattern_largest_component.getBounds().x + pattern_largest_component.getBounds().width + 8;
		int Dpattern_height = pattern_last_component.getBounds().y + pattern_last_component.getBounds().height + 8;
		Dimension Dpattern = new Dimension(Dpattern_width, Dpattern_height);
		int Ppattern_x = 8;
		int Ppattern_y = charset.getBounds().y + charset.getBounds().height + 8;
		Point Ppattern = new Point(Ppattern_x, Ppattern_y);
		Rectangle Rpattern = new Rectangle(Ppattern, Dpattern);
		pattern.setBounds(Rpattern);
		add(pattern);
		
		
		JPanel hashes = new JPanel();
		hashes.setLayout(null);
		TitledBorder tbhashes = BorderFactory.createTitledBorder("Hashes");
		hashes.setBorder(tbhashes);
		
		JTextArea tahashes = new JTextArea();
		Dimension Dtahashes = new Dimension(512, 128);
		Point Ptahashes = new Point(8, 24);
		Rectangle Rtahashes = new Rectangle(Ptahashes, Dtahashes);
		tahashes.setBounds(Rtahashes);
		hashes.add(tahashes);
		
		Dimension Dhashes = new Dimension(tahashes.getBounds().x+tahashes.getBounds().width+8, tahashes.getBounds().y+tahashes.getBounds().height+8);
		int Phashes_x = 8;
		int Phashes_y = pattern.getBounds().y + pattern.getBounds().height + 8;
		Point Phashes = new Point(Phashes_x, Phashes_y);
		Rectangle Rhashes = new Rectangle(Phashes, Dhashes);
		hashes.setBounds(Rhashes);
		add(hashes);
		
		
		JPanel options = new JPanel();
		options.setLayout(null);
		TitledBorder tboptions = BorderFactory.createTitledBorder("Options");
		options.setBorder(tboptions);
		
		JLabel lblthreads = new JLabel("Threads:");
		Dimension Dlblthreads = lblthreads.getPreferredSize();
		Point Plblthreads = new Point(8, 24);
		Rectangle Rlblthreads = new Rectangle(Plblthreads, Dlblthreads);
		lblthreads.setBounds(Rlblthreads);
		options.add(lblthreads);
		
		JTextField threads = new JTextField("20");
		Dimension Dthreads = new Dimension(64, 24);
		int Pthreads_x = lblthreads.getBounds().x + lblthreads.getBounds().width + 8;
		int Pthreads_y = lblthreads.getBounds().y - 2;
		Point Pthreads = new Point(Pthreads_x, Pthreads_y);
		Rectangle Rthreads = new Rectangle(Pthreads, Dthreads);
		threads.setBounds(Rthreads);
		options.add(threads);
		
		Component options_largest_component = threads;
		Component options_last_component = threads;
		int Doptions_width = options_largest_component.getBounds().x + options_largest_component.getBounds().width + 8;
		int Doptions_height = options_last_component.getBounds().y + options_last_component.getBounds().height + 8;
		Dimension Doptions = new Dimension(Doptions_width, Doptions_height);
		int Poptions_x = 8;
		int Poptions_y = hashes.getBounds().y + hashes.getBounds().height + 8;
		Point Poptions = new Point(Poptions_x, Poptions_y);
		Rectangle Roptions = new Rectangle(Poptions, Doptions);
		options.setBounds(Roptions);
		add(options);
		
		
		crack = new JButton("CRACK");
		Dimension Dcrack = crack.getPreferredSize();
		
		Component largest_component = hashes;
		WIDTH = largest_component.getBounds().x + largest_component.getBounds().width + 16;
		
		int Pcrack_x = (WIDTH - Dcrack.width) / 2;
		int Pcrack_y = options.getBounds().y + options.getBounds().height + 8;
		Point Pcrack = new Point(Pcrack_x, Pcrack_y);
		Rectangle Rcrack = new Rectangle(Pcrack, Dcrack);
		crack.setBounds(Rcrack);
		MainWindow me = this;
		
		state = new JLabel("State: Stopped");
		
		crack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(crack.getText().equals("CRACK")) {
					if(tahashes.getText().equals("")) {
						JOptionPane.showMessageDialog(me, "You have to introduce at least one hash.");
						return;
					}

					Pattern endline_pattern = Pattern.compile(Pattern.quote("\n"));
					ArrayList<String> crack_hashes = new ArrayList<String>();
					String lines = tahashes.getText();
					for(int i=0;i<endline_pattern.split(lines).length;i++) {
						crack_hashes.add(endline_pattern.split(lines)[i]);
					}
					
					String crack_alg = "";
					if(md5.isSelected()) {
						crack_alg="MD5";
					} else if(sha1.isSelected()) {
						crack_alg="SHA-1";
					} else if(sha256.isSelected()) {
						crack_alg="SHA-256";
					} else if(unknown.isSelected()) {
						crack_alg="UNKNOWN";
					}
					
					int crack_min = Integer.parseInt(min.getText());
					int crack_max = Integer.parseInt(max.getText());
					
					if(crack_max < crack_min) {
						JOptionPane.showMessageDialog(me, "The max. value is smaller than the min. value.");
						return;
					}
					
					String crack_prefix = "";
					if(cbprefix.isSelected()) {
						crack_prefix = prefix.getText();
						if(crack_min <= crack_prefix.length()) {
							JOptionPane.showMessageDialog(me, "The min. value is smaller than or equals to the size of the prefix.");
							return;
						}
					}
					
					String crack_suffix = "";
					if(cbsuffix.isSelected()) {
						crack_suffix = suffix.getText();
						if(crack_min <= crack_prefix.length() + crack_suffix.length()) {
							JOptionPane.showMessageDialog(me, "The min. value is smaller than or equals to the size of the prefix and the sufix.");
							return;
						}
					}
					
					int crack_threads = Integer.parseInt(threads.getText());
					
					String crack_charset="";
					if(az.isSelected()) crack_charset += "abcdefghijklmnopqrstuvwxyz";
					if(AZ.isSelected()) crack_charset += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
					if(numbers.isSelected()) crack_charset += "0123456789";
					
					if(results != null) {
						results.dispose();
						ResultsWindow.results = new ArrayList<List<String>>();
						results = null;
					}
					
					// CHANGE THE POSITION OF THE WINDOW
					window_x = (getLocation().x - 16 - (480 / 2));
					setLocation(new Point(window_x, window_y));
					
					crack.setText("STOP");
					
					crackers = new ArrayList<Cracker>();
					for(int i=0;i<crack_threads;i++) {
						Cracker cracker = new Cracker(crack_charset, crack_min, crack_max, state, crack_prefix, crack_suffix, i, crack_threads, crack_alg, crack_hashes);
						cracker.start();
						crackers.add(cracker);
					}
					
					results = new ResultsWindow(me);
				} else {
					stop(0, "Stopped.");
				}
			}
		});
		add(crack);
		
		
		Dimension Dstate = new Dimension(WIDTH, 24);
		int Pstate_x = 0;
		int Pstate_y = crack.getBounds().y + crack.getBounds().height + 8;
		Point Pstate = new Point(Pstate_x, Pstate_y);
		Rectangle Rstate = new Rectangle(Pstate, Dstate);
		state.setBounds(Rstate);
		add(state);
		
		
		Component last_component = state;
		HEIGHT = last_component.getBounds().y + last_component.getBounds().height + 32;
		setSize(WIDTH, HEIGHT);
		
		window_x = (Toolkit.getDefaultToolkit().getScreenSize().width - WIDTH) / 2;
		window_y = (Toolkit.getDefaultToolkit().getScreenSize().height - HEIGHT) / 2;
		setLocation(new Point(window_x, window_y));
		setVisible(true);
	}
	
	public static void stop(int threadn, String strstate) {
		if(threadn == 0) {
			for(int i=0;i<crackers.size();i++) {
				crackers.get(i).stop = true;
			}
			
			crackers = new ArrayList<Cracker>();
		}
		
		state.setText("State: "+strstate);
		crack.setText("CRACK");
	}
}
