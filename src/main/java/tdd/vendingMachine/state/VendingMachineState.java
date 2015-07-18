package tdd.vendingMachine.state;

import tdd.vendingMachine.Coin;
import tdd.vendingMachine.Display;
import tdd.vendingMachine.VendingMachine;

/**
 * Created by Robert on 2015-07-16.
 */
public abstract class VendingMachineState
{
    protected final VendingMachine vendingMachine;
    protected final Display display;

    public VendingMachineState(VendingMachine vendingMachine)
    {
	this.vendingMachine = vendingMachine;
	display = vendingMachine.getDisplay();
    }

    public abstract void chooseSlot(int slotNumber);

    public abstract void insertCoin(Coin coin);

}
