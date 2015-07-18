package tdd.vendingMachine;

import lombok.Getter;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Robert on 2015-07-15.
 */
public class Price implements Comparable<Price>
{
    @Getter private int priceInCents;
    private String textPrice;

    public Price()
    {
	this(0);
    }

    public Price(int priceInCents)
    {
	this.priceInCents = priceInCents;
	convertPriceToTextWithCurrency();
    }

    private void convertPriceToTextWithCurrency()
    {
	Locale locale = Locale.forLanguageTag("pl-PL");
	NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

	BigDecimal bigDecimal = new BigDecimal(priceInCents);
	BigDecimal bigDecimalWithCorrectScale = bigDecimal.setScale(2, BigDecimal.ROUND_UNNECESSARY);
	BigDecimal divide = bigDecimalWithCorrectScale.divide(new BigDecimal(100.0), BigDecimal.ROUND_UNNECESSARY);
	textPrice = numberFormat.format(divide);
    }

    public Price add(Price price)
    {
	int amount = priceInCents + price.getPriceInCents();
	return new Price(amount);
    }

    public Price subtract(Price price)
    {
	int amount = priceInCents - price.getPriceInCents();
	return new Price(amount);
    }

    @Override
    public int compareTo(Price price)
    {
	return priceInCents - price.getPriceInCents();
    }

    @Override
    public boolean equals(Object o)
    {
	if (this == o)
	{
	    return true;
	}
	if (o == null || getClass() != o.getClass())
	{
	    return false;
	}
	Price price = (Price) o;
	return priceInCents == price.getPriceInCents();
    }

    @Override
    public String toString()
    {
	return textPrice;
    }
}
