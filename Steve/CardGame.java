public class CardGame {
    
    public static void main (String[] args) {
        // Create a deck
        Deck deck = new Deck();
        // Shuffle the deck
        deck.shuffle();
        // Create a card player
        CardPlayer player = new CardPlayer();
        // Deal 5 cards from the deck to the card player
        for (int i = 0; i < 5; i++)
            player.getCard(deck.deal());
        // Display the player's cards
        player.showCards();
    }
}