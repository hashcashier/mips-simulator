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
import javax.swing.filechooser.FileNameExtensionFilter;

import registers.Register;
import registers.RegisterManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GUI {

	private JFrame frmOraka;
	private JTable registerTable;
	private JTable table;
	private JTable memoryTable;
	private JTextPane logTextPane;
	private JTable reigsterTable;
	private JTextArea editor;
	private String currentFilePath = "";

	private JLabel lblExMem;
	private JLabel lblIdex;
	private JLabel lblIfid;
	private JLabel lblMemwb;
	private JLabel exmemVal;
	private JLabel idexVal;
	private JLabel ifidVal;
	private JLabel memwbVal;

	// private File file;

	private RegisterManager rm = new RegisterManager();

	// private String filePath;

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
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		btnOpenFile.setBounds(119, 6, 117, 29);
		frmOraka.getContentPane().add(btnOpenFile);

		JButton btnSaveFile = new JButton("Save File");
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentFilePath.equals(""))
					saveAs();
				else
					save();
			}
		});
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
		// TextLineNumber tln = new TextLineNumber(editor);
		// editorScrollPane.setRowHeaderView(tln);

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

		lblExMem = new JLabel("EX/MEM");
		lblExMem.setBounds(468, 389, 61, 16);
		frmOraka.getContentPane().add(lblExMem);

		lblIdex = new JLabel("ID/EX");
		lblIdex.setBounds(468, 417, 61, 16);
		frmOraka.getContentPane().add(lblIdex);

		lblIfid = new JLabel("IF/ID");
		lblIfid.setBounds(468, 445, 61, 16);
		frmOraka.getContentPane().add(lblIfid);

		lblMemwb = new JLabel("MEM/WB");
		lblMemwb.setBounds(468, 473, 61, 16);
		frmOraka.getContentPane().add(lblMemwb);

		exmemVal = new JLabel("-----");
		exmemVal.setBounds(541, 389, 312, 16);
		frmOraka.getContentPane().add(exmemVal);

		idexVal = new JLabel("-----");
		idexVal.setBounds(541, 417, 312, 16);
		frmOraka.getContentPane().add(idexVal);

		ifidVal = new JLabel("-----");
		ifidVal.setBounds(541, 445, 312, 16);
		frmOraka.getContentPane().add(ifidVal);

		memwbVal = new JLabel("-----");
		memwbVal.setBounds(541, 473, 312, 16);
		frmOraka.getContentPane().add(memwbVal);
		
		setEXMEM("/\\/\\/\\/\\/\\");
		setIDEX("/\\/\\/\\/\\/\\");
		setIFID("/\\/\\/\\/\\/\\");
		setMEMWB("/\\/\\/\\/\\/\\");

		log("-- Simulator just started --");
		// logTextPane.in

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
		if (currentFilePath.equals("")) {
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

	private void save() {
		File f = new File(currentFilePath);

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(f));

			editor.write(out);
			log("File changes succesfully saved [" + currentFilePath + "]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log("Error occured while trying to save file.");
			e.printStackTrace();
		}

	}

	private void saveAs() {
		FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter(
				"ASM", "mips");
		final JFileChooser saveAsFileChooser = new JFileChooser();
		saveAsFileChooser.setApproveButtonText("Save");
		saveAsFileChooser.setFileFilter(extensionFilter);
		int actionDialog = saveAsFileChooser.showSaveDialog(frmOraka);
		if (actionDialog != JFileChooser.APPROVE_OPTION) {
			return;
		}

		File file = saveAsFileChooser.getSelectedFile();
		if (!file.getName().endsWith(".asm")) {
			file = new File(file.getAbsolutePath() + ".asm");
			currentFilePath = file.getAbsolutePath();
			System.out.println(currentFilePath);
		}

		BufferedWriter outFile = null;
		try {
			outFile = new BufferedWriter(new FileWriter(file));

			editor.write(outFile);
			log("File succesfully saved [" + currentFilePath + "]");
		} catch (IOException ex) {
			ex.printStackTrace();
			log("Error occured while trying to save file.");
		} finally {
			if (outFile != null) {
				try {
					outFile.close();
				} catch (IOException e) {
					log("Error occured while trying to save file.");
				}
			}
		}
	}

	private void openFile() {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(frmOraka);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				// What to do with the file, e.g. display it in a TextArea
				editor.read(new FileReader(file.getAbsolutePath()), null);
				currentFilePath = file.getAbsolutePath();
				log("Opened [" + currentFilePath + "]");
			} catch (IOException ex) {
				log("An error occured trying to open [" + currentFilePath + "]");
			}
		} else {
			log("File access not permitted by system");
		}
	}

	public void setEXMEM(String val) {
		exmemVal.setText(val);
	}

	public void setIDEX(String val) {
		idexVal.setText(val);
	}

	public void setIFID(String val) {
		ifidVal.setText(val);
	}
	
	public void setMEMWB(String val) {
		memwbVal.setText(val);
	}

}