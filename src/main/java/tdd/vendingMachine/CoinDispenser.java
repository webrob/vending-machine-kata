package tdd.vendingMachine;

import lombok.Setter;
import tdd.vendingMachine.exception.ChangeNotAvailableException;
import tdd.vendingMachine.exception.PriceNotSetRuntimeException;
import tdd.vendingMachine.listener.InsertingCoinsListener;
import tdd.vendingMachine.listener.InsertingCoinsListenerEmptyImpl;
import tdd.vendingMachine.util.ListHelper;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Robert on 2015-07-18.
 */
public class CoinDispenser
{
    private Map<Coin, Integer> allCoins = new EnumMap<>(Coin.class);
    private List<Coin> insertedCoinsDuringOneSession = new ArrayList<>();
    private List<Coin> change = new ArrayList<>();
    private Price stillToPay = new Price();
    private Price changeToPrepare;
    private ListHelper<Coin> coinListHelper = new ListHelper<>();
    @Setter private InsertingCoinsListener insertingCoinsListener = new InsertingCoinsListenerEmptyImpl();

    public CoinDispenser()
    {
	for (Coin coin : Coin.values())
	{
	    allCoins.put(coin, 1);
	}
    }

    public List<Coin> takeChange()
    {
	List<Coin> changeToTake = coinListHelper.copyDeep(change);
	change.clear();
	return changeToTake;
    }

    public void insertCoin(Coin coin)
    {
	checkIfThereIsSthToPay();
	insertedCoinsDuringOneSession.add(coin);
	notifyListenerAboutChanges(coin);
    }

    private void checkIfThereIsSthToPay()
    {
	if (stillToPay.equals(new Price()))
	{
	    throw new PriceNotSetRuntimeException();
	}
    }

    private void notifyListenerAboutChanges(Coin coin)
    {
	if (shouldUserInsertMoreCoins(coin))
	{
	    stillToPay = stillToPay.subtract(coin.getPrice());
	    notifyListenerHowMuchStillToPay();
	}
	else
	{
	    changeToPrepare = coin.getPrice().subtract(stillToPay);
	    tryToAcceptPayment();
	}
    }

    private boolean shouldUserInsertMoreCoins(Coin coin)
    {
	return stillToPay.compareTo(coin.getPrice()) > 0;
    }

    private void notifyListenerHowMuchStillToPay()
    {
	insertingCoinsListener.insertMoreCoins(stillToPay);
    }

    private void tryToAcceptPayment()
    {
	try
	{
	    acceptPayment();
	    insertingCoinsListener.prepareProduct();
	}
	catch (ChangeNotAvailableException changeNotAvailableException)
	{
	    returnCoins();
	    insertingCoinsListener.changeNotAvailable();
	}
    }

    private void acceptPayment() throws ChangeNotAvailableException
    {
	putInsertedCoinsToAllCoins();
	prepareChange();
	clearInsertedCoins();
    }

    private void putInsertedCoinsToAllCoins()
    {
	for (Coin coin : insertedCoinsDuringOneSession)
	{
	    int incrementedAmountOfCoins = getIncrementedAmountOfCoins(coin);
	    allCoins.put(coin, incrementedAmountOfCoins);
	}
    }

    private int getIncrementedAmountOfCoins(Coin coin)
    {
	return allCoins.get(coin) + 1;
    }

    private void prepareChange() throws ChangeNotAvailableException
    {
	while (shouldPrepareMoreChange())
	{
	    if (notFoundAnyCoinsToReturn())
	    {
		stillToPay = new Price();
		throw new ChangeNotAvailableException();
	    }
	}
    }

    private boolean shouldPrepareMoreChange()
    {
	Price zero = new Price();
	return !changeToPrepare.equals(zero);
    }

    private boolean notFoundAnyCoinsToReturn()
    {
	return !isFoundAtLeastOneCoinToReturn();
    }

    private boolean isFoundAtLeastOneCoinToReturn()
    {
	boolean foundAtLeastOneCoinToReturn = false;
	for (Coin coin : Coin.values())
	{
	    if (canReturnThisCoin(coin))
	    {
		int decrementedAmountOfCoins = getDecrementedAmountOfCoins(coin);
		allCoins.put(coin, decrementedAmountOfCoins);
		changeToPrepare = changeToPrepare.subtract(coin.getPrice());
		change.add(coin);
		foundAtLeastOneCoinToReturn = true;
	    }
	}
	return foundAtLeastOneCoinToReturn;
    }

    private boolean canReturnThisCoin(Coin coin)
    {
	return changeToPrepare.compareTo(coin.getPrice()) >= 0 && allCoins.get(coin) > 0;
    }

    private int getDecrementedAmountOfCoins(Coin coin)
    {
	return allCoins.get(coin) - 1;
    }

    private void clearInsertedCoins()
    {
	insertedCoinsDuringOneSession.clear();
    }

    public void returnCoins()
    {
	change = coinListHelper.copyDeep(insertedCoinsDuringOneSession);
	stillToPay = new Price();
	clearInsertedCoins();
    }

    public void setPrice(Price price)
    {
	stillToPay = new Price(price.getPriceInCents());
    }
}
