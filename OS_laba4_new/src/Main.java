package laba4;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class Main {

	private JFrame frame;
	static JTextField textId;
	private static JPanel panel;
	public static JTextArea textAreaWindow;
	private DataManager dataManager= new DataManager();;
	private BlockMemory Memory = new BlockMemory();
	private JTextField textFieldSize;
	private JTextField textFieldName;
	Graphics g;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 613, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new Manager(Memory);
		panel.setBounds(10, 11, 401, 141);
		frame.getContentPane().add(panel);

		JLabel labelWrite = new JLabel("Id");
		labelWrite.setBounds(423, 11, 26, 14);
		frame.getContentPane().add(labelWrite);
		textId = new JTextField();
		textId.setBounds(514, 11, 51, 14);
		textId.setColumns(10);
		frame.getContentPane().add(textId);

		JLabel lblSize = new JLabel("Размер файла");
		lblSize.setBounds(423, 38, 106, 14);
		frame.getContentPane().add(lblSize);
		textFieldSize = new JTextField();
		textFieldSize.setColumns(10);
		textFieldSize.setBounds(514, 38, 51, 14);
		frame.getContentPane().add(textFieldSize);

		JLabel lblName = new JLabel("Имя файла");
		lblName.setBounds(423, 65, 66, 14);
		frame.getContentPane().add(lblName);
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(514, 65, 51, 14);
		frame.getContentPane().add(textFieldName);

		JLabel labelCMD = new JLabel("Вывод");
		labelCMD.setBounds(10, 156, 126, 14);
		frame.getContentPane().add(labelCMD);

		textAreaWindow = new JTextArea();
		textAreaWindow.setForeground(Color.GRAY);
		textAreaWindow.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 16));
		textAreaWindow.setEnabled(false);
		textAreaWindow.setBounds(10, 176, 401, 299);
		frame.getContentPane().add(textAreaWindow);

		JButton buttonCreate = new JButton("Добавить файл");
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldSize.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Заполните поле Размер");
					return;
				}
				if (textFieldName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Заполните поле Имя");
					return;
				}
				dataManager.CreateFile(Integer.parseInt(textFieldSize.getText()), textFieldName.getText());
				dataManager.cancelChoiceFile();
				panel.repaint();

			}
		});
		buttonCreate.setBounds(439, 147, 126, 23);
		frame.getContentPane().add(buttonCreate);

		JButton buttonDel = new JButton("Удалить файл");
		buttonDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Заполните поле id");
					return;
				}
				dataManager.DeleteFile(Integer.parseInt(textId.getText()));
				dataManager.cancelChoiceFile();
				panel.repaint();

			}
		});
		buttonDel.setBounds(439, 205, 126, 23);
		frame.getContentPane().add(buttonDel);

		JButton buttonChoose = new JButton("Выбрать файл");
		buttonChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Заполните поле id");
					return;
				}
				dataManager.ChoiceFile(Integer.parseInt(textId.getText()));
				panel.repaint();
			}
		});
		buttonChoose.setBounds(439, 176, 126, 23);
		frame.getContentPane().add(buttonChoose);
	}

}
