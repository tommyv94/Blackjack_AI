public class Dealer extends Player {
	
	public double money = 0.0;
	public int value = 0;
	
	
	public Dealer() {
		super(0);
	}
	
	public void addMoney(double p) {
		this.money += p;
	}
	
	@Override
	public void setValue() {
		int temp = 0;
		boolean ace = false;
		for(int i = 0; i < this.hand.size(); i++) {
			if(i == 1) continue;
			Card c = this.hand.get(i);
			if(c.getValue() == 11) {
				ace = true;
			}
			temp += c.getValue();
		}
		if(ace == true && temp > 21) {
			temp -= 10;
		}
		this.value = temp;
	}
	
	public int revealValue() {
		int temp = 0;
		for(int i = 0; i < this.hand.size(); i++) {
			Card c = this.hand.get(i);
			
			temp += c.getValue();
		}
		this.value = temp;
		return this.value;
	}
	
	public void revealHand() {
		for(int i = 0; i < this.hand.size(); i++) {
			Card c = this.hand.get(i);
			System.out.print(c.getSuit()+":"+c.getVal()+" ");
		}
		System.out.println();
	}
	
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
	
	public void go(Deck deck) throws InterruptedException {
		System.out.println("\nDealer's Turn\n");
		while(this.revealValue() < 17) {
			Card c = deck.Deal();
			this.addCard(c);
			this.revealHand();
			System.out.println(this.revealValue());
			System.out.println("\n");
			Thread.sleep(3000);
		}
		
		if(this.revealValue() > 21) {
			System.out.println("\nDealer Busts!!!");
		}
		if(this.revealValue() == 21) {
			System.out.println("\nDealer has Blackjack!!!");
		}
	}
}
