package ui;

import instructions.UnkownInstructionException;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import registers.Register;
import registers.RegisterManager;
import simulation.Simulator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map.Entry;

import javax.swing.JTextField;

import alu.InvalidOperationException;
import assembly.DuplicateLabelException;

public class GUI {

	private Simulator simulator = new Simulator();

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

	private JTable controlTable;

	private String programStartAddress = "400";
	private JTextField programStartAddressTextField;
	private JTable dataMemoryTable;
	private JTable programMemoryTable;
	private JTextField dataMemoryAddressTextField;
	private JTextField dataMemoryValueTextField;
	private JTextField programMemoryAddressTextField;
	private JTextField programMemoryValueTextField;

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
		frmOraka.setBounds(100, 100, 1000, 550);
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

		Object rowData[][] = { { "$s1", "32", "0x2222" },
				{ "$v1", "1", "0x1234" } };
		Object columnNames[] = { "Title", "#", "Value" };

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

		JScrollPane controlScrollPane = new JScrollPane();
		controlScrollPane.setBounds(865, 47, 129, 203);
		frmOraka.getContentPane().add(controlScrollPane);

		Object controlTableColumns[] = { "Title", "Value" };
		// Object[][] controlData = getControlValues();
		Object[][] controlData = getControlSignals();

		controlTable = new JTable(controlData, controlTableColumns);
		controlScrollPane.setViewportView(controlTable);

		JLabel lblProgramStartAddress = new JLabel("Program Start Adr.");
		lblProgramStartAddress.setBounds(865, 262, 129, 16);
		frmOraka.getContentPane().add(lblProgramStartAddress);

		programStartAddressTextField = new JTextField();
		programStartAddressTextField.setBounds(860, 283, 134, 28);
		frmOraka.getContentPane().add(programStartAddressTextField);
		programStartAddressTextField.setColumns(10);

