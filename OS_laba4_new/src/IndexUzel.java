
public class IndexUzel {
	private Mesto[] positions;
	private String fileName;
	private int fileSize;
	private IndexUzel uzel;

	public IndexUzel(Mesto[] positions, String fileName, int fileSize) {
		this.positions = positions;
		this.fileName = fileName;
		this.fileSize = fileSize;

	}

	public void indexUzel(Mesto[] ps) {
		uzel=new IndexUzel(positions,"имя", -1);
	}

	public Mesto[] getPositions() {
		return positions;
	}

	public String getName() {
		return fileName;
	}

	public int fileSize() {
		if(uzel.fileSize == -1)
			return fileSize;
		return fileSize + uzel.fileSize;
	}

	public String toStr() {
		String s = "";
		for (int i = 0; i < positions.length; i++) {
			s += i + " : I " + positions[i].I
					+" имя: "+ fileName +" размер: "+ fileSize + "\n";
		}
		return s;
	}

	public void setNode(Mesto[] ps, String name, int size) {
		uzel = new IndexUzel(ps,name, size);
	}

	public IndexUzel nextUzel() {
		return uzel;
	}
}
