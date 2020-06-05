package laba4;

public class File {
	private int fileSize;
	private int fileId;
	private String fileName;

	private IndexNode indexNode = new IndexNode(); 

	public File(int fileId, int fileSize, String fileName) {
		this.fileId = fileId;
		this.fileSize = fileSize;
		this.fileName = fileName;
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

	public IndexNode getIndexNode() {
		return indexNode;
	}

	public void addBlock(int i) {
		indexNode.addBlock(i);
	}
}
