package utilities;

import graphSample.Node;

public class LifoQueue {
	private Node list[];
	private int lastIndex;
	
	public LifoQueue() {
		this.list = new Node[1];
		this.list[0] = null;
		this.lastIndex = 0;
	}
	
	public void push(Node n) {
		if(this.lastIndex == 0) {
			this.list[0] = n;
			this.lastIndex = this.lastIndex + 1;
		}
		else {
			Node newList[] = new Node[this.lastIndex+1];
			for(int i = this.lastIndex;i != 0;i--) {
				newList[i] = this.list[i-1];
			}
			newList[0] = n;
			this.list = newList;
			this.lastIndex = this.lastIndex + 1;
		}
		
	}
	
	public Node pop() {
		Node tmp = null;
		if(this.list[0] == null) {
			tmp = null;
		}
		else if(this.lastIndex - 1 == 0) {
			tmp = this.list[0];
			this.list[0] = null;
		}
		else {
			tmp = this.list[0];
			Node newList[] = new Node[this.list.length-1];
			for(int i = this.lastIndex-1;i != 0;i--) {
				newList[i-1] = this.list[i];
			}
			this.list = newList;
			this.lastIndex = this.lastIndex - 1;
		}
		return tmp;
	}
	
	public Node getFirstElement() {
		return this.list[0];
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
