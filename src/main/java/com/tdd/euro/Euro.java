package com.tdd.euro;

import java.math.BigDecimal;
import java.util.Objects;

public class Euro  {

    private final BigDecimal amount;

    Euro(double amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    private Euro(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Euro %.2f", amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Euro euro = (Euro) o;
        return ((Euro) o).amount.compareTo(euro.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    public Euro subtract(Euro subtracted) {
        return new Euro(this.amount.subtract(subtracted.amount));
    }
}
