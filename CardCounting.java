
public class CardCounting {
	static int running_count = 0; //running count on deck
	static double percent_adv;  //percent adv
	static double suggest_bet;
	
	//Adds count to running count and then calculates the percent advantage
	//and the suggested bet
	static void cardCount(Card c, double minBet) {
		if(c.getValue() == 10 || c.getVal().equals("A")) {
			running_count--;
		}
		else if(c.getValue()>= 2 && c.getValue() <= 6) {
			running_count++;
		}
		calcAdv();
		calcBet(minBet);
	}
	
	//resets the count on shuffle
	static void resetCount() {
		running_count = 0;
		suggest_bet = 0;
	}
	
	static void calcAdv() {
		percent_adv = running_count*.5;
	}
	
	static void calcBet(double minBet) {
		if(minBet > 0 && running_count > 0)
			suggest_bet = minBet * running_count;
	}
	
	//prints the count and percent advantage
	static void printCount() {
		System.out.println("\nCount: "+running_count);
		System.out.println("\nPercent Advantage: "+percent_adv);
	}
	
	//prints everything
	static void print() {
		System.out.println("\nCard Counting Information:");
		System.out.println("\nCount: "+running_count);
		System.out.println("\nPercent Advantage: "+percent_adv);
		System.out.printf("\nSuggested Bet: $%.2f",suggest_bet);
	}
}
