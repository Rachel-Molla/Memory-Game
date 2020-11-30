package memory_game;

public class Player extends User {
	
	int playerNumber;
	
	int score = 0;
	
	
	public Player( int playerNumber, String name ) {
		
		this.playerNumber = playerNumber ;
		
		this.name = name;
						
	}
	
	
	void increaseScore() {
		
		this.score++ ;
		
	}
	
	
	@Override
	public String toString() {
		
		return "player " + this.playerNumber + " - " + this.name + " : " + this.score ;
		
	}
	
	
}
