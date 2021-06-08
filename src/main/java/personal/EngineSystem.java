package personal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class EngineSystem implements Engine{
    private final Map<Card,User> accounts;
    public EngineSystem() {
        accounts = new HashMap<>();
    }

    @Override
    public void createCard() {
        Card card = new Card();
        String cardNumber = card.getBASE()+getRandomCardNumber();
        //cardNumber = cardNumber.replace("\\s", "");
        String PIN = getRandomPIN();
        card.setCardNumber(cardNumber);
        card.setCardPIN(PIN);
        accounts.putIfAbsent(card,new User());
        System.out.println("Your card has been created" +
                "\nYour card number:" +
                "\n"+cardNumber+
                "\nYour card PIN:" +
                "\n"+PIN);
    }

    @Override
    public User login(String numberInput, String PINInput) {
        for(Map.Entry<Card,User> acc : accounts.entrySet()){
            boolean cardNumber = acc.getKey().getCardNumber().equals(numberInput);
            boolean PIN = acc.getKey().getCardPIN().equals(PINInput);
            if(cardNumber&&PIN){
                return acc.getValue();
            }
        }
        return null;
    }

    @Override
    public void getInfo(User user) {
        System.out.println("Balance: "+user.getBalance());
    }


    private String getRandomPIN(){
        int PIN = ThreadLocalRandom.current().nextInt(0,9999);
        return String.format("%04d", PIN);
    }
    private String getRandomCardNumber(){
        long number =
                ThreadLocalRandom.current().nextLong(0,(100L *100*100*100*100));
        return String.format("%10d", number);
    }

    public Map<Card, User> getAccounts() {
        return accounts;
    }
}
