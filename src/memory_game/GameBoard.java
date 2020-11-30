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
		
		this.setEmptyBoard();

		this.setTickets( frontStr );
						
		this.setBackTickets( backStrArr );
		
	}
	

	void setEmptyBoard() {

		// create two Dimensional Array
		this.gameBoard = new Ticket[ this.rows ][ this.columns ];


	}
	

	//print two Dimensional Array
	void printGameBoard(){
	
		System.out.println();

		for ( Ticket[] innerArr : this.gameBoard) {
			
			System.out.println( Arrays.toString( innerArr ) );
		
		}
		
		System.out.println();

	}
	
	
	void setTickets( String frontStr ) {

		for ( int i = 0 ; i < this.rows ; i++ ) {
			
			for ( int j = 0 ; j < this.columns ; j++ ) 
		
				this.gameBoard[i][j] = new Ticket( i, j ,frontStr );
			
		}
		
	}
	
	
	void setBackTickets(String[] strArr) {
		
		int numOfStr = strArr.length;
		
		int randRow , randCol; 
				
		for ( int i = 0 ; i < numOfStr ; i++ ) {
			
			String currentStr = strArr[i];
			
			for ( int j = 0; j < 2 ; j++ ) {
				
				do {
					
					randRow = this.randomNum( rows );
					randCol = this.randomNum( columns );
										
				} while ( this.gameBoard[ randRow ][ randCol ].getBackTicket() !=  null );
				
				this.gameBoard[ randRow ][ randCol ].setBackTicket( currentStr ) ;
				
			}
		}
	}
	
	
	int randomNum( int maxNum ) {
		
		return this.rnd.nextInt( maxNum );
		
	}

	
	boolean allTicketsOpposite() {
		
		for ( Ticket[]  innerArr : this.gameBoard ) {
			
			for ( Ticket ticket : innerArr) {
			
				if ( ! ticket.isChoosen() )
			
					return false;
			
			}
			
		}
		
		return true;

	}	
	
	
}
