package tdd.vendingMachine;

import lombok.Getter;

/**
 * Created by Robert on 2015-07-18.
 */
public enum Coin
{
    PLN_5(new Price(500)),
    PLN_2(new Price(200)),
    PLN_1(new Price(100)),
    CENT_50(new Price(50)),
    CENT_20(new Price(20)),
    CENT_10(new Price(10));

    @Getter private Price price;

    Coin(Price price)
    {
	this.price = price;
    }
}
