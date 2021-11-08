package model;

public class Node {
	private int row;
	private int col;
	
	private Node next;
	private Node prev;
	private Node up;
	private Node down;
	
	public Node(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public char getNameCol() {
		return (char)('A'+this.col); 
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getUp() {
		return up;
	}

	public void setUp(Node up) {
		this.up = up;
	}

	public Node getDown() {
		return down;
	}

	public void setDown(Node down) {
		this.down = down;
	}
	
	public String toString() {
		return "[("+this.row+","+this.col+")]";
	}
}
