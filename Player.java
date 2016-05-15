import java.util.*;

public class Player {
 Vector<Card> hand;
 double minBet;
 int value;
 double money;
 Expectimax tree;
 
 public Player() {
  this.hand = new Vector<Card>();
  this.value = 0;
  this.money = 0;
  this.minBet = 0;
 }
 
 public Player(double money) {
  this.hand = new Vector<Card>();
  this.value = 0;
  this.money = money;
  this.minBet = money*.10;
 }
 
 public void addMoney(double m) {
  this.money += m;
 }
 
 public void addCard(Card c) {
  hand.add(c);
  this.setValue();
 }
 
 public double placeBet(Scanner sc) {
  double bet = 0;
  while(true) {
   System.out.printf("\nPlace your bet (min $%.2f): ",minBet);
   bet = sc.nextDouble();
   if(bet < minBet) {
    System.out.println("\nBet too low try again.");
   }
   else {
    break;
   }
  }
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
 
 public void go(Deck deck, Scanner sc, Dealer dealer, Deck s) throws InterruptedException {
  while(value <= 21) {
   // print out expectimax recommendation
   System.out.println("Expectimax Recommendation: " + tree.getBestMove());
   System.out.println("\nhit or stand?");
   String command = sc.nextLine();
   if(command.equals("stand")) {
    tree.traverse("stand");
    break;
   }
   else if(command.equals("hit")) {
    Card c = deck.Deal(s);
    this.addCard(c);
    tree.traverse(c.getVal());
    CardCounting.cardCount(c,minBet);
    CardCounting.printCount();
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
 
 //sets the hand value. If value is over 21 and an ace
 //is present take 10 away to change ace to 1
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
 
 //return cards to the hand by adding to side deck
 public void returnHand(Deck s) {
  for(int i = 0; i < this.hand.size(); i++) {
   s.deck.add(this.hand.get(i));
  }
  this.hand.clear();
  this.value = 0;
  
 }
 
 public Card getCard(int index) {
  return hand.get(index);
 }
 
 public void setExpectimax(Expectimax theTree) {
     tree = theTree;
 }
 
}
