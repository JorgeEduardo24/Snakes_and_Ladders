package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedMatrixTest {
	private LinkedMatrix linkedMatrix;
	
	public void setupScenary1() {
	}
	
	public void setupScenary2() {
		linkedMatrix = new LinkedMatrix(3,2);
	}
	
	public void setupScenary3() {
		
	}

	@Test
	public void testLinkedMatrix() {
		setupScenary1();
		linkedMatrix = new LinkedMatrix(5,4);
		assertTrue(linkedMatrix.getNumberOfColumns()==4);
	}
	
	@Test
	public void testLinkedMatrix2() {
		setupScenary1();
		linkedMatrix = new LinkedMatrix(8,6);
		assertTrue(linkedMatrix.getNumberOfRows()==8);
	}
	
	@Test
	public void testCreateRow(int i, int j, Node currentFirstRow) {
		
	}
	/*
	@Test
	public void testContactBook() {
		setupScenary1();
		ContactBook c = new ContactBook();
		
		assertTrue(c.getContacts().isEmpty());
	}
	*/
}
