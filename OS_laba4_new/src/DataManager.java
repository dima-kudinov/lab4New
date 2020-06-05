package laba4;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DataManager {
	public ArrayList<File> Files = new ArrayList<File>();
	private int chsize;
	int fileId;
	private int freeMemory = BlockMemory.count;
	private ArrayList<Uzel> uzels = new ArrayList<Uzel>();;

	public void CreateFile(int size, String name) {
		chsize = 0;
		fileId = Files.size();
		if (size > freeMemory) {
			Main.textAreaWindow.append("Невозможно добавить файл! \n");
			JOptionPane.showMessageDialog(null, "Недостаточно памяти!");
			return;
		}
		else {
			File file = new File(fileId, size, name);
			for (int i = 0; i <= BlockMemory.count; i++) {
				if (size > chsize) {
					if (BlockMemory.memory.get(i).getFileId() != -1) {
						while (BlockMemory.memory.get(i).getFileId() != -1) {
							i++;
						}
					}
					int fileId = file.getId();
					addUzel(BlockMemory.memory.get(i), fileId);
					freeMemory--;
					chsize++;
				}
				if (size == chsize) {
					Files.add(fileId, file);
					Main.textAreaWindow.append("Добавлен файл с именем "+name+" с №" + fileId + "\n");
					Main.textAreaWindow.append("Свободно памяти:" + freeMemory + "\n");
					return;
				}
			}
		}
	}

	public void DeleteFile(int id) {
		if (Files.get(id) == null) {
			Main.textAreaWindow.append("Данного файла не сущетвует! \n");
			JOptionPane.showMessageDialog(null, "Данного файла не сущетвует!");
			return;
		} else {
			freeMemory = freeMemory + Files.get(id).fileSize();
			removeUzels(id);
			Main.textAreaWindow.append("Удалён файл с именем "+Main.textId.getName()+" с № " + Main.textId.getText() + "\n");
		}
		Main.textAreaWindow.append("Свободно памяти:" + freeMemory + "\n");

	}

	public void ChoiceFile(int id) {
		if (Files.get(id) == null) {
			Main.textAreaWindow.append("Данного файла не сущетвует! \n");
			JOptionPane.showMessageDialog(null, "Данного файла не сущетвует!");
			return;
		} else {
			for (int i=0; i<uzels.size(); i++) {
				Uzel uzel= uzels.get(i);
				uzel.setSelectFlag(true);
			}
		}
	}

	public void cancelChoiceFile() {
		for(int j=0; j<uzels.size(); j++) {
			uzels.get(j).setSelectFlag(false);
		}
	}
	public void addUzel(Uzel uzel, int fileid) {
		uzels.add(uzel);
		uzel.setFile(fileid);
	}

	public void removeUzels(int fileId) {
		for(int i=0; i<uzels.size(); i++) {
			if (uzels.get(i).getFileId() == fileId) {
				uzels.get(i).setFile(-1);
			}
		}
		uzels.clear();
	}
}
