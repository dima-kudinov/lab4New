import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Manager extends JPanel {
	private static int[] memory;
	private static int size;
	private static int countElement;
	private static int countFree;
	private static ArrayList<File> files;
	private int x = 0;
	private int y = 0;
	private static int sixe_i = 4;

	public static void startup(int _size) {
		size = _size;
		countFree = size / sixe_i;
		countElement = size / sixe_i;
		if (files != null) {
			files.removeAll(files);
		}
		files = new ArrayList<File>();

		if ((countElement % 40) == 0) {
			memory = new int[(countElement / 40)];
		} else {
			memory = new int[(countElement / 40) + 1];
		}
		for (int i = 0; i < memory.length; i++) {
			memory = new int[40];
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (memory != null) {
			for (int i = 0; i < memory.length; i++) {
				if (memory[i] == 1)
					g.setColor(Color.GRAY);
				else {
					if (memory[i] == 2) {
						g.setColor(Color.BLUE);
					} else {
						if (memory[i] == 3) {
							g.setColor(Color.RED);
						}
					}
				}
				g.fillRect(x, y, 11, 11);
				x += 12;
			}
			x = 0;
			y += 12;
		}
	}


	public static boolean addFile(String name, int size) {
		Changes changes = new Changes(memory, countElement, countFree, files);
		return changes.addFile(name, size);
	}

	public static void Delete(String s) {
		Changes changes = new Changes(memory, countElement, countFree, files);
		changes.Delete(s);;
	}

	public static void setMemoryPoint(int i, int p) {
		memory[i] = p;
	}

	public static void setSize(int s) {
		size = s;
	}
	public static void setFree() {
		countFree = size / sixe_i;
	}
}
