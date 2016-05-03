import java.util.*;

public class Player {
	Vector<Card> hand;
	int value;
	double money;
	
	public Player() {
		this.hand = new Vector<Card>();
		this.value = 0;
		this.money = 0;
	}
	
	public Player(double money) {
		this.hand = new Vector<Card>();
		this.value = 0;
		this.money = money;
	}
	
	public void addMoney(double m) {
		this.money += m;
	}
	
	public void addCard(Card c) {
		hand.add(c);
		this.setValue();
	}
	
	public double placeBet(Scanner sc) {
		System.out.print("\nPlace your bet: ");
		double bet = sc.nextDouble();
		money -= bet;
		return bet;
	}
	
	public void showHand() {
		for(int i = 0; i < hand.size(); i++) {
			Card c = hand.get(i);
			System.out.print(c.getSuit()+":"+c.getVal()+"  ");
		}
		System.out.println("\n"+this.value);
	}
	
	public void go(Deck deck, Scanner sc, Dealer dealer) throws InterruptedException {
		while(value <= 21) {
			System.out.println("\nhit or stand?");
			String command = sc.nextLine();
			if(command.equals("stand")) {
				break;
			}
			else if(command.equals("hit")) {
				Card c = deck.Deal();
				this.addCard(c);
				System.out.println("\nYou:");
				this.showHand();
				dealer.showHand();
			}
		}
		if(value > 21) {
			System.out.println("\nYou Bust!!!");
		}
		if(value == 21) {
			System.out.println("\nBlackjack!!!");
		}
	}
	
	public double getMoney() {
		return this.money;
	}
	
	public int getValue() {
		return this.value;
	}
		
	public void setValue() {
		int temp = 0;
		boolean ace = false;
		for(int i = 0; i < hand.size(); i++) {
			Card c = hand.get(i);
			if(c.getValue() == 11) {
				ace = true;
			}
			temp +=c.getValue();
		}
		if(ace == true && temp > 21) {
			temp -= 10;
		}
		this.value = temp; 
	}

	public Vector<Card> getHand() {
		return hand;
	}

	public void returnHand() {
		this.hand.clear();
		this.value = 0;
		
	}
	
}
