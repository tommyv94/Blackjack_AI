public class Expectimax {
    
    private Node current;
    
    // build the tree using the intial cards as the basis
    public Expectimax(Card pCard1, Card pCard2, Card dCard1, Card dCard2) {
        Card[] playerCards = {pCard1, pCard2};
        Card[] dealerCards = {dCard1, dCard2};
        current = new Node(playerCards, dealerCards);
    }
    
    public void traverse(String move) {
        current = current.getChild(move);
    }
    
    public String getBestMove() {
        int result = current.getBestMove();
        if (result == 1)
            return "Stand";
        else if (result == 0)
            return "Hit";
        else
            return "Either";
    }
    
    public class Node {
        // int move - 0 represents hit ; 1 represents stand
        // int whoseTurn - 0 represents player ; 1 represents dealer
        private int move, whoseTurn, playerHandValue, dealerHandValue;
        private Node[] children;
        private Card[] playerHand, dealerHand;
        private Card card;
        private boolean playerStand = false;
        private double[] stats = {0,0}; // 1st number is win ratio, 2nd number is tie ratio
        
        public Node(Card[] pHand, Card[] dHand) {
            playerHand = pHand;
            dealerHand = dHand;
            
            children = new Node[14]; // 14 possible moves for the player (13 cards to hit + 1 stand)
            
            String cardString;
            // create the 13 hit nodes
            for (int i = 0; i < 13; i++) {
                if (i <= 8)
                    cardString = "" + (i+2);
                else if (i == 9)
                    cardString = "J";
                else if (i == 10)
                    cardString = "Q";
                else if (i == 11)
                    cardString = "K";
                else
                    cardString = "A";
                
                children[i] = new Node(0, 0, new Card("", cardString), playerHand, dealerHand);
            }
            // create the 1 stand node
            children[13] = new Node(0, 1, null, playerHand, dealerHand);
        }
        
        public Node(int theTurn, int theMove, Card theCard, Card[] pHand, Card[] dHand) {
            whoseTurn = theTurn;
            move = theMove;
            card = theCard;
            
            // if this node represents the player standing
            if (move == 1) {
                // then the hands should remain ths same
                playerHand = pHand;
                dealerHand = dHand;
                
                if (whoseTurn == 0)
                    playerStand = true;
                
            } else {
                // if it is the player's turn, add the new card to the hand
                if (whoseTurn == 0)  {
                    playerHand = new Card[pHand.length+1];
                    for (int i = 0; i < pHand.length; i++) {
                        playerHand[i] = pHand[i];
                    }
                    playerHand[playerHand.length-1] = theCard;
                    
                    dealerHand = dHand;
                    
                    // otherwise it is the dealer's turn, so add the new card to the dealer's deck
                } else {
                    dealerHand = new Card[dHand.length+1];
                    for (int i = 0; i < dHand.length; i++) {
                        dealerHand[i] = dHand[i];
                    }
                    dealerHand[dealerHand.length-1] = theCard;
                    
                    playerHand = pHand;
                }
            }
            
            // calculate the hand values
            playerHandValue = getPlayerHandValue();
            dealerHandValue = getDealerHandValue();
            
            // if it is the player's turn
            if (whoseTurn == 0){
                // if the player is standing, change the turn to the dealer and check his card value
                if (playerStand) {
                    whoseTurn = 1;
                    // check if dealer is 17 or over first before expanding
                    if (dealerHandValue >= 17) {
                        if (dealerHandValue > 21) // dealer bust - player wins
                            stats[0] = 1;
                        else if (dealerHandValue == 21) // dealer gets blackjack - player loses
                            stats[0] = 0;
                        else if (dealerHandValue > playerHandValue) // dealer has higher value - player loses
                            stats[0] = 0;
                        else if (dealerHandValue < playerHandValue) // dealer has lower value - player wins
                            stats[0] = 1; 
                        else
                            stats[1] = 1; // dealer and player have same value - tie
                    } else {
                        // if the dealer is under 17, he can still hit out of 13 cards
                        children = new Node[13];  
                        expand();
                    }
                } else if (playerHandValue < 21) {
                    // if the player is under 21, he can still hit out of 13 cards or stand
                    children = new Node[14];
                    expand();
                } else if (playerHandValue == 21) // the player has BlackJack, so player wins                    
                    stats[0] = 1;
                else // if the player is over 21, player busts and loses 
                    stats[0] = 0;
            } else { // otherwise it is the dealers turn
                if (dealerHandValue >= 17) { // dealer is done - time to compare cards with player
                    if (dealerHandValue > 21) // dealer busts - player wins
                        stats[0] = 1;
                    else if (dealerHandValue == 21) // dealer gets blackjack - player loses
                        stats[0] = 0;
                    else if (dealerHandValue > playerHandValue) // dealer has higher value - player loses
                        stats[0] = 0;
                    else if (dealerHandValue < playerHandValue) // dealer has lower value - player wins
                        stats[0] = 1;
                    else
                        stats[1] = 1; // dealer and player have same value - tie
                } else { // if the dealer has under 17, dealer should keep hitting
                    children = new Node[13];
                    expand();                        
                }                     
            }            
        }
        
        public Node getChild(String move) {
            for (Node n : children) {
                if (n.doesCardMatch(move))
                    return n;
            }
            return null;
        }
        
        public boolean doesCardMatch(String name) {
            if (name.equals("stand")) {
                return move == 1;
            } else {              
                return card.getVal().equals(name);
            }
        }
        
        public int getBestMove() {
            double hitWinRatioSum = 0, hitTieRatioSum = 0, standWinRatio = 0, standTieRatio = 0;            
            
            double[] childStats;
            // get the stats of the children and average the ratios
            for (Node n : children) {
                childStats = n.getStats();
                if (n.getMove() == 0) {// hit
                    hitWinRatioSum += childStats[0];
                    hitTieRatioSum += childStats[1];
                } else {//stand
                    standWinRatio = childStats[0];
                    standTieRatio = childStats[1];
                }              
            }
            
            double hitWinRatio = hitWinRatioSum/(children.length-1);
            double hitTieRatio = hitTieRatioSum/(children.length-1);
            
            // compare the winning ratios for hit vs stand
            if (hitWinRatio > standWinRatio)
                return 0;
            else if (hitWinRatio < standWinRatio)
                return 1;
            else { // if they are the same, then compare the tie ratios for hit vs stand                
                if (hitTieRatio > standTieRatio)
                    return 0;
                else if (hitTieRatio < standTieRatio)
                    return 1;
                else
                    return -1; // if the tie ratios are the same, then it is up to the user to decide
            }
        }
        
        private void updateStats() {
            // go through all children and get win/loss stats for hit/stand
            double[] childStats;
            double winSum = 0, tieSum = 0;
            for (Node n : children) {
                childStats = n.getStats();
                winSum += childStats[0];
                tieSum += childStats[1];
            }
            // calculate the average of the ratios
            stats[0] = winSum / children.length;
            stats[1] = tieSum / children.length;
        }
        
        private void expand() {
            String cardString;
            for (int i = 0; i < 13; i++) {
                if (i <= 8)
                    cardString = "" + (i+2);
                else if (i == 9)
                    cardString = "J";
                else if (i == 10)
                    cardString = "Q";
                else if (i == 11)
                    cardString = "K";
                else
                    cardString = "A";
                
                children[i] = new Node(whoseTurn, 0, new Card("", cardString), playerHand, dealerHand);
            }
            // if there is an extra option to stand for the player, then create it
            if (children.length == 14)
                children[13] = new Node(whoseTurn, 1, null, playerHand, dealerHand);
            
            // after the children get created, update their parent's (this current node) stats
            updateStats();
        }
        
        private int getPlayerHandValue() {
            int sum = 0;
            boolean hasAce = false;
            int cardValue = 0;
            for (int i = 0; i < playerHand.length; i++) {
                cardValue = playerHand[i].getValue();
                if (cardValue == 11)
                    hasAce = true;
                sum += cardValue;
            }
            if (sum > 21 && hasAce)
                sum -= 10;
            return sum;
        }
        
        private int getDealerHandValue() {
            int sum = 0;           
            for (int i = 0; i < dealerHand.length; i++) {
                sum += dealerHand[i].getValue();
            }
            return sum;
        }
        
        public int getMove() {
            return move;
        }
        
        public double[] getStats() {
            return stats;
        }
    }
}