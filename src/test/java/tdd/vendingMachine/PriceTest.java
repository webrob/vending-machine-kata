package tdd.vendingMachine;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Robert on 2015-07-18.
 */
public class PriceTest
{
    @Test
    public void shouldDefaultPriceContainZeroValue()
    {
	//given
	Price price = new Price();

	//when
	String priceWithCurrency = price.toString();

	//then
	assertThat(priceWithCurrency).isEqualTo("0 zł");
    }

    @Test
    public void shouldContainPriceWithCurrencyRepresentation()
    {
	//given
	Price price = new Price(250);

	//when
	String priceWithCurrency = price.toString();

	//then
	assertThat(priceWithCurrency).isEqualTo("2,5 zł");
    }
}
