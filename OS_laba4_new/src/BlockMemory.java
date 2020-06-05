package laba4;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class BlockMemory {
	public static ArrayList<Uzel> memory;
	public static int count = 128;

	public BlockMemory() {
		memory = new ArrayList<Uzel>();
		for (int i = 0; i < count; i++) {
			memory.add(i, new Uzel(-1, i));
		}
	}
	public void Visual_Memory(Graphics g, int width, int height) {
		int cellHeight = 20; 
		int cellWidth = 20;
		int chsize = 0;
		int left = 0; 
		int top = 0; 
		for (int i = 0; i < count; i++) {
			left = chsize * cellWidth;
			chsize++;
			if (memory.get(i).getFileId() == -1) {
				g.setColor(Color.GRAY);
			} else {
				g.setColor(Color.BLUE);
			}
			if (memory.get(i).getSelectFlag()) {
				g.setColor(Color.RED);
			}
			g.fillRect(left, top, cellWidth, cellHeight);
			g.setColor(Color.WHITE);
			g.drawRect(left, top, cellWidth, cellHeight);
			g.drawString(memory.get(i).getFileId()+1 + "", left + 5, top + 20);
			if (chsize == 20) {
				top += cellHeight;
				chsize = 0;
				left = 0;
			}
		}
	}
}
