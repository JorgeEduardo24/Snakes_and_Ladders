package model;

public class Player {
	
	private Player parent;
    private Player next;
    private Player prev;
    private Player postPlayer;
    private Player postPlayerInNode;
    private String nickname;
    private int moves;
    private int score;
    private int position;
    private int dice;
    private int lastPosition;
    private char symbol;
    private boolean isWinner;


    public Player(char symbol, int lastPosition) {
        this.score = 0;
        this.symbol = symbol;
        this.moves = 0;
        this.position = 1;
        this.isWinner = false;
        this.nickname = "";
        this.lastPosition = lastPosition;
    }

    public Player getParent() {
        return this.parent;
    }

    public void setParent(Player parent) {
        this.parent = parent;
    }

    public Player getNext() {
        return this.next;
    }


    public void setNext(Player right) {
        this.next = right;
    }

    public Player getPrev() {
        return this.prev;
    }


    public void setPrev(Player left) {
        this.prev = left;
    }


    public Player getPostPlayer() {
        return this.postPlayer;
    }


    public void setPostPlayer(Player postPlayer) {
        this.postPlayer = postPlayer;
    }


    public String getNickname() {
        return this.nickname;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public char getSymbol() {
        return this.symbol;
    }


    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getMoves() {
        return this.moves;
    }


    public void setMoves(int moves) {
        this.moves = moves;
    }


    public int getScore() {
        return this.score;
    }


    public void setScore(int score) {
        this.score = score;
    }


    public Player getPostPlayerInNode() {
        return this.postPlayerInNode;
    }


    public void setPostPlayerInNode(Player p) {
        this.postPlayerInNode = p;
    }


    public int getLastPosition() {
        return this.lastPosition;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }


    public int getPosition() {
        return this.position;
    }


    public void setPosition(int position) {
        winner(position);
        this.position = position;
    }

    public int getDice() {
        return this.dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    
    public boolean getIsWinner() {
        return this.isWinner;
    }


    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }


    public void dice(int d) {
        dice = d;
        int result = position + d;
        winner(result);
        moves++;
        setPostPlayerInNode(null);
    }


    public void winner(int result) {
        if (result == lastPosition) {
            setIsWinner(true);
            position += dice;
        } else if (result < lastPosition) {
            position += dice;
        }
    }


    public String getPartner(Player first, String msg) {
        if (first.getPostPlayerInNode() != null) {
            msg += " " + first.getPostPlayerInNode().getSymbol();
            return getPartner(first.getPostPlayerInNode(), msg);
        } else {
            return msg;
        }
    }

    public String toString() {
        score = moves * lastPosition;
        return String.valueOf(score);
    }
}
