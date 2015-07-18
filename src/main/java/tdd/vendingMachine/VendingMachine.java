package tdd.vendingMachine;

import lombok.Getter;
import lombok.Setter;
import tdd.vendingMachine.state.VendingMachineState;
import tdd.vendingMachine.state.VendingMachineStateIdle;

import java.util.List;

public class VendingMachine {
    @Getter private final PriceList priceList;
    @Getter private final ProductDispenser productDispenser = new ProductDispenser();
    @Getter private final Storage storage;
    @Getter private final Display display = new Display();
    @Getter private final CoinDispenser coinDispenser = new CoinDispenser();
    @Setter private VendingMachineState machineState = new VendingMachineStateIdle(this);

    public VendingMachine(PriceList priceList, Storage storage)
    {
	this.priceList = priceList;
	this.storage = storage;
    }

    public void chooseSlot(int slotNumber)
    {
	machineState.chooseSlot(slotNumber);
    }

    public String getDisplayedText()
    {
	return display.getText();
    }

    public void insertCoin(Coin coin)
    {
	machineState.insertCoin(coin);
    }

    public Product takeProduct()
    {
	return productDispenser.getProduct();
    }

    public List<Coin> takeChange()
    {
	return coinDispenser.takeChange();
    }

    public void cancel()
    {
	coinDispenser.returnCoins();
    }
}
