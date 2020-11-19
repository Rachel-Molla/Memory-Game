package memory_game;

import java.util.Arrays;

import java.util.Random;

public class GameBoard {

	Ticket[][] gameBoard;	
	
	int rows, columns;

	Random rnd = new Random();
	
	
	public GameBoard( int rows, int columns, String frontStr, String[] backStrArr ) {

		// set number of rows
		this.rows = rows;

		// set number of columns
		this.columns = columns;
		
		this.setGameBoard( frontStr, backStrArr );

	}
	
	
	void setGameBoard( String frontStr, String[] backStrArr ) {
		
		setEmptyBoard();

		printGameBoard();

		setTickets( frontStr );

		printGameBoard();
						
		setBackTickets( backStrArr );
		
		printGameBoard();
	}
	

	void setEmptyBoard() {

		// create two Dimensional Array
		this.gameBoard = new Ticket[ this.rows ][ this.columns ];


	}
	
	
	int getNumOfRows() {
		
		return this.rows;
		
	}
	
	
	int getNumOfCols() {
		
		return this.columns;
		
	}	
	
	//print two Dimensional Array
	void printGameBoard(){
	
		for ( Ticket[] innerArr : gameBoard) 
			
			System.out.println( Arrays.toString( innerArr ));
		
		System.out.println();
		
	}
	
	
	void setTickets( String frontStr ) {

		for ( int i = 0 ; i < rows ; i++ ) {
			
			for ( int j = 0 ; j < columns ; j++ ) 
		
				gameBoard[i][j] = new Ticket( i, j ,frontStr );
			
		}
		
	}
	
	
	int randomNum( int maxNum ) {
		
		return rnd.nextInt( maxNum );
		
	}

	
	void setBackTickets(String[] strArr) {
		
		int numOfStr = strArr.length;
		
		int randRow , randCol; 
				
		for ( int i = 0 ; i < numOfStr ; i++ ) {
			
			String currentStr = strArr[i];
			
			for ( int j = 0; j < 2 ; j++ ) {
				
				do {
					
					randRow = randomNum( rows );
					randCol = randomNum( columns );
										
				} while ( gameBoard[ randRow ][ randCol ].getBackTicket() !=  null );
				
				gameBoard[ randRow ][ randCol ].setBackTicket( currentStr ) ;
				
			}
		}
	}
	
	
	
	
	void checkTickets( Ticket firstTicket , Ticket secondTicket ) {
		
					
		} else {
			
			firstTicket.turnVisibleSide();
			
			secondTicket.turnVisibleSide();
			
		}
						
		printGameBoard();
				
	}



	//check if two tickets equal. if they are, return true. if not return false.
	boolean equalTickets( Ticket first , Ticket second ) {

		return first.getBackTicket() == second.getBackTicket() ;
	
	}
	
	
	
	boolean fullBoard() {
		
		for ( Ticket[]  innerArr : gameBoard ) {
			for ( Ticket ticket : innerArr) {
			
				if ( ! ticket.isChoosen() )
					return false;
			
			}
		}
		
		return true;

	}	
	
}
