public class Card {
    
    private final int suit, value;
    
    // arrays that contain the strings of possible suits and values
    private final String[] suits  = {"Hearts", "Diamonds", "Spades", "Clubs"}, 
                           values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    
    // constructor requires the card suit and value
    // cardSuit can be from 1 to 4
    // cardValue can be from 2 to 14
    public Card (int cardSuit, int cardValue) {
        // make sure it is a valid card
        assert isValidCard(cardSuit, cardValue);
        suit = cardSuit;
        value = cardValue;
    }
    
    private boolean isValidCard(int cardSuit, int cardValue) {
        // ensure cardSuit is between 1 and 4 (inclusive)
        if (cardSuit < 1 || cardSuit > 4)
            return false;
        // ensure cardValue is between 2 and 14 (inclusive)
        if (cardValue < 2 || cardValue > 14)
            return false;
        // if it passed the tests, then the card is valid
        return true;
    }
    
    public int getSuit() {
        return suit;
    }
    
    public int getValue() {
        return value;
    }
    
    public String getSuitAsString() {
        return suits[suit-1];
    }
    
    public String getValueAsString() {
        return values[value-2];
    }
}