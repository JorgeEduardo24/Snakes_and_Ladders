package model;

import java.io.IOException;

public class LinkedMatrix {
	private Node first;
	private int numberOfRows;
	private int numberOfColumns;
	
	private Node firstNode;
    private Player actual;
    private Player one;
    private Player root;
    private String message = "";
    private int amountSnakes;
    private int amountLadders;
    private int amountPlayers;
    private int sizeMatrix;
    private boolean visible;
    private boolean finished;

	public LinkedMatrix(int numberOfRows, int numberOfColumns) {
		this.numberOfRows = numberOfRows;
		this.numberOfColumns = numberOfColumns;
		createMatrix();
	}
	
	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	public void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}
	
	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public Node getFirstNode() {
		return firstNode;
	}

	public void setFirstNode(Node firstNode) {
		this.firstNode = firstNode;
	}

	public Player getActual() {
		return actual;
	}

	public void setActual(Player actual) {
		this.actual = actual;
	}

	public Player getOne() {
		return one;
	}

	public void setOne(Player one) {
		this.one = one;
	}

	public Player getRoot() {
		return root;
	}

	public void setRoot(Player root) {
		this.root = root;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getAmountSnakes() {
		return amountSnakes;
	}

	public void setAmountSnakes(int amountSnakes) {
		this.amountSnakes = amountSnakes;
	}

	public int getAmountLadders() {
		return amountLadders;
	}

	public void setAmountLadders(int amountLadders) {
		this.amountLadders = amountLadders;
	}

	public int getAmountPlayers() {
		return amountPlayers;
	}

	public void setAmountPlayers(int amountPlayers) {
		this.amountPlayers = amountPlayers;
	}

	public int getSizeMatrix() {
		return sizeMatrix;
	}

	public void setSizeMatrix(int sizeMatrix) {
		this.sizeMatrix = sizeMatrix;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	private void createMatrix() {
		first = new Node(0,0,0);
		createRow(0,0,first);
	}
	
	private void createRow(int i, int j, Node currentFirstRow) {
		createColumn(i,j+1,currentFirstRow,currentFirstRow.getUp());
		if(i+1<numberOfRows) {
			Node downFirstRow = new Node(i+1,j,0);
			downFirstRow.setUp(currentFirstRow);
			currentFirstRow.setDown(downFirstRow);
			createRow(i+1,j,downFirstRow);
		}
	}
	
	private void createColumn(int i, int j, Node prev, Node rowPrev) { //Entra el previo para que el siguiente se pueda enlazar con el anterior
		if(j<numberOfColumns) {
			//System.out.println("  en createCol con la columna "+j);
			Node current = new Node(i,j,0);
			current.setPrev(prev);
			prev.setNext(current);
			
			if(rowPrev!=null) {
				rowPrev = rowPrev.getNext();
				current.setUp(rowPrev);
				rowPrev.setDown(current);
			}
			createColumn(i,j+1,current,rowPrev); //Current se vuelve el anterior del siguiente
		}
	}
	
	public String toString() {
		String message;
		message = toStringRow(first);
		return message;
	}

	private String toStringRow(Node firstRow) {
		String message = "";
		if(firstRow!=null) {
			message = toStringCol(firstRow)+"\n";
			message += toStringRow(firstRow.getDown());
		}
		return message;
	}

	private String toStringCol(Node current) {
		String message = "";
		if(current!=null) {
			message = current.toString();
			message += toStringCol(current.getNext());
		}
		return message;
	}
	
	
	public String toString2() {
		String message = "";
		return message;
	}
	
    public void matrixEnum(Node firstNode) {
        matrixFirstRow(firstNode);
    }


    public void matrixFirstRow(Node firstRow) {
        if (firstRow.getDown() != null) {
            matrixFirstRow(firstRow.getDown());
        } else {
            firstRow.setId(1);
            matrixRightRow(firstRow);
        }
    }


    public void matrixRightRow(Node rightRow) {
        if (rightRow.getNext() != null) {
            rightRow.getNext().setId(rightRow.getId() + 1);
            matrixRightRow(rightRow.getNext());
        } else if (rightRow.getUp() != null) {
            rightRow.getUp().setId(rightRow.getId() + 1);
            matrixLeftRow(rightRow.getUp());
        }
    }


    public void matrixLeftRow(Node leftRow) {
        if (leftRow.getPrev() != null) {
            leftRow.getPrev().setId(leftRow.getId() + 1);
            matrixLeftRow(leftRow.getPrev());
        } else if (leftRow.getUp() != null) {
            leftRow.getUp().setId(leftRow.getId() + 1);
            matrixRightRow(leftRow.getUp());
        }
    }

    // ----------------------------------------------SNAKES-------------------------------------------------------

    public void generateSnakes(int snakes, int control, char snakeName) {

        int idHead = (int) (Math.random() * (sizeMatrix) + 1);
        int idEnd = (int) (Math.random() * (sizeMatrix) + 1);

        if (control < snakes) {
            if (idHead != sizeMatrix && idHead - idEnd > numberOfColumns) {
                Node nodeHead = searchNode(idHead, firstNode, firstNode);
                Node nodeEnd = searchNode(idEnd, firstNode, firstNode);
                Snakes newSnake = new Snakes(snakeName);
                if (nodeHead != null && nodeEnd != null) {
                    nodeHead.setSnakes(newSnake);
                    nodeEnd.setSnakes(newSnake);
                    newSnake.setStart(nodeHead);
                    newSnake.setEnd(nodeEnd);

                    generateSnakes(snakes, control + 1, (char) (snakeName + 1));

                } else {
                    generateSnakes(snakes, control, snakeName);
                }

            } else {
                generateSnakes(snakes, control, snakeName);
            }
        }
    }

    
    public Node validateSnakes(Node current, int id) {
        if (current.getSnakes().getStart().getId() == id) {
            System.out.println("El jugador " + actual.getSymbol()
                    + " cayó en el principio de una serpiente, retrocederá a la casilla "
                    + current.getSnakes().getEnd().getId());
            return current.getSnakes().getEnd();
        } else {
            return current;
        }
    }


    public Node searchNode(int id, Node current, Node firstRow) {
        if (current.getId() == id && current.isStatusNode() == false) {
            return current;
        } else if (current.getNext() != null) {
            return searchNode(id, current.getNext(), firstRow);
        } else if (firstRow.getDown() != null)
            return searchNode(id, firstRow.getDown(), firstRow.getDown());
        else {
            return null;
        }
    }

	// ----------------------------------------VALIDATION-SNAKE-AND-LADDERS-----------------------------------

	public Node validateSnakesOrLadders(Node after) {
		if (after.getSnakes() != null) {
			return validateSnakes(after, after.getId());
		} else if (after.getLadders() != null) {
			return validateLadders(after, after.getId());
		} else {
			return after;
		}
	}

	// -----------------------------------------------LADDERS-----------------------------------------------------

	public void generateLadders(int ladders, int control, int ladderName) {

		int idHead = (int) (Math.random() * (sizeMatrix) + 1);
		int idEnd = (int) (Math.random() * (sizeMatrix) + 1);

		if (control < ladders) {
			if (idEnd != 1 && idHead - idEnd > numberOfColumns) {
				Node nodeHead = searchNode(idHead, firstNode, firstNode);
				Node nodeEnd = searchNode(idEnd, firstNode, firstNode);
				Ladders newLadder = new Ladders(ladderName);
				if (nodeHead != null && nodeEnd != null) {
					nodeHead.setLadders(newLadder);
					nodeEnd.setLadders(newLadder);
					newLadder.setStart(nodeHead);
					newLadder.setEnd(nodeEnd);

					generateLadders(ladders, control + 1, (ladderName + 1));
				} else {
					generateLadders(ladders, control, ladderName);
				}

			} else {
				generateLadders(ladders, control, ladderName);
			}
		}
	}

	public Node validateLadders(Node current, int id) {
		if (current.getLadders().getEnd().getId() == id) {
			System.out.println(
					"El jugador " + actual.getSymbol() + " cayó en el final de una escalera, avanzará a la casilla "
							+ current.getLadders().getStart().getId());
			return current.getLadders().getStart();
		} else {
			return current;
		}
	}

	public String generateDice() {
		Player player = changeActualPlayer(actual);
		String msg = "";
		int valorEntero = (int) Math.floor(Math.random() * (6) + 1);
		Node before = searchNodePosition(actual.getPosition(), firstNode, firstNode);
		setNodeBefore(before);
		actual.dice(valorEntero);
		Node after = searchNodePosition(actual.getPosition(), firstNode, firstNode);
		Node validate = validateSnakesOrLadders(after);

		if (validate.getPlayers() == null) {
			actual.setPosition(validate.getId());
			validate.setPlayers(actual);
		} else {
			actual.setPosition(validate.getId());
			setInBox(actual, validate.getPlayers());
		}

		if (actual.getIsWinner() == true) {
			player = actual;
			setFinished(true);
		}

		msg = "El jugador " + actual.getSymbol() + " ha lanzado el dado y ha obtenido: " + valorEntero;
		actual = player;

		return msg;
	}

	public void addPlayer(char letter) {
		Player p = new Player(letter, sizeMatrix);
		addPlayer(p);
	}

	public void addPlayer(Player player) {
		if (one == null) {
			one = player;
			actual = player;
			setPlayerInNode(player, firstNode, firstNode);
		} else {
			addPlayer(one, player);
		}
	}

	private void addPlayer(Player current, Player newPlayer) {
		if (current.getPostPlayer() == null) {
			current.setPostPlayer(newPlayer);
			setPlayerInNode(newPlayer, firstNode, firstNode);
		} else {
			addPlayer(current.getPostPlayer(), newPlayer);
		}
	}

	public Node searchNodePosition(int id, Node current, Node firstRow) {
		if (current.getId() == id) {
			return current;
		} else if (current.getNext() != null) {
			return searchNodePosition(id, current.getNext(), firstRow);
		} else if (firstRow.getDown() != null)
			return searchNodePosition(id, firstRow.getDown(), firstRow.getDown());
		else {
			return null;
		}
	}

	public void setPlayerInNode(Player player, Node current, Node firstRow) {
		Node node = searchNodePosition(player.getPosition(), firstNode, firstNode);
		if (node.getPlayers() == null) {
			node.setPlayers(player);
		} else {
			setInBox(player, node.getPlayers());
		}
	}

	public void setInBox(Player player, Player firstPlayer) {
		if (firstPlayer.getPostPlayerInNode() != null) {
			setInBox(player, firstPlayer.getPostPlayerInNode());
		} else {
			firstPlayer.setPostPlayerInNode(player);
		}
	}

	public void setNodeBefore(Node before) {
		if (actual.getPostPlayerInNode() != null) {
			before.setPlayers(actual.getPostPlayerInNode());
		} else {
			before.setPlayers(null);
		}
	}

	public Player changeActualPlayer(Player player) {
		if (player.getPostPlayer() != null) {
			return player.getPostPlayer();
		} else {
			return one;
		}
	}

	public void addWinner(Player player) throws ClassNotFoundException, IOException {
		if (root == null) {
			root = player;
		} else {
			addWinner(root, player);
		}
	}

	private void addWinner(Player current, Player newWinner) {
		if (newWinner.getScore() <= current.getScore()) {
			if (current.getPrev() == null) {
				current.setPrev(newWinner);
			} else {
				addWinner(current.getPrev(), newWinner);
			}
		} else {
			if (current.getNext() == null) {
				current.setNext(newWinner);
			} else {
				addWinner(current.getNext(), newWinner);
			}
		}
	}

	public void printWinners() {
		if (root != null) {
			printWinners(root);
		} else {
			message = "¡No hay jugadores aún!";
		}
	}

	private void printWinners(Player player) {
		if (player == null) {
			return;
		} else {
			printWinners(player.getPrev());
			message += "      " + player.getNickname() + "                 " + player.getSymbol() + "                 "
					+ player.toString() + "\n";
			printWinners(player.getNext());
		}
	}
	
	
	
	
}
