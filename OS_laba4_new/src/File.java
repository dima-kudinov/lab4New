package laba4;

public class File {
	private int fileSize;
	private int fileId;
	private String fileName;

	private Uzel uzel;

	public File(int fileId, int fileSize, String fileName) {
		this.fileId = fileId;
		this.fileSize = fileSize;
		this.fileName=fileName;
	}
	public Uzel getUzel() {
		return uzel.nextUzel();
	}

	public int getId() {
		return fileId;
	}

	public int fileSize() {
		return fileSize;
	}
	public String fileName() {
		return fileName;
	}
}
