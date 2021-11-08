package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
	
	public void showMenu() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\nEnter the option that you want to do: ");
		System.out.println("[1] Play snakes and ladders.\n"+
		                   "[2] Exit the program.\n");
		
		int option = Integer.parseInt(br.readLine());
		
		switch(option) {
		case 1:
			System.out.println("-------------------------------------------------------");
			System.out.println("|                  SNAKES AND LADDERS                  |");
			System.out.println("-------------------------------------------------------\n");
			
			
			showMenu();
			break;
			
		
		case 2:
			System.out.println("Bye! ;)");
			br.close();
			break;

		default:
			System.out.println("Wrong choice. Try again");
			showMenu();
			break;
			
		}
	}
	
}
