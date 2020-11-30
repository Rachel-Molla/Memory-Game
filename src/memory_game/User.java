package memory_game;

import java.util.Scanner;

public class User {

	Scanner input = new Scanner( System.in );
	
	String name;
	
	
	void setName( String name) {
			
		this.name = name;
		
	}
	
	// Overloaded setName() if user name is null
	void setName() {
			
		setName( "manager" );
		
	}
	
	
	String getName( String name ){
	
		return this.name;
	
	}
	

	int choosePositiveNumber( String numUse ) {
		
		int positiveNumInput;
		
		System.out.print( this.name +", please insert number of " + numUse + " you want: " );

		do {
			
			this.validIntInput(); // check that user insert integer 
				
			positiveNumInput = this.input.nextInt();
			
		} while ( ! isPositiveInt( positiveNumInput ) ); //check that user insert positive number
		
		return positiveNumInput;
	
	}
	
		
	void validIntInput() {
		
		while ( !this.input.hasNextInt() ) {
			
			System.out.print( "That's not a number, try again: " );
			
			this.input.next();
		}
		
	}
	
	
	boolean isPositiveInt( int integer) {
		
		if ( integer <= 0 ) {
			
			System.out.print("That's not a positive number, try again: ");
			
			return false;
		}
		
		return  true ;
	
	}
	

	String chooseString( String strUse ){
		
		String strInput;
		
		System.out.print( this.name + ", please insert " + strUse + ": " ) ;

		do {
			
			strInput = this.input.nextLine();

		} while ( strInput.isEmpty() ); // check that user insert string 

		return strInput;
	
	}	

	
	@Override
	public String toString() {
		
		return this.name ;
		
	}
	
	
}
