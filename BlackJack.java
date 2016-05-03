import java.util.*;


public class BlackJack {
	
	static Scanner sc = new Scanner(System.in);
	static double pot = 0.0;
			
	public static void Display(Player player, Player dealer) {
		System.out.println("Player:");
		player.showHand();
		System.out.println("Money: $"+player.getMoney());
		
		dealer.showHand();
	}
	
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
		winDisplay(player, dealer);
	}
	
	private static void winDisplay(Player player, Dealer dealer) {
		System.out.println("\nPlayer:");
		player.showHand();
		System.out.println("Money: "+player.getMoney());
		
		System.out.println("\nDealer:");
		dealer.revealHand();
		System.out.println(dealer.revealValue());
	}

	public static void main(String[] args) throws InterruptedException {
		Deck deck = new Deck();
		
		Dealer dealer = new Dealer();
		Player player = new Player(100);
		while(player.getMoney() > 0.0) {
			System.out.println("\nStarting Round...\n");
			
			deck.start(player, dealer);
			Display(player, dealer);
			pot += player.placeBet(sc);
			
			player.go(deck,sc,dealer);
			dealer.go(deck);
			
			Results(player, dealer);
			
			System.out.println("\nYour winnings: "+player.getMoney());
			System.out.println("Dealer's winnings: "+dealer.getMoney());
			
			System.out.println("\nReturning Cards...\n");
			player.returnHand();
			dealer.returnHand();
			
			if(player.getMoney() > 0.0) {
				System.out.println("You still have some money. Play Again? (y or n)");
				String c = sc.nextLine();
				if(c.equals("n")) {
					break;
				}
			}
		}
		
		if(player.getMoney() <= 0.0) {
			System.out.println("\nThanks for playing but your out of money.");
		}
		sc.close();
		
	}

}
