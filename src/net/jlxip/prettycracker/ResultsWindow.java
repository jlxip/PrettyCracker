package net.jlxip.prettycracker;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ResultsWindow extends JFrame {
	private static final long serialVersionUID = 429962883081891565L;
	
	public static ArrayList<List<String>> results = new ArrayList<List<String>>();
	JTable table;
	
	public ResultsWindow(MainWindow mainWindow) {
		super("Results");
		setLayout(new BorderLayout());
		setSize(480, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int window_x = mainWindow.getLocation().x + MainWindow.WIDTH + 16;
		int window_y = (Toolkit.getDefaultToolkit().getScreenSize().height - 640) / 2;
		setLocation(new Point(window_x, window_y));
		
		table = new JTable();
		table.setBounds(0, 0, 480, 640);
		JScrollPane scrollpane = new JScrollPane(table);
		add(scrollpane, BorderLayout.CENTER);
		
		setVisible(true);
		
		new ResultsUpdater(this).start();
	}
	
	public void setTable() {
		Object[] columns = {"NUMBER", "HASH", "ALGORITHM", "RESULT"};
		Object[][] rows = new Object[results.size()][4];
		for(int i=0;i<results.size();i++) {
			for(int j=0;j<results.get(i).size();j++) {
				rows[i][j] = results.get(i).get(j);
			}
		}
		
		TableModel model = new DefaultTableModel(rows, columns);
		table.setModel(model);
	}
}
