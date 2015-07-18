package tdd.vendingMachine;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Robert on 2015-07-18.
 */
public class DisplayTest
{
    @Test
    public void shouldDisplayInitTextWhenCreated()
    {
	//given
	Display display = new Display();

	//when
	String text = display.getText();

	//then
	assertThat(text).isEqualTo(Display.INIT_TEXT);
    }
}
