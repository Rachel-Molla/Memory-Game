package memory_game;

import java.util.Scanner;

public class Player {

	Scanner input = new Scanner( System.in );
	
	String name;
	
	int index;
	
	int score = 0;
	
	
	public Player( int index ) {
		
		this.index = index;
		
		this.setName( index + 1 );
		
	}
	
	
	void setName( int playerNumber ) {
		
		System.out.print( "Please insert player " + playerNumber + " name : " ) ;
		
		this.name = input.nextLine();
		
	}
	
	
	String getName(){
	
		return this.name;
	
	}
	
	
	int getPlayerNumber( String numUse ) {
		
		System.out.print( this.name +", please insert number of " + numUse + " you want : " );
		
		int playerNum = input.nextInt();
		
		return playerNum;
	
	}
	

	String getPlayerString( String strUse ){
		
		String playerStr;
		
		System.out.print( "Please insert string " + strUse + " : " ) ;

		do {
			
			playerStr = input.nextLine();

		} while ( playerStr.isEmpty() ); 

		return playerStr;
	
	}	
	

	void increaseScore() {
		
		this.score++ ;
		
	}
	
	
	@Override
	public String toString() {
		
		return this.name + " : " + this.score ;
		
	}
	
	
}
