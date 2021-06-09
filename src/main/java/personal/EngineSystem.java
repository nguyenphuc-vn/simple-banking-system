package personal;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class EngineSystem implements Engine {
    private final Map<Card, User> accounts;

    public EngineSystem() {
        accounts = new HashMap<>();
    }

    @Override
    public void createCard() {
        Card card = new Card();
        String cardNumber = Card.BASE + getRandomCardNumber();
        String PIN = getRandomPIN();
        card.setCardNumber(cardNumber);
        card.setCardPIN(PIN);
        accounts.putIfAbsent(card, new User());
        System.out.println("Your card has been created" +
                "\nYour card number:" +
                "\n" + cardNumber +
                "\nYour card PIN:" +
                "\n" + PIN);
    }

    @Override
    public User login(String numberInput, String PINInput) {
        for (Map.Entry<Card, User> acc : accounts.entrySet()) {
            boolean cardNumber = acc.getKey().getCardNumber().equals(numberInput);
            boolean PIN = acc.getKey().getCardPIN().equals(PINInput);
            if (cardNumber && PIN) {
                return acc.getValue();
            }
        }
        return null;
    }

    @Override
    public void getInfo(User user) {
        System.out.println("Balance: " + user.getBalance());
    }

    private long getTotal(long rdNum) {
        long sum = 0;
        List<Long> list = new LinkedList<>();
        while (rdNum > 0) {
            list.add(rdNum % 10);
            rdNum = rdNum / 10;
        }
        for (int num = 1; num < list.size(); num++) {
            if (num % 2 != 0) {
                long temp = list.get(num) * 2;
                if (temp > 9) {
                    temp = temp - 9;
                }
                list.set(num, temp);
            }
            sum += list.get(num);
        }
        return sum + list.get(0)+8;
    }

    private boolean checkSum(long rdNum) {
        return rdNum % 10 == 0;
    }

    private String getRandomPIN() {
        int PIN = ThreadLocalRandom.current().nextInt(9999);
        return String.format("%04d", PIN);
    }

    private String getRandomCardNumber() {
        while (true) {
            long number = tenDigits();
            if (checkSum(getTotal(number))) {
                return String.format("%10d", number);
            }
        }
    }
    private long tenDigits(){
        return ThreadLocalRandom.current().nextLong(1_000_000_000L, 10_000_000_000L);
    }
    public Map<Card, User> getAccounts() {
        return accounts;
    }
}
