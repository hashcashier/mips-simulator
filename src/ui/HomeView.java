package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class HomeView {

	private JFrame frmOraka;
	private JTable registerTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeView window = new HomeView();
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
	public HomeView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOraka = new JFrame();
		frmOraka.setTitle("ORAKA MIPS Simulator");
		frmOraka.setBounds(100, 100, 600, 400);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 47, 430, 310);
		frmOraka.getContentPane().add(scrollPane);
		
		JTextArea editor = new JTextArea();
		scrollPane.setViewportView(editor);
	}
}
