package memory_game;

import java.util.Arrays;

import java.util.ArrayList;

public class MemoryGame {

	User manager = new User() ;
	
	int numOfPlayers;
	
	Player[] players;

	Player currentPlayer;
	
	GameBoard board;
	
	Ticket firstChoosenTicket , secondChoosenTicket ;
	
	
	void start() {
		
		setManager();
		
		setPlayers();
		
		setBoard();
		
		startPlay();
		
		gameFinish();
	
	}

	
	void setManager() {
		
		this.manager.setName();
		
	}
	
	
	void setPlayers() {
		
		this.setNumberOfPlayers();
		
		this.setPlayersList();
								
	}
	
	
	void setNumberOfPlayers() {
			
		this.numOfPlayers = this.manager.choosePositiveNumber( "players" );
				
	}

	
	void setPlayersList() {
		
		this.players = new Player[ this.numOfPlayers ];
		
		int playerNumber;
		
		String playerName;

		for ( int i = 0 ; i < this.numOfPlayers ; i++) {
			
			playerNumber = i + 1 ;
			
			playerName = this.manager.chooseString( "player number " + playerNumber + " name" ); 
			
			this.players[i] = new Player( playerNumber, playerName );
			
		}
				
	}
	
	
	void setBoard() {
	
		int rows, columns;
		
		System.out.println( "Memory-game board will contain even number of tickets. therefore number of rows or columns must be even.");

		do {
			
			// set rows
			rows = this.manager.choosePositiveNumber( "game board rows" );

			// set columns
			columns = this.manager.choosePositiveNumber( "game board columns" );
		
		} while ( ! validEvenNumberOfTickets ( rows, columns ) );

		String frontStr = this.manager.chooseString( "string that will fill the front side of board game tickets" );

		String[] backStrings = this.getBackStrings( rows, columns );
		
		// set the game board
		this.board = new GameBoard( rows, columns, frontStr, backStrings );
		
	}
	
	
	boolean validEvenNumberOfTickets( int numberOfRows, int numberOfColumns) {
		
		if ( numberOfRows * numberOfColumns % 2 != 0 ) {
			
			System.out.println( "there is not a even number of tickets, try again.");
			
			return false;
			
		}
		
		return true;
		
	}

	
	String[] getBackStrings( int rows, int columns ){
		
		int numOfStrings = ( rows * columns ) / 2 ;
		
		String[] backStrings = new String[ numOfStrings ];
		
		for ( int i = 0 ; i < numOfStrings ; i++ ) {

			backStrings[i] = this.manager.chooseString( "string that will fill the back side of pair tickets" ) ;
			
		}
				
		return backStrings;
		
	}

	
	void startPlay() {
	
		this.board.printGameBoard();
		
		this.printPlayers();

		while ( ! this.board.allTicketsOpposite() ) {
			
			for ( Player player : this.players ) { 
		
				this.setCurrentPlayer( player );
				
				this.currentPlayerTurn();
				
			}
			
		}
	
	}
	

	void setCurrentPlayer( Player player ) {
		
		this.currentPlayer = player;
	
	}
	
	
	void currentPlayerTurn() {
		
		if ( ! this.board.allTicketsOpposite() ) {
							
			this.currentPlayerPlay();
										
		}
		
	}
	
	
	void currentPlayerPlay() {
		
		this.chooseTwoTickets() ;
		
		this.checkChoosenTickets();
	
	}
	
	
	void chooseTwoTickets() {

		this.firstChoosenTicket = this.chooseOneTicket();
		this.secondChoosenTicket = this.chooseOneTicket();
		
	}
	
	
	Ticket chooseOneTicket() {
		
		Ticket currentChoosenTicket;
		
		currentChoosenTicket = this.chooseValidTicket();
		
		currentChoosenTicket.turnVisibleSide();
		
		this.board.printGameBoard();
		
		return currentChoosenTicket;
		
	}
	
	
	Ticket chooseValidTicket() {
		
		Ticket ticket;
		
		do {
 
			ticket = this.chooseTicket();
			
		} while (  isTicketChoosen( ticket ) );
				
		return ticket;
		
	}
	
	
	boolean isTicketChoosen( Ticket ticket ) {
		
		if ( ticket.isChoosen() ) {
			
			System.out.println( "This ticket already choosen. please choose another ticket.");
			
			return true; 
		}
		
		return false;
		
	}
	

	Ticket chooseTicket() {
		
		Ticket choosenTicket; 
				
		int rowTicket = this.ChooseTicketLocationComponent( "row" , this.board.rows );
		
		int colTicket = this.ChooseTicketLocationComponent( "column" , this.board.columns );
				
		choosenTicket = this.board.gameBoard[ rowTicket-1 ][ colTicket-1 ];
				
		return choosenTicket;
	
	}

	
	int ChooseTicketLocationComponent( String componentLocationName, int maxComponenetLocation ) {
		
		int choosenLocationComponent;
		
		do {
		
			choosenLocationComponent = this.currentPlayer.choosePositiveNumber( componentLocationName );
			
		} while ( ! numberInValidRange( choosenLocationComponent, componentLocationName, maxComponenetLocation  ) ) ;
		
		return choosenLocationComponent;
		
	}
	
	
	boolean numberInValidRange( int num,  String componentNumName,  int maxRange ) {
		
		if ( num < 1 || num > maxRange) {
			
			System.out.print( "That's not a valid " + componentNumName + ", try again. " );
			
			return false;
		}
		
		return true;
		
	}
	
	
	void checkChoosenTickets() {
		
		if ( equalTickets( this.firstChoosenTicket, this.secondChoosenTicket ) ) {
						
			this.currentPlayer.increaseScore();
			
			this.printPlayers();
			
			this.currentPlayerTurn();

		} else {
			
			this.firstChoosenTicket.turnVisibleSide();
			
			this.secondChoosenTicket.turnVisibleSide();

			this.board.printGameBoard();

		}
		
	}

	
	//check if two tickets equal. if they are, return true. if not return false.
	boolean equalTickets( Ticket first , Ticket second ) {

		return first.getBackTicket() == second.getBackTicket() ;
	
	}
	
	
	void printPlayers() {
			
		System.out.println( Arrays.toString( this.players ) );

	}
	
	
	int getHighestScore() {
		
		int highScore = 0 ;
		
		for ( Player player : this.players ) {
			
			if ( player.score > highScore ) { 

				highScore = player.score ;

			}

		}	
		
		return highScore;

	}
	
	
	ArrayList<String> getWinnersNames() {
		
		int highestScore = getHighestScore() ;
		
		ArrayList<String> winnersNames = new ArrayList<String>() ;  
		
		for ( Player player : this.players ) {
			
			if ( player.score == highestScore) {
				
				winnersNames.add( player.name );
				
			}
			
		}
		
		return winnersNames;

	}
	
	
	void gameFinish() {
		
		System.out.println( "\nCongratulations! " +  this.getWinnersNames() + ", you win.");
		
	}
	
}
