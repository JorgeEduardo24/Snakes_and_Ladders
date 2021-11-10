package model;

public class Node {
	private int row;
	private int col;
	
	private Node next;
	private Node prev;
	private Node up;
	private Node down;
	
	private Snakes snakes;
	private Ladders ladders;
	private Player players;
	
	private int id;
	private boolean visible;
	private boolean statusNode;
	
	public Node(int row, int col, int id) {
		this.row = row;
		this.col = col;
		this.id = id;
		visible = true;
		statusNode = false;
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
	
	
	public Snakes getSnakes() {
		return snakes;
	}

	public void setSnakes(Snakes snakes) {
		this.snakes = snakes;
	}

	public Ladders getLadders() {
		return ladders;
	}

	public void setLadders(Ladders ladders) {
		this.ladders = ladders;
	}

	public Player getPlayers() {
		return players;
	}

	public void setPlayers(Player players) {
		this.players = players;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isStatusNode() {
		return statusNode;
	}

	public void setStatusNode(boolean statusNode) {
		this.statusNode = statusNode;
	}

	public String toString() {
		return "[("+this.row+","+this.col+")]";
	}
}
