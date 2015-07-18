package tdd.vendingMachine.util;

import tdd.vendingMachine.Coin;
import tdd.vendingMachine.Price;

import java.util.List;

/**
 * Created by Robert on 2015-07-18.
 */
public class CoinHelper
{
    public static Price calculateValueOfCoins(List<Coin> coins)
    {
	Price price = new Price();
	for (Coin coin : coins)
	{
	    Price coinPrice = coin.getPrice();
	    price = price.add(coinPrice);
	}
	return price;
    }
}
