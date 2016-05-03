public class Card {
	
	private String suit;
	private String val;
	private int value;
	
	public Card(String suit, String val) {
		this.suit = suit;
		this.val = val;
		if(val.equals("J") || val.equals("Q") || val.equals("K")) {
			this.value = 10;
		}
		else if(val.equals("A")) {
			this.value = 11;
		}
		else {
			this.value = Integer.parseInt(val);
		}
	}
	
	public String getVal() {
		return val;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getSuit() {
		return suit;
	}

}
