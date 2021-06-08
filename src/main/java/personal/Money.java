package personal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private final BigDecimal amount;
    public static Money dollars(String amount){
        return new Money(new BigDecimal(amount));
    }
    private Money(BigDecimal amount){
        this.amount = amount.setScale(1, RoundingMode.HALF_EVEN);
    }
    public Money add(Money other){
        return new Money(amount.add(other.amount));
    }
    public Money subtract(Money other){
      // Money money = new Money(amount.subtract(other.amount));
       return new Money(amount.subtract(other.amount));
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
