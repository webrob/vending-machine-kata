package tdd.vendingMachine;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Robert on 2015-07-18.
 */
public class Display
{
    public static String INIT_TEXT = "SELECT SLOT";
    @Getter @Setter private String text;

    public Display()
    {
	showInitText();
    }

    private void showInitText()
    {
	text = INIT_TEXT;
    }
}
