package tdd.vendingMachine;

import lombok.Getter;

public class VendingMachine {

    @Getter private Display display = new Display();

    public String getDisplayedText()
    {
	return display.getText();
    }
}
