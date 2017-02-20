package domain;

import java.math.BigDecimal;

/**
 * Created by romm on 06.02.17.
 */
public class PaymentDTO {

    private Integer userFrom;
    private Integer[] usersTo;
    private BigDecimal amount;

    public PaymentDTO() {
    }

    public PaymentDTO(Integer userFrom, Integer[] usersTo, BigDecimal amount) {
        this.userFrom = userFrom;
        this.usersTo = usersTo;
        this.amount = amount;
    }

    public Integer getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(Integer userFrom) {
        this.userFrom = userFrom;
    }

    public Integer[] getUsersTo() {
        return usersTo;
    }

    public void setUsersTo(Integer[] usersTo) {
        this.usersTo = usersTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
