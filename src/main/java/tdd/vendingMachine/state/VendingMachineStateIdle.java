package tdd.vendingMachine.state;

import tdd.vendingMachine.Coin;
import tdd.vendingMachine.CoinDispenser;
import tdd.vendingMachine.VendingMachine;

/**
 * Created by Robert on 2015-07-16.
 */
public class VendingMachineStateIdle extends VendingMachineState
{
    public VendingMachineStateIdle(VendingMachine vendingMachine)
    {
	super(vendingMachine);
	display.showInitText();
    }

    @Override
    public void chooseSlot(int slotNumber)
    {
	vendingMachine.setMachineState(new VendingMachineStateAfterChoosingSlot(vendingMachine, slotNumber));
    }

    @Override
    public void insertCoin(Coin coin)
    {
	CoinDispenser coinDispenser = vendingMachine.getCoinDispenser();
	coinDispenser.insertCoin(coin);
	coinDispenser.returnCoins();
    }
}
