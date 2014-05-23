package ui;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import registers.Register;
import registers.RegisterManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GUI {

	private JFrame frmOraka;
	private JTable registerTable;
	private JTable table;
	private JTable memoryTable;
	private JTextPane logTextPane;
	private JTable reigsterTable;
	private JTextArea editor;
	private String currentFilePath = "";
	
//	private File file;

	private RegisterManager rm = new RegisterManager();
//	private String filePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmOraka.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOraka = new JFrame();
		frmOraka.setTitle("ORAKA MIPS Simulator");
		frmOraka.setBounds(100, 100, 860, 550);
		// frame.setBounds(100, 100, 450, 300);
		frmOraka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOraka.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Assemble");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				assembleProgram();
			}
		});
		btnNewButton.setBounds(341, 6, 105, 29);
		frmOraka.getContentPane().add(btnNewButton);

		JButton btnCompile = new JButton("Run");
		btnCompile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runProgram();
			}
		});
		btnCompile.setBounds(439, 6, 65, 29);
		frmOraka.getContentPane().add(btnCompile);

		JButton btnOpenFile = new JButton("Open File");
		btnOpenFile.setBounds(119, 6, 117, 29);
		frmOraka.getContentPane().add(btnOpenFile);

		JButton btnSaveFile = new JButton("Save File");
		btnSaveFile.setBounds(230, 6, 117, 29);
		frmOraka.getContentPane().add(btnSaveFile);
		
		JButton btnStep = new JButton("Step");
		btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performStep();
			}
		});
		btnStep.setBounds(497, 6, 75, 29);
		frmOraka.getContentPane().add(btnStep);
		
		JButton btnStop = new JButton("Stop/Reset");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAll();
			}
		});
		btnStop.setBounds(568, 6, 105, 29);
		frmOraka.getContentPane().add(btnStop);
		
		JButton btnNewFile = new JButton("Clear/New File");
		btnNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFile();
			}
		});
		btnNewFile.setBounds(6, 6, 117, 29);
		frmOraka.getContentPane().add(btnNewFile);

		JScrollPane editorScrollPane = new JScrollPane();
		editorScrollPane.setBounds(16, 47, 430, 310);
		frmOraka.getContentPane().add(editorScrollPane);

		editor = new JTextArea();
		editorScrollPane.setViewportView(editor);

		// TODO Comment the next 2 lines if you want to use the WindowBuilder
//		 TextLineNumber tln = new TextLineNumber(editor);
//		 editorScrollPane.setRowHeaderView(tln);

		JScrollPane registerTableScrollPane = new JScrollPane();
		registerTableScrollPane.setBounds(453, 47, 197, 310);
		frmOraka.getContentPane().add(registerTableScrollPane);

		Object registerTableColumnNames[] = { "Title", "#", "Value" };
		Object[][] registerData = getRegisterValues();

		registerTable = new JTable(registerData, registerTableColumnNames);
		registerTableScrollPane.setViewportView(registerTable);
		registerTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		registerTable.getColumnModel().getColumn(1).setPreferredWidth(75);
		registerTable.getColumnModel().getColumn(2).setPreferredWidth(200);

		JScrollPane memoryTableScrollPane = new JScrollPane();
		memoryTableScrollPane.setBounds(656, 48, 197, 310);
		frmOraka.getContentPane().add(memoryTableScrollPane);

		Object rowData[][] = { { "$s1", "32", "0x2222" },
				{ "$v1", "1", "0x1234" } };
		Object columnNames[] = { "Title", "#", "Value" };
		JTable table = new JTable(rowData, columnNames);

		// memoryTable = new JTable();
		memoryTableScrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(75);
		table.getColumnModel().getColumn(2).setPreferredWidth(160);

		JScrollPane logScrollPane = new JScrollPane();
		logScrollPane.setBounds(16, 389, 440, 115);
		frmOraka.getContentPane().add(logScrollPane);

		logTextPane = new JTextPane();
		logScrollPane.setColumnHeaderView(logTextPane);
		logTextPane.setEditable(false);

		log("-- Simulator just started --");
		System.out.println(rm.binaryToHex("0000000000001111"));
		// logTextPane.in

		
		/* START OF ACTION LISTENERS */
		
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				JFileChooser fileChooser = new JFileChooser();
//				File file = fileChooser.getSelectedFile();
//				String filePath = file.getAbsolutePath();
//				
//				try {
//					FileReader reader = new FileReader(filePath);
//					BufferedReader br = new BufferedReader(reader);
//					
//					editor.read(reader, null);
//					br.close();
//					
//					editor.requestFocus();
//					
//					log("Opening file: " + filePath);
//					log("Don't forget to save file if you make any changes.");
//					log("To run perform: Assemble > Run");
//				} catch (Exception e) {
//					// TODO: handle exception
//					log("An error occured trying to read the file.");
//				}
				
				System.out.println("Inside Action performed?");
			}
		});
		
		/* END OF ACTION LISTENERS */
	}

	public void log(String s) {
		logTextPane.setText(logTextPane.getText() + s + "\n");
	}

	public boolean setRegisterValue() {
		// TODO
		return true;
	}

	private Object[][] getRegisterValues() {
		Object[][] values = new Object[32][3];
		for (int i = 0; i < 32; i++) {
			Register r = rm.getRegister(i);
			String regTitle = r.getTitle();
			Integer regNumber = new Integer(i);
			String regValue = rm.formatHex(rm.binaryToHex(r.getValue()));
			values[i][0] = regTitle;
			values[i][1] = regNumber;
			values[i][2] = regValue;
		}
		// TODO
		return values;
	}
	
	public void runProgram() {
		if(currentFilePath.equals("")) {
			log("There seems to be no file currently open");
			log("Make sure you have saved the currently open file before running");
			
			return;
		}
		
		// TODO ELSE perform run.
	}
	
	public void assembleProgram() {
		log("Assembling program upto latest save performed");
	}
	
	public void performStep() {
		// TODO
	}
	
	public void resetAll() {
		
	}
	
	private void clearFile() {
		currentFilePath = "";
		editor.setText("");
		log("Cleared editor and file path.");
	}
}
