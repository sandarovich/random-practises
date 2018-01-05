package com.tdd.euro;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EuroTest {

    @Test
    public void shouldRepresentObjectAsString() {
        assertThat(new Euro(2).toString()).isEqualTo("Euro 2.00");
        assertThat(new Euro(7.50).toString()).isEqualTo("Euro 7.50");
    }

    @Test
    public void shouldBeEqual() {
        assertThat(new Euro(2.00)).isEqualTo(new Euro(2));
    }

    @Test
    public void shouldSubstractEuro() {
        assertThat(new Euro(1)).isEqualTo(new Euro(3).subtract(new Euro(2)));
    }

    @Test
    public void shouldBeNumericSafety() {
        assertThat(new Euro(0.61)).isEqualTo(new Euro(1.03).subtract(new Euro(0.42)));
    }

    @Test
    public void shouldBeNumericSafetyWhenAdvancedPrecision() {
        assertThat(new Euro(0.61999)).isEqualTo(new Euro(9.81999).subtract(new Euro(0.2)));
    }
}
