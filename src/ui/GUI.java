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

import registers.Register;
import registers.RegisterManager;

public class GUI {

	private JFrame frmOraka;
	private JTable registerTable;
	private JTable table;
	private JTable memoryTable;
	private JTextPane logTextPane;
	private JTable reigsterTable;

	private RegisterManager rm = new RegisterManager();

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
		frmOraka.setBounds(100, 100, 850, 550);
		// frame.setBounds(100, 100, 450, 300);
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
		memoryTableScrollPane.setBounds(656, 48, 141, 310);
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

		// memoryTableScrollPane.setViewportView(memoryTable);
		// memoryTable.setValueAt(aValue, row, column)

		JLabel lblRegisters = new JLabel("Registers");
		lblRegisters.setBounds(453, 28, 61, 16);
		frmOraka.getContentPane().add(lblRegisters);

		JLabel lblMemory = new JLabel("Memory");
		lblMemory.setBounds(656, 28, 61, 16);
		frmOraka.getContentPane().add(lblMemory);

		JScrollPane logScrollPane = new JScrollPane();
		logScrollPane.setBounds(16, 370, 440, 115);
		frmOraka.getContentPane().add(logScrollPane);

		logTextPane = new JTextPane();
		logScrollPane.setColumnHeaderView(logTextPane);
		logTextPane.setEditable(false);
		log("-- Simulator just started --");
		System.out.println(rm.binaryToHex("0000000000001111"));
		// logTextPane.in

	}

	private void log(String s) {
		logTextPane.setText(logTextPane.getText() + s + "\n");
	}

	private boolean setRegisterValue() {
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
}
