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
			generateAutomatic(br, numberOfRows, numberOfColumns, snakes, ladders, players);
            System.out.print("\n");
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
	
    // ---------------------------------------------AUTOMATIC-PLAYERS-----------------------------------------

    public void generateAutomatic(BufferedReader br, int numberOfRows, int numberOfColumns, int snakes, int ladders, int players) throws NumberFormatException, IOException {
		if (players > 7 && players < 0) {
			System.err.println("Error. The game can have between 1 and 7 players");
			showMenu();
		} else {
			System.out.println("\n");
			System.out.println("Board successfully created !");
			createWorldAutomatic(numberOfRows, numberOfColumns);
		}
		assignAutomatic(players, 0);
		System.out.println(linkedMatrix);
	}


    public void assignAutomatic(int players, int counter) {
		if (counter < players) {
			linkedMatrix.addPlayer(genrateSymbol(counter++));
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

   
    public void createWorldAutomatic(int numberOfRows, int numberOfColumns) {
    	linkedMatrix = new LinkedMatrix(numberOfRows, numberOfColumns);
    }

    // ----------------------------------GAME-SIMULATION-------------------------------------------------------

    public void gameSimulation(BufferedReader br) throws IOException {
        if (!linkedMatrix.isFinished()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(linkedMatrix.generateDice());
            System.out.println(linkedMatrix);
            gameSimulation(br);

        } else {
            showWinner(br);
        }
    }

    // -------------------------------------------------WINNERS---------------------------------------------------

    public void showWinner(BufferedReader br) throws IOException {
        System.out.println("\033[032m¡EL JUGADOR " + linkedMatrix.getActual().getSymbol() + " HA GANADO CON UN TOTAL DE "
                + linkedMatrix.getActual().getMoves() + " MOVIMIENTOS!" + "\033[0m");
        calculateWinner(br);
    }


    public void calculateWinner(BufferedReader br) throws IOException {
        System.out.print("Por favor ingrese su nickname: ");
        String nickname = br.readLine();
        System.out.print("\n");
        if (nickname.equals("")) {
            System.err.println("Su nickname no puede estar vacio, por favor, vuelva a intentarlo!\n");
            calculateWinner(br);
        } else {
            linkedMatrix.getActual().setNickname(nickname);
            try {
            	linkedMatrix.addWinner(linkedMatrix.getActual());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
}
