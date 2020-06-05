

public class DiskBlock {
	private int fileId;
	private boolean selectFlag;	

	public DiskBlock(int fileId) {
		this.fileId = fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public boolean getSelectFlag(boolean selectFlag) {
		this.selectFlag = selectFlag;
	}

	public boolean  setSelectFlag() {
		return selectFlag;
	}

	public int getFileId() {
		return fileId;
	}
}
