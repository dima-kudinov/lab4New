package laba4;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DataManager {
	public ArrayList<File> Files = new ArrayList<File>();
	private int chsize;
	private int freeMemory = BlockMemory.count;

	public void CreateFile(int size, String name) {
		chsize = 0; 

		if (size > freeMemory) {
			Main.textAreaWindow.append("���������� �������� ����! \n");
			JOptionPane.showMessageDialog(null, "������������ ������!");
			return;
		} else {
			File file = new File(Files.size(), size, name);
			for (int i = 0; i <= BlockMemory.count; i++) {
				if (size > chsize) { 
					while (BlockMemory.memory.get(i).getFileId() != -1) {
						i++;
					}
					file.addBlock(i); 
					BlockMemory.memory.get(i).setFileId(file.getId());
					freeMemory--;
					chsize++;
				}
				if (size == chsize) {
					Files.add(file);
					Main.textAreaWindow.append("�������� ���� � ������ " + name + " � �" + file.getId() + "\n"); //123123412354125
					Main.textAreaWindow.append("�������� ������:" + freeMemory + "\n");
					return;
				}
			}
		}

	}

	public void DeleteFile(int id) {
		if (Files.get(id) == null) {
			Main.textAreaWindow.append("������� ����� �� ���������! \n");
			JOptionPane.showMessageDialog(null, "������� ����� �� ���������!");
			return;
		} else {
			 
			freeMemory = freeMemory + Files.get(id).fileSize();
			for (int discBlock : Files.get(id).getIndexNode().getBlocks()) {
				BlockMemory.memory.get(discBlock).setFileId(-1);
			}
			Files.remove(id);
			Main.textAreaWindow
					.append("����� ���� � ������ " + Main.textId.getName() + " � � " + Main.textId.getText() + "\n");
		}
		Main.textAreaWindow.append("�������� ������:" + freeMemory + "\n");

	}

	public void ChoiceFile(int id) {
		if (Files.get(id) == null) {
			Main.textAreaWindow.append("������� ����� �� ���������! \n");
			JOptionPane.showMessageDialog(null, "������� ����� �� ���������!");
			return;
		} else { 
			for (int discBlock : Files.get(id).getIndexNode().getBlocks()) {
				BlockMemory.memory.get(discBlock).setSelectFlag(true);
			}
		}
	}

	public void cancelChoiceFile() { //123123412531453465542362546
		for (int i = 0; i < BlockMemory.memory.size(); i++) {
			BlockMemory.memory.get(i).setSelectFlag(false);
		}
	}
}
