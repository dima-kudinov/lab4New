package laba4;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Manager extends JPanel {
	BlockMemory Memory;

	public Manager(BlockMemory memory) {
		Memory = memory;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Memory.Visual_Memory(g, this.getWidth(), this.getHeight());
	}
}