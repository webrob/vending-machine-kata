package tdd.vendingMachine.state;

import tdd.vendingMachine.*;
import tdd.vendingMachine.listener.InsertingCoinsListener;

/**
 * Created by Robert on 2015-07-16.
 */
public class VendingMachineStateAfterInsertingCoin extends VendingMachineState implements InsertingCoinsListener
{
    private final int slotNumber;
    private CoinDispenser coinDispenser;

    public VendingMachineStateAfterInsertingCoin(VendingMachine vendingMachine, Coin coin, int slotNumber, Price price)
    {
	super(vendingMachine);
	this.slotNumber = slotNumber;
	adjustCoinDispenser(vendingMachine, price);
	insertCoin(coin);
    }

    private void adjustCoinDispenser(VendingMachine vendingMachine, Price price)
    {
	coinDispenser = vendingMachine.getCoinDispenser();
	coinDispenser.setPrice(price);
	coinDispenser.setInsertingCoinsListener(this);
    }

    @Override
    public void chooseSlot(int slotNumber)
    {

    }

    @Override
    public void insertCoin(Coin coin)
    {
	coinDispenser.insertCoin(coin);
    }

    @Override
    public void insertMoreCoins(Price price)
    {
	display.setText(price.toString());
    }

    @Override
    public void prepareProduct()
    {
	Product product = getProduct();
	setProduct(product);

	vendingMachine.setMachineState(new VendingMachineStateIdle(vendingMachine));
    }

    private Product getProduct()
    {
	Storage storage = vendingMachine.getStorage();
	return storage.takeProductFromSlot(slotNumber);
    }

    private void setProduct(Product product)
    {
	vendingMachine.getProductDispenser().setProduct(product);
    }

    @Override
    public void changeNotAvailable()
    {
	display.setChangeNotAvailableText();
    }
}
