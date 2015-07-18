package tdd.vendingMachine;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VendingMachineTest
{
    @Test
    public void shouldDisplayInitText()
    {
	//given
	VendingMachine vendingMachine = new VendingMachine();

	//when
	String displayedText = vendingMachine.getDisplayedText();

	//then
	assertThat(displayedText).isEqualTo(Display.INIT_TEXT);
    }

}
