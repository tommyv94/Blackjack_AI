public class Dealer extends Player {
	
	public double money = 0.0;  //The dealer's money
	public int value = 0;  //The value of the Dealer's hand
	
	//Create the dealer by calling the player constructor
	public Dealer() {
		super(0);
	}
	
	//Adds money to the dealer's cash
	public void addMoney(double p) {
		this.money += p;
	}
	
	//Overrides the player's setValue.
	//Skips the hidden card in setting the value.
	//goes through every other card in hand and adds values up.
	@Override
	public void setValue() {
		int temp = 0;
		for(int i = 0; i < this.hand.size(); i++) {
			if(i == 1) continue;
			Card c = this.hand.get(i);
			temp += c.getValue();
		}
		this.value = temp;
	}
	
	//sets the value but with the hidden card this time
	public int revealValue() {
		int temp = 0;
		for(int i = 0; i < this.hand.size(); i++) {
			Card c = this.hand.get(i);
			
			temp += c.getValue();
		}
		this.value = temp;
		return this.value;
	}
	
	//Shows the dealer's hand including the value of the hidden card
	public void revealHand() {
		for(int i = 0; i < this.hand.size(); i++) {
			Card c = this.hand.get(i);
			System.out.print(c.getSuit()+":"+c.getVal()+" ");
		}
		System.out.println();
	}
	
	//Overrides the player's showHand
	//Shows the Dealer's cards but replaces the face down card
	//with ?
	@Override
	public void showHand() {
		System.out.println("\nDealer:");
		for(int i = 0; i < this.hand.size(); i++) {
			if(i == 1) {
				System.out.print("?  ");
			}
			else {
				Card c = this.hand.get(i);
				System.out.print(c.getSuit()+":"+c.getVal()+"  ");
			}
		}
		System.out.println("\n"+this.value);
	}
	
	//The dealer's turn to hit until he reaches 17 or higher
	public void go(Deck deck, Deck s) throws InterruptedException {
		System.out.println("\nDealer's Turn\n");
		while(this.revealValue() < 17) {
			Card c = deck.Deal(s);
			this.addCard(c);
			CardCounting.cardCount(c,0); //show card count
			this.revealHand();
			System.out.println(this.revealValue());
			System.out.println("\n");
			Thread.sleep(3000); //pause for 3 secs between hits
		}
		
		if(this.revealValue() > 21) {
			System.out.println("\nDealer Busts!!!");
		}
		if(this.revealValue() == 21) {
			System.out.println("\nDealer has Blackjack!!!");
		}
	}
}
