package utilities;
import graphSample.Node;

public class FifoQueue {
	private Node list[];
	private int nextIndex;
	
	public FifoQueue() {
		this.list = new Node[1];
		this.nextIndex = 0;
	}
	
	public void push(Node n) {
		Node[] tmp = this.list;
		if(this.nextIndex == 0) {
			tmp[this.nextIndex] = n;
			this.nextIndex = this.nextIndex + 1;
		}
		else {
			Node[] newList = new Node[this.nextIndex+1];
			for(int i = 0;i < tmp.length;i++) {
				newList[i] = tmp[i];
			}
			newList[this.nextIndex] = n;
			tmp = newList;
			this.nextIndex = this.nextIndex + 1;
		}
		this.list = tmp;
	}
	
	public Node pop() {
		Node tmp = null;
		if(this.nextIndex - 1 == 0) {
			tmp = this.list[0];
			this.list[0] = null;
		}
		else {
			tmp = this.list[0];
			Node[] newList = new Node[this.list.length-1];
			for(int i = 1;i < this.list.length;i++) {
				newList[i-1] = this.list[i];
			}
			this.list = newList;
			this.nextIndex = this.nextIndex - 1;
		}
		return tmp;
	}
	
	public int getLength() {
		return this.nextIndex;
	}
	
	public int getNodeLinksNumber(int i) {
		return this.list[i].getLinksNumber();
	}
	
	public void setNull(int index) {
		this.list[index] = null;
	}
		
	public void sortQueue() {
		Node tmp[] = new Node[this.list.length];
		int currentMin = 9999, currentMinIndex = 0;
		for(int i = 0;i < this.list.length;i++) {
			for(int j = 0;j < this.list.length;j++) {
				if((this.list[j] != null) && (this.list[j].getLinksNumber() < currentMin)) {
					currentMinIndex = j;
					currentMin = this.list[j].getLinksNumber();
				}
			}
			tmp[this.list.length - (i + 1)] = this.list[currentMinIndex];
			this.list[currentMinIndex] = null;
			currentMinIndex = 0;
			currentMin = 9999;
		}
		this.list = tmp;
	}
	
	public String toString() {
		if(this.list[0] == null) {
			return "The Queue is empty!\n";
		}
		else{
			String tmp = "Queue containing: \n\t";
			for(Node n: this.list) {
				tmp = tmp + n.toString()+"\n\t";
			}
			return tmp+"\n";
		}
	}	
}
