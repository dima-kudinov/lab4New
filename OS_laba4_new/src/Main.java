
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Main {
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private File file;
	private JTextField textField_2;
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
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 685, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new Manager();
		panel.setBorder(new LineBorder(new Color(0, 0, 255)));
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(12, 35, 484, 288);
		frame.getContentPane().add(panel);

		final DefaultListModel<String> list2 = new DefaultListModel<String>();
		JList<String> list = new JList<String>(list2);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBorder(new LineBorder(new Color(0, 0, 255)));
		list.setBackground(SystemColor.inactiveCaption);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String s = (String) list.getSelectedValue();
				if (file != null) {
					Mesto[] ps = file.getUzel().getPositions();
					for (int i = 0; i < ps.length; i++) {
						Manager.setMemoryPoint(ps[i].I, 2);
					}
				}
				File f = Changes.getfile(s);
				if (f != null) {
					Mesto[] ps = f.getUzel().getPositions();
					if (ps != null) {
						for (int i = 0; i < ps.length; i++) {
							Manager.setMemoryPoint(ps[i].I, 3);
						}
					}
					panel.repaint();
					file = f;
				}
			}
		});
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					JOptionPane.showMessageDialog(null, file.getUzel().toStr());

				}
			}
		});
		list.setBounds(530, 35, 125, 288);
		frame.getContentPane().add(list);

		JButton btnCreate = new JButton("Размер памяти");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				list.removeAll();
				list2.clear();
				Manager.setFree();
				String str = textField.getText();
				if (Integer.parseInt(str) % 4 != 0 && str != "") {
					JOptionPane.showMessageDialog(null,
							"Размер памяти должен быть кратным 4.");
				} else {
					Manager.startup(Integer.parseInt(str));
				}
				textField.setText("");
				panel.repaint();
			}
		});
		btnCreate.setBounds(115, 328, 148, 23);
		frame.getContentPane().add(btnCreate);

		textField = new JTextField();
		textField.setBounds(12, 330, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnAdd = new JButton("Добавить файл");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int size = Integer.parseInt(textField_1.getText());
				if (textField_2.getText().trim().length() > 0) {
						if (size % 4 != 0) {
							while (size % 4 != 0) {
								size++;
							}
						}
						if (size % 4 == 0) {
							String name = textField_2.getText();
							if (name != null) {
								Boolean add = Manager.addFile(name, size);
								if (add) {
									list2.addElement(name);
									list.setModel(list2);
								}
								panel.repaint();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Размер файла должен быть кратным 4.");
						}
				} else {
					JOptionPane.showMessageDialog(null,
							"Введите имя файла");
				}
				textField_2.setText("");
				textField_1.setText("");
			}
		});
		btnAdd.setBounds(210, 375, 148, 23);
		frame.getContentPane().add(btnAdd);

		JButton btnCopy = new JButton("Копировать файла");
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField_2.getText().trim().length() > 0) {
					String name = textField_2.getText();
					Boolean add = Manager.addFile(name + " - копия", Changes
							.getfile(name).getUzel().fileSize());
					if (add) {
						list2.addElement(name + " - копия");
						list.setModel(list2);
					}
					panel.repaint();
					textField_2.setText("");
				} else {
					JOptionPane.showMessageDialog(null,
							"Выберете файл для копирования");
				}
			}
		});
		btnCopy.setBounds(12, 425, 150, 23);
		frame.getContentPane().add(btnCopy);

		JButton btnDelete = new JButton("Удалить файл");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField_2.getText().trim().length() > 0) {
					String name = textField_2.getText();
					Manager.Delete(name);
					file = null;
					list2.removeElement(name);
					list.setModel(list2);
					panel.repaint();
					textField_2.setText("");
				} else {
					JOptionPane.showMessageDialog(null,
							"Выберете файл для удаления");
				}
			}
		});
		btnDelete.setBounds(180, 425, 148, 23);
		frame.getContentPane().add(btnDelete);

		JLabel lblNewLabel = new JLabel("Размер файла:");
		lblNewLabel.setBounds(12, 365, 102, 16);
		frame.getContentPane().add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setBounds(110, 365, 89, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Имя файла:");
		lblNewLabel_1.setBounds(12, 395, 116, 16);
		frame.getContentPane().add(lblNewLabel_1);

		textField_2 = new JTextField();
		textField_2.setBounds(110, 395, 89, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Память:");
		lblNewLabel_2.setBounds(12, 10, 100, 16);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Все файлы:");
		lblNewLabel_3.setBounds(530, 10, 100, 16);
		frame.getContentPane().add(lblNewLabel_3);

	}
}
