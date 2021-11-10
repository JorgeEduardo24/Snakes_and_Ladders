package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.LinkedMatrix;

public class Menu {
	private LinkedMatrix linkedMatrix;
	
	public Menu() {
	}
	
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
			
			System.out.println("Please, enter the number of rows:");
			int numberOfRows = Integer.parseInt(br.readLine());
			
			System.out.println("Please, enter the number of columns: ");
			int numberOfColumns = Integer.parseInt(br.readLine());
			
			System.out.println("Please, enter the number of snakes on the board: ");
			int snakes = Integer.parseInt(br.readLine());
			
			System.out.println("Please, enter the number of ladders on the board");
			int ladders = Integer.parseInt(br.readLine());
			
			System.out.println("Please, enter the number of players: ");
			int players = Integer.parseInt(br.readLine());
			
			linkedMatrix = new LinkedMatrix(numberOfRows, numberOfColumns);
			System.out.println(linkedMatrix);
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
	
	
	public void generateAutomatic(int numberOfRows, int numberOfColumns, int snakes, int ladders, int players) {
		if (players > 7 && players < 0) {
			System.err.println("Error. The game can have between 1 and 7 players");
			showMenu();
		} else {
			System.out.println("\n");
			System.out.println("Board successfully created !");
			createWorldAutomatic(parts);
		}
		assignAutomatic(Integer.parseInt(parts[4]), 0);
		System.out.println(world);
		initializeGame(false);
	}
	
	
	public void assignAutomatic(int players, int counter) {
		if (counter < players) {
			world.addPlayer(genrateSymbol(counter++));
			assignAutomatic(players, (counter++));
		}
	}
	
	public char genrateSymbol(int option) {
        char symbol = ' ';
        switch (option) {
            case 1:
                symbol = '*';
                break;
            case 2:
                symbol = '!';
                break;
            case 3:
                symbol = 'O';
                break;
            case 4:
                symbol = 'X';
                break;
            case 5:
                symbol = '%';
                break;
            case 6:
                symbol = '$';
                break;
            case 7:
                symbol = '#';
                break;
            case 8:
                symbol = '+';
                break;
            case 9:
                symbol = '&';
                break;
        }
        return symbol;
    }
	
	
}
