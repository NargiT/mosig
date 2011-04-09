package node.utils;

public enum NodeStatus {
	MASTER("MASTER"),
	MIDDLE("MIDLLE"),
	LEAF("LEAF");

	private String status;

	NodeStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return status;
	}
}
