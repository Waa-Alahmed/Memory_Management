
public class Partition {
	
	String partitionStatus;
	int partitionSize;
	int startingAddress;
	int endingAddress;
	String processID;
	int internalFragmentation;
	
	public Partition(String partitionStatus, int partitionSize, int startingAddress, int endingAddress, String processID, int internalFragmentation) {
		this.partitionStatus = partitionStatus;
		this.partitionSize = partitionSize;
		this.startingAddress = startingAddress;
		this.endingAddress = endingAddress;
		this.processID = processID;
		this.internalFragmentation = internalFragmentation;
	}
	
	public void setPartitionStatus(String partitionStatus) {
		this.partitionStatus = partitionStatus;
	}
	public void setProcessID(String processID) {
		this.processID = processID;
	}
	public void setInternalFragmentation(int internalFragmentation) {
		this.internalFragmentation = internalFragmentation;
	}
}