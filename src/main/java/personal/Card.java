package personal;

public class Card {
    public static final String BASE= "400000";
    private String cardNumber ;
    private String cardPIN;

    public Card(String cardNumber, String cardPIN) {
        this.cardNumber = cardNumber;
        this.cardPIN = cardPIN;
    }

    public Card() {
    }

    @Override
    public String toString() {
        return BASE+ cardNumber +cardPIN ;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardPIN() {
        return cardPIN;
    }

    public void setCardPIN(String cardPIN) {
        this.cardPIN = cardPIN;
    }
}
