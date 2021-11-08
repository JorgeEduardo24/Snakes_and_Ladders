package ui;

import java.io.IOException;

public class Main {
	public static Menu menu;
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println("        ¡WELCOME!");
		menu = new Menu();
		menu.showMenu();
	}
}
