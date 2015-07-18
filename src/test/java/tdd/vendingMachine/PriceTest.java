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

    @Test
    public void shouldAddTwoPrices()
    {
	//given
	Price price1 = new Price(200);
	Price price2 = new Price(100);

	//when
	Price sum = price1.add(price2);

	//then
	assertThat(sum).isEqualTo(new Price(300));
    }

    @Test
    public void shouldSubtractTwoPrices()
    {
	//given
	Price price1 = new Price(200);
	Price price2 = new Price(100);

	//when
	Price sum = price1.subtract(price2);

	//then
	assertThat(sum).isEqualTo(new Price(100));
    }
}
