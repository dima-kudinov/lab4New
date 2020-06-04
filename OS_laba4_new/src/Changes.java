import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Changes extends JPanel{

	private static int[] memory;
	private static int countElement;
	private static int countFree;
	private static ArrayList<File> files;

	public Changes(int[] memory, int countElement, int countFree, ArrayList<File> files) {
		this.memory = memory;
		this.countElement = countElement;
		this.countFree = countFree;
		this.files = files;
	}

	public static boolean addFile(String name, int size) {
		if (files != null) {
			for (int i = 0; i < files.size(); i++) {
				if (files.get(i).getUzel().getName().equals(name)) {
					JOptionPane.showMessageDialog(null, "Такой файл уже есть.");
					return false;
				}
			}
		}
		if (size / 4 > countFree) {
			JOptionPane.showMessageDialog(null, "Слишком большой файл.");
			return false;
		}
		countFree -= size / 4;
		countElement = size / 4;
		Mesto[] ps = new Mesto[countElement];
		int ps_id = 0;
		for (int i = 0; i < memory.length && countElement > 0; i++) {
			if (memory[i] == 1) {
				countElement--;
				memory[i] = 2;
				ps[ps_id++] = new Mesto(i);
			}
		}

		int sizeLocal = 0;
		if(size / 4 > 3) {
			sizeLocal = 3*4;
			size -=sizeLocal;
		} else {
			sizeLocal = size;
			size = 0;
		}
		IndexUzel uzel = new IndexUzel(ps,name, sizeLocal);
		uzel.indexUzel(ps);
		while (size > 0) {
			if(size / 4 > 3) {
				sizeLocal = 3*4;
				size -=sizeLocal;
				uzel.setNode(ps,name, sizeLocal);
			} else {
				sizeLocal = size;
				size = 0;
				uzel.setNode(ps,name, sizeLocal);
			}
		}
		File file = new File(uzel);
		files.add(file);
		return true;
	}

	public static void Delete(String s) {
		Mesto[] ps = getfile(s).getUzel().getPositions();
		if (ps != null) {
			for (int i = 0; i < ps.length; i++) {
				memory[ps[i].I] = 1;
			}
		}
		countFree += getfile(s).getUzel().fileSize() / 2;
		files.remove(getfile(s));
	}

	public static File getfile(String s) {
		if (files != null) {
			for (int i = 0; i < files.size(); i++) {
				if (files.get(i).getUzel().getName().equals(s)) {
					return files.get(i);
				}
			}
		}
		return null;
	}
}
