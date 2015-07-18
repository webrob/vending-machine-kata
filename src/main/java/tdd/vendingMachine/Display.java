package tdd.vendingMachine;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Robert on 2015-07-18.
 */
public class Display
{
    public static final String INIT_TEXT = "SELECT SLOT";
    public static final String CHANGE_NOT_AVAILABLE_TEXT = "CHANGE NOT AVAILABLE";
    @Getter @Setter private String text;

    public Display()
    {
	showInitText();
    }

    public void showInitText()
    {
	text = INIT_TEXT;
    }

    public void setChangeNotAvailableText()
    {
	text = CHANGE_NOT_AVAILABLE_TEXT;
    }
}
