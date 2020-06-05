import java.util.ArrayList;

public class IndexNode {
	private ArrayList<Integer> blocks = new ArrayList<Integer>();
	
	public void addBlock(int i) {
		blocks.add(i);
	}
	
	public ArrayList<Integer> getBlocks() {
		return blocks;
	}
}