		JButton btnSetAddress = new JButton("Set Address");
		btnSetAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String addr = programStartAddressTextField.getText().trim();
				if (!isNumeric(addr)) {
					log("Please enter a valid program start address and make sure it is greater than 400");
				} else {
					int addrVal = Integer.parseInt(addr);
					if (addrVal < 400)
						log("Please enter a value greater than 400");
					else {
						programStartAddress = (new Integer(addrVal)).toString();
						log("Program Start Address has been set to "
								+ programStartAddress);
					}
				}
			}
		});
		btnSetAddress.setBounds(865, 328, 117, 29);
		frmOraka.getContentPane().add(btnSetAddress);

		JScrollPane dataMemoryScrollPane = new JScrollPane();
		dataMemoryScrollPane.setBounds(662, 47, 197, 115);
		frmOraka.getContentPane().add(dataMemoryScrollPane);

		Object dataMemoryTableCols[] = { "Address", "Value" };
		Object[][] dataMemoryData = getDataMemoryValues();

		dataMemoryTable = new JTable(dataMemoryData, dataMemoryTableCols);
		dataMemoryScrollPane.setViewportView(dataMemoryTable);

		JScrollPane programMemoryScrollPane = new JScrollPane();
		programMemoryScrollPane.setBounds(662, 224, 197, 90);
		frmOraka.getContentPane().add(programMemoryScrollPane);

		Object[] programMemoryCols = { "Address", "Value" };
		Object[][] programMemoryData = getProgramMemoryData();

		programMemoryTable = new JTable(programMemoryData, programMemoryCols);
		programMemoryScrollPane.setViewportView(programMemoryTable);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(662, 166, 61, 16);
		frmOraka.getContentPane().add(lblAddress);

		JLabel lblValue = new JLabel("Value");
		lblValue.setBounds(735, 166, 61, 16);
		frmOraka.getContentPane().add(lblValue);

		JLabel lblAddress_1 = new JLabel("Address");
		lblAddress_1.setBounds(662, 318, 61, 16);
		frmOraka.getContentPane().add(lblAddress_1);

		JLabel lblValue_1 = new JLabel("Value");
		lblValue_1.setBounds(735, 318, 61, 16);
		frmOraka.getContentPane().add(lblValue_1);

		dataMemoryAddressTextField = new JTextField();
		dataMemoryAddressTextField.setBounds(662, 184, 65, 28);
		frmOraka.getContentPane().add(dataMemoryAddressTextField);
		dataMemoryAddressTextField.setColumns(10);

		dataMemoryValueTextField = new JTextField();
		dataMemoryValueTextField.setBounds(730, 184, 65, 28);
		frmOraka.getContentPane().add(dataMemoryValueTextField);
		dataMemoryValueTextField.setColumns(10);

		JButton btnSetDataMemory = new JButton("SDM");
		btnSetDataMemory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDataMemory(
						Long.parseLong(dataMemoryAddressTextField.getText()),
						dataMemoryValueTextField.getText());
			}
		});
		btnSetDataMemory.setBounds(792, 183, 65, 29);
		frmOraka.getContentPane().add(btnSetDataMemory);

		programMemoryAddressTextField = new JTextField();
		programMemoryAddressTextField.setBounds(662, 346, 61, 28);
		frmOraka.getContentPane().add(programMemoryAddressTextField);
		programMemoryAddressTextField.setColumns(10);

		programMemoryValueTextField = new JTextField();
		programMemoryValueTextField.setBounds(735, 346, 61, 28);
		frmOraka.getContentPane().add(programMemoryValueTextField);
		programMemoryValueTextField.setColumns(10);

		JButton btnSpm = new JButton("SPM");
		btnSpm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setProgramMemory(
						Long.parseLong(programMemoryAddressTextField.getText()),
						programMemoryValueTextField.getText());
			}
		});
		btnSpm.setBounds(792, 347, 61, 29);
		frmOraka.getContentPane().add(btnSpm);

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

	public Object[][] getControlSignals() {
		Hashtable<String, String> signals = simulator.getControlSignals();
		// COME HERE
		Object[][] values = new Object[signals.size()][2];

		int count = 0;
		for (Entry<String, String> entry : signals.entrySet()) {
			values[count][0] = entry.getKey();
			values[count][1] = entry.getValue();
			count++;
		}
		return values;
	}

	public void setDataMemory(long address, String value) {
		simulator.setMemoryContent(address, value);
	}

	public void setProgramMemory(long address, String value) {
		setDataMemory(address, value);
	}

	public Object[][] getProgramMemoryData() {
		Object[][] values = null;
		Hashtable<Long, String> programContents = simulator
				.getInstructionMemoryContents();

		values = new Object[programContents.size()][2];

		int count = 0;
		for (Entry<Long, String> entry : programContents.entrySet()) {
			values[count][0] = entry.getKey();
			values[count][1] = entry.getValue();
			count++;
		}
		return values;
	}

	public Object[][] getDataMemoryValues() {
		Object[][] values = null;
		Hashtable<Long, String> memoryContents = simulator
				.getDataMemoryContents();

		values = new Object[memoryContents.size()][2];

		int count = 0;
		for (Entry<Long, String> entry : memoryContents.entrySet()) {
			values[count][0] = entry.getKey();
			values[count][1] = entry.getValue();
			count++;
		}
		return values;
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
		return values;
	}

	private void setPipelineRegisterValues() {
		Hashtable<String, String> registers = simulator
				.getPipelineRegistersContents();

		for (Entry<String, String> entry : registers.entrySet()) {
			if (entry.getKey().equals("EX/MEM")
					|| entry.getKey().equals("EXMEM"))
				setEXMEM(entry.getValue());
			else if (entry.getKey().equals("ID/EX")
					|| entry.getKey().equals("IDEX"))
				setIDEX(entry.getValue());
			else if (entry.getKey().equals("IF/ID")
					|| entry.getKey().equals("IFID"))
				setIFID(entry.getValue());
			else
				setMEMWB(entry.getValue());
		}
	}

	public void runProgram() {
		if (currentFilePath.equals("")) {
			log("There seems to be no file currently open");
			log("Make sure you have saved the currently open file before running");
			return;
		} else {
			log("Running [" + currentFilePath + "]");
			try {
				simulator.run();
				log("Program ran succesfully.");
				update();
			} catch (InvalidOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log("And Error occured stopping the program from running in (GUI:runProgram)");
			}
		}

	}

	public void assembleProgram() {
		if (currentFilePath.equals("")) {
			log("Please save the file before assembling the program");
			return;
		}

		log("Assembling program upto latest save performed");

		try {
			simulator.assemble(currentFilePath, "Pipelined",
					Integer.parseInt(programStartAddress));
		} catch (NumberFormatException e) {
			log("An error occured while assembling the program");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			log("An error occured while assembling the program");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateLabelException e) {
			log("An error occured while assembling the program");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnkownInstructionException e) {
			log("An error occured while assembling the program");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO
		update();
	}

	public void performStep() {
		if (currentFilePath.equals("")) {
			log("Please save the file before you proceed.");
			log("Save > Assemble > Step");
			return;
		}

		try {
			simulator.step();
			update();
		} catch (InvalidOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void resetAll() {
		simulator = new Simulator();
		update();
	}

	public void update() {
		// TODO
		updateDataMemoryTable();
		updateProgramMemoryTable();
		updateRegistersTable();
		updateControlSignalsTable();
		updatePipelineRegistersData();
		log("PC: " + simulator.getProgramCounterValue());
	}

	public void updatePipelineRegistersData() {
		setPipelineRegisterValues();
	}

	public void updateControlSignalsTable() {
		Object controlTableColumns[] = { "Title", "Value" };
		Object[][] controlData = getControlSignals();

		DefaultTableModel model = new DefaultTableModel(controlData,
				controlTableColumns);
		controlTable.setModel(model);
		model.fireTableDataChanged();
	}

	public void updateRegistersTable() {
		Object registerTableColumnNames[] = { "Title", "#", "Value" };
		Object[][] registerData = getRegisterValues();

		DefaultTableModel model = new DefaultTableModel(registerData,
				registerTableColumnNames);

		registerTable.setModel(model);
		model.fireTableDataChanged();
	}

	public void updateDataMemoryTable() {
		Object dataMemoryTableCols[] = { "Address", "Value" };
		Object[][] dataMemoryData = getDataMemoryValues();

		DefaultTableModel model = new DefaultTableModel(dataMemoryData,
				dataMemoryTableCols);
		dataMemoryTable.setModel(model);
		model.fireTableDataChanged();
	}

	public void updateProgramMemoryTable() {
		Object programMemoryTableCols[] = { "Address", "Value" };
		Object[][] programMemoryData = getProgramMemoryData();

		DefaultTableModel model = new DefaultTableModel(programMemoryData,
				programMemoryTableCols);
		programMemoryTable.setModel(model);
		model.fireTableDataChanged();
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
//			System.out.println(currentFilePath);
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

	private boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public String getProgramStartAddress() {
		return programStartAddress;
	}
}