package tdd.vendingMachine.listener;

import tdd.vendingMachine.Price;

/**
 * Created by Robert on 2015-07-18.
 */
public interface InsertingCoinsListener
{
    void insertMoreCoins(Price price);

    void prepareProduct();

    void changeNotAvailable();
}
