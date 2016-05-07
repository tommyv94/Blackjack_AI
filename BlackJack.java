import java.util.*;


public class BlackJack {
	
	static Scanner sc = new Scanner(System.in);
	static double pot = 0.0; //pot
	
	//displays the player and dealer's hands, values and money
	public static void Display(Player player, Player dealer) {
		System.out.println("Player:");
		player.showHand();
		System.out.printf("Money: $%.2f",player.getMoney());
		
		dealer.showHand();
	}
	
	//determines who won and distributes winnings properly
	public static void Results(Player player, Dealer dealer) {
		int playerValue = player.getValue();
		int dealerValue = dealer.revealValue();
		
		
		if(playerValue < 21 && dealerValue < 21) {
			if(playerValue < dealerValue) {
				dealer.addMoney(pot);
				pot = 0.0;
				System.out.println("You Lose, House Wins");
			}
			else if(playerValue > dealerValue) {
				player.addMoney(pot);
				pot = 0.0;
				System.out.println("You Win, House Loses");
			}
			else {
				System.out.println("Tie. It's A Push");
			}
		}
		else if(playerValue > 21 && dealerValue > 21) {
			dealer.addMoney(pot);
			pot = 0.0;
			System.out.println("Both Bust, House Wins");
		}
		else if(playerValue > 21 && dealerValue < 21) {
			dealer.addMoney(pot);
			pot = 0.0;
			System.out.println("You Lose, House Wins");
		}
		else if(playerValue < 21 && dealerValue > 21) {
			player.addMoney(pot);
			pot = 0.0;
			System.out.println("You Win, House Loses");
		}
		else if(playerValue == 21 && dealerValue == 21){
			//blackjack wins 3:2
			if(player.getHand().size() == 2 && dealer.getHand().size() != 2) {
				double pay = (pot/2)*3;
				player.addMoney(pay);
				pot = 0.0;
				System.out.println("Blackjack. You Win, House Loses");
			}
			else if(player.getHand().size() != 2 && dealer.getHand().size() == 2) {
				double pay = (pot/2)*3;
				dealer.addMoney(pay);
				pot = 0.0;
				System.out.println("Blackjack. You Lose, House Wins");
			}
			else {
				System.out.println("Tie. It's A Push");
			}
		}
		winDisplay(player, dealer); //displays winner information
	}
	
	//reveals all of the dealer's information as well as the player's
	private static void winDisplay(Player player, Dealer dealer) {
		System.out.println("\nPlayer:");
		player.showHand();
		System.out.printf("Money: $%.2f",player.getMoney());
		
		System.out.println("\nDealer:");
		dealer.revealHand();
		System.out.println(dealer.revealValue());
	}

	public static void main(String[] args) throws InterruptedException {
		Deck deck = new Deck(); //main deck
		Deck side = new Deck(0); //side deck
		int round = 1; //round #
		
		Dealer dealer = new Dealer();  //dealer
		System.out.println("Please enter your buy in: ");
		double m = sc.nextDouble();  //your buy in
		
		Player player = new Player(m); //player created with buy in
		while(player.getMoney() > 0.0) {
			System.out.println("\nStarting Round " + round+"...\n");
			
			Display(player, dealer); //displays the information
			if(round > 1) CardCounting.print(); //displays the count or the player
			pot += player.placeBet(sc); //player places bet
			
			deck.start(player, dealer, side); //initial deal
			Display(player, dealer);
			
			player.go(deck,sc,dealer,side); //player goes until they stand or bust
			dealer.go(deck,side); //dealer goes until they get 17 or higher
			
			Results(player, dealer); // show results
			
			//display your total winnings
			System.out.printf("\nYour winnings: $%.2f",player.getMoney());
			
			//return all the cards to the side deck
			System.out.println("\nReturning Cards...\n");
			player.returnHand(side);
			dealer.returnHand(side);
			
			//if the player still as money 
			//show them the count and then ask if they would like to contiune playing
			if(player.getMoney() > 0.0) {
				CardCounting.printCount();
				System.out.println("You still have some money. Play Again? (y or n)");
				String c = sc.nextLine();
				if(c.equals("n")) {
					break;
				}
				round++;
			}
		}
		
		//if the player ends the game with no money then thank them for playing and end.
		if(player.getMoney() <= 0.0) {
			System.out.println("\nThanks for playing but your out of money.");
		}
		sc.close();
		
	}

}
