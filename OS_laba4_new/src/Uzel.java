package laba4;

public class Uzel {
	private int uzelId;
	private int fileId;
	private boolean selectFlag;	
	private Uzel uzel;

	public Uzel(int fileId, int uzelId) {
		this.fileId = fileId;
		this.uzelId = uzelId;
	}

	public void setFile(int fileId) {
		this.fileId = fileId;
	}

	public void setUzel(int fileId, int knotId) {
		uzel = new Uzel(fileId, knotId);
	}

	public Uzel nextUzel() {
		return uzel;
	}

	public int getUzelId() {
		return uzelId;
	}

	public boolean getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(boolean selectFlag) {
		this.selectFlag = selectFlag;
	}

	public int getFileId() {
		return fileId;
	}
}
