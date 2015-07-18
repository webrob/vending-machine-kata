package tdd.vendingMachine;

/**
 * Created by Robert on 2015-07-15.
 */
public class Product
{
    private final ProductType productType;

    public Product(ProductType productType)
    {
	this.productType = productType;
    }

    public ProductType getProductType()
    {
	return productType;
    }
}
