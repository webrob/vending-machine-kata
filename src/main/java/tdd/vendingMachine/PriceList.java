package tdd.vendingMachine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Robert on 2015-07-15.
 */
public class PriceList
{
    private Map<ProductType, Price> priceList = new HashMap<>();

    public void addPriceForProductType(ProductType productType, Price price)
    {
	priceList.put(productType, price);
    }

    public Price getPriceForProductType(ProductType productType)
    {
	return priceList.get(productType);
    }
}
