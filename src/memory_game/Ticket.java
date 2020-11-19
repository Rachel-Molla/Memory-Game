package memory_game;

public class Ticket {
	
	String front , back , visibleSide;
	
	int rowIndex , colIndex;
	
	boolean choosen = false ;
	
	
	public Ticket ( int rowIndex, int colIndex, String frontStr ) {
	
		this.rowIndex = rowIndex;
	
		this.colIndex = colIndex ;
		
		this.front = frontStr; 
		
		this.setVisibleSide();

	}
	
	
	void setVisibleSide() {
	
		this.visibleSide = this.front;		
		
	}
	
	
	String getFrontTicket() {
		
		return this.front ;
		
	}
	
	
	void setBackTicket( String backStr ) {
	
		this.back = backStr ;
	
	}
	
	
	String getBackTicket() {
		
		return this.back ;
		
	}
	
	
	int getTicketRowIndex() {
		
		return this.rowIndex;
		
	}
	

	int getTicketColIndex() {
		
		return this.colIndex;
	
	}
	

	void turnVisibleSide() {
		
		if ( this.visibleSide == this.front) {
			
			this.visibleSide = this.back;
			
		} else {
			
			this.visibleSide = this.front;
		}
		
		this.turnChoose();
	
	}
	
	
	void turnChoose() {
		 
		this.choosen = !this.choosen;
	
	}
	
	
	boolean isChoosen() {
		
		return this.choosen;
		
	}
	
	
	@Override
	public String toString() {
		
		return this.visibleSide; 
		
	}
	
	
}
