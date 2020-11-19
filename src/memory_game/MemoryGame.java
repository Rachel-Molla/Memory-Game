package memory_game;

import java.util.Arrays;
import java.util.Scanner;

public class MemoryGame {

	Scanner input = new Scanner( System.in );
	
	int numOfPlayers;
	
	Player[] players;

	Player currentPlayer;
	
	GameBoard board;
	
	Ticket[] twoTickets = new Ticket[2];

	
	public MemoryGame() {
		
	}
	
	
	void start() {
		
		setPlayers();
		
		setBoard();
		
		startPlay();
		
		gameFinish();
	
	}

	
	void setPlayers() {
		
		setNumberOfPlayers();
		
		setPlayersList();
		
		setNamePlayers();
		
		setCurrentPlayer( this.players[0] );
				
	}
	
	
	void setNumberOfPlayers() {
		
		System.out.print( "Please insert number of players : " );
		
		this.numOfPlayers = input.nextInt();
				
	}
	
	
	void setPlayersList() {
		
		this.players = new Player[ numOfPlayers ];
		
	}
	
	
	void setNamePlayers() {
				
		for ( int i = 0 ; i < this.numOfPlayers ; i++) {
			
			this.players[i] = new Player( i );
	
		}
		
		printPlayers();
				
	}
	
	
	void setCurrentPlayer( Player player) {
		
		this.currentPlayer = player;
	
	}
	
	
	void setBoard() {
	
		int rows, columns;
		// set rows
		rows = this.currentPlayer.getPlayerNumber( "rows" );

		// set columns
		columns = this.currentPlayer.getPlayerNumber( "columns" );
		
		String frontStr = this.currentPlayer.getPlayerString( "that will fill the front tickets" ) ;

		String[] backStrings = this.getBackStrings();
		
		// set the game board
		this.board = new GameBoard( rows, columns, frontStr, backStrings );
				
	}

	
	String[] getBackStrings(){
		
		int numOfStrings = ( this.board.rows * this.board.columns ) / 2 ;
		
		String[] backStrings = new String[ numOfStrings ];
		
		for ( int i = 0 ; i < numOfStrings ; i++ ) {

			backStrings[i] = this.currentPlayer.getPlayerString( "that will fill the back ticket" ) ;
			
		}
		
		System.out.println( Arrays.toString( backStrings ) );
		
		return backStrings;
		
	}

	
	void startPlay() {
	
		for ( Player player : this.players ) { 
		
			setCurrentPlayer( player );
			play();
				
			if  ( this.board.fullBoard() != true)  
				
				break;
		
		}
	
	}
	

	void play() {
		
		Ticket firstTicket , secondTicket ;
		
		do {
			
			chooseTwoTickets() ;
			
			firstTicket = this.twoTickets[0];
			secondTicket =  this.twoTickets[1];
			
			checkTickets( firstTicket, secondTicket);
		
		} while ( this.equalTickets( firstTicket, secondTicket ) &&  this.board.fullBoard() != true );
				
	}
		
	
	void chooseTwoTickets() {

		for ( int i = 0 ; i < 2 ; i++ ) {
			
			this.twoTickets[i] = chooseValidTicket() ;
		
			this.twoTickets[i].turnVisibleSide();

			this.board.printGameBoard();

		}
		
	}
	
	
	Ticket chooseValidTicket() {
		
		Ticket ticket;
		
		do {
 
			ticket = chooseTicket();
			
		} while (  ticket.isChoosen() );
				
		return ticket;
		
	}

	
	Ticket chooseTicket() {
		
		Ticket choosenTicket; 
		
		System.out.println( "\nchoose ticket row between 1 to " + this.board.rows + " : ");
		
		int rowTicket = this.currentPlayer.getPlayerNumber( "row" );
		
		System.out.println( "\nchoose ticket column between 1 to " + this.board.columns + " : " ); 

		int colTicket = this.currentPlayer.getPlayerNumber( "column" );
				
		choosenTicket = this.board.gameBoard[ rowTicket-1 ][ colTicket-1 ];
				
		return choosenTicket;
	
	}

	
	void checkTickets( Ticket firstTicket , Ticket secondTicket ) {
		
		if ( equalTickets( firstTicket, secondTicket) ) {
						
			this.currentPlayer.increaseScore();
			
		} else {
			
			firstTicket.turnVisibleSide();
			
			secondTicket.turnVisibleSide();
			
		}
						
		this.board.printGameBoard();
		
		printPlayers();
		
	}

	
	//check if two tickets equal. if they are, return true. if not return false.
	boolean equalTickets( Ticket first , Ticket second ) {

		return first.getBackTicket() == second.getBackTicket() ;
	
	}
	
	
	void printPlayers() {
			
		System.out.println( Arrays.toString( this.players ) );

	}

	
	Player getWinner() {
		
		Player winner = players[0];
		
		for ( int i = 1 ; i < numOfPlayers ; i++) {
			
			if ( players[i].score > winner.score ) { 

				winner = players[i];

			}

		}	
		
		return winner;

	}
	
	
	void gameFinish() {
		
		System.out.println( "Congratulations! " +  getWinner().name + ", you win.");

	}
	
}
