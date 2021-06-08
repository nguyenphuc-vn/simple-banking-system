package personal;



import java.math.BigDecimal;

public class User {
    private Money money;

    public User() {
        this.money = Money.dollars("0");
    }

    public BigDecimal getBalance() {
        return money.getAmount();
    }
}
