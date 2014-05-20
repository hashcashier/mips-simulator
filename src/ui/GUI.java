package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class GUI {

	private JFrame frmOraka;
	private JTable registerTable;
	private JTable table;
	private JTable memoryTable;
	private JTextPane logTextPane;

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
		frmOraka.setBounds(100, 100, 750, 550);
//		frame.setBounds(100, 100, 450, 300);
		frmOraka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOraka.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Assemble");
		btnNewButton.setBounds(264, 6, 105, 29);
		frmOraka.getContentPane().add(btnNewButton);
		
		JButton btnCompile = new JButton("Run");
		btnCompile.setBounds(381, 6, 65, 29);
		frmOraka.getContentPane().add(btnCompile);
		
		JButton btnOpenFile = new JButton("Open File");
		btnOpenFile.setBounds(6, 6, 117, 29);
		frmOraka.getContentPane().add(btnOpenFile);
		
		JButton btnSaveFile = new JButton("Save File");
		btnSaveFile.setBounds(135, 6, 117, 29);
		frmOraka.getContentPane().add(btnSaveFile);
		
		JScrollPane editorScrollPane = new JScrollPane();
		editorScrollPane.setBounds(16, 47, 430, 310);
		frmOraka.getContentPane().add(editorScrollPane);
		
		JTextArea editor = new JTextArea();
		editorScrollPane.setViewportView(editor);
		
		
		// TODO Comment the next 2 lines if you want to use the WindowBuilder
//		TextLineNumber tln = new TextLineNumber(editor);
//		editorScrollPane.setRowHeaderView(tln);
		
		JScrollPane registerTableScrollPane = new JScrollPane();
		registerTableScrollPane.setBounds(453, 47, 141, 310);
		frmOraka.getContentPane().add(registerTableScrollPane);
		
		JTable reigsterTable = new JTable();
		registerTableScrollPane.setViewportView(registerTable);
		
		JScrollPane memoryTableScrollPane = new JScrollPane();
		memoryTableScrollPane.setBounds(600, 48, 141, 310);
		frmOraka.getContentPane().add(memoryTableScrollPane);
		
		memoryTable = new JTable();
		memoryTableScrollPane.setViewportView(memoryTable);
		
		JLabel lblRegisters = new JLabel("Registers");
		lblRegisters.setBounds(453, 28, 61, 16);
		frmOraka.getContentPane().add(lblRegisters);
		
		JLabel lblMemory = new JLabel("Memory");
		lblMemory.setBounds(600, 28, 61, 16);
		frmOraka.getContentPane().add(lblMemory);
		
		JScrollPane logScrollPane = new JScrollPane();
		logScrollPane.setBounds(26, 375, 440, 115);
		frmOraka.getContentPane().add(logScrollPane);
		
		logTextPane = new JTextPane();
		logScrollPane.setViewportView(logTextPane);
		logTextPane.setEditable(false);
		log("-- Simulator just started --");
//		logTextPane.in
		
	}
	
	private void log(String s) {
		logTextPane.setText(logTextPane.getText() + s + "\n");
	}
}
