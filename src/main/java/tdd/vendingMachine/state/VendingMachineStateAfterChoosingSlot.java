package tdd.vendingMachine.state;

import tdd.vendingMachine.*;

/**
 * Created by Robert on 2015-07-16.
 */
public class VendingMachineStateAfterChoosingSlot extends VendingMachineState
{
    private int slotNumber;
    private Price price;

    public VendingMachineStateAfterChoosingSlot(VendingMachine vendingMachine, int slotNumber)
    {
	super(vendingMachine);
	chooseSlot(slotNumber);
    }

    @Override
    public void chooseSlot(int slotNumber)
    {
	this.slotNumber = slotNumber;
	ProductType productType = getProductTypeFromSlot(slotNumber);
	price = getPriceForProductType(productType);
	display.setText(price.toString());
    }

    private Price getPriceForProductType(ProductType productType)
    {
	PriceList priceList = vendingMachine.getPriceList();
	return priceList.getPriceForProductType(productType);
    }

    private ProductType getProductTypeFromSlot(int slotNumber)
    {
	Storage storage = vendingMachine.getStorage();
	return storage.getProductTypeFromSlot(slotNumber);
    }

    @Override
    public void insertCoin(Coin coin)
    {
	vendingMachine.setMachineState(
			new VendingMachineStateAfterInsertingCoin(vendingMachine, coin, slotNumber, price));
    }
}
