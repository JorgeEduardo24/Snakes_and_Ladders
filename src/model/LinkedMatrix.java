package model;

public class LinkedMatrix {
	private Node first;
	private int numberOfRows;
	private int numberOfColumns;

	public LinkedMatrix(int numberOfRows, int numberOfColumns) {
		this.numberOfRows = numberOfRows;
		this.numberOfColumns = numberOfColumns;
		createMatrix();
	}
	
	private void createMatrix() {
		first = new Node(0,0);
		createRow(0,0,first);
	}
	
	private void createRow(int i, int j, Node currentFirstRow) {
		createColumn(i,j+1,currentFirstRow,currentFirstRow.getUp());
		if(i+1<numberOfRows) {
			Node downFirstRow = new Node(i+1,j);
			downFirstRow.setUp(currentFirstRow);
			currentFirstRow.setDown(downFirstRow);
			createRow(i+1,j,downFirstRow);
		}
	}
	
	private void createColumn(int i, int j, Node prev, Node rowPrev) { //Entra el previo para que el siguiente se pueda enlazar con el anterior
		if(j<numberOfColumns) {
			Node current = new Node(i,j);
			current.setPrev(prev);
			prev.setNext(current);
			
			if(rowPrev!=null) {
				rowPrev = rowPrev.getNext();
				current.setUp(rowPrev);
				rowPrev.setDown(current);
			}
			createColumn(i,j+1,current,rowPrev.getNext()); //Current se vuelve el anterior del siguiente
		}
	}
}
